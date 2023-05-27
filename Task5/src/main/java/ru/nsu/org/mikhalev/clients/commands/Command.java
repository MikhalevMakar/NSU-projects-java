package ru.nsu.org.mikhalev.clients.commands;

import ru.nsu.org.mikhalev.clients.controller.Controller;
import ru.nsu.org.mikhalev.universal_utile_class.Message;

import java.io.IOException;

public interface Command {

    void execute(Controller controller, Message<?> message) throws IOException, ClassNotFoundException;

}
