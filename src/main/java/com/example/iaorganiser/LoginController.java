package com.example.iaorganiser;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class LoginController {

    public void LogIn() {

    }

    @FXML
    private Button button;
    @FXML
    private Label wrongLogin;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    private void checkLogin() throws IOException {

        if(username.getText().equals("javacoding") && password.getText().equals("123")) {
            wrongLogin.setText("Success!");

            OrganiserApplication.setRoot("afterLogin.fxml");
        }

        else if(username.getText().isEmpty() && password.getText().isEmpty()) {
            wrongLogin.setText("Please enter your valid data.");
        }

        else {
            wrongLogin.setText("Wrong username or password!");
        }
    }

    public ImageView todoImageBox;
    public ImageView finImageBox;
    public ImageView calImageBox;
    public ImageView loginImageBox;
    public ImageView lockImageBox;

    public void initialize(){
        todoImageBox.setImage(new Image(getClass().getResourceAsStream("/todolist.png")));
        finImageBox.setImage(new Image (getClass().getResourceAsStream("/finance.png")));
        calImageBox.setImage(new Image (getClass().getResourceAsStream("/calendar.png")));
        loginImageBox.setImage(new Image (getClass().getResourceAsStream("/login.png")));
        lockImageBox.setImage(new Image (getClass().getResourceAsStream("/lock.png")));
    }

    public void loginButtonAction(ActionEvent actionEvent) throws IOException {
        //checkLogin();
        OrganiserApplication.setRoot("afterlogin-view");
    }
}