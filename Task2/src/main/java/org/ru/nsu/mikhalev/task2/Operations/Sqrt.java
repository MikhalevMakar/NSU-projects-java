package org.ru.nsu.mikhalev.task2.Operations;

import org.ru.nsu.mikhalev.task2.CalculatorController.Context;

import java.util.LinkedList;

import static java.lang.Math.sqrt;

public class Sqrt  implements Operation {
    @Override
    public void calculation(Context context, LinkedList<String> listValue) {
        CheckCorrectArgs(listValue);
        Double v, result;
        try {
            v = context.popValue();
            if(v < 0) {
                throw new IllegalArgumentException("v < 0");
            }
            context.pushValue(v.toString());
        } catch(IllegalArgumentException illegalArgumentException) {
            throw  illegalArgumentException;
        }
        result = sqrt(v);
        context.pushValue(result.toString());
    }
}
