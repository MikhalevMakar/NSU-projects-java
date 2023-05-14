package ru.nsu.org.mikhalev.clients.commands.command_implementation;

import org.jetbrains.annotations.NotNull;
import ru.nsu.org.mikhalev.clients.commands.LoaderCommands;
import ru.nsu.org.mikhalev.server.Message;

public class CommandExecution {

    private final LoaderCommands loaderCommands;


    public CommandExecution(String link) {
        loaderCommands = new LoaderCommands(link);
    }

    public void run(@NotNull Message nameCommand) {

       // loaderCommands.createInstanceClass(nameCommand.getTypeMessage());
    }
}
