package com.example.demo1;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    Button login;
    @FXML
    TextField txtUser;
   ;
    @FXML
    public void initialize() throws IOException {

    }

    public void onLogin() throws IOException {

        Cliente.nickName=txtUser.getText();
      /*  Stage stage= new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Cliente.class.getResource("cliente-view.fxml"));*/
        Cliente.changeScene("cliente-view.fxml");
        // Scene scene = new Scene(fxmlLoader.load(), 600, 400);
      /*  stage.setTitle("SALA DE CHAT");
        stage.setScene(scene);
        stage.setOnHidden(e -> ClienteController.shutdown());
        stage.show();*/
    }

    public static void shutdown() {
        // cleanup code here...
        System.out.println("Stop login");
        System.exit(0);
        Platform.exit();

    }
}
