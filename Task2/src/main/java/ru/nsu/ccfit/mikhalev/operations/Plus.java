package ru.nsu.ccfit.mikhalev.operations;

import org.jetbrains.annotations.NotNull;
import ru.nsu.ccfit.mikhalev.calculatorcontroller.Context;
import ru.nsu.ccfit.mikhalev.checkerdouble.CheckerDouble;
import ru.nsu.ccfit.mikhalev.exceptions.FormatDouble;
import ru.nsu.ccfit.mikhalev.exceptions.OperationException;

import java.util.LinkedList;

@CommandAnnotation
public class Plus implements Operation {
    @Override
    public void calculation(@NotNull Context context, LinkedList<String> listValue) {
        LOGGER.info ("Check correct list args for command " + this.getClass ());
        checkCorrectArgs (listValue);
        try {
            Double v1 = context.popValue();
            Double v2 = context.popValue();
            Double result = v1 + v2;

            if(!CheckerDouble.IsNumberFormat (result.toString()) ) {
                throw new FormatDouble ("Number  format is not correct, PLUS command");
            }
            context.pushValue(result.toString());
        } catch(OperationException numericArguments) {
            LOGGER.error ("Failed to pop element from stack" + numericArguments.getMessage ());
            throw new OperationException("Failed to pop element from stack" +
                                         numericArguments.getStackTrace() +
                                         this.getClass ());
        }
    }
}
