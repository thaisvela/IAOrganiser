package com.example.iaorganiser;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.time.LocalDate;

public class EventMakerController {


    public TextField inputName;
    public TextField inputNumber;
    public DatePicker dateInput;
    public Text success;


    public void createEvent(ActionEvent actionEvent) throws IOException {

        String name = inputName.getText();
        String s_number = inputNumber.getText();
        LocalDate date = dateInput.getValue();
        int number = 0;

        try {
            number = Integer.parseInt(s_number);
        }
        catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Formatting error");


            if(name == null || s_number == null || date == null){
                alert.setContentText("Fill all fields");
            }
            else {
                alert.setContentText("Service number field can only include numbers");
            }
            alert.show();
        }

        if(name != null && number != 0 && date != null){
            CalendarActivity activity = new CalendarActivity(date, name, s_number);
            CalendarController.myActivities.add(activity);
            success.setText("Event successfully created");
        }

    }

    public void homeButtonAction(ActionEvent actionEvent) throws IOException{
        OrganiserApplication.setRoot("afterlogin-view");
    }
}
