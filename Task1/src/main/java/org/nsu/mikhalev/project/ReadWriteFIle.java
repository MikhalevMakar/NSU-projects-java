package org.nsu.mikhalev.project;


import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ReadWriteFIle {
    private  Reader reader = null;
    private Writer writer = null;

    public ReadWriteFIle(String fileIn, String fileOut) throws FileNotFoundException {
        reader = new InputStreamReader(new FileInputStream(fileIn));
        writer = new OutputStreamWriter(new FileOutputStream(fileOut));
    }
    public void write(Map<String, Integer> treeMap, int countFrequency) throws IOException {
        writer.write("Key, Value, PercentCount\n");

        for(Map.Entry<String,Integer> pair : treeMap.entrySet()) {
            writer.write(pair.getKey() + ",");
            writer.write(pair.getValue() + ",");
            writer.write(String.format("%.0f", ((double)pair.getValue() / (double)countFrequency)*100) + "\n");
            writer.flush();
        }
    }
    public int read() throws IOException {
        return reader.read();
    }
    public boolean ready() throws IOException {
        return reader.ready();
    }

    public void closeFile() {
        if (null != reader) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace(System.err);
            }
        }
    }
}
