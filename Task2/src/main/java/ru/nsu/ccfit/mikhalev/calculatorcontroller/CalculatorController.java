package ru.nsu.ccfit.mikhalev.calculatorcontroller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import org.apache.commons.cli.ParseException;
import ru.nsu.ccfit.mikhalev.loaderfactory.LoaderFactory;
import ru.nsu.ccfit.mikhalev.operations.Operation;
import ru.nsu.ccfit.mikhalev.parseline.ParseLine;
import org.apache.log4j.*;

public class CalculatorController {
    private static final Logger LOGGER = Logger.getLogger (CalculatorController.class.getName ());
    private final ParseLine parseLine;
    private String line;
    private Operation operation;

    private final Context context;

    {
        parseLine = new ParseLine ();
    }
    public CalculatorController() throws ParseException, FileNotFoundException {
        LOGGER.debug ("Calculator controller constructor empty");
        context = new Context();
    }
    public CalculatorController(String[] args) throws ParseException, FileNotFoundException {
        LOGGER.debug ("Calculator controller constructor args");
        context = new Context(args);
    }

    public void launch() {
        LOGGER.info ("Create buffered reader");
        try (context) {
            BufferedReader br = new BufferedReader(context.getReader());
            LOGGER.debug ("Create load factory");
            LoaderFactory loaderFactory = new LoaderFactory ();

            while ((line = br.readLine ()) != null) {
                if(line.toUpperCase ().equals ("EXIT")) return;

                LOGGER.debug ("Read command and parameter " + line);
                if (line.charAt (0) == '#') continue;

                LOGGER.debug("Parse read line");
                parseLine.parse (line);

                operation = loaderFactory.createInstanceClass (parseLine.getNameCommand ());
                LOGGER.debug ("Create objet " + operation.getClass ());

                operation.calculation (context, parseLine.getListValue ());
                LOGGER.debug ("Calc command " + parseLine.getNameCommand ());
            }
        } catch (Exception ex) {
            throw new RuntimeException (ex);
        }
    }
}
