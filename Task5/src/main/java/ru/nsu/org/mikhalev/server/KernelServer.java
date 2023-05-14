package ru.nsu.org.mikhalev.server;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import ru.nsu.org.mikhalev.clients.User;
import ru.nsu.org.mikhalev.exceptions.ExcKernelServer;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.net.ServerSocket;
import java.util.Map;

@Log4j2
public class KernelServer {

    @Getter
    private final ServerSocket serverSocket;

    private Socket clientSocket  = new Socket();

    @Getter
    private final Map<String, ServerCommunication> mapUser = new HashMap<>();

    public KernelServer(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }

    public void removeUser(User user) {
        mapUser.remove(user);
    }

    public void addNewUser(String nameUser, ServerCommunication serverCommunication) {
        log.info("Add new user, name " + nameUser);
         mapUser.put(nameUser, serverCommunication);
    }

    public  boolean contains(final String nameUser)  {
        log.info("Check contain user name in mapUser");

        return mapUser.containsKey(nameUser);
    }

    public void broadCastListUsers() throws IOException {
        log.info("Broad cast list users");

        final Message<Map<String, ServerCommunication>> message = new Message<>("list", mapUser);

        for (Map.Entry<String, ServerCommunication> entry : mapUser.entrySet()) {
            entry.getValue().requestSendMessage(message);
        }
    }

    public void start() {
        log.info("Start work " + this);

        while(true) {
            try {
                log.info("Connection with user");
                clientSocket = serverSocket.accept();
                log.info("Find new user");

                Thread thread = new Thread(new ServerCommunication(this, clientSocket));
                thread.start();
            } catch(IOException ex) {
                throw new ExcKernelServer("Error int class " + this);
            }
        }
    }

    public String toString() {
        return this.getClass().getSimpleName();
    }
}



