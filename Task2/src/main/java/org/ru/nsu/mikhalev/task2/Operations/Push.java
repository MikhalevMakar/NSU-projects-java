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
        LOGGER.info ("Check correct size list args");
        if (listValue.size () != 1) {
            LOGGER.error ("Size list was not correct " + this.getClass ());
        throw new NumericArguments ("Incorrect number of arguments");
        }

        LOGGER.info ("Push value in stack");
        if(CheckerDouble.IsNumberFormat(listValue.getFirst())) {
            context.pushValue(listValue.getFirst());
        } else {
            context.pushValue(context.getDefineValue(listValue.getFirst()).toString());
        }
    }
}
