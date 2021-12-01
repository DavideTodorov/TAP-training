package greeter.writers;


public class ConsoleWriter implements StringWriter {

    @Override
    public void write(String text, String... fileName) {
        System.out.println(text);
    }
}
