package ru.nsu.org.mikhalev.server.commands;

import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import ru.nsu.org.mikhalev.server.ServerCommunication;
import ru.nsu.org.mikhalev.universal_utile_class.Message;
import ru.nsu.org.mikhalev.universal_utile_class.exceptions.UserNameException.NameContainsException;
import ru.nsu.org.mikhalev.universal_utile_class.exceptions.UserNameException.NameInvalidFormatException;
import ru.nsu.org.mikhalev.universal_utile_class.exceptions.UserNameException.NameMaxLengthException;

import java.io.IOException;


@Log4j2
@CommandServer
public class CommandLogin implements Command {

    private static final int MAX_LENGTH_NAME = 30;

    @Contract(pure = true)
    private boolean isCorrectNameFormat(final @NotNull String nameUser, ServerCommunication server)
                                                                                                    throws NameInvalidFormatException,
                                                                                                           IOException {
        log.info("Check correct name format");

        String regex = "^\\w+$";

        if(nameUser.matches(regex)) return true;

        Message<String> message = new Message<>("error", "Format user name is incorrect: " + nameUser);

        server.getObjectOutputStream().writeObject(message);

        log.warn("Format user name is incorrect: " + nameUser);
        throw new NameInvalidFormatException("Incorrect name " + nameUser);
    }

    private boolean isContains(final @NotNull String nameUser, ServerCommunication server) throws NameContainsException,
                                                                                                  IOException {

        if(!server.getKernelServer().contains(nameUser)) return true;

        server.getObjectOutputStream()
            .writeObject(new Message<>("error", "This user is contains in chat " + nameUser));

        throw new NameContainsException("This user is contains in chat");
    }

    @Contract(pure = true)
    private boolean isCorrectLengthName(final @NotNull String nameUser, ServerCommunication server) throws NameMaxLengthException,
                                                                                                           IOException {
        log.info("Check correct max length user name");

        if(nameUser.length() <  MAX_LENGTH_NAME) return true;

        server.getObjectOutputStream()
                .writeObject(new Message<>("error", "Max length user name is incorrect: " + nameUser));

        log.warn("Max length user name is incorrect: " + nameUser);
        throw new NameMaxLengthException ("Max length user name >  " + MAX_LENGTH_NAME);
    }

    public boolean execute(@NotNull ServerCommunication serverCommunication, @NotNull Message<String> message) {
        try {
            return isCorrectLengthName(message.getContent(), serverCommunication) &&
                   isCorrectNameFormat(message.getContent(), serverCommunication) &&
                   isContains(message.getContent(), serverCommunication);

        } catch (NameInvalidFormatException | NameContainsException | NameMaxLengthException | IOException e) {
            return false;
        }
    }

}
