package ru.nsu.org.mikhalev.suppliers;

import ru.nsu.org.mikhalev.detail.Motor;
import ru.nsu.org.mikhalev.storage.DetailStorage;

public class MotorSupplier extends DetailSupplier<Motor> {
    public MotorSupplier(DetailStorage<Motor> motorStorage, Class<Motor> clazz){
        super(motorStorage, clazz);
    }
}
