package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader (HelloApplication.class.getResource ("hello-view.fxml"));
        Scene scene = new Scene (fxmlLoader.load (), 320, 240);
        stage.setTitle ("Hello!");
        stage.setScene (scene);
        Image image = new Image (new FileInputStream ("../demo/src/main/resources/gameMenu.png"));
        ImageView imageView = new ImageView (image);
       s

        stage.show ();
    }

    public static void main(String[] args) {
        launch ();
    }
}