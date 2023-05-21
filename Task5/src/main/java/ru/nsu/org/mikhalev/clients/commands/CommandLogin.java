package ru.nsu.org.mikhalev.clients.commands;

import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import ru.nsu.org.mikhalev.clients.controller.Controller;
import ru.nsu.org.mikhalev.universal_utile_class.Message;
import ru.nsu.org.mikhalev.universal_utile_class.exceptions.ExcConnection;

import java.io.IOException;
import java.util.Arrays;


@Log4j2
@CommandClient
public class CommandLogin implements Command {

    @Override
    public void execute(@NotNull Controller controller, @NotNull Message<?> message) throws IOException, ClassNotFoundException {

        log.info("Call function tryLogin, login: " + message.getTypeMessage());

        try {
            controller.getUser().getObjectOutputStream().writeObject(message);
            controller.getUser().getObjectOutputStream().flush();
        } catch (IOException ex) {
            log.error("Error in method connect" + ex);
            throw new ExcConnection ("Error in method connect" + Arrays.toString(ex.getStackTrace()));
        }

        message = (Message<?>) controller.getUser().getObjectInputStream().readObject();

        log.info("Request server: correct nameUser: " + message);
        controller.getUser().getController().getView().printErrorMessage(message.getContent().toString());

        if(Boolean.TRUE.equals(message.getContent())) {
            controller.getUser().getController().getView()
                                .generateChat(controller.getUser().getController().getLinkConfigurationJSON().getChatFXML());

            controller.getUser().launchListener();
        }

        log.info("Request server: correct name user: " + message.getContent());
    }
}
