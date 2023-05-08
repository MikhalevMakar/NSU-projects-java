package ru.nsu.org.mikhalev.proces_input.properties_read;

import lombok.Getter;
import lombok.Setter;

/*
 * { Properties_Value } enum contains all the necessary data for the file factory.properties
 */

public enum Properties_Value {
    STORAGE_BODY_SIZE,
    STORAGE_MOTOR_SIZE,
    STORAGE_ACCESSORY_SIZE,
    STORAGE_AUTO_SIZE,
    ACCESSORY_SUPPLIERS,
    WORKERS,
    DEALERS,
    LOG_SALE;

    @Setter
    @Getter
    private String value;
}

