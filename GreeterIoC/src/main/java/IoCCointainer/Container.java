package IoCCointainer;

import greeter.readers.ConsoleStringReader;
import greeter.readers.FileStringReader;

import java.util.HashMap;

public class Container {
    private final HashMap<Class<?>, Object> container;


    public Container() {
        this.container = new HashMap<>();
        container.put(ConsoleStringReader.class, new ConsoleStringReader());
        container.put(FileStringReader.class, new FileStringReader());
    }

    public <K, V extends K> V resolve(Class<K> clazz){
        return (V) container.get(clazz);
    }
}
