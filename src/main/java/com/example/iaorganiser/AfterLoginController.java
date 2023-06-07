package com.example.iaorganiser;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class AfterLoginController {


    public void userLogOut(ActionEvent event) throws IOException{
        OrganiserApplication.setRoot("hello-view.fxml");
    }

    public void calendarButtonAction(ActionEvent actionEvent) throws IOException {
        OrganiserApplication.setRoot("calendar-view");
    }
}