package org.example;

import org.apache.commons.cli.*;
import org.example.ParseCommandLine.*;
import org.example.InputProcessing.*;
import org.example.Operations.DEFINE;
public class Main {
    public static void main(String[] args) {
        try {
            ParseCommandLine parseCommandLine = new ParseCommandLine();
            System.out.println(parseCommandLine.findNameFile(args));
            //TerminalCalculator terminalCalculator = new TerminalCalculator();
           System.out.println(DEFINE.getParamValue("name"));

        } catch (ParseException parseException) {
            System.out.println(parseException);
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }
}