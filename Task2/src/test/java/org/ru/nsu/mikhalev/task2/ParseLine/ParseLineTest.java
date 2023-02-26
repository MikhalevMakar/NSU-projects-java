package org.ru.nsu.mikhalev.task2.ParseLine;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.ru.nsu.mikhalev.task2.Exceptions.NumericArguments;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;


class ParseLineTest {
    private static @NotNull Stream<Arguments> argsRightProviderFactory() {
        return Stream.of(
                Arguments.of("DEFINE A 1",  "DEFINE", new LinkedList<String> (List.of ("A", "1"))),
                Arguments.of("DIVISION B 2",  "DIVISION", new LinkedList<String> (List.of ("B", "2"))),
                Arguments.of("MINUS C 3",  "MINUS", new LinkedList<String> (List.of ("C", "3")))
        );
    }

    private static @NotNull Stream<Arguments> argsThrowProviderFactory() {
        return Stream.of(
                Arguments.of("123 A nan",  "DEFINE", new LinkedList<String> (List.of ("A", "1")))
        );
    }


    @ParameterizedTest
    @MethodSource("argsRightProviderFactory")
    void parseRightTest(@NotNull String line, String name, LinkedList<String> list) throws NumericArguments {
        ParseLine parseLine = new ParseLine ();
        parseLine.parse (line);

        assertEquals (list, parseLine.getListValue ());
        assertEquals(name, parseLine.getNameCommand ());
    }

    @ParameterizedTest
    @MethodSource("argsThrowProviderFactory")
    void parseExceptionsTest(@NotNull String line, String name, LinkedList<String> list) {
        ParseLine parseLine = new ParseLine ();
        NumericArguments exception = Assertions.assertThrows(NumericArguments.class, () -> parseLine.parse (line), "Invalid command entry");
        assertEquals("Invalid command entry", exception.getMessage ());
    }
}