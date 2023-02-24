package org.ru.nsu.mikhalev.task2.Operations;

import org.ru.nsu.mikhalev.task2.CalculatorController.Context;
import java.util.LinkedList;

public class Division implements Operation {

    @Override
    public void calculation(Context context, LinkedList<String> listValue) {
        checkCorrectArgs (listValue);
        try {
            Double v1 = context.popValue();
            Double v2 = context.popValue();
            if(v2 == 0) {
                throw new IllegalArgumentException("Division by zero");
            }
            Double result = v1 / v2;
            context.pushValue(result.toString());
        } catch(IllegalArgumentException illegalArgumentException) {
            throw  illegalArgumentException;
        }
    }
}
