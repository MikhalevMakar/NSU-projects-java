package ru.nsu.org.mikhalev.universal_utile_class.create_command;

import lombok.extern.log4j.Log4j2;
import ru.nsu.org.mikhalev.universal_utile_class.exceptions.ExcLoadCommand;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Log4j2
public class LoaderCommands {

    protected final Properties properties;

    public LoaderCommands(String linkCommandsProperties) {
        properties = new Properties();
        try(InputStream inputStream = getClass()
            .getClassLoader().getResourceAsStream(linkCommandsProperties)) {
            properties.load(inputStream);
        } catch(IOException io) {
            log.warn("Constructor loader commands " + io.getMessage());
            throw new ExcLoadCommand ("classCommands.properties was not found" + io.getMessage());
        }
    }
}
