package com.example.iaorganiser;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class OrganiserApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(OrganiserApplication.class.getResource("login-view.fxml"));
         scene = new Scene(fxmlLoader.load());
        stage.setTitle("Organiser");
        stage.setScene(scene);
        stage.show();
    }

    private static Scene scene;
    static void setRoot(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(OrganiserApplication.class.getResource(fxml + ".fxml"));
        scene.setRoot(fxmlLoader.load());
    }
    public static void main(String[] args) {
        launch();

    }
}