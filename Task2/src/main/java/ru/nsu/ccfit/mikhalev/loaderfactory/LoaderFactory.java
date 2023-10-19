package ru.nsu.ccfit.mikhalev.loaderfactory;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import ru.nsu.ccfit.mikhalev.exceptions.LoadException;
import ru.nsu.ccfit.mikhalev.operations.Operation;
import ru.nsu.ccfit.mikhalev.operations.CommandAnnotation;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.util.Properties;

public class LoaderFactory {
    private Properties properties;
    private static final Logger LOGGER = Logger.getLogger (LoaderFactory.class.getName ());

    private  Class cl;
    public LoaderFactory() throws IOException {
        properties = new Properties();
        try(InputStream inputStream = getClass()
                .getClassLoader().getResourceAsStream("classCommands.properties")) {
            properties.load(inputStream);
        } catch(IOException io) {
            LOGGER.warn ("Constructor loader factory " + io.getMessage ());
            throw new IOException("classCommands.properties was not found" + io.getMessage ());
        }
    }
    public Operation createInstanceClass(@NotNull String nameClass) throws Exception {
        LOGGER.info("Find class by nameCommand");

        try {
            cl = Class.forName(properties.getProperty(nameClass.toUpperCase()));
        } catch (NullPointerException nullPointerException) {
            throw new NullPointerException ("This command wasn't found " + nullPointerException.getMessage ());
        }

        Annotation[] annotations = cl.getAnnotations();

        LOGGER.info("Annotation comparison CommandAnnotation");
        for (Annotation annotation : annotations) {
            if (annotation instanceof CommandAnnotation fileInfo) {
                fileInfo.
                return (Operation)cl.newInstance();
            }
        }
        LOGGER.trace ("Class wasn't found");
        throw new LoadException ("Command not found or class was not annotated");
    }
}
