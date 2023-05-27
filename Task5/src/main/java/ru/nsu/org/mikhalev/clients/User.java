package ru.nsu.org.mikhalev.clients;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import ru.nsu.org.mikhalev.clients.controller.Controller;
import ru.nsu.org.mikhalev.universal_utile_class.Message;
import ru.nsu.org.mikhalev.universal_utile_class.exceptions.ExcConnection;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.UUID;

@Log4j2
public class User implements Closeable, Serializable {

    @Setter
    @Getter
    private String logIn;

    @Getter
    private final UUID id;

    private Socket socket;

    @Getter
    private ObjectInputStream objectInputStream;

    @Getter
    private ObjectOutputStream objectOutputStream;

    @Getter
    private final Controller controller;

    public User(Controller controller, int port, String host) throws ExcConnection{
        log.info("Create user");
        this.id = UUID.randomUUID();

        this.controller = controller;

        connect(port, host);
    }

    private void connect(int port, String host) throws ExcConnection{
        try {
            socket = new Socket(host, port);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectInputStream = new ObjectInputStream (socket.getInputStream());

        } catch (IOException e) {
            log.warn("Connection error: " + socket.getLocalPort());
            throw new ExcConnection("Error: in method connectToServer " + e.getMessage());
        }
    }

    public void launchListener() {
        Runnable r = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    controller.queryManagement((Message<?>) objectInputStream.readObject());
                } catch (IOException | ClassNotFoundException e) {
                    throw new ExcConnection("Error: " + Arrays.toString(e.getStackTrace()));
                }
            }
        });

        Thread thread = new Thread(r);
        thread.start();
    }

    public void sendToServer(Message<?> message) throws IOException {
        objectOutputStream.writeObject(message);
        objectOutputStream.flush();
    }

    @Override
    public void close() throws IOException {
        objectInputStream.close();
        objectOutputStream.close();
    }
}