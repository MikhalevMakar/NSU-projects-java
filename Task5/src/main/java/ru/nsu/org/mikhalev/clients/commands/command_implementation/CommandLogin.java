package ru.nsu.org.mikhalev.clients.commands.command_implementation;

import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import ru.nsu.org.mikhalev.clients.User;
import ru.nsu.org.mikhalev.server.Message;


@Log4j2
public class CommandLogin implements Command {
    @Override
    public void execute(@NotNull User user, Message message) {
//        log.info("Command login, user: " + user.getNameUser());

//        if(!KernelServer.contains(message.getDate()) && user.getNameUser().length() < MAX_LENGTH_USER_NAME) {
//            log.info("This name is correct and unique!");
//
//
//        } else {
//            log.info(""); // TODO
//        }
//
//        if(!KernelServer.addNewUser(user)) {
//            log.info("Failed to add a user");
//        }
//      KernelServer.broadcastMessage(message);

    }
}
