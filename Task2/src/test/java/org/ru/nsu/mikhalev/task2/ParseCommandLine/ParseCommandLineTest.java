package org.ru.nsu.mikhalev.task2.ParseCommandLine;

import org.apache.commons.cli.ParseException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParseCommandLineTest {

    @Test
    void searchCommandLineRightTest(String... args) throws ParseException {
        ParseCommandLine.searchCommandLine (args);
        
    }
}