package org.ru.nsu.mikhalev.task2.Operations;

import org.apache.commons.cli.ParseException;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.ru.nsu.mikhalev.task2.CalculatorController.Context;
import org.ru.nsu.mikhalev.task2.Exceptions.FormatDouble;
import org.ru.nsu.mikhalev.task2.Exceptions.OperationException;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class PopTest {
    private final String[] args = new String[]{"-i=../Task2/src/main/resources/inputFile.txt"};
    private final Context context = new Context(args);;
    private LinkedList<String> list = new LinkedList<>();
    PopTest() throws FileNotFoundException, ParseException {
    }

    private static @NotNull Stream<Arguments> argsProviderFactoryRight() {
        return Stream.of (
                Arguments.of ("3.0", 3.0)
        );
    }
    @ParameterizedTest
    @MethodSource("argsProviderFactoryRight")
    public void calculationRightTest(String v1, Double result) {
        Pop pop = new Pop ();
        context.pushValue (v1);
        pop.calculation(context, list);
    }

    private static @NotNull Stream<Arguments> argsProviderFactoryListEmpty() {
        return Stream.of (
                Arguments.of ("3.0", 3.0)
        );
    }
    @ParameterizedTest
    @MethodSource("argsProviderFactoryListEmpty")
    public void calculationListEmpty(String v1, Double result) {
        Pop pop = new Pop ();
        context.pushValue (v1);
        pop.calculation(context, list);

        FormatDouble exception = Assertions.assertThrows(FormatDouble.class,
                () -> pop.calculation (context, list));
        assertEquals ("Stack is empty, an exception was thrown when the pop element was raised",
                      exception.getMessage ());
    }
}
