package ru.nsu.ccfit.mikhalev.operations;

import org.jetbrains.annotations.NotNull;
import ru.nsu.ccfit.mikhalev.calculatorcontroller.Context;
import ru.nsu.ccfit.mikhalev.exceptions.OperationException;

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
