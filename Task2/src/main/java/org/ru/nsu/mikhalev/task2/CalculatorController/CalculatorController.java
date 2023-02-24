package org.ru.nsu.mikhalev.task2.CalculatorController;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.cli.ParseException;
import org.ru.nsu.mikhalev.task2.LoaderFactory.LoaderFactory;
import org.ru.nsu.mikhalev.task2.Operations.Operation;
import org.ru.nsu.mikhalev.task2.ParseLine.ParseLine;

public class CalculatorController implements Closeable {
    //private static final Logger LOGGER = Logger.getLogger(CalculatorController.class.getName());
    private Context context;
    private final ParseLine parseLine;
    private  String line;
    private Operation operation;
    public CalculatorController(String...  args) throws ParseException, FileNotFoundException {
        //LOGGER.info("Create context");
        context = new Context(args);
        parseLine = new ParseLine();
    }

    public void launch() {
        try(BufferedReader br = new BufferedReader(context.getReader())) {
            LoaderFactory loaderFactory = new LoaderFactory();
            while ((line = br.readLine()) != null) {

               if(line.charAt(0) == '#') continue;

               parseLine.parse(line);

               operation = loaderFactory.getFilePathToSave (parseLine.getNameCommand ());

               operation.calculation(context, parseLine.getListValue());

            }
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public void close() throws IOException {
        context.close();
    }
}
