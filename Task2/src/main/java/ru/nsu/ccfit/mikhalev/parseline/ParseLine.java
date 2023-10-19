package ru.nsu.ccfit.mikhalev.parseline;

import org.jetbrains.annotations.NotNull;
import ru.nsu.ccfit.mikhalev.checkerdouble.CheckerDouble;
import ru.nsu.ccfit.mikhalev.exceptions.NumericArguments;

import java.util.LinkedList;
import java.util.List;

public class ParseLine {
    private  LinkedList<String> listValue;
    String nameCommand;
    public void parse(@NotNull String line) throws NumericArguments {
        listValue = new LinkedList<>(List.of(line.split("\\s")));
        nameCommand = listValue.getFirst();

        if(CheckerDouble.IsNumberFormat(nameCommand))
            throw new NumericArguments ("Invalid command entry");
        listValue.removeFirst();
    }

    public LinkedList getListValue() {
        return listValue;
    }

    public String getNameCommand() {
        return nameCommand;
    }
}
