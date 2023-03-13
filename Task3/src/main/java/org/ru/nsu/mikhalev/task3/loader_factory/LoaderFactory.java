package org.ru.nsu.mikhalev.task3.loader_factory;

import org.ru.nsu.mikhalev.task3.shape.AnnotationShape;
import org.ru.nsu.mikhalev.task3.shape.Shape;
import java.io.*;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Properties;
import org.apache.log4j.Logger;
public class LoaderFactory {
    private Properties properties;
    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger (LoaderFactory.class.getName ());

    private  Class cl;
    public LoaderFactory() throws IOException {
        properties = new Properties ();
        try(InputStream inputStream = getClass()
                .getClassLoader().getResourceAsStream("classCommands.properties")) {
            properties.load(inputStream);
        } catch(IOException io) {
            LOGGER.warn ("Constructor loader factory " + io.getMessage());
            throw new IOException ("classCommands.properties was not found" + io.getMessage ());
        }
    }
    public Shape createInstanceClass(String nameClass) throws Exception {
        LOGGER.info("Find class by nameCommand");

        try {
            cl = Class.forName(properties.getProperty(nameClass.toUpperCase()));
        } catch (NullPointerException nullPointerException) {
            throw new NullPointerException ("This command wasn't found " + nullPointerException.getMessage());
        }

        Annotation[] annotations = cl.getAnnotations();

        LOGGER.info("Annotation comparison AnnotationShape");
        for (Annotation annotation : annotations) {
            if (annotation instanceof AnnotationShape) {
                return (Shape)cl.newInstance();
            }
        }
        LOGGER.trace ("Class wasn't found");
        throw new RuntimeException("Command not found or class was not annotated");
    }
}
