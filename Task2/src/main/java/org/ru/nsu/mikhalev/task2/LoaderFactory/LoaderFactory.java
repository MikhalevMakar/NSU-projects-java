package org.ru.nsu.mikhalev.task2.LoaderFactory;

import org.jetbrains.annotations.NotNull;
import org.ru.nsu.mikhalev.task2.Operations.Operation;

import java.io.IOException;
import java.io.InputStream;
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

    public Operation getFilePathToSave(@NotNull String nameClass) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        return (Operation) Class.forName(properties.getProperty(nameClass.toUpperCase())).newInstance();
    }

}

