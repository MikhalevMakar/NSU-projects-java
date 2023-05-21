package ru.nsu.org.mikhalev.clients;

import org.jetbrains.annotations.Contract;
import ru.nsu.org.mikhalev.clients.controller.Controller;
import ru.nsu.org.mikhalev.universal_utile_class.Message;
import ru.nsu.org.mikhalev.universal_utile_class.exceptions.ExcConnection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Arrays;

public class RequestMessage extends Thread implements Serializable {

    private final Controller controller;

    private final ObjectInputStream in;

    public RequestMessage(final Controller controller, final ObjectInputStream in){
        this.controller = controller;
        this.in = in;
    }

    @Contract(pure = true)
    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()) {
            try {
                controller.queryManagement((Message<?>) in.readObject());
            } catch (IOException | ClassNotFoundException e) {
                throw new ExcConnection("Error: " + Arrays.toString(e.getStackTrace()));
            }
        }
    }
}
