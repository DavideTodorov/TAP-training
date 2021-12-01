import IoCCointainer.Container;
import greeter.Greeter;
import greeter.readers.ConsoleStringReader;
import greeter.readers.FileStringReader;
import greeter.writers.ConsoleWriter;


public class Main {

    public static void main(String[] args) {
        Container container = new Container();

        Greeter greeter = container.resolve(Greeter.class);
        greeter.setStringReader(container.resolve(FileStringReader.class));
        greeter.setStringWriter(container.resolve(ConsoleWriter.class));


        //You can specify file path if you are using FileStringReader
        greeter.greet("src/main/resources/text.txt");
    }
}
