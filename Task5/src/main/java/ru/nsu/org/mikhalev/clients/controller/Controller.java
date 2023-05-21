package ru.nsu.org.mikhalev.clients.controller;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.*;

import ru.nsu.org.mikhalev.clients.User;
import ru.nsu.org.mikhalev.clients.commands.Command;
import ru.nsu.org.mikhalev.clients.file_management.LinksToConfiguration;
import ru.nsu.org.mikhalev.clients.view.View;
import ru.nsu.org.mikhalev.clients.commands.ExecuteCommand;
import ru.nsu.org.mikhalev.universal_utile_class.*;
import ru.nsu.org.mikhalev.universal_utile_class.create_command.ContextCommand;
import ru.nsu.org.mikhalev.universal_utile_class.exceptions.*;
import ru.nsu.org.mikhalev.universal_utile_class.file_manager.*;

import java.io.IOException;
import java.util.Arrays;


@Log4j2
public class Controller {

    private final ExecuteCommand executeCommand;

    @Getter
    private final User user;

    @Getter
    private final LinksToConfiguration linkConfigurationJSON;

    @Getter
    private final View view;

    public Controller(View view, @NotNull LinksToConfiguration linkConfigurationJSON) throws ExcParseFileJSON, ExcConnection {
        this.view = view;

        this.linkConfigurationJSON = linkConfigurationJSON;

        this.executeCommand = new ExecuteCommand(linkConfigurationJSON.getCommandsClients());

        Configuration configuration = ParseConfiguration.parseConfigurationJSON(Configuration.class,
                                                                                linkConfigurationJSON.getConfigurationJSON());
        assert configuration != null;

        user = new User(this, configuration.getPort(), configuration.getHost());
    }

    public void queryManagement(@NotNull Message<?> message) {
        Command command;
        try {

            command = executeCommand.createInstanceClass(message.getTypeMessage ());
            log.info ("Create command: " + command.getClass());

            command.execute(this, message);

        } catch (IOException  | ClassNotFoundException ex) {
            throw new ExcLoadCommand("Exc load " + Arrays.toString(ex.getStackTrace()));
        }
    }

    @Contract(value = "null -> false", pure = true)
    public void tryLogin(final String login) {
        log.info("Call function tryLogin, login: " + login);
        queryManagement(new Message<>(ContextCommand.getLOG_IN(), login));
    }
}