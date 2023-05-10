package ru.nsu.org.mikhalev.server.model;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import ru.nsu.org.mikhalev.server.KernelServer;
import ru.nsu.org.mikhalev.server.commands.command_implementation.CommandExecution;
import ru.nsu.org.mikhalev.server.object_serializable.Message;

import java.io.*;
import java.net.Socket;
import java.util.Objects;
import java.util.UUID;

@Log4j2
public class User  implements Runnable, Closeable, Serializable {

    @Setter
    private boolean isDisconnected;

    @Getter
    private final String nameUser;

    @Getter
    private final Socket socket;

    @Getter
    private final UUID id;

    ObjectInputStream objectInputStream;

    ObjectOutputStream objectOutputStream;

    CommandExecution commandExecution;

    private static final String commandMessage = "message";


    public User(Socket socket, String nameUser) throws IOException {

        this.socket = socket;

        this.id = UUID.randomUUID();

        this.nameUser = nameUser;

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
                                                    commandMessage);

            KernelServer.broadcastMessage(disconnectMessage);
        }

        return message;
    }

    public boolean equals(@NotNull User user) {
        return (Objects.equals(user.getNameUser(), nameUser) && user.getId() == id);
    }

    @Override
    public void run() {
        Message message;
        while(!isDisconnected) {
            message = messageSend();
            commandExecution.run(message);
        }
    }


    @Override
    public void close() throws IOException{
        socket.close();
        objectInputStream.close();
        objectOutputStream.close();
    }
}