package org.ru.nsu.mikhalev.task2.Operations;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.ru.nsu.mikhalev.task2.CalculatorController.Context;
import org.ru.nsu.mikhalev.task2.Exceptions.OperationException;
import java.util.LinkedList;

public interface Operation {
    public static final Logger LOGGER = Logger.getLogger (Operation.class.getName ());
    public void calculation(Context context, LinkedList<String> listValue) throws Exception;
    public default void checkCorrectArgs(@NotNull LinkedList<String> listValue) {
        if(!listValue.isEmpty()) {
            throw new OperationException ("Size args is not correct");
        }
    }
}


