package ru.nsu.org.mikhalev.clients.aplication_main;

import javafx.application.Application;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;
import ru.nsu.org.mikhalev.clients.controller.Controller;
import ru.nsu.org.mikhalev.clients.view.ControllerView;


@Log4j2
public class Main extends Application {

    public static void main(String... args) {
        log.info("Start program");

        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        ControllerView controllerView = new ControllerView();
        controllerView.generateView(stage);
        ControllerView.registration(new Controller(controllerView));
    }
}

