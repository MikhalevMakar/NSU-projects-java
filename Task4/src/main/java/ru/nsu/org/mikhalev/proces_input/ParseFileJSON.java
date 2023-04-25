package ru.nsu.org.mikhalev.proces_input;


import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.cli.*;
import ru.nsu.org.mikhalev.exceptions.ExcParseFileJSON;

import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Pattern;

@Log4j2
public class ParseFileJSON {
    private static Options posixOptions = new Options();
    private GuiComponents gui_components;
    private InfoFactory info_factory;

    private static final Logger LOGGER = LogManager.getLogger(ParseFileJSON.class);

    private static class GuiComponents {
        private String reference;
    }

    private static class InfoFactory {
        private String reference;
    }

    private String linkGuiComponents, linkInfoFactory;
    private static void printHelp(
                                  final Options options,
                                  final int printedRowWidth,
                                  final String header,
                                  final String footer,
                                  final int spacesBeforeOption,
                                  final int spacesBeforeOptionDescription,
                                  final boolean displayUsage,
                                  final OutputStream out) {

        final String commandLineSyntax = "path to file";

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

    private static void createOptions(String line) throws ExcParseFileJSON {
        Option optComInputFile = new Option("p",
            "pathFile",
            true,
            "-p = path to file JSON");

        optComInputFile.setArgs(1);

        optComInputFile.setArgName("file");

        optComInputFile.setOptionalArg(true);

        posixOptions.addOption(optComInputFile);

        Pattern patternHelp = Pattern.compile("-help");
        Matcher matcherHelp = patternHelp.matcher(line);

        if(matcherHelp.find()) {
            printHelp(
                posixOptions,
                80,
                "Option:",
                "-- HELP --",
                0,
                3,
                true,
                System.out
            );

            throw new ExcParseFileJSON("call -help");
        }
    }

    private static String searchCommandLine(String[] args) throws ParseException {
        CommandLineParser cmdLinePosixParser = new PosixParser();
        CommandLine commandLine = cmdLinePosixParser.parse(posixOptions, args);

        String[] argInput;

        if(commandLine.hasOption("p")) {
            argInput = commandLine.getOptionValues("p");
        } else {
            throw new ParseException("Not found command -p");
        }

        Pattern patternTxt = Pattern.compile(".*\\.json$");

        if(!argInput[0].matches(patternTxt.pattern()))
            throw new ParseException("Incorrect file name");

        argInput[0] = argInput[0].replaceFirst(".*=+", "");
        return  argInput[0];
    }

    public ParseFileJSON(String[] nameFile) throws ParseException, ExcParseFileJSON {
        LOGGER.info ("Entrance ParseFileJSON");

        createOptions(nameFile[0]);

        Gson gson = new Gson();

        try(FileReader reader = new FileReader(searchCommandLine(nameFile))) {
            ParseFileJSON myClass = gson.fromJson(reader, ParseFileJSON.class);

            this.linkGuiComponents = myClass.gui_components.reference;
            this.linkInfoFactory = myClass.info_factory.reference;

        } catch(IOException e) {
            LOGGER.error("File json isn't found", e);
            e.printStackTrace();
            throw new ExcParseFileJSON("file json isn't found");
        }
    }

    public String getLinkGuiComponents() {
        return linkGuiComponents;
    }

    public String getLinkInfoFactory() {
        return linkInfoFactory;
    }
}
