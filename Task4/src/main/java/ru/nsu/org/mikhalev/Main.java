package ru.nsu.org.mikhalev;

import org.apache.commons.cli.ParseException;
import ru.nsu.org.mikhalev.exceptions.ExcParseFileJSON;

public class Main {

    public static void main(String[] args) throws ParseException, ExcParseFileJSON {
        ParseFileJSON parseFileJSON = new ParseFileJSON(args);
        
    }
}

//    public static void main(String[] args) {
//        //new GenerateMainMenu();
//        new ru.nsu.org.mikhalev.view.SupplierSpeedSlider();
//    }