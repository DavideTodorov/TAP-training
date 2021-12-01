package greeter.writers;


public class ConsoleWriter implements StringWriter {

    @Override
    public void write(String text) {
        System.out.println(text);
    }
}
