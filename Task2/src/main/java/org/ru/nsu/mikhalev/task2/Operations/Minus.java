package org.ru.nsu.mikhalev.task2.Operations;

import org.jetbrains.annotations.NotNull;
import org.ru.nsu.mikhalev.task2.CalculatorController.Context;

import java.util.LinkedList;

public class Minus implements Operation {


    @Override
    public void calculation(Context context, @NotNull LinkedList<String> listValue) {
        CheckCorrectArgs(listValue);
        try {
            Double v1 = context.popValue();
            Double v2 = context.popValue();
            Double result = v1 - v2;
            context.pushValue(result.toString());
        } catch(IllegalArgumentException illegalArgumentException) {
            throw  illegalArgumentException;
        }
    }
}
