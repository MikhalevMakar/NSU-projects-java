package org.ru.nsu.mikhalev.task3.loader_factory;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.util.Properties;
import org.ru.nsu.mikhalev.task3.model.TetrisShape;
import org.ru.nsu.mikhalev.task3.model.shape.AnnotationShape;

public class LoaderFactory {
    private Properties properties;
    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger (LoaderFactory.class.getName ());
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
    public TetrisShape createInstanceClass(@NotNull String nameClass) throws Exception {
        LOGGER.info("Find class by nameCommand");
        try {
            cl = Class.forName(properties.getProperty(nameClass.toUpperCase()));
        } catch (NullPointerException nullPointerException) {
            throw new NullPointerException ("This command wasn't found " + nullPointerException.getMessage ());
        }

        Annotation[] annotations = cl.getAnnotations();

        LOGGER.info("Annotation comparison CommandAnnotation");
        for(Annotation annotation : annotations) {
            if (annotation instanceof AnnotationShape) {
                return (TetrisShape)cl.newInstance();
            }
        }
        LOGGER.trace ("Class wasn't found");
        throw new Exception("Command not found or class was not annotated");
    }
}