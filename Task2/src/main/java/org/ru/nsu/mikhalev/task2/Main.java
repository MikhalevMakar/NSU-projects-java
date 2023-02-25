package org.ru.nsu.mikhalev.task2;

import org.apache.commons.cli.ParseException;
import org.apache.log4j.Logger;
import org.ru.nsu.mikhalev.task2.CalculatorController.CalculatorController;

import java.io.IOException;


public class Main {
    private  static  final Logger LOGGER = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) throws IOException, ParseException {
        LOGGER.info ("Chat gpt");
        LOGGER.info ("Start process");
        LOGGER.info ("Call calculator controller");
        CalculatorController calculator = new CalculatorController (args);
        calculator.launch ();
    }
}