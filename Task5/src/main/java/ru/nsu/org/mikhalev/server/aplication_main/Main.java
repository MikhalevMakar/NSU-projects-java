package ru.nsu.org.mikhalev.server.aplication_main;

import lombok.extern.log4j.Log4j2;
//import org.apache.commons.cli.ParseException;
//import ru.nsu.org.mikhalev.server.file_management.ParseConfiguration;
import ru.nsu.org.mikhalev.server.KernelServer;

import java.io.IOException;


@Log4j2
public class Main {

    public static void main(String... args) throws IOException {
        log.info("Start program");

        KernelServer kernelServer = new KernelServer(8080);
        kernelServer.start();

    }
}
