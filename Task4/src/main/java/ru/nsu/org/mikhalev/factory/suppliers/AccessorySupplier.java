package ru.nsu.org.mikhalev.factory.suppliers;

import ru.nsu.org.mikhalev.factory.detail.Accessory;
import ru.nsu.org.mikhalev.factory.storage.DetailStorage;

/*
 * { AccessorySupplier } class supplies of parts of the type Accessory
 *
 * Calling the base class DetailSupplier<Accessory>
 */

public class AccessorySupplier extends DetailSupplier<Accessory> {
    public AccessorySupplier(DetailStorage<Accessory> accessoryStorage, Class<Accessory> clazz){
        super(accessoryStorage, clazz);
    }
}
