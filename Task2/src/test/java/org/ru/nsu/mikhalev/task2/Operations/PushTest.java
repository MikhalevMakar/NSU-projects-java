package org.ru.nsu.mikhalev.task2.Operations;

import org.apache.commons.cli.ParseException;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.ru.nsu.mikhalev.task2.CalculatorController.Context;
import org.ru.nsu.mikhalev.task2.Exceptions.FormatDouble;
import org.ru.nsu.mikhalev.task2.Exceptions.NumericArguments;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PushTest {
    private final String[] args = new String[]{"-i=../Task2/src/main/resources/inputFile.txt"};
    private final Context context = new Context(args);;
    private LinkedList<String> list = new LinkedList<>();
    PushTest() throws FileNotFoundException, ParseException {
    }

    private static @NotNull Stream<Arguments> argsProviderFactoryRight() {
        return Stream.of (
                Arguments.of (3.0, new LinkedList<String>(List.of("3.0"))),
                Arguments.of (-1.0, new LinkedList<String>(List.of("-1.0")))
        );
    }
    @ParameterizedTest
    @MethodSource("argsProviderFactoryRight")
    public void calculationRightTest(Double v1, LinkedList<String> list) throws Exception {
        Push push = new Push ();

        push.calculation (context, list);
        assertEquals(v1, context.popValue ());
    }

    private static @NotNull Stream<Arguments> argsProviderFactoryExceptListEmpty() {
        return Stream.of (
                Arguments.of ("3.0", new LinkedList<String>(List.of("3.0")))
        );
    }
    @ParameterizedTest
    @MethodSource("argsProviderFactoryListEmpty")
    public void calculationExceptListEmpty(String v1, LinkedList<String> list) {
        Push push = new Push ();

        NumericArguments exception = Assertions.assertThrows(NumericArguments.class,
                                                         () -> push.calculation (context, list));
        assertEquals ("Incorrect number of arguments",
                      exception.getMessage ());
    }
}