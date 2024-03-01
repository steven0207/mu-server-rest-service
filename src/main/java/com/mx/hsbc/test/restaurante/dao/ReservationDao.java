package com.mx.hsbc.test.restaurante.dao;

import com.mx.hsbc.test.restaurante.model.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface ReservationDao {

    public Reservation saveReservation(Reservation reservation);

    public Reservation getReservationById(int idReservation);

    public List<Reservation> getReservationsByDate(String dateReservation);

    public List<Reservation> getAllReservations();

    public void deleteReservation(int idReservation);

}
