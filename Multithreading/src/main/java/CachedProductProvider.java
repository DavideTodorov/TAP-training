public class CachedProductProvider implements ProductProvider {

    private final MainframeProductProvider mainframeProductProvider;

    public CachedProductProvider(MainframeProductProvider mainframeProductProvider) {
        this.mainframeProductProvider = mainframeProductProvider;
    }


    @Override
    public Product get(String productId) {
        // not a correct implementation
        return mainframeProductProvider.get(productId);
    }
}
