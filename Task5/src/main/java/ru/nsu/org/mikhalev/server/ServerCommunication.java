package ru.nsu.org.mikhalev.server;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import ru.nsu.org.mikhalev.server.commands.ExecuteCommand;
import ru.nsu.org.mikhalev.server.commands.Command;
import ru.nsu.org.mikhalev.universal_utile_class.create_command.ContextCommand;
import ru.nsu.org.mikhalev.universal_utile_class.Message;
import ru.nsu.org.mikhalev.universal_utile_class.exceptions.ExcIO;
import ru.nsu.org.mikhalev.universal_utile_class.exceptions.ExcLoadCommand;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;

@Log4j2
public class ServerCommunication implements Runnable, Closeable, Serializable {

    @Getter
    private final ObjectInputStream objectInputStream;

    @Getter
    private final ObjectOutputStream objectOutputStream;

    private final Socket clientSocket;

    @Getter
    private final KernelServer kernelServer;

    private final ExecuteCommand executeCommand;

    public ServerCommunication(final KernelServer kernelServer, final Socket clientSocket, String link) throws IOException {
        log.info("Create " + this);

        this.kernelServer = kernelServer;
        this.clientSocket = clientSocket;

        this.objectInputStream = new ObjectInputStream(this.clientSocket.getInputStream());

        this.objectOutputStream = new ObjectOutputStream(this.clientSocket.getOutputStream());

        this.executeCommand = new ExecuteCommand(link);
    }

    public boolean queryManagement(@NotNull Message<?> message) {
        log.info("Get command create: " + message.getTypeMessage());

        Command command = executeCommand.createInstanceClass(message.getTypeMessage());
        assert command != null;

        log.info("Create command: " + command.getClass());
        return command.execute(this, (Message<String>) message);
    }

    private void requestAddUser() throws IOException, ClassNotFoundException {
        log.info("Call requestAddUser");
        Message<?> message;
        boolean statusRun;

        do {
            message = (Message<?>) objectInputStream.readObject();
            log.info("New user: " + message.getContent());

            statusRun = queryManagement(message);

            log.info("Status add new user: " + statusRun);
        } while(!statusRun);

        requestSendMessage(new Message<>(ContextCommand.getLOG_IN(), true));

        kernelServer.addNewUser((String)message.getContent(), this);
    }

    public void requestSendMessage(final Message<?> message) {
        try {
            log.info("Try send message");
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
            log.info("Sended message");
        } catch (IOException ex) {
            throw new ExcIO("Error: io " + Arrays.toString(ex.getStackTrace()));
        }
    }

    @Override
    public void run() {
        try {
            requestAddUser();
        } catch (IOException | ClassNotFoundException ex) {
            log.warn ("Error in request user" + ex);
        }
        Message<?> message;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                message = (Message<?>) objectInputStream.readObject();

                log.info("New command has been received: " + message.getTypeMessage());

                queryManagement(message);

            } catch (IOException | ClassNotFoundException e) {
                throw new ExcLoadCommand("Error: execute command");
            }
        }
    }

    @Override
    public void close() throws IOException {
        log.info("Close resources: " + this.getClass());

        clientSocket.close();
        objectOutputStream.close();
        objectInputStream.close();
    }
}