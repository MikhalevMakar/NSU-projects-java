package ru.nsu.org.mikhalev.factory.storage;

import ru.nsu.org.mikhalev.factory.detail.Accessory;
import ru.nsu.org.mikhalev.proces_input.properties_read.Properties_Value;

public class AccessoryStorage extends DetailStorage<Accessory> {
    public AccessoryStorage() {
        super(Integer.parseInt(Properties_Value.STORAGE_ACCESSORY_SIZE.getValue()));
    }
}

