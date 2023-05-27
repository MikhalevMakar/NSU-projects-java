package ru.nsu.org.mikhalev.clients.view;


import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import ru.nsu.org.mikhalev.clients.controller.Controller;
import ru.nsu.org.mikhalev.universal_utile_class.Message;
import ru.nsu.org.mikhalev.universal_utile_class.create_command.ContextCommand;
import ru.nsu.org.mikhalev.universal_utile_class.exceptions.ExcIO;

import java.io.IOException;
import java.util.List;

@Log4j2
public class ControllerView {

    private final ObservableList<String> observableList = FXCollections.observableArrayList();

    private static Controller controller;

    @FXML
    private TextField inputText;

    @FXML
    private ListView<String> historyMessageView;

    @FXML
    private ListView<String> participantsListView;

    @FXML
    private TextField nameUser;

    public static void registration(final Controller controllerUser){
        controller = controllerUser;
    }

    public void displayList(@NotNull List<String> list) {

        log.info("Show list on display");

        Platform.runLater(() -> {
            observableList.setAll(list);
            participantsListView.setItems(observableList);
            participantsListView.refresh();
        });
    }

    public void updateHistoryMessage(List<Message<String>> historyMessage) {
        log.info("update messages");

        Platform.runLater(() -> {
            for (var message : historyMessage) {
                historyMessageView.getItems().add(message.getLogIn() + " : " + message.getContent());
            }
            historyMessageView.refresh();
        });
    }

    public void handleEnterKeyPressed() {
        inputText.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER && (inputText.getText().length() != 0)) {
                log.info("Send text " + inputText.getText());
                try {
                    controller.getUser().sendToServer(new Message<>(ContextCommand.getMESSAGE(), inputText.getText()));
                    inputText.clear();
                } catch (IOException e) {
                    throw new ExcIO("Error i | o" + e.getMessage());
                }
            }
        });
    }

    public void buttonConnect() {
        log.info("Action button connect " + nameUser.getText());
        controller.tryLogin(nameUser.getText());
    }
}
