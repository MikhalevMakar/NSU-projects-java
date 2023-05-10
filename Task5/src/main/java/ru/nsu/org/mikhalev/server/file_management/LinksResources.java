package ru.nsu.org.mikhalev.server.file_management;

import lombok.Getter;

public class LinksResources {
    @Getter
    private final String commandsServer;

    @Getter
    private final String commandsClients;

    @Getter
    private final String configurationServer;

    LinksResources(String commandsServer, String commandsClients, String configurationServer) {
        this.commandsServer = commandsServer;
        this.commandsClients = commandsClients;
        this.configurationServer = configurationServer;
    }
}
