package ru.nsu.org.mikhalev.suppliers;

import ru.nsu.org.mikhalev.detail.Accessory;
import ru.nsu.org.mikhalev.storage.DetailStorage;

public class AccessorySupplier extends DetailSupplier<Accessory> {
    public AccessorySupplier(DetailStorage<Accessory> accessoryStorage, Class<Accessory> clazz){
        super(accessoryStorage, clazz);
    }
}
