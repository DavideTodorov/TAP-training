import java.util.Random;

public class MainframeProductProvider implements ProductProvider {

    private Random random = new Random();

    @Override
    public synchronized Product get(String productId) throws IllegalCallerException {

        if (random.nextBoolean()) {
            //ToDo: Think of a proper exception handling; what to do if the method returns null;
//            throw new IllegalCallerException();
        }

        return new Product();
    }
}
