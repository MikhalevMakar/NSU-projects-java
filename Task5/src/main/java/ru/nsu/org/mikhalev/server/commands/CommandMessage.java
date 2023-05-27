package ru.nsu.org.mikhalev.server.commands;

import org.jetbrains.annotations.NotNull;
import ru.nsu.org.mikhalev.server.ServerCommunication;
import ru.nsu.org.mikhalev.universal_utile_class.Message;


@CommandServer
public class CommandMessage implements Command {

    @Override
    public boolean execute(@NotNull ServerCommunication serverCommunication, Message<String> message){

        System.out.println(message.getTypeMessage() + message.getContent());

        return true;
    }
}
