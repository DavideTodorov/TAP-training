import greeter.Greeter;
import greeter.readers.FileStringReader;


public class Main {

    public static void main(String[] args) {
        Greeter greeter = new Greeter(FileStringReader.class);

        //You can specify file path if you are using FileStringReader
        greeter.greet("src/main/resources/text.txt");
    }
}
