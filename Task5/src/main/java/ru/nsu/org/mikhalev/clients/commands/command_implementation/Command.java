package ru.nsu.org.mikhalev.clients.commands.command_implementation;

import ru.nsu.org.mikhalev.clients.User;
import ru.nsu.org.mikhalev.server.Message;

public interface Command {

    void execute(User user, Message message);

}
