package ru.nsu.org.mikhalev.clients.view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.jetbrains.annotations.NotNull;
import ru.nsu.org.mikhalev.universal_utile_class.Message;

public class MessageItem extends VBox {
    private final Label logInLabel;
    private final Label contentLabel;

    public MessageItem(@NotNull Message<String> message, Pos alignment) {
        logInLabel = new Label(message.getLogIn());
        contentLabel = new Label(message.getContent());

        logInLabel.setStyle("-fx-text-fill: #346ead;");
        contentLabel.setStyle("-fx-text-fill: white;");

        getChildren().addAll(logInLabel, contentLabel);
        setAlignment(alignment);
        //getStyleClass().add("message-item");
    }

    public void setLogIn(String logIn) {
        logInLabel.setText(logIn);
    }

    public void setContent(String content) {
        contentLabel.setText(content);
    }
}