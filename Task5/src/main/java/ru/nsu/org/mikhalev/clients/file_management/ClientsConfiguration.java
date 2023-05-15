package ru.nsu.org.mikhalev.clients.file_management;

import lombok.Getter;

public class ClientsConfiguration {

    @Getter
    private final String commandsClients;

    @Getter
    private final String configurationServer;

    ClientsConfiguration(String commandsClients, String configurationServer) {
        this.commandsClients = commandsClients;
        this.configurationServer = configurationServer;
    }
}
