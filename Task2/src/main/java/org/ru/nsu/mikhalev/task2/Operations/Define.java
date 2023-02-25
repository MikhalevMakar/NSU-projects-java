package org.ru.nsu.mikhalev.task2.Operations;

import java.util.*;
import org.jetbrains.annotations.NotNull;
import org.ru.nsu.mikhalev.task2.CalculatorController.Context;
import org.ru.nsu.mikhalev.task2.CheckerDouble.CheckerDouble;
import org.ru.nsu.mikhalev.task2.Exceptions.FormatDouble;
import org.ru.nsu.mikhalev.task2.Exceptions.OperationException;

@CommandAnnotation
public class Define implements Operation {
    @Override
    public void calculation(@NotNull Context context, @NotNull LinkedList<String> listValue) {
        if(listValue.size() != 2) {
            throw new OperationException ("Count value in DEFINE class not correct");
        }

        String parameter = listValue.get(0);
        String value = listValue.get(1);
        if(CheckerDouble.IsNumberFormat(parameter) || !CheckerDouble.IsNumberFormat(value)) {
            throw new FormatDouble("Number or parameter format is not correct, DEFINE command");
        }
        context.addDefineValue(parameter, value);
    }
}



