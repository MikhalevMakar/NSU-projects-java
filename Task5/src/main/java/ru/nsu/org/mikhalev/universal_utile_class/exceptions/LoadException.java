package ru.nsu.org.mikhalev.universal_utile_class.exceptions;

public class LoadException extends  RuntimeException {
    public LoadException(final String message) {
        super(message);
    }
}