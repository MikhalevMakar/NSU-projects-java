package org.ru.nsu.mikhalev.task2.Operations;

import org.jetbrains.annotations.NotNull;
import org.ru.nsu.mikhalev.task2.CalculatorController.Context;
import org.ru.nsu.mikhalev.task2.Exceptions.OperationException;

import java.util.LinkedList;

import static java.lang.Math.sqrt;
@CommandAnnotation
public class Sqrt implements Operation {
    @Override
    public void calculation(@NotNull Context context, LinkedList<String> listValue) {
        checkCorrectArgs (listValue);
        Double v;
        try {
            v = context.popValue();
            if(v < 0) {
                throw new OperationException("the sqrt argument should: >= 0");
            }
            context.pushValue(v.toString());
        } catch(OperationException operationException) {
            throw  new OperationException("Stack is empty" + operationException.getStackTrace());
        }
        context.pushValue(Double.valueOf(sqrt(v)).toString());
    }
}
