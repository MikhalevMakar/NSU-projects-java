package org.ru.nsu.mikhalev.task2.Operations;

import org.jetbrains.annotations.NotNull;
import org.ru.nsu.mikhalev.task2.CalculatorController.Context;
import org.ru.nsu.mikhalev.task2.Exceptions.OperationException;

import java.util.LinkedList;

@CommandAnnotation
public class Pop implements Operation {

    @Override
    public void calculation(@NotNull Context context, LinkedList<String> listValue) {
        LOGGER.info ("Try to pop value");
        checkCorrectArgs (listValue);
        context.popValue();
    }
}
