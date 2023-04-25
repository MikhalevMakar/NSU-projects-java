package ru.nsu.org.mikhalev.properties_read;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FactoryReader {
    public static void read(String link) throws IOException {
        try (FileInputStream propertiesStream = new FileInputStream(link)) {
            Properties properties = new Properties();
            properties.load(propertiesStream);

            for(Properties_Value value : Properties_Value.values()) {
                value.setValue(properties.getProperty(String.valueOf(value)));
            }

        } catch(IOException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
    }
}
