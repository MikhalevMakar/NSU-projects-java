package org.ru.nsu.mikhalev.task2.CalculatorController;

import java.io.*;
import java.text.Format;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.Stack;
import java.util.Map;


import org.apache.commons.cli.ParseException;
import org.ru.nsu.mikhalev.task2.Exceptions.FormatDouble;
import org.ru.nsu.mikhalev.task2.Exceptions.OperationException;
import org.ru.nsu.mikhalev.task2.ParseCommandLine.*;
import org.ru.nsu.mikhalev.task2.CheckerDouble.*;
import javax.inject.Inject;
public class Context {

    @Inject
    private final  Map<String, Double> mapDefineValue;
    @Inject
    private final Stack<Double> stackDouble;
    private final Reader input;

    public Context(String[] args) throws ParseException, FileNotFoundException {
        mapDefineValue = new TreeMap<>();
        stackDouble = new Stack<>();

        ParseCommandLine.searchCommandLine(args);
        input = new InputStreamReader(new FileInputStream(ParseCommandLine.getFileInput()));
    }
    public void pushValue(String value) {
        if(CheckerDouble.IsNumberFormat(value))
            stackDouble.push(Double.valueOf(value));
    }
    public Reader getReader() { return input; }
    public Double popValue() {
        if(stackDouble.size() == 0) {
            throw new FormatDouble("Stack is empty, " +
                    "an exception was thrown when the pop element was raised");
        }
        return stackDouble.pop();
    }

    public void addDefineValue(String parameter, String value) {
        if(CheckerDouble.IsNumberFormat(parameter) ||
           !CheckerDouble.IsNumberFormat(value)) {
            throw new FormatDouble("Format not correct, when add define value");
        }
        mapDefineValue.put(parameter, Double.valueOf(value));
    }

    public Double getDefineValue(String param) {
        Iterator iterator  = mapDefineValue.entrySet().iterator();

        for(var entry: mapDefineValue.entrySet()) {
            if(entry.getKey().equals(param)) return entry.getValue();
        }
        throw new OperationException("Element was not found, when try to" +
                " get define value");
    }

    public Double peekValueStack() {
        try {
            return stackDouble.peek();
        } catch(OperationException e) {
            throw new OperationException ("Failed to pop element from stack" + e.getStackTrace());
        }
    }

    public void close() throws IOException {
        input.close();
    }
}
