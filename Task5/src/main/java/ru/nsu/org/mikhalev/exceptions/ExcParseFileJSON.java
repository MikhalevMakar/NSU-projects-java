package ru.nsu.org.mikhalev.exceptions;

import org.apache.commons.cli.ParseException;

import java.io.IOException;

public class ExcParseFileJSON  extends ParseException {
    public ExcParseFileJSON(String message) {
        super(message);
    }
}
