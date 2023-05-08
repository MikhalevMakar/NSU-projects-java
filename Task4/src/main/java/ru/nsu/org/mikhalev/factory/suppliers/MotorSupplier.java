package ru.nsu.org.mikhalev.factory.suppliers;

import ru.nsu.org.mikhalev.factory.detail.Motor;
import ru.nsu.org.mikhalev.factory.storage.DetailStorage;

/*
 * { MotorSupplier } class supplies of parts of the type Motor
 *
 * Calling the base class DetailSupplier<Motor>
 */

public class MotorSupplier extends DetailSupplier<Motor> {
    public MotorSupplier(DetailStorage<Motor> motorStorage, Class<Motor> clazz){
        super(motorStorage, clazz);
    }
}
