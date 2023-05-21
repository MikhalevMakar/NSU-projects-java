package ru.nsu.org.mikhalev.universal_utile_class.exceptions;

import java.io.IOException;

public class ExcIO extends RuntimeException {

    public ExcIO(String message) {
        super(message);
    }
}
