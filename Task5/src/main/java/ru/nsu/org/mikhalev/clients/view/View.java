package ru.nsu.org.mikhalev.clients.view;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import ru.nsu.org.mikhalev.clients.controller.Controller;

import java.io.File;
import java.io.IOException;
import java.util.List;


@Log4j2
public class View {

    private Stage stage;

    private Pane root;

    /**
     * Creates an instance of Text on the given coordinates containing the given string.
     * Params: x – the horizontal position of the text
     *         y – the vertical position of the text
     *         text – text to be contained in the instance
     */
    private final Text error = new Text(250, 450, "");

    private static Controller controller;

    @FXML
    private ListView<String> participantsListView;

    @FXML
    private TextField nameUser;

    private final ObservableList<String> observableList = FXCollections.observableArrayList();

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

    public View() {}

    public View(final Stage stage) {
        this.stage = stage;

        Font font = Font.font("System", 15);
        error.setFont(font);
        error.setStyle("-fx-fill: red;");
    }

    public void buttonConnect() {
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
        stage.setResizable(false);
        stage.show();
    }

    public void generateChat(String linkFXML) throws IOException {
        log.info("Generate chat");

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(new File(linkFXML).toURI().toURL());

        root = fxmlLoader.load();

        stage.setScene(new Scene(root));
        stage.show();
    }
}