package ru.nsu.org.mikhalev.server.commands;

import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import ru.nsu.org.mikhalev.server.ServerCommunication;
import ru.nsu.org.mikhalev.universal_utile_class.Message;

import java.util.LinkedList;
import java.util.List;

@Log4j2
@CommandServer
public class CommandMessage implements Command {

    @Override
    public boolean execute(@NotNull ServerCommunication serverCommunication, @NotNull Message<String> message) {
       log.info("Execute command " + message.getTypeMessage() + " data " + message.getContent() + "user " + serverCommunication.getNameUser());

       message.setLogIn(serverCommunication.getNameUser());

       serverCommunication.getKernelServer().getMessages().add(message);
       List<Message<String>> messageList = new LinkedList<>();
       messageList.add(message);
       serverCommunication.getKernelServer().broadCastListMessages(messageList);

       return true;
    }
}
