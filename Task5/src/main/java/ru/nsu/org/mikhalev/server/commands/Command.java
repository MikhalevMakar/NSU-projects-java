package ru.nsu.org.mikhalev.server.commands;

import org.jetbrains.annotations.NotNull;
import ru.nsu.org.mikhalev.server.ServerCommunication;
import ru.nsu.org.mikhalev.universal_utile_class.Message;

public interface
Command {

    boolean execute(@NotNull ServerCommunication serverCommunication, Message<String> message);
}
