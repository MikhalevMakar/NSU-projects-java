package ru.nsu.org.mikhalev.clients.commands;

import ru.nsu.org.mikhalev.clients.User;
import ru.nsu.org.mikhalev.universal_utile_class.Message;

public interface Command {

    void execute(User user, Message message);

}
