package IoCCointainer;

import greeter.Greeter;
import greeter.readers.ConsoleStringReader;
import greeter.readers.FileStringReader;
import greeter.writers.ConsoleWriter;
import greeter.writers.FileWriter;

import java.io.File;
import java.util.HashMap;


public class Container {
    private final HashMap<Class<?>, Object> container = new HashMap<>();

    public Container(){
        container.put(ConsoleStringReader.class, new ConsoleStringReader());
        container.put(FileStringReader.class, new FileStringReader());
        container.put(ConsoleWriter.class, new ConsoleWriter());
        container.put(FileWriter.class, new FileWriter());
        container.put(Greeter.class, new Greeter());
    }

    public <K, V extends K> V resolve(Class<K> clazz){

        return (V) container.get(clazz);
    }
}
