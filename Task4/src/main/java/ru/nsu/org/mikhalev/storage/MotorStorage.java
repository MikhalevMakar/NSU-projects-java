package ru.nsu.org.mikhalev.storage;

import ru.nsu.org.mikhalev.detail.Motor;
import ru.nsu.org.mikhalev.properties_read.Properties_Value;

public class MotorStorage extends DetailStorage<Motor> {
    public MotorStorage() {
        super((Integer) Properties_Value.STORAGE_MOTOR_SIZE.getValue(), 1);
    }
}
