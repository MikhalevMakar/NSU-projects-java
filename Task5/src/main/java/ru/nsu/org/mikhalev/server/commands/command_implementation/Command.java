package ru.nsu.org.mikhalev.server.commands;

import ru.nsu.org.mikhalev.server.model.User;
import ru.nsu.org.mikhalev.server.object_serializable.Message;

public interface Command {
    void execute(User user, Message message);
}
