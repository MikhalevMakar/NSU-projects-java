package ru.nsu.ccfit.mikhalev.operations;

import org.apache.commons.cli.ParseException;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.nsu.ccfit.mikhalev.calculatorcontroller.Context;
import ru.nsu.ccfit.mikhalev.exceptions.FormatDouble;
import ru.nsu.ccfit.mikhalev.exceptions.OperationException;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DivisionTest {
    private final String[] args = new String[]{"-i=../Task2/src/main/resources/inputFile.txt"};
    private final Context context = new Context(args);;
    private LinkedList<String> list = new LinkedList<>();
    DivisionTest() throws FileNotFoundException, ParseException {
    }
    private static @NotNull Stream<Arguments> argsProviderFactoryRight() {
        return Stream.of (
                Arguments.of ("3.0", "2.0", 1.5),
                Arguments.of ("-10.0", "5.0", -2.0)
        );
    }
    @ParameterizedTest
    @MethodSource("argsProviderFactoryRight")
    public void calculationRightTest(String v1, String v2, Double result) {
        Division division = new Division ();
        context.pushValue (v2);
        context.pushValue (v1);
        division.calculation (context, list);
        assertEquals(context.popValue (), result);
    }


    private static @NotNull Stream<Arguments> argsProviderFactoryExceptDivByZero() {
        return Stream.of (
                Arguments.of ("3.0", "0.0")
        );
    }

    @ParameterizedTest
    @MethodSource("argsProviderFactoryExceptDivByZero")
    public void calculationExceptDivisionZero(String v1, String v2) {
        Division division = new Division ();
        context.pushValue (v2);
        context.pushValue (v1);

        OperationException exception = Assertions.assertThrows(OperationException.class,
                () -> division.calculation (context, list));
    }

    private static @NotNull Stream<Arguments> argsProviderFactoryExceptPopValue() {
        return Stream.of (
                Arguments.of ("3.0")
        );
    }

    @ParameterizedTest
    @MethodSource("argsProviderFactoryExceptPopValue")
    public void calculationExceptPopValue(String v1) throws FileNotFoundException, ParseException {
        Division division = new Division ();

        context.pushValue (v1);

        FormatDouble exception = Assertions.assertThrows(FormatDouble.class,
                () -> division.calculation (context, list));
    }
}