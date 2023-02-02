package org.example;

import org.apache.commons.cli.*;
import org.example.ParseCommandLine.*;


public class Main {

    public static void main(String[] args) {
        try {
            ParseCommandLine parseCommandLine = new ParseCommandLine();
            System.out.println(parseCommandLine.findNameFile(args));
        } catch (ParseException parseException) {
            System.out.println(parseException);
        }
    }
}