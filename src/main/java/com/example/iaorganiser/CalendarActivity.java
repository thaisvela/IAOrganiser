package com.example.iaorganiser;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoLocalDate;

public class CalendarActivity {
    private LocalDate date;
    private String clientName;
    private String serviceNo;
    private Boolean isPaid = false;

    public CalendarActivity(LocalDate date, String clientName, String serviceNo) {
        this.date = date;
        this.clientName = clientName;
        this.serviceNo = serviceNo;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    public Boolean isPaid() {
        return isPaid;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getServiceNo() {
        return serviceNo;
    }

    public void setServiceNo(String serviceNo) {
        this.serviceNo = serviceNo;
    }

    @Override
    public String toString() {
        return "CalenderActivity{" +
                "date=" + date +
                "clientName='" + clientName + '\'' +
                "serviceNo=" + serviceNo +
                '}';
    }
}
