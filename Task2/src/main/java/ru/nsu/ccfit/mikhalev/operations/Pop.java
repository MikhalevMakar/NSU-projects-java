package ru.nsu.ccfit.mikhalev.operations;

import org.jetbrains.annotations.NotNull;
import ru.nsu.ccfit.mikhalev.calculatorcontroller.Context;

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
