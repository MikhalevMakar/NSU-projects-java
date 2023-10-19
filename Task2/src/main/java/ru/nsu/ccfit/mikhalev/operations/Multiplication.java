package ru.nsu.ccfit.mikhalev.operations;

import org.jetbrains.annotations.NotNull;
import ru.nsu.ccfit.mikhalev.calculatorcontroller.Context;
import ru.nsu.ccfit.mikhalev.checkerdouble.CheckerDouble;
import ru.nsu.ccfit.mikhalev.exceptions.FormatDouble;
import ru.nsu.ccfit.mikhalev.exceptions.OperationException;

import java.util.LinkedList;
@CommandAnnotation
public class Multiplication implements Operation {
    @Override
    public void calculation(@NotNull Context context, LinkedList<String> listValue) {
        checkCorrectArgs (listValue);
        try {
            Double v1 = context.popValue();
            Double v2 = context.popValue();
            Double result = v1 * v2;

            if(!CheckerDouble.IsNumberFormat (result.toString()) ) {
                throw new FormatDouble ("Number  format is not correct, MULTIPLICATION command");
            }
            context.pushValue(result.toString());

        } catch(OperationException operationException) {
            throw new OperationException ("Incorrect number of arguments" + operationException.getMessage() + this.getClass ());
        }
    }
}
