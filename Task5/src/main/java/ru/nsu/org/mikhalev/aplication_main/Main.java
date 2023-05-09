package ru.nsu.org.mikhalev.aplication_main;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.cli.ParseException;
import ru.nsu.org.mikhalev.server.file_management.Configuration;
import ru.nsu.org.mikhalev.server.file_management.ParseConfiguration;
import ru.nsu.org.mikhalev.exceptions.ExcInvalidArg;

@Log4j2
public class Main {

    private static final int CORRECT_NUMBER_ARGS = 1;

    private static final int BEGIN_INDEX = 0;

    private static boolean isCorrectArgs(String... args) { return args.length ==  CORRECT_NUMBER_ARGS; }


    public static void main(String... args) throws ParseException {
        log.info("Start program");
        if(!isCorrectArgs(args)) throw new ExcInvalidArg("usage: <link to JSON FILE>");

        Configuration configuration =  ParseConfiguration.parseConfiguration(Configuration.class,
                                                                             args[BEGIN_INDEX]);

    }
}