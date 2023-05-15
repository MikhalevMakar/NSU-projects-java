package ru.nsu.org.mikhalev.server.aplication_main;

import lombok.extern.log4j.Log4j2;
import ru.nsu.org.mikhalev.server.file_management.LinksToConfiguration;
import ru.nsu.org.mikhalev.server.KernelServer;
import ru.nsu.org.mikhalev.universal_utile_class.exceptions.ExcInvalidArg;
import ru.nsu.org.mikhalev.universal_utile_class.exceptions.ExcParseFileJSON;
import ru.nsu.org.mikhalev.universal_utile_class.file_manager.ParseConfiguration;

import java.io.IOException;

@Log4j2
public class Main {

    private static final int BEGIN_INDEX = 0;

    private static final int CORRECT_NUMBER_ARGS = 1;

    public static void main(String... args) throws IOException, ExcParseFileJSON {
        log.info("Start program");

        log.info("Check args" );
        if(!ParseConfiguration.isCorrectArgs(CORRECT_NUMBER_ARGS, args)) {
            log.error("usage: <link to JSON FILE>");
            throw new ExcInvalidArg ("usage: <link to JSON FILE>");
        }

        LinksToConfiguration linksResources = ParseConfiguration.parseConfigurationJSON(
                                                                        LinksToConfiguration.class, args[BEGIN_INDEX]);
        assert linksResources != null;

        KernelServer kernelServer = new KernelServer(linksResources);
        kernelServer.start();
    }
}
