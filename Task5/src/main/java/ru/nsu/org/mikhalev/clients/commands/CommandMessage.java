package ru.nsu.org.mikhalev.clients.commands;

import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import ru.nsu.org.mikhalev.clients.controller.Controller;
import ru.nsu.org.mikhalev.universal_utile_class.Message;

import java.util.List;

@CommandClient
@Log4j2
public class CommandMessage implements Command {

    @Override
    public void execute(@NotNull Controller controller, @NotNull Message<?> message) {
        log.info("Execute command: " + message.getTypeMessage() + message.getContent());

        controller.getView().getControllerView().updateHistoryMessage((List<Message<String>>) message.getContent());
    }
}