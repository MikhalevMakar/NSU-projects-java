package ru.nsu.org.mikhalev.server;

import lombok.extern.log4j.Log4j2;
import ru.nsu.org.mikhalev.server.model.Chat;
import ru.nsu.org.mikhalev.server.model.User;
import ru.nsu.org.mikhalev.server.object_serializable.Message;

import java.util.LinkedList;
import java.util.List;


@Log4j2
public class KernelServer {

    private static final List<User> users = new LinkedList<>();

    //private static final List<Chat> chats = new LinkedList<>();

    public static void removeUser(User user) {
        users.remove(user);
    }

    public static void broadcastMessage(Message message) {
        for(var user : users) {
            user.messageReceive(message);
        }
    }

    public static void sendMessagePerUser(User user, Message message) { // TODO
        log.info("Attempting to send a message to a specific user");
        for(var equelseUser : users) {
            if(equelseUser.equals(user)) {
                log.info("User %s received a message", user.getNameUser());
                user.messageReceive(message);
                return;
            }
        }
        log.warn("No such user was found %s", user.getNameUser());
    }



    public static boolean addNewUser(User user) {
        return users.add(user);
    }

    public static boolean contains(String nameUser) {
        for(var existingUser : users) {
            if(existingUser.getNameUser().equals(nameUser)) {
                return false;
            }
        }
        return true;
    }
}



