package ru.nsu.org.mikhalev.factory.storage;

import ru.nsu.org.mikhalev.factory.detail.Body;
import ru.nsu.org.mikhalev.proces_input.properties_read.Properties_Value;

public class BodyStorage extends DetailStorage<Body> {
    public BodyStorage() {
        super(Integer.parseInt(Properties_Value.STORAGE_BODY_SIZE.getValue()));
    }
}
