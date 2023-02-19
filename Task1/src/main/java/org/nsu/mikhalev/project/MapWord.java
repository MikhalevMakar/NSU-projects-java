package org.nsu.mikhalev.project;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.LinkedHashMap;

public class MapWord {
    private ReadWriteFIle readWriteFIle;
    private Map<String, Integer> newMapSortedByValue;
    private  TreeMap<String, Integer> treeMap;
    private int wordFrequency = 0;

    public MapWord(String fileIn, String fileOut) throws FileNotFoundException {
         readWriteFIle = new ReadWriteFIle(fileIn, fileOut);
         treeMap = new TreeMap<>();
    }
    public void addWord(String word) {
        treeMap.compute(word, (key, value)->((value == null) ? 1: (value + 1)));
        ++wordFrequency;
    }

    public String createWord() throws IOException {
        int symbolInt;
        StringBuilder line = new StringBuilder();

        while((symbolInt = readWriteFIle.read()) != -1){
            if (Character.isWhitespace(symbolInt)) {
               break;
            }
            line.append((char)symbolInt);
        }
        return line.toString();
    }

    public boolean readyRead() throws IOException {
        return readWriteFIle.ready();
    }
    public void closeFile() {
        readWriteFIle.closeFile();
    }

    public void sortDescending() {
        newMapSortedByValue = treeMap.entrySet().stream()
                .sorted(Map.Entry.<String,Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1,e2) -> e1, LinkedHashMap::new));
        System.out.println(newMapSortedByValue);
    }
    public void writeFileCSV() throws IOException {
        readWriteFIle.write(newMapSortedByValue, wordFrequency);
        System.out.println(newMapSortedByValue);
    }
}
