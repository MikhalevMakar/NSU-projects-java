package ru.nsu.org.mikhalev.exceptions;

public class LoadException extends  RuntimeException {
    public LoadException(final String message) {
        super(message);
    }
}