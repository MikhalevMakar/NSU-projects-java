package ru.nsu.org.mikhalev.clients.controller;

import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import ru.nsu.org.mikhalev.clients.User;
import ru.nsu.org.mikhalev.clients.view.ControllerView;
import ru.nsu.org.mikhalev.exceptions.ExcInvalidArg;
import ru.nsu.org.mikhalev.server.KernelServer;
import ru.nsu.org.mikhalev.server.file_management.LinksResources;
import ru.nsu.org.mikhalev.server.file_management.ServerConfiguration;

import java.io.IOException;


@Log4j2
public class Controller {

    private static final String answerUserAdded  = "true";

    private static final int CORRECT_NUMBER_ARGS = 1;

    private static final int BEGIN_INDEX = 0;

    private final User user;

    private final ControllerView controllerView;

    @Contract(pure = true)
    private static boolean isCorrectArgs(String @NotNull ... args) {
        return args.length ==  CORRECT_NUMBER_ARGS;
    }

    public Controller(ControllerView controllerView) throws IOException {

//        if(!isCorrectArgs(args)) throw new ExcInvalidArg ("usage: <link to JSON FILE>");

//        LinksResources linksResources = ParseConfiguration.parseConfigurationJSON(LinksResources.class, args[BEGIN_INDEX]);
//        assert linksResources != null;
//
//        ServerConfiguration configuration =  ParseConfiguration.parseConfigurationJSON(ServerConfiguration.class,
//                                                                                       linksResources.getConfigurationServer());
//        assert configuration != null;

        this.controllerView = controllerView;

        user = new User(8080, "Makar");

    }

    public void tryLogin(final String login) throws IOException {
        log.info("Call function tryLogin, login: " + login);

        String message;

        //do {
            message = user.connect(login);

            log.info("Request server: correct nameUser: " + message);
            controllerView.printErrorMessage(message);

       // } while(message.equals(answerUserAdded));
    }
}
