import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CachedProductProvider implements ProductProvider {
    private final ConcurrentHashMap<String, Product> cachedProducts;

    /*
     I decided to use ReadWriteLock which allows the data to be read from multiple threads, but allow one thread to write data.
     In my opinion in real world situation we are going to retrieve the products from a database and in most cases the get method
     will try to retrieve already existing product. This will result in more reading than writing, so we will not have as many threads
     waiting for the lock to be unlocked.
     */
    private final ReadWriteLock readWriteLock;

    private final MainframeProductProvider mainframeProductProvider;

    public CachedProductProvider(MainframeProductProvider mainframeProductProvider) {
        this.mainframeProductProvider = mainframeProductProvider;
        this.cachedProducts = new ConcurrentHashMap<>();
        this.readWriteLock = new ReentrantReadWriteLock();
    }


    @Override
    public Product get(String productId) {
        Product product;

        readWriteLock.readLock().lock();
        try {
            product = cachedProducts.get(productId);
        } finally {
            readWriteLock.readLock().unlock();
        }

        if (product != null) {
            System.out.println("Getting Product by " + Thread.currentThread().getName());

            return product;
        }


        /*
        This while loop will handle the case of thrown errors from MainframeProductProvider.
        If the MainframeProductProvider throws an error our product will be null, and we are not supposed to return nulls.
        That is why we are executing the same method until we get a valid product.
         */
        while (product == null) {
            readWriteLock.readLock().lock();
            try {
                product = mainframeProductProvider.get(productId);
            } catch (IllegalCallerException e) {
                System.out.println("MainFrame didn't give product. Trying again...");
            } finally {
                readWriteLock.readLock().unlock();
            }
        }


        readWriteLock.writeLock().lock();
        try {
            cachedProducts.put(productId, product);
        } finally {
            readWriteLock.writeLock().unlock();
        }


        System.out.println("Getting Product by " + Thread.currentThread().getName());
        return product;
    }
}
