package org.ru.nsu.mikhalev.task2;//package org.ru.nsu.mikhalev.task2;//package org.ru.nsu.mikhalev.task2;

import java.io.*;
import org.apache.commons.cli.ParseException;
import org.ru.nsu.mikhalev.task2.CalculatorController.CalculatorController;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.logging.*;
public class Main {

   // private  static  final Logger LOGGER = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) throws IOException, ParseException {
//        LOGGER.info ("Start process");
//        LOGGER.info ("Call calculator controller");
        CalculatorController calculator = new CalculatorController (args);
        calculator.launch ();
    }
}

