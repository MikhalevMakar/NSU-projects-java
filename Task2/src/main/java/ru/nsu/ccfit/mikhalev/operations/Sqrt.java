package ru.nsu.ccfit.mikhalev.operations;

import org.jetbrains.annotations.NotNull;
import ru.nsu.ccfit.mikhalev.calculatorcontroller.Context;
import ru.nsu.ccfit.mikhalev.exceptions.OperationException;

import java.util.LinkedList;

import static java.lang.Math.sqrt;
@CommandAnnotation
public class Sqrt implements Operation {
    @Override
    public void calculation(@NotNull Context context, LinkedList<String> listValue) {
        checkCorrectArgs (listValue);
        Double v;
        try {
            v = context.popValue();
            if(v < 0) {
                LOGGER.equals ("Value < 0 " + this.getClass ());
                throw new OperationException("The sqrt argument should: >= 0");
            }
            context.pushValue(v.toString());
        } catch(OperationException operationException) {
            LOGGER.warn ("Stack is empty" + operationException.getMessage ());
            throw  new OperationException("Stack is empty" + operationException.getStackTrace());
        }
        String pushValue = Double.valueOf(sqrt(v)).toString ();
        LOGGER.debug ("Push value "  + pushValue);
        context.pushValue(pushValue);
    }
}
