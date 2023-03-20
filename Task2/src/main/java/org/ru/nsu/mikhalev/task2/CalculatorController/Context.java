package org.ru.nsu.mikhalev.task2.CalculatorController;

import java.io.*;
import java.util.*;
import org.apache.commons.cli.ParseException;
import org.apache.log4j.Logger;
import org.ru.nsu.mikhalev.task2.CheckerDouble.CheckerDouble;
import org.ru.nsu.mikhalev.task2.Exceptions.FormatDouble;
import org.ru.nsu.mikhalev.task2.Exceptions.OperationException;
import org.ru.nsu.mikhalev.task2.ParseCommandLine.ParseCommandLine;

import javax.inject.Inject;

public class Context implements Closeable {
    @Inject
    private final  Map<String, Double> mapDefineValue = new TreeMap<>();
    @Inject
    private final Stack<Double> stackDouble = new Stack<>();
    private final Reader input;

    private static final Logger LOGGER = Logger.getLogger(Context.class.getName());
    public Context() {
        LOGGER.debug ("Call constructor Context input console");
        input = new InputStreamReader(System.in);

    }
    public Context(String[] args) throws ParseException, FileNotFoundException {
        LOGGER.debug ("Call constructor Context input file");
        ParseCommandLine.searchCommandLine(args);
        input = new InputStreamReader(new FileInputStream(ParseCommandLine.getFileInput()));
    }
    public void pushValue(String value) {
        LOGGER.debug ("Push value in Stack<Double>");
         if (CheckerDouble.IsNumberFormat (value))
             stackDouble.push (Double.valueOf (value));
         else
             throw new FormatDouble ("It's not a number");
    }
    public Reader getReader() { return input; }
    public Double popValue() {
        LOGGER.debug ("Try pop value in stack");
        if(stackDouble.size() == 0) {
            LOGGER.error ("Stack<Double> is empty");
            throw new FormatDouble ("Stack is empty");
        }
        return stackDouble.pop();
    }

    public boolean findValueMap(String value) {
        return mapDefineValue.containsKey(value);
    }

    public void addDefineValue(String parameter, String value) {
        if(CheckerDouble.IsNumberFormat(parameter) ||
           !CheckerDouble.IsNumberFormat(value)) {
            LOGGER.error ("Parameter or value was not correct");
            throw new FormatDouble ("Format not correct");
        }
        mapDefineValue.put(parameter, Double.valueOf(value));
    }

    public Double getDefineValue(String param) {
        Iterator iterator  = mapDefineValue.entrySet().iterator();

        for(var entry: mapDefineValue.entrySet()) {
            if(entry.getKey().equals(param)) return entry.getValue();
        }
        throw new OperationException ("Element was not found");
    }

    public Double peekValueStack() {
        try {
            return stackDouble.peek();
        } catch(EmptyStackException e) {
            LOGGER.warn (e);
            throw new EmptyStackException ();
        }
    }
    @Override
    public void close() throws IOException {
        LOGGER.debug("Close Reader");
        input.close();
    }
}
