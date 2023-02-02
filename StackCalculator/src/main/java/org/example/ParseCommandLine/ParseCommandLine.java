package org.example.ParseCommandLine;

import org.apache.commons.cli.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.OutputStream;
import java.io.PrintWriter;

//System.out.println("Input param stack calculator:");

public class ParseCommandLine {
    public static void printHelp(
            final Options options,
            final int printedRowWidth,
            final String header,
            final String footer,
            final int spacesBeforeOption,
            final int spacesBeforeOptionDescription,
            final boolean displayUsage,
            final OutputStream out)
    {
        final String commandLineSyntax = "java test.jar";

        final PrintWriter writer = new PrintWriter(out);

        final HelpFormatter helpFormatter = new HelpFormatter();

        helpFormatter.printHelp(
                writer,
                printedRowWidth,
                commandLineSyntax,
                header,
                options,
                spacesBeforeOption,
                spacesBeforeOptionDescription,
                footer,
                displayUsage);
        writer.flush();
    }
    private String[] searchCommandLine(String[] args) throws ParseException {
        Option optCommands = new Option("n", "nameFile", true, "NameFile");

        optCommands.setArgs(2);
        optCommands.setArgName("nameFile");
        optCommands.setArgName("n");

        optCommands.setOptionalArg(true);

        Options posixOptions = new Options();
        posixOptions.addOption(optCommands);

        printHelp(
                posixOptions,
                80,
                "Options",
                "-- HELP --",
                0,
                3,
                true,
                System.out
        );

        CommandLineParser cmdLinePosixParser = new PosixParser();
        CommandLine commandLine = cmdLinePosixParser.parse(posixOptions, args);

        String[] arguments = new String[]{""};

        if(commandLine.hasOption("nameFile")) {
            arguments = commandLine.getOptionValues("nameFile");
        } else if(commandLine.hasOption("n")) {
            arguments = commandLine.getOptionValues("n");
        } else if(commandLine.hasOption("NameFile")) {
            arguments = commandLine.getOptionValues("NameFile");
        }

        return arguments;
    }
    public String findNameFile(String[] args) throws ParseException {
        String[] arguments = searchCommandLine(args);
        Pattern pattern = Pattern.compile("=.+");
        Matcher matcher = pattern.matcher(arguments[0]);

        String line = "";

        while (matcher.find()) {
            line = (arguments[0].substring(matcher.start() + 1,
                    matcher.end()));
        }
        return line;
    }

}


