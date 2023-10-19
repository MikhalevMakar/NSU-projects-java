package ru.nsu.org.mikhalev.server;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import ru.nsu.org.mikhalev.clients.User;
import ru.nsu.org.mikhalev.server.file_management.LinksToConfiguration;
import ru.nsu.org.mikhalev.universal_utile_class.exceptions.ExcKernelServer;
import ru.nsu.org.mikhalev.universal_utile_class.Message;
import ru.nsu.org.mikhalev.universal_utile_class.exceptions.ExcParseFileJSON;
import ru.nsu.org.mikhalev.universal_utile_class.file_manager.Configuration;
import ru.nsu.org.mikhalev.universal_utile_class.file_manager.ParseConfiguration;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.net.ServerSocket;
import java.util.Map;

@Log4j2
public class KernelServer {

    @Getter
    private final ServerSocket serverSocket;

    @Getter
    private final Map<String, ServerCommunication> mapUser = new HashMap<>();

    public KernelServer(@NotNull LinksToConfiguration linksResources) throws IOException, ExcParseFileJSON {

        Configuration configuration = ParseConfiguration.parseConfigurationJSON(Configuration.class,
                                                                                linksResources.getConfigurationServer());
        assert configuration != null;

        this.serverSocket = new ServerSocket(configuration.getPort());
    }

    public void removeUser(User user) {
        mapUser.remove(user);
    }

    public synchronized void addNewUser(String nameUser, ServerCommunication serverCommunication) {
         log.info("Add new user, name " + nameUser);
         mapUser.put(nameUser, serverCommunication);
    }

    public synchronized boolean contains(final String nameUser)  {
        log.info("Check contain user name in mapUser: " + nameUser);

        return mapUser.containsKey(nameUser);
    }

    public void broadCastListUsers() throws IOException {
        log.info("Broad cast list users");

        final Message<Map<String, ServerCommunication>> message = new Message<>("list", mapUser);

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

                Thread thread = new Thread(new ServerCommunication(this, clientSocket));
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



