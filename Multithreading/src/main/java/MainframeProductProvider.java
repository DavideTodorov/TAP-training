import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.function.Function;

public class MainframeProductProvider implements ProductProvider {

    private Random random = new Random();

    @Override
    public Product get(String productId) throws IllegalCallerException {

        if (random.nextBoolean()) {
            throw new IllegalCallerException();
        }


        return new Product();
    }
}
