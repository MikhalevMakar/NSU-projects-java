package ru.nsu.org.mikhalev.server;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import ru.nsu.org.mikhalev.clients.User;
import ru.nsu.org.mikhalev.exceptions.UserNameException.NameContainsException;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.net.ServerSocket;
import java.util.Map;

@Log4j2
public class KernelServer {

    @Getter
    private ServerSocket serverSocket;

    private Socket clientSocket = new Socket();

    private final Map<String, ServerCommunication> mapUser = new HashMap<>();

    public KernelServer(int port) throws IOException{
        this.serverSocket = new ServerSocket(port);
    }

    public void removeUser(User user) {
        mapUser.remove(user);
    }

    public void addNewUser(String nameUser, ServerCommunication serverCommunication) {
         mapUser.put(nameUser, serverCommunication);
    }

    public  boolean contains(final String nameUser)  {
        log.info("Check contain user name in mapUser");

        return mapUser.containsKey(nameUser);
    }

    public void start() {
        while(true) {
            try {
                clientSocket = serverSocket.accept();

                Thread thread = new Thread(new ServerCommunication(this, clientSocket));
                thread.start();
            } catch(IOException ex) {
                throw new RuntimeException();
            }
        }
    }
}



