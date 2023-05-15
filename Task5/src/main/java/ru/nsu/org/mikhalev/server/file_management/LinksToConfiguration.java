package ru.nsu.org.mikhalev.server.file_management;

import lombok.Getter;

public class LinksToConfiguration {

    @Getter
    private String commandsServer;

    @Getter
    private String configurationServer;

    public LinksToConfiguration(String commandsServer, String configurationServer) {
        this.commandsServer = commandsServer;
        this.configurationServer = configurationServer;
    }
}
