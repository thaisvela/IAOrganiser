package com.example.iaorganiser;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class AfterLoginController {


    public void userLogOut(ActionEvent event) throws IOException{
        OrganiserApplication.setRoot("login-view");
    }

    public void calendarButtonAction(ActionEvent actionEvent) throws IOException {
        OrganiserApplication.setRoot("calendar-view");
    }

    public void eventMakerButton(ActionEvent actionEvent) throws IOException {
        OrganiserApplication.setRoot("EventMaker-view");
    }
}