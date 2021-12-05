import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CachedProductProvider implements ProductProvider {
    private final ConcurrentHashMap<String, Product> cachedProducts;
    private final Map<Thread, Lock> locks;
    private final MainframeProductProvider mainframeProductProvider;

    public CachedProductProvider(MainframeProductProvider mainframeProductProvider) {
        this.mainframeProductProvider = mainframeProductProvider;
        this.cachedProducts = new ConcurrentHashMap<>();
        this.locks = new HashMap<>();
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
        I tried to simulate the logic behind Striped
         */
        Thread currentThread = Thread.currentThread();
        Lock currentLock = null;

        if (!locks.containsKey(currentThread)) {
            currentLock = new ReentrantLock(true);
            locks.put(currentThread, currentLock);
        }

        currentLock = locks.get(currentThread);


        currentLock.lock();

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

        currentLock.unlock();

        System.out.println("Getting Product by " + Thread.currentThread().getName());
        return product;
    }
}
