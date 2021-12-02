import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CachedProductProvider implements ProductProvider {
    private final ConcurrentHashMap<String, Product> cachedProducts;
    private final MainframeProductProvider mainframeProductProvider;

    public CachedProductProvider(MainframeProductProvider mainframeProductProvider) {
        this.mainframeProductProvider = mainframeProductProvider;
        this.cachedProducts = new ConcurrentHashMap<>();
    }


    @Override
    public Product get(String productId) {
        Product product;

        product = cachedProducts.get(productId);

        if (product != null) {
            System.out.println("Getting Product by " + Thread.currentThread().getName());
            return product;
        }


        /*
        With Semaphore(1) only one thread can enter the block of code that the semaphore protects
         */
        Semaphore semaphore = new Semaphore(1);

        try {
            semaphore.acquire();
            /*
            This while loop will handle the case of thrown errors from MainframeProductProvider.
            If the MainframeProductProvider throws an error our product will be null, and we are not supposed to return nulls.
            That is why we are executing the same method until we get a valid product.
            */
            while (product == null) {
                try {
                    product = mainframeProductProvider.get(productId);
                } catch (IllegalCallerException e) {
                    System.out.println("MainFrame didn't give product. Trying again...");
                }
                System.out.println("MainFrame didn't give product. Trying again...");
            }

            cachedProducts.put(productId, product);

            System.out.println("Getting Product by " + Thread.currentThread().getName());
            return product;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }


        return product;
    }
}
