package org.ru.nsu.mikhalev.task1;

import java.io.*;

public class ReadFile {
    private Reader reader = null;
    public ReadFile(String fileIn) throws FileNotFoundException {
        reader = new InputStreamReader(new FileInputStream(fileIn));
    }
    public int read() throws IOException {
        return reader.read();
    }
    public boolean ready() throws IOException {
        return reader.ready();
    }

    public void closeFile() {
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
