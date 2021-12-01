package greeter;

import IoCCointainer.Container;
import greeter.writers.ConsoleWriter;
import greeter.readers.StringReader;
import greeter.writers.Writer;

public class Greeter {
    private final Container container = new Container();
    private final Writer writer;
    private final StringReader stringReader;

    public Greeter(Class<? extends StringReader> clazz) {
        this.stringReader = container.resolve(clazz);
        this.writer = new ConsoleWriter();
    }

    public void greet(String... filePath){
        String stringRead = stringReader.readString(filePath);
        writer.write(stringRead);
    }
}
