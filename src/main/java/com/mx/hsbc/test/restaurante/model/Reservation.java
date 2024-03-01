package com.mx.hsbc.test.restaurante.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation {
    private int id;
    private String name;
    private int tableSize;

    private String dateReservation;

    private String timeReservation;

    public Reservation() {}

    public Reservation(int id, String name, int numberOfGuests, String dateReservation, String timeReservation) {
        this.id = id;
        this.name = name;
        this.tableSize = numberOfGuests;
        this.dateReservation = dateReservation;
        this.timeReservation = timeReservation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTableSize() {
        return tableSize;
    }

    public void setTableSize(int tableSize) {
        this.tableSize = tableSize;
    }

    public String getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(String dateReservation) {
        this.dateReservation = dateReservation;
    }

    public String getTimeReservation() {
        return timeReservation;
    }

    public void setTimeReservation(String timeReservation) {
        this.timeReservation = timeReservation;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tableSize=" + tableSize +
                ", dateReservation='" + dateReservation + '\'' +
                ", timeReservation='" + timeReservation + '\'' +
                '}';
    }
}
