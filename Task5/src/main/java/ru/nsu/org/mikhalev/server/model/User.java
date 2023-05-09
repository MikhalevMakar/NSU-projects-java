package ru.nsu.org.mikhalev.server.model;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import ru.nsu.org.mikhalev.server.KernelServer;
import ru.nsu.org.mikhalev.server.commands.CommandExecution;
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
    private final UUID id;

    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;

    CommandExecution commandExecution;

    public User(final Socket socket, String nameUser, String linkCommands) throws IOException {

        this.socket = socket;
        this.id = UUID.randomUUID();
        this.nameUser = nameUser;
        this.commandExecution = new CommandExecution(linkCommands);

        objectInputStream = new ObjectInputStream(this.socket.getInputStream());
        objectOutputStream = new ObjectOutputStream(this.socket.getOutputStream());
    }

    public void disconnect() {
        KernelServer.removeUser(this);
        try {
            socket.close();
        } catch (IOException e) {
            log.error("Error: occurred while closing the socket", e);
        }
        isDisconnected = true;
    }

    public synchronized void messageReceive(Message message) {
        try {
            objectOutputStream.writeObject(message);
        } catch (IOException e) {
            log.error(String.format("Failed to send message %s to %s", message, nameUser), e);
        }
    }

    public synchronized Message messageSend() {
        Message message = null;
        try {
            message = (Message) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            disconnect();

            log.warn(String.format("User disconnected: %s", nameUser), e);

            Message disconnectMessage = new Message(String.format("Server: there was a problem with the %s", objectInputStream),
                                                    nameUser);

            KernelServer.broadcastMessage(disconnectMessage);
        }

        return message;
    }

    @Override
    public void run() {
        Message message;
        while(!isDisconnected) {
            message = messageSend();
            commandExecution.run(message);
        }
    }
}