package ru.nsu.org.mikhalev.factory.suppliers;

import lombok.Setter;
import ru.nsu.org.mikhalev.factory.detail.Accessory;
import ru.nsu.org.mikhalev.factory.storage.DetailStorage;

public class AccessorySupplier extends DetailSupplier<Accessory> {
    public AccessorySupplier(DetailStorage<Accessory> accessoryStorage, Class<Accessory> clazz){
        super(accessoryStorage, clazz);
    }
}
