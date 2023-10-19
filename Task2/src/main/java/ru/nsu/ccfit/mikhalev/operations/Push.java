package ru.nsu.ccfit.mikhalev.operations;

import org.jetbrains.annotations.NotNull;
import ru.nsu.ccfit.mikhalev.calculatorcontroller.Context;
import ru.nsu.ccfit.mikhalev.checkerdouble.CheckerDouble;
import ru.nsu.ccfit.mikhalev.exceptions.FormatDouble;
import ru.nsu.ccfit.mikhalev.exceptions.NumericArguments;


import java.util.LinkedList;
@CommandAnnotation
public class Push implements Operation{
    @Override
    public void calculation(Context context, @NotNull LinkedList<String> listValue) throws Exception {
        LOGGER.debug ("Check correct size list args");
        if (listValue.size () != 1) {
            LOGGER.error ("Size list was not correct " + this.getClass ());
            throw new NumericArguments ("Incorrect number of arguments");
        }

        LOGGER.debug ("Push value in stack");
        if(CheckerDouble.IsNumberFormat(listValue.getFirst())) {
            context.pushValue(listValue.getFirst());
        } else if (context.findValueMap (listValue.getFirst())){
            context.pushValue(context.getDefineValue(listValue.getFirst()).toString());
        } else {
            throw new FormatDouble ("No such symbol was found");
        }
    }
}
