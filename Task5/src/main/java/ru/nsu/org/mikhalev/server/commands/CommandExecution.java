package ru.nsu.org.mikhalev.server.commands;

import ru.nsu.org.mikhalev.server.object_serializable.Message;

import java.io.IOException;

public class CommandExecution {
    private final LoaderCommands loaderCommands;
    public CommandExecution(String link) throws IOException {
        loaderCommands = new LoaderCommands(link);
    }
    public void run(Message nameCommand) throws Exception {
        loaderCommands.createInstanceClass(nameCommand.getNameCommands());
    }
}
