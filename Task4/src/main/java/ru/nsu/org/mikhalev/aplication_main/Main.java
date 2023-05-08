package ru.nsu.org.mikhalev.aplication_main;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.cli.ParseException;
import ru.nsu.org.mikhalev.factory.Factory;
import ru.nsu.org.mikhalev.proces_input.ParseFileJSON;
import ru.nsu.org.mikhalev.view.GenerateMainMenu;

import java.io.IOException;


/*
 * Initially, the factory menu is started
 * The menu has a chat that responds to the following command : start
 *                                                              help
 *                                                              stop
 *
 */
@Log4j2
public class Main {
    public static void main(String[] args) throws ParseException, IOException {
        log.info("Create parseFileJSON");
        //parse the link to the file hierarchy of the program.
        ParseFileJSON parseFileJSON = new ParseFileJSON(args);

        log.info("Create factory");
        Factory factory = new Factory(parseFileJSON.getLinkInfoFactory());

        log.info("Create generateMainMenu");
        new GenerateMainMenu(parseFileJSON.getLinkGuiComponents(), factory);
    }
}





