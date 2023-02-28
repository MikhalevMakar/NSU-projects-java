package org.ru.nsu.mikhalev.task2.Operations;

import org.jetbrains.annotations.NotNull;
import org.ru.nsu.mikhalev.task2.CalculatorController.Context;
import org.ru.nsu.mikhalev.task2.Exceptions.OperationException;

import java.util.LinkedList;

@CommandAnnotation
public class Print implements Operation{
    @Override
    public void calculation(@NotNull Context context, LinkedList<String> listValue) throws Exception {
        checkCorrectArgs (listValue);
        try {
            LOGGER.debug ("Try to print value, That's why call peek in stack");
            Double value = context.peekValueStack();
        } catch (OperationException operationException) {
            LOGGER.error ("Failed pop element " + this.getClass ());
            new OperationException ("Failed to pop element from stack"  + operationException.getStackTrace());
        }
        System.out.println (context.peekValueStack());
    }
}
