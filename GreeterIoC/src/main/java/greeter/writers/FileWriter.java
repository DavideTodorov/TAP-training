package greeter.writers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

public class FileWriter implements StringWriter{

    @Override
    public void write(String text, String... fileName) {
        if (fileName.length == 0){
            System.out.println("No file name entered.");
            return;
        }

        File file = new File(fileName[0]);

        try {
            BufferedWriter bw = new BufferedWriter(new java.io.FileWriter(file));
            bw.write(text);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
