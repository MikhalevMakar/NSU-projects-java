package ru.nsu.org.mikhalev.server.commands.command_implementation;

import ru.nsu.org.mikhalev.server.model.User;
import ru.nsu.org.mikhalev.server.object_serializable.Message;

public interface Command {

    int MAX_LENGTH_USER_NAME = 255;

    void execute(User user, Message message);
}
