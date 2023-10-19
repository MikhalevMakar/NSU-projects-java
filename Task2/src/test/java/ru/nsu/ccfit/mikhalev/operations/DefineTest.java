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
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


class DefineTest {
    private final String[] args = new String[]{"-i=../Task2/src/main/resources/inputFile.txt"};
    private final Context context = new Context(args);;

    DefineTest() throws FileNotFoundException, ParseException {
    }

    private static @NotNull Stream<Arguments> argsProviderFactoryRight() {
        return Stream.of (
                Arguments.of (new LinkedList<String>(List.of ("VALUE1", "3.14")), "VALUE1", 3.14),
                Arguments.of (new LinkedList<String>(List.of ("VALUE2", "-1.22")), "VALUE2", -1.22),
                Arguments.of (new LinkedList<String>(List.of ("VALUE3", "4.0")), "VALUE3", 4.0),
                Arguments.of (new LinkedList<String>(List.of ("VALUE4", "0.0")), "VALUE4", 0.098)
        );
    }
    @ParameterizedTest
    @MethodSource("argsProviderFactoryRight")
    public void calculation(LinkedList<String> listValue, String key, Double value) {
        Define define = new Define ();
        define.calculation (context, listValue);

        assertEquals(context.getDefineValue (key),value);
    }


    private static @NotNull Stream<Arguments> argsProviderFactoryExceptSizeIsNotCorrect() {
        return Stream.of (
                Arguments.of (new LinkedList<String>(List.of ("VALUE1")))
        );
    }

    @ParameterizedTest
    @MethodSource("argsProviderFactoryExceptSizeIsNotCorrect")
    public void calculationExceptSizeIsNotCorrect(LinkedList<String> listValue) {
        Define define = new Define ();

        OperationException exception = Assertions.assertThrows(OperationException.class,
                () -> define.calculation (context, listValue), "Count value in DEFINE class not correct");
        assertEquals("Count value in DEFINE class not correct", exception.getMessage ());
    }

    private static @NotNull Stream<Arguments> argsProviderFactoryExceptKeValue() {
        return Stream.of (
                Arguments.of (new LinkedList<String>(List.of ("1", "3.13"))),
                Arguments.of (new LinkedList<String>(List.of ("KEY", "VALUE")))
        );
    }

    @ParameterizedTest
    @MethodSource("argsProviderFactoryExceptKeValue")
    public void calculationExceptKeValue(LinkedList<String> listValue) {
        Define define = new Define ();

        FormatDouble exception = Assertions.assertThrows(FormatDouble.class,
                () -> define.calculation (context, listValue), "Number or parameter format is not correct, DEFINE command");
        assertEquals("Number or parameter format is not correct, DEFINE command", exception.getMessage ());
    }

}