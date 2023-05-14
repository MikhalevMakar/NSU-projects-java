package ru.nsu.org.mikhalev.exceptions;

import java.io.IOException;

public class ExcKernelServer extends RuntimeException {
    public ExcKernelServer(final String message) {
        super(message);
    }
}
