package org.ru.nsu.mikhalev.task2.Exceptions;

public class OperationException extends IllegalArgumentException {
    public OperationException(String message) {
        super(message);
    }
}
