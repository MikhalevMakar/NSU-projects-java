package org.ru.nsu.mikhalev.task2;

import org.apache.commons.cli.ParseException;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.ru.nsu.mikhalev.task2.CalculatorController.CalculatorController;
import java.io.IOException;

//-i=../Task2/src/main/resources/inputFile.txt
public class Main {
    private  static  final Logger LOGGER = Logger.getLogger(Main.class.getName());
    public static void main(String @NotNull [] args) throws IOException, ParseException {
        LOGGER.info ("Call calculator controller");
        CalculatorController calculator;
        if(args.length > 0) {
            calculator = new CalculatorController (args);
        } else {
            calculator = new CalculatorController ();
        }
        calculator.launch();
    }
}