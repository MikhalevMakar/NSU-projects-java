package org.ru.nsu.mikhalev.task2;

import java.io.*;
import org.apache.commons.cli.ParseException;
import org.ru.nsu.mikhalev.task2.CalculatorController.CalculatorController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private  static  final Logger LOGGER = LoggerFactory.getLogger(Main.class.getName());
    public static void main(String[] args) throws IOException, ParseException {
        //LOGGER.info ("Start process");
//        LOGGER.info ("Call calculator controller");
        CalculatorController calculator = new CalculatorController (args);
        calculator.launch ();
    }
}