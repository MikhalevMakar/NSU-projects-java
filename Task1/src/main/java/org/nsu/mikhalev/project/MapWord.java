package org.nsu.mikhalev.project;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class MapWord {
    private ReadWriteFIle readWriteFIle;
    private final Map<String, Integer>  map;
    private int wordFrequency = 0;

    public MapWord(String fileIn, String fileOut) throws FileNotFoundException {
         readWriteFIle = new ReadWriteFIle(fileIn, fileOut);
         map = new HashMap<>();
    }
    public void addWord(String word) {
        map.compute(word, (key, value) -> (value == null) ? 1: value+1);
        ++wordFrequency;
        System.out.println(map);
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
        map.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed());
    }
    public void writeFileCSV() throws IOException {
        readWriteFIle.write(map, wordFrequency);
    }
}
