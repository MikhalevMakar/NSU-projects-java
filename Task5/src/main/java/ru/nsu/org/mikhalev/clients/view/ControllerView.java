package ru.nsu.org.mikhalev.clients.view;


import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import ru.nsu.org.mikhalev.clients.controller.Controller;

import java.util.List;

@Log4j2
public class ControllerView {


    private final ObservableList<String> observableList = FXCollections.observableArrayList();

    private static Controller controller;

    @FXML
    private ListView<String> participantsListView;

    @FXML
    private TextField nameUser;

    public static void registration(final Controller controllerUser){
        controller = controllerUser;
    }

    public void displayList(@NotNull List<String> list) {

        log.info("Show list on display");

        for(var v : list) {
            System.out.println(v);
        }

        Platform.runLater(() -> {
            observableList.setAll(list);
            participantsListView.setItems(observableList);
            participantsListView.refresh();
        });
    }

    public void buttonConnect() {
        log.info("Action button connect " + nameUser.getText());
        controller.tryLogin(nameUser.getText());
    }

}
