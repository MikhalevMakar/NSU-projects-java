package ru.nsu.org.mikhalev.exceptions;

public class LoadException extends  RuntimeException {
    public LoadException(String message) {
        super(message);
    }
}