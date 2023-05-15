package ru.nsu.org.mikhalev.clients.file_management;

import lombok.Getter;

public class LinksToConfiguration {

    @Getter
    private String commandsClients;

    @Getter
    private String loginFXML;

    @Getter
    private String chatFXML;

    @Getter
    private String configurationJSON;

    public LinksToConfiguration(String commandsClients, String loginFXML, String chatFXML, String configurationJSON) {
        this.loginFXML = loginFXML;
        this.chatFXML = chatFXML;
        this.commandsClients = commandsClients;
        this.configurationJSON =  configurationJSON;
    }
}
