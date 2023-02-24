package org.ru.nsu.mikhalev.task2.Operations;

import java.util.*;
import org.jetbrains.annotations.NotNull;
import org.ru.nsu.mikhalev.task2.CalculatorController.Context;
import org.ru.nsu.mikhalev.task2.Exceptions.OperationException;

@CommandAnnotation
public class Define implements Operation {
    @Override
    public void calculation(@NotNull Context context, @NotNull LinkedList<String> listValue) {
        if(listValue.size() != 2) {
            throw new OperationException ("Count value in DEFINE class not correct");
        }
        context.addDefineValue(listValue.get(0), listValue.get(1));
    }
}


