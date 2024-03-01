package com.mx.hsbc.test.restaurante.service.impl;

import com.mx.hsbc.test.restaurante.dao.ReservationDao;
import com.mx.hsbc.test.restaurante.dao.impl.ReservationDaoImpl;
import com.mx.hsbc.test.restaurante.model.Reservation;
import com.mx.hsbc.test.restaurante.service.ReservationService;

import java.util.List;

public class ReservationServiceImpl implements ReservationService {

    private ReservationDao reservationDao = new ReservationDaoImpl();
    @Override
    public Reservation saveReservation(Reservation reservation) {
        Reservation res = reservationDao.saveReservation(reservation);
        return res;
    }

    @Override
    public Reservation getReservationById(int idReservation) {
        return reservationDao.getReservationById(idReservation);
    }

    @Override
    public List<Reservation> getReservationsByDate(String date) {
        return reservationDao.getReservationsByDate(date);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationDao.getAllReservations();
    }

    @Override
    public String deleteReservation(int idReservation) {
        reservationDao.deleteReservation(idReservation);
        return "Se elimino la reservacion con el id: " +  idReservation;
    }
}
