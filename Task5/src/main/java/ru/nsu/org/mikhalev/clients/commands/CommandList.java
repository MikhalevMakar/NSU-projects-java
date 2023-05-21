package ru.nsu.org.mikhalev.clients.commands;

import lombok.extern.log4j.Log4j2;
import ru.nsu.org.mikhalev.clients.controller.Controller;
import ru.nsu.org.mikhalev.universal_utile_class.Message;

import java.util.List;


@Log4j2
@CommandClient
public class CommandList implements Command {

    @Override
    public void execute(Controller controller, Message<?> message) {
        log.info("Call " + message.getTypeMessage());

        controller.getView().displayList((List<String>)message.getContent());

    }
}
