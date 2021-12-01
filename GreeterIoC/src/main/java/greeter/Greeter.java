package greeter;

import greeter.readers.StringReader;
import greeter.writers.StringWriter;

public class Greeter {
    private  StringWriter stringWriter;
    private  StringReader stringReader;

    public Greeter(){
    }

    public Greeter(StringReader reader, StringWriter writer) {
        this.stringReader = reader;
        this.stringWriter = writer;
    }

    public void greet(String... filePath){
        String stringRead = stringReader.readString(filePath);
        stringWriter.write(stringRead);
    }

    //Setter DI
    public void setStringReader(StringReader stringReader) {
        this.stringReader = stringReader;
    }

    //Setter DI
    public void setStringWriter(StringWriter stringWriter) {
        this.stringWriter = stringWriter;
    }
}
