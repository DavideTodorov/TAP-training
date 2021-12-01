package IoCCointainer;

import greeter.Greeter;
import greeter.readers.ConsoleStringReader;
import greeter.readers.FileStringReader;
import greeter.readers.StringReader;
import greeter.writers.ConsoleWriter;
import greeter.writers.FileWriter;
import greeter.writers.StringWriter;

import java.io.File;
import java.util.HashMap;


public class Container {
    private final HashMap<Class<?>, Object> container = new HashMap<>();

    public Container() {
        container.put(ConsoleStringReader.class, new ConsoleStringReader());
        container.put(FileStringReader.class, new FileStringReader());
        container.put(ConsoleWriter.class, new ConsoleWriter());
        container.put(FileWriter.class, new FileWriter());
        container.put(Greeter.class, new Greeter());
    }

    public <K extends Greeter, R extends StringReader, W extends StringWriter> K resolve(Class<R> readerClazz, Class<W> writerClazz) {

        Greeter greeter = (Greeter) container.get(Greeter.class);
        greeter.setStringReader((StringReader) container.get(readerClazz));
        greeter.setStringWriter((StringWriter) container.get(writerClazz));

        return (K) greeter;
    }
}
