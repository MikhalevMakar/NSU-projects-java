package ru.nsu.org.mikhalev.server.commands;

import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import ru.nsu.org.mikhalev.exceptions.LoadException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.util.Properties;

@Log4j2
public class LoaderCommands {
    private final Properties properties;

    public LoaderCommands(String linkCommandsProperties) throws IOException{
        properties = new Properties();
        try(InputStream inputStream = getClass()
            .getClassLoader().getResourceAsStream(linkCommandsProperties)) {
            properties.load(inputStream);
        } catch(IOException io) {
            log.warn("Constructor loader commands " + io.getMessage ());
            throw new IOException("classCommands.properties was not found" + io.getMessage ());
        }
    }

    public Command createInstanceClass(@NotNull String nameClass) throws Exception {
        log.info("Find class by nameCommand");

        Class clazz;
        try {
            clazz = Class.forName(properties.getProperty(nameClass.toUpperCase()));
        } catch (NullPointerException nullPointerException) {
            throw new NullPointerException ("This command wasn't found " + nullPointerException.getMessage ());
        }

        Annotation[] annotations = clazz.getAnnotations();

        log.info("Annotation comparison CommandAnnotation");
        for (Annotation annotation : annotations) {
            if (annotation instanceof CommandAnnotation) {
                return (Command) clazz.newInstance();
            }
        }
        log.trace("Class wasn't found");
        throw new LoadException("Command not found or class wasn't annotated");
    }
}
