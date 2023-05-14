package ru.nsu.org.mikhalev.clients;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import ru.nsu.org.mikhalev.clients.commands.command_implementation.Command;
import ru.nsu.org.mikhalev.server.Message;

import java.io.*;
import java.net.Socket;
import java.util.UUID;

@Log4j2
public class User  implements Closeable {
    @Getter
    private final UUID id;

    ObjectInputStream objectInputStream;

    ObjectOutputStream objectOutputStream;

    private static final String host = "localhost";

    public User(Integer port) throws IOException {
        log.info("Create user");

        Socket socket = new Socket(host, port);

        this.id = UUID.randomUUID();

        log.info("create object deserialization");

        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

        log.info("Create object serialization");
        objectInputStream = new ObjectInputStream(socket.getInputStream());

    }

    public synchronized void messageReceive() {
        try {
            log.info("messageReceive to server");
            Message<?> message = (Message<?>) objectInputStream.readObject();
            System.out.println(message.getTypeMessage());
        } catch (IOException | ClassNotFoundException e) {
            log.error("Failed to send message", e);
        }
    }

//    public synchronized Message messageSend(final Message<?> messag) {
//        Message message = null;
//        try {
//            message = (Message) objectInputStream.readObject();
//        } catch (IOException | ClassNotFoundException e) {
//
//            log.warn(String.format("User disconnected: %s", nameUser), e);
//
//            Message disconnectMessage = new Message(String.format("Server: there was a problem with the %s", objectInputStream),
//                                                    commandMessage);
//
//           // KernelServer.broadcastMessage(disconnectMessage);
//        }
//
//        return message;
//    }

//    public boolean equals(@NotNull User user) {
//        return (Objects.equals(user.getNameUser(), nameUser) && user.getId() == id);
//    }

    public Message<?> connect(final @NotNull Message<String> login) throws IOException, ClassNotFoundException {

        log.info("Request server: correct name user: " + login.getContent());
        objectOutputStream.writeObject(login);
        objectOutputStream.flush();

        return (Message<?>) objectInputStream.readObject();
    }

    public void launchCommunication() {
       // while(socket.isConnected()) {
            messageReceive();
        //}
    }

    @Override
    public void close() throws IOException {

        objectInputStream.close();
        objectOutputStream.close();
    }
}