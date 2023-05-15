package ru.nsu.org.mikhalev.clients;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import ru.nsu.org.mikhalev.universal_utile_class.Message;
import ru.nsu.org.mikhalev.universal_utile_class.exceptions.EcxClose;
import ru.nsu.org.mikhalev.universal_utile_class.exceptions.ExcConnection;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.UUID;

@Log4j2
public class User  implements Closeable {
    @Getter
    private final UUID id;

    private ObjectInputStream objectInputStream;

    private ObjectOutputStream objectOutputStream;

    private static final String HOST = "localhost";

    public User(int port) {
        log.info("Create user");
        this.id = UUID.randomUUID();

        connectToServer(port);
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

    private void connectToServer(int port) {
        try (Socket socket = new Socket(HOST, port)) {
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream (socket.getInputStream());
        } catch (IOException e) {
            log.warn("Connection error: " + e);
            throw new ExcConnection("Connection error:" + e.getMessage());
        }
    }

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
    public void close() {
        try {
            objectInputStream.close();
            objectOutputStream.close();
        } catch (IOException ex) {
            log.error("Error close: " + ex);
            throw new EcxClose("Error close: " + Arrays.toString(ex.getStackTrace()));
        }
    }
}