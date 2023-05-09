package ru.nsu.org.mikhalev.server;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import ru.nsu.org.mikhalev.server.object_serializable.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.UUID;

@Log4j2
public class User  implements Runnable {
    @Setter
    private boolean isDisconnected;

    private final String nameUser;

    @Getter
    private final Socket socket;

    @Getter
    private final KernelServer server;

    @Getter
    private final UUID id;

    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;


    public User(final Socket socket, final KernelServer server, String nameUser) throws IOException {

        this.socket = socket;
        this.server = server;
        this.id = UUID.randomUUID();
        this.nameUser = nameUser;

        objectInputStream = new ObjectInputStream(this.socket.getInputStream());
        objectOutputStream = new ObjectOutputStream(this.socket.getOutputStream());
    }

    public void disconnect() {
        server.removeUser(this);
        try {
            socket.close();
        } catch (IOException e) {
            log.error("Error: occurred while closing the socket", e);
        }
        isDisconnected = true;
    }

    private Message  messageReceive() {
        Message message = null;
        try {
            message = (Message) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            disconnect();

            log.warn(String.format("User disconnected: %s", nameUser), e);

            Message disconnectMessage = new Message(String.format("There was a problem with the %s", objectInputStream),
                                                    "Server: user disconnected",
                                                    nameUser);

            server.broadcastMessage(disconnectMessage);
        }
        return message;
    }

    private synchronized void messageSend(Message message) {
        try {
            objectOutputStream.writeObject(message);
        } catch (IOException e) {
            log.error(String.format("Failed to send message to %s", message), e);
        }
    }

    @Override
    public void run(){
        Message message;
        while(!isDisconnected) {
            message  = messageReceive();

        }
    }
}
