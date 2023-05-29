package ru.nsu.org.mikhalev.server;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import ru.nsu.org.mikhalev.server.file_management.LinksToConfiguration;
import ru.nsu.org.mikhalev.universal_utile_class.create_command.ContextCommand;
import ru.nsu.org.mikhalev.universal_utile_class.exceptions.ExcKernelServer;
import ru.nsu.org.mikhalev.universal_utile_class.Message;
import ru.nsu.org.mikhalev.universal_utile_class.exceptions.ExcParseFileJSON;
import ru.nsu.org.mikhalev.universal_utile_class.file_manager.Configuration;
import ru.nsu.org.mikhalev.universal_utile_class.file_manager.ParseConfiguration;

import java.io.IOException;
import java.net.Socket;
import java.util.*;
import java.net.ServerSocket;

@Log4j2
public class KernelServer {

    @Getter
    private final ServerSocket serverSocket;

    @Getter
    private final Map<String, ServerCommunication> mapUser = new HashMap<>();

    @Getter
    private final List<Message<String>> messages = new LinkedList<>();

    private final LinksToConfiguration linksToConfiguration;

    public KernelServer(@NotNull LinksToConfiguration linksResources) throws IOException, ExcParseFileJSON {
        this.linksToConfiguration = linksResources;

        Configuration configuration = ParseConfiguration.parseConfigurationJSON(Configuration.class,
                                                                                linksResources.getConfigurationServer());
        assert configuration != null;

        this.serverSocket = new ServerSocket(configuration.getPort());
    }

    public void removeUser(String user) {
        mapUser.remove(user);
    }

    public void broadCastListMessages(@NotNull List<Message<String>> messageList) {
        log.info("Broad cast list messages");

        final Message<List<Message<String>>> message = new Message<>(ContextCommand.getMESSAGE(), messageList.stream().toList());

        for (Map.Entry<String, ServerCommunication> entry : mapUser.entrySet()) {
            System.out.println("here");
            entry.getValue().requestSendMessage(message);
        }
    }

    public synchronized void addNewUser(String nameUser, ServerCommunication serverCommunication) {
         log.info("Add new user, name: " + nameUser);

         mapUser.put(nameUser, serverCommunication);
         broadCastListUsers();

         List<Message<String>> messageList = new LinkedList<>();
         messageList.add(new Message<>(ContextCommand.getBROAD_CAST_NEW_USER(), String.format("Add new user: %s", nameUser)));
         broadCastListMessages(messageList); //TODO

         serverCommunication.requestSendMessage(new Message<>(ContextCommand.getMESSAGE(), messages));
    }

    public synchronized boolean contains(final String nameUser)  {
        log.info("Check contain user name in mapUser: " + nameUser);

        return mapUser.containsKey(nameUser);
    }

    public void broadCastListUsers()  {
        log.info ("Broad cast list users");

        final Message<List<String>> message = new Message<>(ContextCommand.getLIST_PARTICIPANTS(),
                                                            mapUser.keySet().stream().toList());

        for (Map.Entry<String, ServerCommunication> entry : mapUser.entrySet()) {
            entry.getValue().requestSendMessage(message);
        }
    }

    public void start() {
        log.info("Start work " + this);

        Socket clientSocket;
        while(!serverSocket.isClosed()) {
            try {
                clientSocket = serverSocket.accept();

                log.info("Joining user: " + clientSocket.toString());

                Thread thread = new Thread(new ServerCommunication(this,
                                                                    clientSocket,
                                                                    linksToConfiguration.getCommandsServer()));
                thread.start();
            } catch(IOException ex) {
                throw new ExcKernelServer("Error: int class " + this);
            }
        }
    }

    public String toString() {
        return this.getClass().getSimpleName();
    }
}