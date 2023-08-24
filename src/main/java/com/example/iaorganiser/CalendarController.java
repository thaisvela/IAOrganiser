package com.example.iaorganiser;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class CalendarController {
    public static ObservableList<CalendarActivity> myActivities = FXCollections.observableArrayList();;
    @FXML
    public TableView<DateActivity> calendarTableView;
    private List<LocalDate> daysInMonth;

    public void initialize() {

        //https://mkyong.com/java/how-do-convert-java-object-to-from-json-format-gson-api/

        ObservableList<DateActivity> activityForDate = FXCollections.observableArrayList();

        myActivities.add(new CalendarActivity(LocalDate.of(2023, Month.AUGUST, 15), "Client A", "42"));
        myActivities.add(new CalendarActivity(LocalDate.of(2023, Month.AUGUST, 23), "Client B", "78"));
        myActivities.add(new CalendarActivity(LocalDate.of(2023, Month.AUGUST, 2), "Client C", "16"));
        myActivities.add(new CalendarActivity(LocalDate.of(2023, Month.AUGUST, 29), "Client D", "51"));
        myActivities.add(new CalendarActivity(LocalDate.of(2023, Month.AUGUST, 12), "Client E", "93"));

        saveJson();
        myActivities.get(3).setPaid(true);
        daysInMonth = getDaysInMonth();

        TableColumn<DateActivity, String> dateColumn = new TableColumn<>("Date");
//https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/cell/PropertyValueFactory.html
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));


//https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/TableColumn.html
        TableColumn<DateActivity, String> clientColumn = new TableColumn<>("Client");
        clientColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getActivity().getClientName()));

        TableColumn<DateActivity, String> serviceIDColumn = new TableColumn<>("Service Num");
        serviceIDColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getActivity().getServiceNo()));

        calendarTableView.getColumns().addAll(dateColumn, clientColumn, serviceIDColumn);

        for (LocalDate date : daysInMonth) {
            boolean hasActivity = false;
            for (CalendarActivity activity : myActivities) {
                if (activity.getDate().equals(date)) {
                    activityForDate.add(new DateActivity(date, activity));
                    hasActivity=true;
                }
            }
            if(!hasActivity) {
                activityForDate.add(new DateActivity(date, new CalendarActivity(date, "", "")));
            }

        }

//https://stackoverflow.com/questions/67141514/javafx-error-in-setonmouseclicked-when-i-click-on-empty-row-in-tableview

        calendarTableView.setRowFactory(tv -> {
            TableRow<DateActivity> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && !row.isEmpty()) {
                    DateActivity clickedDate = row.getItem();
                    // Light green so the user can click if they have finished the task.
                    row.setStyle("-fx-background-color: rgba(178, 223, 138, 0.72);");
                    clickedDate.getActivity().setPaid(true);
                }
            });
            return row;
        });

        LocalDate dateNow = LocalDate.now();
        calendarTableView.setRowFactory(tv -> {
            TableRow<DateActivity> row = new TableRow<>();
            row.itemProperty().addListener((obs, oldItem, newItem) -> {
                if (newItem != null && !newItem.getActivity().getClientName().equals("") && newItem.getDate().isBefore(LocalDate.now()) && !newItem.getActivity().isPaid()) {
                    row.setStyle("-fx-background-color: rgba(255, 0, 0, 0.72);");
                } else if(newItem.getActivity().isPaid()) {
                    row.setStyle("-fx-background-color: rgba(178, 223, 138, 0.72);");
                }else if(dateNow.isEqual(newItem.getDate())){
                    row.setStyle("-fx-background-color: rgba(0, 0, 138, 0.72);");
                }else{
                    row.setStyle("");
                }
            });
            return row;
        });

        calendarTableView.setItems(activityForDate);

    }

    private List<LocalDate> getDaysInMonth() {
        List<LocalDate> days = new ArrayList<>();
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue();
        int lengthOfMonth = today.lengthOfMonth();
        for (int day = 1; day <= lengthOfMonth; day++) {
            days.add(LocalDate.of(year, month, day));
        }
        return days;
    }

    public void homeButtonAction(ActionEvent actionEvent) throws IOException {
        OrganiserApplication.setRoot("afterlogin-view");
    }

    public void saveJson(){
        Gson gson = new Gson();

        // Java objects to File
        try (FileWriter writer = new FileWriter("activities.json")) {
            gson.toJson(myActivities, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadJson(){

    }
}
