package ru.nsu.org.mikhalev.exceptions;

public class ExcInvalidArg  extends IllegalArgumentException {
    public ExcInvalidArg(final String message) {
        super(message);
    }
}