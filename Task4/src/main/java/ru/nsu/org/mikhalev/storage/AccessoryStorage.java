package ru.nsu.org.mikhalev.storage;

import ru.nsu.org.mikhalev.detail.Accessory;
import ru.nsu.org.mikhalev.properties_read.Properties_Value;

public class AccessoryStorage extends DetailStorage<Accessory>{
    public AccessoryStorage() {
        super((Integer) Properties_Value.STORAGE_ACCESSORY_SIZE.getValue(),
              (Integer) Properties_Value.ACCESSORY_SUPPLIERS.getValue());
    }
}

