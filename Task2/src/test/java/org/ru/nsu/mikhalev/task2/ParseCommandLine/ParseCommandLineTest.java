package org.ru.nsu.mikhalev.task2.ParseCommandLine;

import org.apache.commons.cli.ParseException;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ParseCommandLineTest {

    private static @NotNull Stream<Arguments> argsRightProviderFactory() {
        return Stream.of(
                Arguments.of(new String[]{"i"},  "file.txt")
        );
    }

    @Test
    void searchCommandLineRightTest(String[] testLine, String nameFile) throws ParseException {
        ParseCommandLine.searchCommandLine (testLine);
        System.out.println (ParseCommandLine.getFileInput ());
        System.out.println (nameFile);
       // assertEquals (ParseCommandLine.getFileInput (), nameFile);
    }
}