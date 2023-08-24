package com.example.iaorganiser;

import javafx.collections.ObservableList;

import java.time.LocalDate;

public class DateActivity {
    private LocalDate date;
    private CalendarActivity activity;

    public DateActivity(LocalDate date, CalendarActivity activity) {
        this.date = date;
        this.activity = activity;
    }

    public LocalDate getDate() {
        return date;
    }

    public CalendarActivity getActivity() {
        return activity;
    }
}
