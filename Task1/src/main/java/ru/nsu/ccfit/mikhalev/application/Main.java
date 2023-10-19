package ru.nsu.ccfit.mikhalev.application;

import org.apache.commons.cli.ParseException;
import ru.nsu.ccfit.mikhalev.exception.DataTransfersException;
import ru.nsu.ccfit.mikhalev.filehandling.MapWord;
import ru.nsu.ccfit.mikhalev.parser.ParseCommandLine;

import java.io.*;
import static ru.nsu.ccfit.mikhalev.parser.ParseCommandLine.*;

public class Main {
    public static void main(String[] args) throws ParseException {
        ParseCommandLine.searchCommandLine(args);

        try (MapWord mapWord = new MapWord(ParseCommandLine.getFileInput(), getFileOutput())) {
            mapWord.readFileFillMap();
            mapWord.sortDescending();
            mapWord.writeFileCSV();
        } catch (IOException e) {
            throw new DataTransfersException(e.getMessage());
        }
    }
}