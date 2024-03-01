package com.mx.hsbc.test.restaurante.service;

import com.mx.hsbc.test.restaurante.model.Reservation;

import java.util.List;

public interface ReservationService {

    public Reservation saveReservation(Reservation reservation);

    public Reservation getReservationById(int idReservation);

    public List<Reservation> getReservationsByDate(String date);

    public List<Reservation> getAllReservations();

    public String deleteReservation(int idReservation);
}
