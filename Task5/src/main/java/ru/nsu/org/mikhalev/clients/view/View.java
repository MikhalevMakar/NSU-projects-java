package ru.nsu.org.mikhalev.clients.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.IOException;


@Log4j2
public class View {

    private Stage stage;

    private Pane root;

    @Getter
    private ControllerView controllerView;
    private final Text error = new Text(250, 450, "");

    public View() {}
    public View(final Stage stage) {
        this.stage = stage;

        Font font = Font.font("System", 15);
        controllerView = new ControllerView(stage);
        error.setFont(font);
        error.setStyle("-fx-fill: red;");
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
        stage.setResizable(false);
        stage.show();
    }

    public void generateChat(String linkFXML) throws IOException {
        log.info("Generate chat");

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(new File(linkFXML).toURI().toURL());

        root = fxmlLoader.load();

        controllerView = fxmlLoader.getController();

        stage.setScene(new Scene(root));
        stage.show();
    }
}