package org.ru.nsu.mikhalev.task2.LoaderFactory;

import org.jetbrains.annotations.NotNull;
import org.ru.nsu.mikhalev.task2.Operations.Operation;
import org.ru.nsu.mikhalev.task2.Operations.CommandAnnotation;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.util.Properties;
public class LoaderFactory {
    private Properties properties;
    public LoaderFactory() throws IOException {
        properties = new Properties();
        try(InputStream inputStream = getClass()
                .getClassLoader().getResourceAsStream("config.properties")){
            properties.load(inputStream);
        } catch(IOException io) {
            throw io;
        }
    }

    public Operation getFilePathToSave(@NotNull String nameClass) throws Exception {
        Class cl = Class.forName(properties.getProperty(nameClass.toUpperCase()));
        Annotation[] annotations = cl.getAnnotations();

        for (Annotation annotation : annotations){
            if (annotation instanceof CommandAnnotation fileInfo) {
                return (Operation)cl.newInstance();
            }
        }
        throw new Exception ("Not found command");
    }
}
