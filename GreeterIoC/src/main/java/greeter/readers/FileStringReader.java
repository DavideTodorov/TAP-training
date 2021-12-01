package greeter.readers;

import java.io.*;

public class FileStringReader implements StringReader{

    @Override
    public String readString(String... filePath) {
        String text = "You are using FileStringReader. The file does not exist or is empty.";

        if (filePath.length == 0){
            return text;
        }

        File file = new File(filePath[0]);
        try {
            final BufferedReader bf = new BufferedReader(new FileReader(file));
            text = bf.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return text;
    }
}
