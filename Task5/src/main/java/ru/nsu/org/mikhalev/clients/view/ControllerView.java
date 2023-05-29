package ru.nsu.org.mikhalev.clients.view;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import lombok.extern.log4j.Log4j2;
import ru.nsu.org.mikhalev.clients.controller.Controller;
import ru.nsu.org.mikhalev.universal_utile_class.Message;
import ru.nsu.org.mikhalev.universal_utile_class.create_command.ContextCommand;
import ru.nsu.org.mikhalev.universal_utile_class.exceptions.ExcIO;

import java.io.IOException;
import java.util.List;

@Log4j2
public class ControllerView {

    private static Controller controller;

    @FXML
    private TextField inputText;

    @FXML
    private TextField countConnectUser;

    @FXML
    private ListView<MessageItem> historyMessageView;

    @FXML
    private ListView<Label> participantsListView;

    @FXML
    private TextField nameUser;

    public ControllerView() {}
    public ControllerView(@NotNull Stage stage) {
        stage.setOnCloseRequest(event -> handleWindowClose());
    }

    private void handleWindowClose() {
        try {
            Message<String> message = new Message<>(ContextCommand.getUSER_EXIT(),
                                                    String.format("User: %s has left the chat", controller.getUser().getLogIn()));

            message.setLogIn(controller.getUser().getLogIn());

            controller.getUser().sendToServer(message);
            Platform.exit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void registration(final Controller controllerUser){
        controller = controllerUser;
    }

    public void displayList(String nameUser, @NotNull List<String> list) {
        log.info("Show list on display");

        Platform.runLater(() -> {
            participantsListView.getItems().clear();
            countConnectUser.setText(String.format("%d", list.size()));
            for (String user : list) {
                Label label = new Label(user);
                if (nameUser.equals(user)) {
                    label.setStyle("-fx-text-fill: #346ead;");
                }
                participantsListView.getItems().add(label);
            }
        });
    }

    public void updateHistoryMessage(List<Message<String>> historyMessage) {

        Platform.runLater(() -> {
            log.info("Update messages");

            for (var message : historyMessage) {
                //MessageItem messageItem = new MessageItem(message);

                MessageItem messageItem = new MessageItem(message, controller.getUser().getLogIn());

                historyMessageView.getItems().add(messageItem);
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
