package org.ru.nsu.mikhalev.task2.CalculatorController;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.logging.*;
import org.apache.commons.cli.ParseException;
import org.ru.nsu.mikhalev.task2.ParseLine.ParseLine;

public class CalculatorController {
    private static final Logger LOGGER = Logger.getLogger(CalculatorController.class.getName());
    private Context context;
    private final ParseLine parseLine;
    private  String line;

    public CalculatorController(String...  args) throws ParseException, FileNotFoundException {
        LOGGER.info("Create context");
        context = new Context(args);
        parseLine = new ParseLine();
    }

    public void launch() throws IOException {
        try(BufferedReader br = new BufferedReader(context.getReader())) {
            while ((line = br.readLine()) != null) {
               if(line.charAt(0) == '#') continue;
               parseLine.parse(line);

            }
        }

    }



}