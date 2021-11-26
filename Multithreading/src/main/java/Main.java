import java.io.Closeable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main {


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        MainframeProductProvider mainframeProductProvider = new MainframeProductProvider();
        CachedProductProvider cachedProductProvider = new CachedProductProvider(mainframeProductProvider);

        try {
            Product p1 = cachedProductProvider.get("product1");
        } catch (IllegalCallerException e) {
            throw new RuntimeException();
        }

        ExecutorService es = Executors.newFixedThreadPool(10);

        try {

            new Thread(() -> cachedProductProvider.get("product1")).start();
            new Thread(() -> cachedProductProvider.get("product2")).start();
        } catch (IllegalCallerException e) {
            throw new RuntimeException();
        }

        Set<Callable<Product>> tasks = Set.of(
                () -> cachedProductProvider.get("product1"),
                () -> cachedProductProvider.get("product1"),
                () -> cachedProductProvider.get("product1"),
                () -> cachedProductProvider.get("product1"),
                () -> cachedProductProvider.get("product1"),
                () -> cachedProductProvider.get("product1"),
                () -> cachedProductProvider.get("product1"),
                () -> cachedProductProvider.get("product1"),
                () -> cachedProductProvider.get("product1"),
                () -> cachedProductProvider.get("product1"),
                () -> cachedProductProvider.get("product1")
        );

        es.invokeAll(tasks).forEach(x -> {
            try {
                x.get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        });


    }


}
