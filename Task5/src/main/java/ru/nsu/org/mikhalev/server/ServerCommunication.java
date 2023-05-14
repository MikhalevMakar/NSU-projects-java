package ru.nsu.org.mikhalev.server;

import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import ru.nsu.org.mikhalev.exceptions.ExcKernelServer;
import ru.nsu.org.mikhalev.exceptions.UserNameException.NameContainsException;
import ru.nsu.org.mikhalev.exceptions.UserNameException.NameInvalidFormatException;
import ru.nsu.org.mikhalev.exceptions.UserNameException.NameMaxLengthException;

import java.io.*;
import java.net.Socket;

@Log4j2
public class ServerCommunication implements Runnable, Closeable {

    private static final int MAX_LENGTH_NAME = 30;

    private final ObjectInputStream objectInputStream;

    private final ObjectOutputStream objectOutputStream;

    private final Socket clientSocket;

    private final KernelServer kernelServer;


    public ServerCommunication(final KernelServer kernelServer, final Socket clientSocket) throws IOException {
        log.info("Create " + this);
        this.kernelServer = kernelServer;
        this.clientSocket = clientSocket;

        this.objectInputStream = new ObjectInputStream(this.clientSocket.getInputStream());

        log.info("Create ");
        this.objectOutputStream = new ObjectOutputStream(this.clientSocket.getOutputStream());

    }


    @Contract(pure = true)
    private boolean isCorrectNameFormat(final @NotNull String nameUser) throws NameInvalidFormatException, IOException {
        log.info("Check correct name format");

        String regex = "^\\w+$";

        if(nameUser.matches(regex)) return true;

        Message<String> message = new Message<>("error", "Format user name is incorrect: " + nameUser);

        objectOutputStream.writeObject(message);
        log.warn("Format user name is incorrect: " + nameUser);
        throw new NameInvalidFormatException("Incorrect name " + nameUser);
    }


    private boolean isContains(final @NotNull String nameUser) throws NameContainsException, IOException {

        if(!kernelServer.contains(nameUser)) return false;


        objectOutputStream.writeObject(new Message<>("error", "This user is contains in chat " + nameUser));
        throw new NameContainsException("This user is contains in chat");
    }
    @Contract(pure = true)
    private boolean isCorrectLengthName(final @NotNull String nameUser) throws NameMaxLengthException, IOException {
        log.info("Check correct max length user name");

        if(nameUser.length() <  MAX_LENGTH_NAME) return true;

        objectOutputStream.writeObject(new Message<>("error", "Max length user name is incorrect: " + nameUser));

        log.warn("Max length user name is incorrect: " + nameUser);
        throw new NameMaxLengthException ("Max length user name >  " + MAX_LENGTH_NAME);
    }

    @Contract(pure = true)
    private  boolean isCorrectNameUser(final @NotNull String nameUser) {
        try {
            return isCorrectLengthName(nameUser) && isCorrectNameFormat(nameUser) && !isContains(nameUser);
        } catch (NameInvalidFormatException | NameContainsException | NameMaxLengthException | IOException e) {
            return false;
        }
    }

    private void requestAddUser() throws IOException, ClassNotFoundException {
        log.info("Call requestAddUser");
        Message<?> message;
        boolean statusRun;

        do {
            message = (Message<?>) objectInputStream.readObject();
            log.info("New user: " + message.getTypeMessage());
            statusRun = isCorrectNameUser(message.getTypeMessage());
            log.info("Status add new user: " + statusRun);
        } while(!statusRun);

        objectOutputStream.writeObject(new Message<> ("login", "true"));
        objectOutputStream.flush();
        kernelServer.addNewUser(message.getTypeMessage(), this);
        //kernelServer.broadCastListUsers();
    }

    public void requestSendMessage(final Message<?> message) throws IOException {
        objectOutputStream.writeObject(message);
        objectOutputStream.flush();
    }

    @Override
    public void run() {
        try {
            requestAddUser();
        } catch(IOException | ClassNotFoundException ex) {
            log.warn("Error in request user"  + ex);
            return;
        }

//        Message<?> message;
//        while (!Thread.currentThread().isInterrupted()) {
//            try {
//                message = (Message<?>)objectInputStream.readObject();
//                log.info("Received a message:" + message.getContent());
//            } catch (IOException | ClassNotFoundException ex) {
//                log.warn("Error in class" + this);
//                throw new ExcKernelServer("Error in class" + this);
//            }
//        }
    }


    @Override
    public void close() throws IOException {
        log.info("Close resources: " + this.getClass());

        clientSocket.close();
        objectOutputStream.close();
        objectInputStream.close();
    }
}