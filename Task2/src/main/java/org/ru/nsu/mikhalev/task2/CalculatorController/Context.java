package org.ru.nsu.mikhalev.task2.CalculatorController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.Stack;
import java.util.Map;

import org.apache.commons.cli.ParseException;
import org.ru.nsu.mikhalev.task2.ParseCommandLine.*;
import org.ru.nsu.mikhalev.task2.CheckerDouble.*;
public class Context {
    private final Map<String, Double> mapDefineValue;
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
    public Reader getReader() { return input;}
    public Double popValue() {
        if(stackDouble.size() == 0) {
            throw new IllegalArgumentException("not correct");
        }
        return stackDouble.pop();
    }

    public void addDefineValue(String parameter, String value) {
        if(CheckerDouble.IsNumberFormat(parameter) ||
           !CheckerDouble.IsNumberFormat(value)) {
            throw new IllegalArgumentException("format not correct");
        }
        mapDefineValue.put(parameter, Double.valueOf(value));
    }

    public Double getDefineValue(String param) throws Exception {
        Iterator iterator  = mapDefineValue.entrySet().iterator();

        for(var entry: mapDefineValue.entrySet()) {
            if(entry.getKey().equals(param)) return entry.getValue();
        }
        throw new Exception("Not found element\n");
    }

    public Double peekValueStack() {
        try {
            return stackDouble.peek();
        } catch(IllegalArgumentException e) {
            throw new IllegalArgumentException("not correct stack");
        }
    }
}
