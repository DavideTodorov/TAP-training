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

        Product p1 = cachedProductProvider.get("product1");

        ExecutorService es = Executors.newFixedThreadPool(10);

        new Thread(() -> cachedProductProvider.get("product1")).start();
        new Thread(() -> cachedProductProvider.get("product2")).start();

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

        es.invokeAll(tasks).forEach( x -> {
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
