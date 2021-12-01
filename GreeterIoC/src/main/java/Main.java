import IoCCointainer.Container;
import greeter.Greeter;
import greeter.readers.ConsoleStringReader;
import greeter.readers.FileStringReader;
import greeter.writers.ConsoleWriter;
import greeter.writers.FileWriter;


public class Main {

    public static void main(String[] args) {
        Container container = new Container();

        Greeter greeter = container.resolve(ConsoleStringReader.class, FileWriter.class);


        //You can specify file path if you are using FileStringReader
        greeter.greet("src/main/resources/text.txt", "newText.txt");
    }
}
