package ru.nsu.org.mikhalev.universal_utile_class.exceptions;

public class ExcLoadCommand extends  RuntimeException {
    public ExcLoadCommand(final String message) {
        super(message);
    }
}