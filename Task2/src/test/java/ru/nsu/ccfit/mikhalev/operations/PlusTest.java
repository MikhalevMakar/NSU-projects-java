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

class PlusTest {

    private final String[] args = new String[]{"-i=../Task2/src/main/resources/inputFile.txt"};
    private final Context context = new Context(args);;
    private LinkedList<String> list = new LinkedList<>();
    PlusTest() throws FileNotFoundException, ParseException {
    }
    private static @NotNull Stream<Arguments> argsProviderFactoryRight() {
        return Stream.of (
                Arguments.of ("3.0", "2.0", 5.0),
                Arguments.of ("-10.0", "5.0", -5.0),
                Arguments.of ("-10.0", "10.0", 0.0),
                Arguments.of ("-15.32", "-2.0", -17.32)
        );
    }
    @ParameterizedTest
    @MethodSource("argsProviderFactoryRight")
    public void calculationRightTest(String v1, String v2, Double result) {
        Plus plus = new Plus ();
        context.pushValue (v2);
        context.pushValue (v1);
        plus.calculation (context, list);
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
        Plus plus = new Plus ();
        context.pushValue (v2);
        context.pushValue (v1);

        FormatDouble exception = Assertions.assertThrows(FormatDouble.class,
                () -> plus.calculation (context, list));
    }

    private static @NotNull Stream<Arguments> argsProviderExceptSizeArgs() {
        return Stream.of (
                Arguments.of ("3.0"),
                Arguments.of ("value")
        );
    }
    @ParameterizedTest
    @MethodSource("argsProviderExceptSizeArgs")
    public void calculationExceptSizeArgs(String v) throws FileNotFoundException, ParseException {
        Plus plus = new Plus ();

        context.pushValue (v);

        FormatDouble exception = Assertions.assertThrows(FormatDouble.class,
                () -> plus.calculation (context, list));
    }
}