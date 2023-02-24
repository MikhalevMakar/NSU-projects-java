package org.ru.nsu.mikhalev.task2.Operations;


import org.jetbrains.annotations.NotNull;
import org.ru.nsu.mikhalev.task2.CalculatorController.Context;

import java.util.LinkedList;

public interface Operation {
    public void calculation(Context context, LinkedList<String> listValue) throws Exception;
    public default void checkCorrectArgs(@NotNull LinkedList<String> listValue) {
        if(!listValue.isEmpty()) {
            throw new IllegalArgumentException("Size args is not correct");
        }
    }
}
