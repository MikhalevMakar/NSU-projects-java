package ru.nsu.org.mikhalev.factory.suppliers;

import ru.nsu.org.mikhalev.factory.detail.Motor;
import ru.nsu.org.mikhalev.factory.storage.DetailStorage;

public class MotorSupplier extends DetailSupplier<Motor> {
    public MotorSupplier(DetailStorage<Motor> motorStorage, Class<Motor> clazz){
        super(motorStorage, clazz);
    }
}
