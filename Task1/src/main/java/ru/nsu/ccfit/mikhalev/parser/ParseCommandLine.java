package ru.nsu.ccfit.mikhalev.parser;

import java.io.PrintWriter;
import java.util.regex.*;
import org.apache.commons.cli.*;
import org.jetbrains.annotations.NotNull;


public class ParseCommandLine {
    private ParseCommandLine() {
        throw new IllegalStateException("utility class");
    }

    private static final Options posixOptions = new Options();

    private static String[] argInput = {""};
    private static String[] argOutput = {""};

    private static void printHelp() {
        final String commandLineSyntax = "java test.jar";

        final PrintWriter writer = new PrintWriter(System.out);

        final HelpFormatter helpFormatter = new HelpFormatter();

        helpFormatter.printHelp(
                writer,
            80,
                commandLineSyntax,
            "Option:",
            ParseCommandLine.posixOptions,
            0,
            3,
            "-- HELP --",
            true);
        writer.flush();
    }

    private static void createOptions(String @NotNull [] args) throws ParseException {
        Option optComInputFile = new Option("i",
                "inputFile",
                true,
                "-i = input name file");
        Option optComOutFile = new Option("o",
                "outputFile",
                true,
                "-o = output name file");

        optComInputFile.setArgs(1);
        optComOutFile.setArgs(1);

        optComInputFile.setArgName("file");
        optComOutFile.setArgName("file");

        optComInputFile.setOptionalArg(true);


        posixOptions.addOption(optComInputFile);
        posixOptions.addOption(optComOutFile);

        Pattern patternHelp = Pattern.compile("-help");
        Matcher matcherHelp = patternHelp.matcher(args[0]);

        if(matcherHelp.find()) {
            printHelp(
            );
            throw new ParseException("call -help");
        }
    }
    public static void searchCommandLine(String[] args) throws ParseException {
        createOptions(args);
        @Deprecated
        CommandLineParser cmdLinePosixParser = new PosixParser();
        CommandLine commandLine = cmdLinePosixParser.parse(posixOptions, args);

        if(commandLine.hasOption("i") &&
                commandLine.hasOption("o")) {
            argInput = commandLine.getOptionValues("i");
            argOutput = commandLine.getOptionValues("o");
        }  else {
            throw new ParseException("not found command -i | -o");
        }

        Pattern patternTxt = Pattern.compile(".*\\.txt$");
        Pattern patternCSV = Pattern.compile(".*\\.csv$");

        if(!argInput[0].matches(patternTxt.pattern()) ||
                !argOutput[0].matches(patternCSV.pattern()))
            throw new ParseException("Incorrect file name");

        argInput[0] = argInput[0].replaceFirst(".*=+", "");
        argOutput[0] = argOutput[0].replaceFirst(".*=+", "");
    }

    public static String getFileInput() {
        return argInput[0];
    }
    public static String getFileOutput() {
        return argOutput[0];
    }
}
