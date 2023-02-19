package org.nsu.mikhalev.project;

import org.apache.commons.cli.ParseException;
import java.io.*;
import static org.nsu.mikhalev.project.ParseCommandLine.*;

public class Main {
    public static void main(String[] args) {
        MapWord mapWord = null;
        String line = new String();
        try {
            ParseCommandLine.searchCommandLine(args);
            mapWord = new MapWord(ParseCommandLine.getFileInput(), getFileOutput());

             while(mapWord.readyRead()) {
                 line = mapWord.createWord();
                 if(line != null) {
                     mapWord.addWord(line);
                 }
             }
             mapWord.sortDescending();
             mapWord.writeFileCSV();
        } catch ( ParseException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if(mapWord != null) mapWord.closeFile();
        }
    }
}