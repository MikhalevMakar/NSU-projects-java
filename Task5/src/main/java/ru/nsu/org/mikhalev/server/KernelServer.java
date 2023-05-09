package ru.nsu.org.mikhalev.server;

import lombok.extern.log4j.Log4j2;
import ru.nsu.org.mikhalev.server.object_serializable.Message;

import java.util.LinkedList;
import java.util.List;


@Log4j2
public class KernelServer {

    private static final List<User> users = new LinkedList<>();

    private static final List<Chat> chats = new LinkedList<>();


    public static void removeUser(User user) {
        users.remove(user);
    }

    public void broadcastMessage(Message message) {

    }



}


