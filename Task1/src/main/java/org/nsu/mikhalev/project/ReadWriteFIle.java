package org.nsu.mikhalev.project;



import java.io.*;
import java.util.Map;

public class ReadWriteFIle {
    private  Reader reader = null;
    private Writer writer = null;

    public ReadWriteFIle(String fileIn, String fileOut) throws FileNotFoundException {
        reader = new InputStreamReader(new FileInputStream(fileIn));
        writer = new OutputStreamWriter(new FileOutputStream(fileOut));
    }
    public void write(Map<String, Integer> map, int countFrequency) throws IOException {
        System.out.println(countFrequency);
        writer.write("Key, Value, PercentCount\n");
        for(Map.Entry<String, Integer> pair : map.entrySet()) {
            writer.write(pair.getKey() + ",");
            System.out.print(pair.getKey() + ",");
            writer.write(pair.getValue() + ",");
            System.out.print(pair.getValue() + ",");
            writer.write(String.format("%.0f", ((double)pair.getValue() / (double)countFrequency)*100) + "\n");
            double v= (double)pair.getValue() / (double)countFrequency;
            System.out.println(String.format("%.2f", v) + "\n");
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
