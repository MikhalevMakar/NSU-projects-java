package ru.nsu.org.mikhalev.clients.commands;

import ru.nsu.org.mikhalev.clients.controller.Controller;
import ru.nsu.org.mikhalev.universal_utile_class.Message;
import ru.nsu.org.mikhalev.universal_utile_class.exceptions.ExcIO;

import java.io.IOException;

@CommandClient
public class CommandMessage implements Command {

    @Override
    public void execute(Controller controller, Message<?> message) {
        try {
            controller.getUser().sendToServer(message);
        } catch (IOException ex) {
            throw new ExcIO ("Error send message: type message" + message.getTypeMessage());
        }
    }
}
