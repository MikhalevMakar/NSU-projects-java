package ru.nsu.ccfit.mikhalev.filehandling;

import org.jetbrains.annotations.NotNull;
import java.io.*;
import java.util.Map;

public class WriteFile {
    private final Writer writer;

    public WriteFile(String fileOut) throws FileNotFoundException {
        writer = new OutputStreamWriter(new FileOutputStream(fileOut));
    }

    public void write(@NotNull Map<StringBuilder, Integer> treeMap, int countFrequency) throws IOException {
        writer.write("Key, Value, PercentCount\n");

        for(Map.Entry<StringBuilder,Integer> pair : treeMap.entrySet()) {
            writer.write(pair.getKey() + ",");
            writer.write(pair.getValue() + ",");
            writer.write(String.format("%.0f", ((double)pair.getValue() / (double)countFrequency)*100) + "\n");
            writer.flush();
        }
    }

    public void closeFile() {
        try {
            writer.close();
        } catch (IOException e) {
            throw new ClassCastException("failed to close the file for writing");
        }
    }
}
