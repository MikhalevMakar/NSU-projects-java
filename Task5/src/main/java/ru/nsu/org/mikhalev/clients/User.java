package ru.nsu.org.mikhalev.clients;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import ru.nsu.org.mikhalev.server.commands.command_implementation.CommandExecution;
import ru.nsu.org.mikhalev.server.Message;

import java.io.*;
import java.net.Socket;
import java.util.UUID;

@Log4j2
public class User  implements Closeable {
    @Getter
    private final String nameUser;

    @Getter
    private final UUID id;

    private Socket socket;

    ObjectInputStream objectInputStream;

    ObjectOutputStream objectOutputStream;

    private static BufferedReader reader;

    private static BufferedReader in;

    private static PrintWriter out;

    private static final String host = "localhost";

    private static final String answerUserAdded  = "true";

    private static final String commandMessage = "message";

    public User(Integer port, String nameUser) throws IOException {

        socket = new Socket(host, port);

        this.id = UUID.randomUUID();

        this.nameUser = nameUser;

        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        out = new PrintWriter (new OutputStreamWriter(socket.getOutputStream()), true);
    }

//    public synchronized void messageReceive(Message message) {
//        try {
//            objectOutputStream.writeObject(message);
//        } catch (IOException e) {
//            log.error(String.format("Failed to send message %s to %s", message, nameUser), e);
//        }
//    }
//
//    public synchronized Message messageSend() {
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

    public String connect(final String login) throws IOException {

        log.info("Call function connect, name user: " + login);


        log.info("Request server: correct nameUser: " + login);
        out.println(login);

        return in.readLine();
    }

    @Override
    public void close() throws IOException{

        objectInputStream.close();
        objectOutputStream.close();
    }
}