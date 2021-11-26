public interface ProductProvider {
    Product get(String productId) throws IllegalCallerException;
}
