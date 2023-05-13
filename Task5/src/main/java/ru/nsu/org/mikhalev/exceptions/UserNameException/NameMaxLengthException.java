package ru.nsu.org.mikhalev.exceptions.UserNameException;

public class NameMaxLengthException extends Exception {
    public NameMaxLengthException(final String message) {
        super(message);
    }
}
