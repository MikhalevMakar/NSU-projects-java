package org.ru.nsu.mikhalev.task1;

import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class MapWord implements Closeable {
    private final ReadFile readerFile;
    private final WriteFile writerFile;
    private Map<StringBuilder, Integer> newMapSortedByValue;
    private final TreeMap<StringBuilder, Integer> treeMap;
    private int wordFrequency = 0;

    public MapWord(String fileIn, String fileOut) throws FileNotFoundException {
        readerFile = new ReadFile(fileIn);
        writerFile = new WriteFile(fileOut);
        treeMap = new TreeMap<>();
    }
    public void addWord(StringBuilder word) {
        treeMap.compute(word, (key, value)->((value == null) ? 1 : (value + 1)));
        ++wordFrequency;
    }

    public StringBuilder createWord() throws IOException {
        int symbolInt;
        StringBuilder line = new StringBuilder();

        while((symbolInt = readerFile.read()) != -1){
            if (Character.isWhitespace(symbolInt)) {
                break;
            }
            line.append((char)symbolInt);
        }
        return line;
    }

    public void readFileFillMap() throws IOException {
        StringBuilder line;
        while(readerFile.ready()) {
            line = this.createWord();
            if (line != null) {
                this.addWord(line);
            }
        }
    }

    public void sortDescending() {
        newMapSortedByValue = treeMap.entrySet().stream()
                .sorted(Map.Entry.<StringBuilder,Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    public void writeFileCSV() throws IOException {
        writerFile.write(newMapSortedByValue, wordFrequency);
    }

    @Override
    public void close() {
        writerFile.closeFile();
        readerFile.closeFile();
    }
}
