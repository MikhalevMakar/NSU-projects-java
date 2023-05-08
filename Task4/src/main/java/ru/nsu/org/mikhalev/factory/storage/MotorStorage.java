package ru.nsu.org.mikhalev.factory.storage;

import lombok.extern.log4j.Log4j2;
import ru.nsu.org.mikhalev.factory.detail.Motor;
import ru.nsu.org.mikhalev.proces_input.properties_read.Properties_Value;


/*
 * MotorStorage class which accesses the DetailStorage<Motor>
 */

public class MotorStorage extends DetailStorage<Motor> {

    /*
     * Construct MotorStorage
     *
     * These parameters are taken from Properties_Value.
     */
    public MotorStorage() {
        super(Integer.parseInt(Properties_Value.STORAGE_MOTOR_SIZE.getValue()));
    }
}
