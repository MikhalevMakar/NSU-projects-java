package org.ru.nsu.mikhalev.task2;

import java.util.HashMap;
import java.util.logging.*;
import java.util.Map;

public class Calculator {
private static Map<String, Double> map;
    static {
        map = new HashMap<>();
    }
    private static final Logger log = Logger.getLogger(Calculator.class.getName());
    public Calculator(String... args) {

    }
}


//log.log(Level.SEVERE, "Exception: ", ex);