import IoCCointainer.Container;
import greeter.Greeter;
import greeter.readers.ConsoleStringReader;
import greeter.readers.FileStringReader;
import greeter.writers.ConsoleWriter;
import greeter.writers.FileWriter;


public class Main {

    public static void main(String[] args) {
        Container container = new Container();

        //Setter DI
        Greeter greeter = container.resolve(Greeter.class);
        greeter.setStringReader(container.resolve(FileStringReader.class));
        greeter.setStringWriter(container.resolve(ConsoleWriter.class));

        //Constructor DI
        Greeter greeter2 =
                new Greeter(container.resolve(ConsoleStringReader.class), container.resolve(FileWriter.class));


        //You can specify file path if you are using FileStringReader
        greeter.greet("src/main/resources/text.txt");
        greeter2.greet("newText.txt");
    }
}
