package org.ru.nsu.mikhalev.task2.CalculatorController;

import java.io.*;
import java.text.Format;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.Stack;
import java.util.Map;


import org.apache.commons.cli.ParseException;
import org.apache.log4j.Logger;
import org.ru.nsu.mikhalev.task2.Exceptions.FormatDouble;
import org.ru.nsu.mikhalev.task2.Exceptions.OperationException;
import org.ru.nsu.mikhalev.task2.ParseCommandLine.*;
import org.ru.nsu.mikhalev.task2.CheckerDouble.*;
import javax.inject.Inject;
public class Context implements Closeable {
    @Inject
    private final  Map<String, Double> mapDefineValue;
    @Inject
    private final Stack<Double> stackDouble;
    private final Reader input;
    {
        mapDefineValue = new TreeMap<>();
        stackDouble = new Stack<>();
    }
    private static final Logger LOGGER = Logger.getLogger(Context.class.getName());
    public Context() {
        LOGGER.info ("Call constructor Context input console");
        input = new InputStreamReader(System.in);

    }
    public Context(String[] args) throws ParseException, FileNotFoundException {
        LOGGER.info ("Call constructor Context input file");
        ParseCommandLine.searchCommandLine(args);
        input = new InputStreamReader(new FileInputStream(ParseCommandLine.getFileInput()));
    }
    public void pushValue(String value) {
        LOGGER.info ("Push value in Stack<Double>");
        if(CheckerDouble.IsNumberFormat(value))
            stackDouble.push(Double.valueOf(value));
    }
    public Reader getReader() { return input; }
    public Double popValue() {
        LOGGER.info ("Try pop value in stack");
        if(stackDouble.size() == 0) {
            LOGGER.error ("Stack<Double> is empty");
            throw new FormatDouble("Stack is empty, " +
                    "an exception was thrown when the pop element was raised");
        }
        return stackDouble.pop();
    }

    public void addDefineValue(String parameter, String value) {
        if(CheckerDouble.IsNumberFormat(parameter) ||
           !CheckerDouble.IsNumberFormat(value)) {
            LOGGER.error ("Parameter or value was not correct");
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
            LOGGER.warn (e);
            throw new OperationException ("Failed to pop element from stack" + e.getStackTrace());
        }
    }
    @Override
    public void close() throws IOException {
        LOGGER.info("Close Reader");
        input.close();
    }
}
