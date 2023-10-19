package ru.nsu.org.mikhalev.universal_utile_class.exceptions;

import java.io.IOException;

public class EcxClose extends RuntimeException {
    public EcxClose(String message) {
        super(message);
    }
}
