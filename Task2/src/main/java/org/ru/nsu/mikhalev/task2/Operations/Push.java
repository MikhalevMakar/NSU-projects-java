package org.ru.nsu.mikhalev.task2.Operations;

import org.jetbrains.annotations.NotNull;
import org.ru.nsu.mikhalev.task2.CalculatorController.Context;
import org.ru.nsu.mikhalev.task2.CheckerDouble.CheckerDouble;
import org.ru.nsu.mikhalev.task2.Exceptions.NumericArguments;
import org.ru.nsu.mikhalev.task2.Exceptions.OperationException;

import java.util.LinkedList;
@CommandAnnotation
public class Push implements Operation{
    @Override
    public void calculation(Context context, @NotNull LinkedList<String> listValue) throws Exception {
        if(listValue.size() != 1)
            throw new NumericArguments("Incorrect number of arguments");

        if(CheckerDouble.IsNumberFormat(listValue.getFirst())) {
            context.pushValue(listValue.getFirst());
        } else {
            context.pushValue(context.getDefineValue(listValue.getFirst()).toString());
        }
    }
}
