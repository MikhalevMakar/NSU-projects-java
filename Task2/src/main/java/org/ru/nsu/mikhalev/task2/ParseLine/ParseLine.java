package org.ru.nsu.mikhalev.task2.ParseLine;

import org.jetbrains.annotations.NotNull;
import org.ru.nsu.mikhalev.task2.CheckerDouble.CheckerDouble;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ParseLine {
    private  LinkedList<String> listValue;
    String nameCommand;
    public void parse(@NotNull String line) {
        listValue = new LinkedList<>(List.of(line.split("\\s")));
        nameCommand = listValue.getFirst();

        if(CheckerDouble.IsNumberFormat(nameCommand))
            throw new IllegalArgumentException("Invalid command entry");

        listValue.removeFirst();
    }

    public LinkedList getListValue() {
        return listValue;
    }

    public String getNameCommand() {
        return nameCommand;
    }
}
