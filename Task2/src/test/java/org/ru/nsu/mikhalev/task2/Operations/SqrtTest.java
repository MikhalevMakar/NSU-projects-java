package org.ru.nsu.mikhalev.task2.Operations;

import org.apache.commons.cli.ParseException;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.ru.nsu.mikhalev.task2.CalculatorController.Context;
import org.ru.nsu.mikhalev.task2.Exceptions.NumericArguments;
import org.ru.nsu.mikhalev.task2.Exceptions.OperationException;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class SqrtTest {
    private final String[] args = new String[]{"-i=../Task2/src/main/resources/inputFile.txt"};
    private final Context context = new Context(args);;
    private LinkedList<String> list = new LinkedList<>();
    SqrtTest() throws FileNotFoundException, ParseException {
    }

    private static @NotNull Stream<Arguments> argsProviderFactoryArgsEmpty() {
        return Stream.of (
                Arguments.of (new LinkedList<String>(List.of("-1")))
        );
    }
    @ParameterizedTest
    @MethodSource("argsProviderFactoryArgsEmpty")
    public void calculationRightTest( LinkedList<String> list) {
        Sqrt sqrt = new Sqrt ();

        OperationException exception = Assertions.assertThrows(OperationException.class,
                                                             () -> sqrt.calculation (context, list));
        assertEquals ("Size args is not correct",
                exception.getMessage ());
    }

    private static @NotNull Stream<Arguments> argsProviderFactoryNotCorrectValue() {
        return Stream.of (
                Arguments.of ("-1")
        );
    }

    @ParameterizedTest
    @MethodSource("argsProviderFactoryNotCorrectValue")
    public void calculationNotCorrectValueTest(String value) {
        Sqrt sqrt = new Sqrt ();
        context.pushValue (value);
        OperationException exception = Assertions.assertThrows(OperationException.class,
                () -> sqrt.calculation (context, list));
    }

}