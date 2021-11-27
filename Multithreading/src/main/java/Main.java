import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

public class Main {


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        MainframeProductProvider mainframeProductProvider = new MainframeProductProvider();
        CachedProductProvider cachedProductProvider = new CachedProductProvider(mainframeProductProvider);
        List<Product> products = new ArrayList<>();


        Product p1 = cachedProductProvider.get("product1");
        products.add(p1);

        ExecutorService es = Executors.newFixedThreadPool(10);

        new Thread(() -> products.add(cachedProductProvider.get("product1"))).start();
        new Thread(() -> products.add(cachedProductProvider.get("product2"))).start();


        Set<Callable<Product>> tasks = Set.of(
                () -> cachedProductProvider.get("product1"),
                () -> cachedProductProvider.get("product2"),
                () -> cachedProductProvider.get("product3"),
                () -> cachedProductProvider.get("product4"),
                () -> cachedProductProvider.get("product5"),
                () -> cachedProductProvider.get("product5"),
                () -> cachedProductProvider.get("product6"),
                () -> cachedProductProvider.get("product6"),
                () -> cachedProductProvider.get("product4"),
                () -> cachedProductProvider.get("product1"),
                () -> cachedProductProvider.get("product1")
        );

        es.invokeAll(tasks).forEach(x -> {
            try {
                x.get();
                products.add(x.get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        });

        es.shutdown();
    }
}
