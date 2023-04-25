package ru.nsu.org.mikhalev.factory.storage;

import ru.nsu.org.mikhalev.factory.detail.Motor;
import ru.nsu.org.mikhalev.proces_input.properties_read.Properties_Value;

public class MotorStorage extends DetailStorage<Motor> {
    public MotorStorage() {
        super(Integer.parseInt(Properties_Value.STORAGE_MOTOR_SIZE.getValue()), 1);
    }
}
