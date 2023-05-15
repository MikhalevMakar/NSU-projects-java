package ru.nsu.org.mikhalev.universal_utile_class.exceptions;

import org.apache.commons.cli.ParseException;


public class ExcParseFileJSON  extends ParseException {
    public ExcParseFileJSON(final String message) {
        super(message);
    }
}
