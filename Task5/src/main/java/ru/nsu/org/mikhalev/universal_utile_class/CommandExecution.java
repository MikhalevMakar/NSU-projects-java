package ru.nsu.org.mikhalev.universal_utile_class;

import org.jetbrains.annotations.NotNull;

public class CommandExecution {

    private final LoaderCommands loaderCommands;


    public CommandExecution(String link) {
        loaderCommands = new LoaderCommands(link);
    }

    public void run(@NotNull Message<?> nameCommand) {
        loaderCommands.createInstanceClass(nameCommand.getTypeMessage());
    }
}
