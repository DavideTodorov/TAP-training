package greeter;

import greeter.writers.ConsoleWriter;
import greeter.readers.StringReader;
import greeter.writers.Writer;

public class Greeter {

    private final Writer writer;
    private final StringReader stringReader;

    public Greeter(StringReader stringReader) {
        this.stringReader = stringReader;
        this.writer = new ConsoleWriter();
    }

    public void greet(String... filePath){
        String stringRead = stringReader.readString(filePath);
        writer.write(stringRead);
    }
}
