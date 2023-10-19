package ru.nsu.ccfit.mikhalev.operations;

import org.apache.commons.cli.ParseException;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.nsu.ccfit.mikhalev.calculatorcontroller.Context;
import ru.nsu.ccfit.mikhalev.exceptions.FormatDouble;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MinusTest {
    private final String[] args = new String[]{"-i=../Task2/src/main/resources/inputFile.txt"};
    private final Context context = new Context(args);;
    private LinkedList<String> list = new LinkedList<>();
    MinusTest() throws FileNotFoundException, ParseException {
    }
    private static @NotNull Stream<Arguments> argsProviderFactoryRight() {
        return Stream.of (
                Arguments.of ("3.0", "2.0", 1.0),
                Arguments.of ("-10.0", "5.0", -15.0),
                Arguments.of ("-10.0", "-10.0", 0.0),
                Arguments.of ("-15.32", "-1.23", -14.09)
        );
    }
    @ParameterizedTest
    @MethodSource("argsProviderFactoryRight")
    public void calculationRightTest(String v1, String v2, Double result) {
        Minus minus = new Minus ();
        context.pushValue (v2);
        context.pushValue (v1);
        minus.calculation (context, list);
        assertEquals(context.popValue (), result);
    }

    private static @NotNull Stream<Arguments> argsProviderExceptNumberFormat() {
        return Stream.of (
                Arguments.of ("3.0", "value"),
                Arguments.of ("value", "5.0")
        );
    }
    @ParameterizedTest
    @MethodSource("argsProviderExceptNumberFormat")
    public void calculationExceptNumberFormat(String v1, String v2) {
        Minus minus = new Minus ();
        context.pushValue (v2);
        context.pushValue (v1);

        FormatDouble exception = Assertions.assertThrows(FormatDouble.class,
                () -> minus.calculation (context, list));
    }

    private static @NotNull Stream<Arguments> argsProviderExceptSizeArgs() {
        return Stream.of (
                Arguments.of ("3.0"),
                Arguments.of ("value")
        );
    }
    @ParameterizedTest
    @MethodSource("argsProviderExceptSizeArgs")
    public void calculationExceptSizeArgs(String v) {
        Minus minus = new Minus ();

        context.pushValue (v);

        FormatDouble exception = Assertions.assertThrows(FormatDouble.class,
                () -> minus.calculation (context, list));
    }
}