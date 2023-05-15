package ru.nsu.org.mikhalev.clients.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;
import ru.nsu.org.mikhalev.clients.controller.Controller;

import java.io.File;
import java.io.IOException;


@Log4j2
public class ControllerView {

    private Stage stage;

    private Pane root;

    /**
     * Creates an instance of Text on the given coordinates containing the given string.
     * Params: x – the horizontal position of the text
     *         y – the vertical position of the text
     *         text – text to be contained in the instance
     */
    private final Text error = new Text(100, 450, "");

    private static Controller controller;

    public static void registration(Controller _controller) {
        controller = _controller;
    }

    @FXML
    private TextField nameUser;

    public ControllerView() {}
    public ControllerView(final Stage stage) {
        this.stage = stage;

        Font font = Font.font("System", 15);
        error.setFont(font);
        error.setStyle("-fx-fill: red;");
    }

    public void buttonConnect() throws IOException, ClassNotFoundException {
        log.info("Action button connect " + nameUser.getText());
        controller.tryLogin(nameUser.getText());
    }
    
    public void printErrorMessage(String error) {
        this.error.setText(error);
    }
    
    public void generateLogin(String linkFXML) throws IOException {
        log.info("Generate login");

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(new File(linkFXML).toURI().toURL());
        root = fxmlLoader.load();

        root.getChildren().add(error);

        log.info("Load fxml");

        Scene scene = new Scene(root);
        stage.setTitle("General conversation");
        stage.setScene (scene);
        stage.setResizable (false);
        stage.show();
    }

    public void generateChat(String linkFXML) throws IOException {
        log.info("Generate chat");

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation (new File(linkFXML).toURI().toURL());

        root = fxmlLoader.load();
        stage.setScene(new Scene(root));
    }
}
