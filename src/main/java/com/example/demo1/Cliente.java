package com.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;

public class Cliente extends Application {

    public static String nickName;
    public static Stage stagePrimary;
    @Override
    public void start(Stage stage) throws IOException {
        this.stagePrimary=stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Cliente.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stagePrimary.setTitle("LOGIN");
        stagePrimary.setScene(scene);
        stagePrimary.setUserData(stage);
        stagePrimary.setOnHidden(e -> LoginController.shutdown());
        stagePrimary.show();

    }

    public static void changeScene(String fxml) throws IOException {
        stagePrimary.setTitle("SALA DE CHAT");

        Parent pane = FXMLLoader.load(Cliente.class.getResource(fxml));
        pane.resize(600,400);

        stagePrimary.getScene().setRoot(pane);

    }

    public static void main(String[] args) {
        launch();
    }



}