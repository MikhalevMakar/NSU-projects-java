package org.ru.nsu.mikhalev.task2.Operations;

import org.jetbrains.annotations.NotNull;
import org.ru.nsu.mikhalev.task2.CalculatorController.Context;

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
                throw new IllegalArgumentException("v < 0");
            }
            context.pushValue(v.toString());
        } catch(IllegalArgumentException illegalArgumentException) {
            throw  illegalArgumentException;
        }
        context.pushValue(Double.valueOf(sqrt(v)).toString());
    }
}
