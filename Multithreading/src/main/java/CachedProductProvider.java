import java.util.concurrent.ConcurrentHashMap;

public class CachedProductProvider implements ProductProvider {
    private final ConcurrentHashMap<String, Product> cachedProducts;

    private final MainframeProductProvider mainframeProductProvider;

    public CachedProductProvider(MainframeProductProvider mainframeProductProvider) {
        this.mainframeProductProvider = mainframeProductProvider;
        this.cachedProducts = new ConcurrentHashMap<>();
    }


    @Override
    public synchronized Product get(String productId) throws IllegalCallerException{
        Product product = cachedProducts.get(productId);

        if (product != null) {
            System.out.println("Getting Product by " + Thread.currentThread().getName());
            return product;
        }

        product = mainframeProductProvider.get(productId);
        cachedProducts.put(productId, product);
        System.out.println("Getting Product by " + Thread.currentThread().getName());
        return product;
    }
}
