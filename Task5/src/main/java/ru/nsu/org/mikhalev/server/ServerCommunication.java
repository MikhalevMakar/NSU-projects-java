package ru.nsu.org.mikhalev.server;

import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import ru.nsu.org.mikhalev.exceptions.ExcInvalidArg;
import ru.nsu.org.mikhalev.exceptions.UserNameException.NameContainsException;
import ru.nsu.org.mikhalev.exceptions.UserNameException.NameInvalidFormatException;
import ru.nsu.org.mikhalev.exceptions.UserNameException.NameMaxLengthException;

import java.io.*;
import java.net.Socket;


@Log4j2
public class ServerCommunication implements Runnable, Closeable {

    private static final int MAX_LENGTH_NAME = 30;
    private static BufferedReader in;

    private static PrintWriter out;

    private Socket clientSocket;

    private final KernelServer kernelServer;


    public ServerCommunication(final KernelServer kernelServer, final Socket clientSocket) throws IOException {
        this.kernelServer = kernelServer;
        this.clientSocket = clientSocket;

        this.in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
        this.out = new PrintWriter(this.clientSocket.getOutputStream(), true);
    }


    @Contract(pure = true)
    private boolean isCorrectNameFormat(final @NotNull String nameUser) throws NameInvalidFormatException {
        log.info("Check correct name format");

        String regex = "^\\w+$";

        if(nameUser.matches(regex)) return true;

        out.println("Format user name is incorrect: " + nameUser);
        log.warn("Format user name is incorrect: " + nameUser);
        throw new NameInvalidFormatException("Incorrect name " + nameUser);
    }


    private boolean isContains(final @NotNull String nameUser) throws NameContainsException {

        if(!kernelServer.contains(nameUser)) return true;

        out.println("This user is contains in chat " + nameUser);
        throw new NameContainsException("This user is contains in chat");
    }
    @Contract(pure = true)
    private boolean isCorrectLengthName(final @NotNull String nameUser) throws NameMaxLengthException {
        log.info("Check correct max length user name");

        if(nameUser.length() <  MAX_LENGTH_NAME) return true;

        out.println("Max length user name is incorrect: " + nameUser);
        log.warn("Max length user name is incorrect: " + nameUser);
        throw new NameMaxLengthException ("Max length user name >  " + MAX_LENGTH_NAME);
    }

    @Contract(pure = true)
    private  boolean isCorrectNameUser(final @NotNull String nameUser) {
        try {
            return isCorrectLengthName(nameUser) && isCorrectNameFormat(nameUser) && isContains(nameUser);
        } catch (NameInvalidFormatException | NameContainsException | NameMaxLengthException e) {
            return false;
        }
    }

    private boolean requestAddUser() throws IOException {
        log.info("Call requestAddUser");
        String nameUser;
        boolean statusRun;

        do {
            nameUser = in.readLine();
            log.info("New user: " + nameUser);
            statusRun = isCorrectNameUser(nameUser);
            log.info("Status add new user: " + statusRun);

        } while(!statusRun);
        out.println(true);
        kernelServer.addNewUser(nameUser.toString(), this);
        return true;
    }

    @Override
    public void run() {
        try {
            requestAddUser();

        } catch(IOException ex) {
            log.warn("Error in request user"  + ex);
            return;
        }

        while (!Thread.currentThread().isInterrupted()) {
            try {
                String word = in.readLine();
                System.out.println(word);

                out.write("Привет, это Сервер! Подтверждаю, вы написали : " + word + "\n");
                out.flush();
            } catch (IOException e) {
                throw new RuntimeException(e);

            }
        }
    }

    @Override
    public void close() throws IOException{
        clientSocket.close();
        in.close();
        out.close();
    }
}