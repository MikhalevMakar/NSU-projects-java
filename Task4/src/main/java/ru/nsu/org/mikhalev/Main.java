package ru.nsu.org.mikhalev;

import org.apache.commons.cli.ParseException;
import ru.nsu.org.mikhalev.proces_input_dlogic.ParseFileJSON;
import ru.nsu.org.mikhalev.properties_read.FactoryReader;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws ParseException, IOException{
        ParseFileJSON parseFileJSON = new ParseFileJSON(args);

        FactoryReader.read(parseFileJSON.getLinkInfoFactory());
    }
}

//    public static void main(String[] args) {
//        //new GenerateMainMenu();
//        new ru.nsu.org.mikhalev.view.SupplierSpeedSlider();
//    }