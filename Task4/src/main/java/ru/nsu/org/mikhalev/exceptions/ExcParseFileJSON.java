package ru.nsu.org.mikhalev.exceptions;

import java.io.IOException;

public class ExcParseFileJSON extends IOException {
    public ExcParseFileJSON(String message) {
        super(message);
    }
}
