package ru.nsu.org.mikhalev.exceptions;

public class ExcInvalidArg  extends IllegalArgumentException {
    public ExcInvalidArg(String message) {
        super(message);
    }
}