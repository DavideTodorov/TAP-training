package greeter.readers;

import java.util.Scanner;

public class ConsoleStringReader implements StringReader{

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public String readString(String... filePath) {
        System.out.println("Please enter string: ");
        return scanner.nextLine();
    }
}
