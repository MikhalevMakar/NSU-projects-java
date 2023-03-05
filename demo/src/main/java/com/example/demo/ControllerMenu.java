package com.example.demo;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ControllerMenu {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button click;

    @FXML
    private Button music;

    @FXML
    private Button point;

    @FXML
    private Button rules;

    @FXML
    private Button startGame;

    @FXML
    void initialize() {
        assert click != null : "fx:id=\"click\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert music != null : "fx:id=\"music\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert point != null : "fx:id=\"point\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert rules != null : "fx:id=\"rules\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert startGame != null : "fx:id=\"startGame\" was not injected: check your FXML file 'hello-view.fxml'.";


    }

}
