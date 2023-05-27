package ru.nsu.org.mikhalev.server;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import ru.nsu.org.mikhalev.clients.User;
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

    private final List<Message<String>> messages = new LinkedList<>();

    private final LinksToConfiguration linksToConfiguration;

    public KernelServer(@NotNull LinksToConfiguration linksResources) throws IOException, ExcParseFileJSON {
        this.linksToConfiguration = linksResources;

        Configuration configuration = ParseConfiguration.parseConfigurationJSON(Configuration.class,
                                                                                linksResources.getConfigurationServer());
        assert configuration != null;

        this.serverSocket = new ServerSocket(configuration.getPort());
    }

    public void removeUser(User user){
        mapUser.remove(user);
    }

    private void broadCastMessagesHistory(ServerCommunication serverCommunication) {

        serverCommunication.requestSendMessage(new Message<>("Message", messages));
    }

    public synchronized void addNewUser(String nameUser, ServerCommunication serverCommunication) {
         log.info("Add new user, name: " + nameUser);

         mapUser.put(nameUser, serverCommunication);
         broadCastListUsers();

         //broadCastMessagesHistory(serverCommunication);
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

    private void broadCastListMessages(List<Message<String>> messagesList) {
        log.info("Broad cast list messages");

        final Message<List<Message<String>>> message = new Message<>(ContextCommand.getCHAT_HISTORY(), messagesList);

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



