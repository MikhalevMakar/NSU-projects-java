package org.ru.nsu.mikhalev.task2.Operations;

import org.jetbrains.annotations.NotNull;
import org.ru.nsu.mikhalev.task2.CalculatorController.Context;
import org.ru.nsu.mikhalev.task2.Exceptions.NumericArguments;
import org.ru.nsu.mikhalev.task2.Exceptions.OperationException;

import java.util.LinkedList;

@CommandAnnotation
public class Plus implements Operation {
    @Override
    public void calculation(@NotNull Context context, LinkedList<String> listValue) {
        checkCorrectArgs (listValue);
        try {
            Double v1 = context.popValue();
            Double v2 = context.popValue();
            Double result = v1 + v2;
            context.pushValue(result.toString());
        } catch(OperationException numericArguments) {
            throw new OperationException("Failed to pop element from stack" + numericArguments.getStackTrace());
        }
    }
}
