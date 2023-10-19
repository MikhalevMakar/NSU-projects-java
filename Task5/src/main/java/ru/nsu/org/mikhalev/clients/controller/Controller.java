package ru.nsu.org.mikhalev.clients.controller;

import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import ru.nsu.org.mikhalev.clients.User;
import ru.nsu.org.mikhalev.clients.file_management.LinksToConfiguration;
import ru.nsu.org.mikhalev.clients.view.ControllerView;
import ru.nsu.org.mikhalev.universal_utile_class.Message;
import ru.nsu.org.mikhalev.universal_utile_class.exceptions.ExcParseFileJSON;
import ru.nsu.org.mikhalev.universal_utile_class.file_manager.Configuration;
import ru.nsu.org.mikhalev.universal_utile_class.file_manager.ParseConfiguration;

import java.io.IOException;


@Log4j2
public class Controller {

    private static final String answerUserAdded  = "true"; //TODO

    private  User user;

    private LinksToConfiguration linkConfigurationJSON;

    private final ControllerView controllerView;

    public Controller(ControllerView controllerView, @NotNull LinksToConfiguration linkConfigurationJSON) throws ExcParseFileJSON {
        this.controllerView = controllerView;

        this.linkConfigurationJSON = linkConfigurationJSON;

        Configuration configuration = ParseConfiguration.parseConfigurationJSON(Configuration.class,
                                                                                linkConfigurationJSON.getConfigurationJSON());
        assert configuration != null;

        user = new User(configuration.getPort());
        // user.connectToServer(configuration.getPort());
        log.info("Finished create controller");
    }

    @Contract(value = "null -> false", pure = true)
    public void tryLogin(final String login) throws IOException, ClassNotFoundException {
        log.info("Call function tryLogin, login: " + login);

        Message<?> message = user.connect(new Message<>("tryLogin", login));

        log.info("Request server: correct nameUser: " + message);
        controllerView.printErrorMessage(message.getContent().toString());

        if(message.getContent().equals(answerUserAdded))
            controllerView.generateChat(linkConfigurationJSON.getChatFXML());
    }
}
