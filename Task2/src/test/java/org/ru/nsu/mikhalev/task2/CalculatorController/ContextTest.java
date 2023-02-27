package org.ru.nsu.mikhalev.task2.CalculatorController;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.ru.nsu.mikhalev.task2.Exceptions.FormatDouble;
import org.ru.nsu.mikhalev.task2.Exceptions.OperationException;
import java.io.IOException;
import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContextTest {

    @ParameterizedTest
    @CsvSource({
            "value, It's not a number"
    })
    void pushValueTestExceptInvalidNumberExceptions(String value, String message) throws IOException {
        Context context = new Context();
        FormatDouble exception = Assertions.assertThrows(FormatDouble.class,
                                 () -> context.pushValue (value));
        assertEquals (message, exception.getMessage ());
    }

    @ParameterizedTest
    @CsvSource({
            "Stack is empty"
    })
    public void popValueExceptions(String message) {
        Context context = new Context();
        FormatDouble exception = Assertions.assertThrows(FormatDouble.class,
                                 () -> context.popValue ());
        assertEquals (message, exception.getMessage ());
        assertEquals (message, exception.getMessage ());
    }

    @ParameterizedTest
    @CsvSource({
            "Failed to pop element from stack"
    })
    public void peekValueStackExceptions(String message) {
        Context context = new Context();
        EmptyStackException exception = Assertions.assertThrows(EmptyStackException.class,
                                 () -> context.peekValueStack ());
    }
    @ParameterizedTest
    @CsvSource({
            "value, Element was not found"
    })
    public void getDefineValueExceptions(String value, String message) {
        Context context = new Context();
        OperationException exception = Assertions.assertThrows(OperationException.class,
                () -> context.getDefineValue (value));
        assertEquals (message, exception.getMessage ());
    }

    @ParameterizedTest
    @CsvSource({
            "key, value, Format not correct",
            "123, 123,  Format not correct"
    })
    public void addDefineValueExceptions(String parameter, String value, String message) {
        Context context = new Context();
        FormatDouble exception = Assertions.assertThrows(FormatDouble.class,
                () -> context.addDefineValue (parameter, value));
        assertEquals (message, exception.getMessage ());
    }
}