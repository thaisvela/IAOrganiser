package com.example.iaorganiser;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class CalendarController {

    @FXML
    public TableView<LocalDate> calendarTableView;

    private List<LocalDate> daysInMonth;
    private ObservableList<LocalDate> paidSessions;

    public void initialize() {
        daysInMonth = getDaysInMonth();
        paidSessions = FXCollections.observableArrayList();

        TableColumn<LocalDate, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("dayOfMonth"));

        calendarTableView.getColumns().add(dateColumn);

        for (LocalDate date : daysInMonth) {
            calendarTableView.getItems().add(date);
        }

        calendarTableView.setRowFactory(tv -> {
            TableRow<LocalDate> row = new TableRow<>();
            row.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (event.getClickCount() == 2 && !row.isEmpty()) {
                        LocalDate clickedDate = row.getItem();
                        row.setStyle("-fx-background-color: rgba(248,164,164,0.72);");
                        paidSessions.add(clickedDate);
                    }
                }
            });
            return row;
        });
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
}

