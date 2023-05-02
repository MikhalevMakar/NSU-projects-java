package ru.nsu.org.mikhalev;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.cli.ParseException;
import ru.nsu.org.mikhalev.factory.Factory;
import ru.nsu.org.mikhalev.proces_input.ParseFileJSON;
import ru.nsu.org.mikhalev.view.GenerateMainMenu;

import java.io.IOException;

@Log4j2
public class Main {
    public static void main(String[] args) throws ParseException, IOException {
        log.info("Create parseFileJSON");
        ParseFileJSON parseFileJSON = new ParseFileJSON(args);

        log.info("Create factory");
        Factory factory = new Factory(parseFileJSON.getLinkInfoFactory());

        log.info("Create generateMainMenu");
        new GenerateMainMenu(parseFileJSON.getLinkGuiComponents(), factory);
    }
}





