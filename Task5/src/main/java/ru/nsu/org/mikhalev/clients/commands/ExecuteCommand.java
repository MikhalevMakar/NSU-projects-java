package ru.nsu.org.mikhalev.clients.commands;

import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import ru.nsu.org.mikhalev.universal_utile_class.create_command.LoaderCommands;
import ru.nsu.org.mikhalev.universal_utile_class.exceptions.ExcLoadCommand;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

@Log4j2
public class ExecuteCommand extends LoaderCommands {
    public ExecuteCommand(String linkCommandsProperties){
        super(linkCommandsProperties);
    }

    public Command createInstanceClass(@NotNull String nameCommand) {
        log.info("Find class by nameCommand: " + nameCommand);

        Class<?> clazz;
        Command command;
        try {
            clazz = Class.forName(properties.getProperty(nameCommand));
        } catch (NullPointerException | ClassNotFoundException ex) {
            throw new NullPointerException("This command wasn't found " + ex.getMessage());
        }

        Annotation[] annotations = clazz.getAnnotations();

        log.info("Annotation comparison CommandAnnotation");
        for (Annotation annotation : annotations) {
            if (annotation instanceof CommandClient) {
                try {
                    command = (Command) clazz.getConstructor().newInstance();
                } catch (InstantiationException | IllegalAccessException | NoSuchMethodException |
                         InvocationTargetException ex) {
                    log.error("Error: occurred at the time of command creation " + nameCommand);
                    throw new ExcLoadCommand ("Command: " + nameCommand + Arrays.toString(ex.getStackTrace()));
                }
                return command;
            }
        }

        log.trace("Class wasn't found");
        log.warn("Command not found or class wasn't annotated");
        return null;
    }
}