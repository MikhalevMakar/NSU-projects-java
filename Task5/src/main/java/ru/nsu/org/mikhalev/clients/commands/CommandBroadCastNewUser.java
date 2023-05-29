package ru.nsu.org.mikhalev.clients.commands;

import org.jetbrains.annotations.NotNull;
import ru.nsu.org.mikhalev.clients.controller.Controller;
import ru.nsu.org.mikhalev.universal_utile_class.Message;

import java.util.List;

public class CommandBroadCastNewUser implements Command {

        @Override
        public void execute(@NotNull Controller controller, Message<?> message) {
            controller.getView().getControllerView().updateHistoryMessage((List<Message<String>>) message);
        }
}
