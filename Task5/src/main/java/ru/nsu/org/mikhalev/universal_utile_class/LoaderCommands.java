package ru.nsu.org.mikhalev.universal_utile_class;

import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import ru.nsu.org.mikhalev.clients.commands.Command;
import ru.nsu.org.mikhalev.clients.commands.CommandClients;
import ru.nsu.org.mikhalev.universal_utile_class.exceptions.LoadException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Properties;

@Log4j2
public class LoaderCommands {

    private final Properties properties;

    public LoaderCommands(String linkCommandsProperties) {
        properties = new Properties();
        try(InputStream inputStream = getClass()
            .getClassLoader().getResourceAsStream(linkCommandsProperties)) {
            properties.load(inputStream);
        } catch(IOException io) {
            log.warn("Constructor loader commands " + io.getMessage ());
            throw new LoadException("classCommands.properties was not found" + io.getMessage());
        }
    }

    public Command createInstanceClass(@NotNull String nameCommand) {
        log.info("Find class by nameCommand");

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
            if (annotation instanceof CommandClients) {
                try {
                    command = (Command) clazz.getConstructor().newInstance();
                } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ex) {
                    log.error("Error: occurred at the time of command creation " + nameCommand);
                    throw new LoadException("Command: " + nameCommand + Arrays.toString(ex.getStackTrace()));
                }
                return command;
            }
        }
        log.trace("Class wasn't found");
        log.warn("Command not found or class wasn't annotated");
        return null;
    }
}
