package org.ru.nsu.mikhalev.task2.Operations;

import org.ru.nsu.mikhalev.task2.CalculatorController.Context;

import java.util.LinkedList;

public class Pop implements Operation {

    @Override
    public void calculation(Context context, LinkedList<String> listValue) {
        CheckCorrectArgs(listValue);
        context.popValue();
    }
}
