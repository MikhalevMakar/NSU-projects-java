package org.ru.nsu.mikhalev.task1;

import org.apache.commons.cli.ParseException;

import java.io.*;
import static org.ru.nsu.mikhalev.task1.ParseCommandLine.*;

public class Main {
    public static void main(String[] args) throws ParseException {
        ParseCommandLine.searchCommandLine(args);

        try (MapWord mapWord = new MapWord(ParseCommandLine.getFileInput(), getFileOutput())) {
            mapWord.readFileFillMap();
            mapWord.sortDescending();
            mapWord.writeFileCSV();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}