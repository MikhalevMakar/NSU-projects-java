package ru.nsu.org.mikhalev.storage;

import ru.nsu.org.mikhalev.detail.Body;
import ru.nsu.org.mikhalev.properties_read.Properties_Value;

public class BodyStorage extends DetailStorage<Body> {
    public BodyStorage() {
        super((Integer) Properties_Value.STORAGE_BODY_SIZE.getValue(), 1);
    }
}
