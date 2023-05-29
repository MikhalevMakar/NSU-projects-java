package ru.nsu.org.mikhalev.clients.view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import org.jetbrains.annotations.NotNull;
import ru.nsu.org.mikhalev.universal_utile_class.Message;
import ru.nsu.org.mikhalev.universal_utile_class.create_command.ContextCommand;

public class MessageItem extends VBox {
    private Label logInLabel;
    private final Label contentLabel;

    private Pos switchColorCommand(@NotNull Message<String> message, String logIn) {
        Pos alignment = null;

        if(message.getTypeMessage().equals(ContextCommand.getMESSAGE())) {
            alignment = message.getLogIn().equals(logIn) ? Pos.CENTER_RIGHT : Pos.CENTER_LEFT;
            logInLabel.setStyle ("-fx-text-fill: #346ead;");
            contentLabel.setStyle("-fx-text-fill: white;");
            getChildren().addAll(logInLabel, contentLabel);

        }
        else if(message.getTypeMessage().equals(ContextCommand.getUSER_EXIT())) {
            alignment = Pos.CENTER;
            contentLabel.setStyle("-fx-text-fill: red;");
            getChildren().addAll(contentLabel);

        }
        else if(message.getTypeMessage().equals(ContextCommand.getBROAD_CAST_NEW_USER())) {
            alignment = Pos.CENTER;
            contentLabel.setStyle("-fx-text-fill: #00b13c;");
            getChildren().addAll(contentLabel);
        }

        return alignment;
    }

    public MessageItem(@NotNull Message < String > message,String logIn) {
        logInLabel = new Label (message.getLogIn ());
        contentLabel = new Label (message.getContent ());

        Pos alignment = switchColorCommand(message, logIn);

        setAlignment (alignment);
        //getStyleClass().add("message-item");
    }

    public void setLogIn(String logIn) {
        logInLabel.setText(logIn);
    }

    public void setContent(String content) {
        contentLabel.setText(content);
    }
}