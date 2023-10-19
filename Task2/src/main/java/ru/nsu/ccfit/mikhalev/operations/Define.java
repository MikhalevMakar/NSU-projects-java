package ru.nsu.ccfit.mikhalev.operations;

import java.util.*;
import org.jetbrains.annotations.NotNull;
import ru.nsu.ccfit.mikhalev.calculatorcontroller.Context;
import ru.nsu.ccfit.mikhalev.checkerdouble.CheckerDouble;
import ru.nsu.ccfit.mikhalev.exceptions.FormatDouble;
import ru.nsu.ccfit.mikhalev.exceptions.OperationException;

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



