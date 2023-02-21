package org.ru.nsu.mikhalev.task2;

import org.apache.commons.cli.*;
import org.ru.nsu.mikhalev.task2.ParseCommandLine.*;
import org.ru.nsu.mikhalev.task2.InputProcessing.TerminalCalculator;
import org.ru.nsu.mikhalev.task2.Operations.DEFINE;

public class Main {
    public static void main(String[] args) {
          try {
            ParseCommandLine parseCommandLine = new ParseCommandLine();
            parseCommandLine.searchCommandLine(args);
            System.out.println(parseCommandLine.getFileInput());
            TerminalCalculator terminalCalculator = new TerminalCalculator();
            //System.out.println(DEFINE.getParamValue("name"));

        }
          catch (ParseException parseException) {
            System.out.println(parseException);
        } catch (Exception e) {
              throw new RuntimeException(e);
        }
    }
}