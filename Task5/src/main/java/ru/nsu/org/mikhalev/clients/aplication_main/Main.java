package ru.nsu.org.mikhalev.clients.aplication_main;

import javafx.application.Application;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;
import ru.nsu.org.mikhalev.clients.controller.Controller;
import ru.nsu.org.mikhalev.clients.file_management.LinksToConfiguration;
import ru.nsu.org.mikhalev.clients.view.View;
import ru.nsu.org.mikhalev.universal_utile_class.exceptions.ExcInvalidArg;
import ru.nsu.org.mikhalev.universal_utile_class.exceptions.ExcParseFileJSON;
import ru.nsu.org.mikhalev.universal_utile_class.file_manager.ParseConfiguration;


@Log4j2
public class Main  extends Application {

    private static final int CORRECT_NUMBER_ARGS = 1;

    private static final int BEGIN_INDEX = 0;

    private static LinksToConfiguration linksResources;

    public static void main(String... args) throws ExcParseFileJSON {
        log.info("Start program");

        log.info("Check args" );
        if(!ParseConfiguration.isCorrectArgs(CORRECT_NUMBER_ARGS, args)) {
            log.error("usage: <link to JSON FILE>");
            throw new ExcInvalidArg ("usage: <link to JSON FILE>");
        }

        linksResources = ParseConfiguration.parseConfigurationJSON(
                                                        LinksToConfiguration.class, args[BEGIN_INDEX]);
        assert linksResources != null;

        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

        View view = new View(stage);

        view.generateLogin(linksResources.getLoginFXML());

        view.registration(new Controller(view, linksResources));
    }

}

