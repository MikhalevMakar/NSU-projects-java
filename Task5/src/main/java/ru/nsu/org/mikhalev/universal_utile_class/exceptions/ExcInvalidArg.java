package ru.nsu.org.mikhalev.universal_utile_class.exceptions;

public class ExcInvalidArg  extends IllegalArgumentException {
    public ExcInvalidArg(final String message) {
        super(message);
    }
}