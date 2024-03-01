package com.mx.hsbc.test.restaurante.dao.impl;

import com.mx.hsbc.test.restaurante.dao.ReservationDao;
import com.mx.hsbc.test.restaurante.model.Reservation;
import org.h2.jdbcx.JdbcDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ReservationDaoImpl implements ReservationDao {


    public ReservationDaoImpl()  {
        try {
            this.dataSource.setURL("jdbc:h2:mem:testdb");
            this.conn = dataSource.getConnection();
            createTable();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    private Connection conn ;
    private static final Logger log = LoggerFactory.getLogger(ReservationDaoImpl.class);
    JdbcDataSource dataSource = new JdbcDataSource();
    DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Override
    public Reservation saveReservation(Reservation reservation) {

        System.out.println("Insertando en bd");
        try(PreparedStatement stmt = conn.prepareStatement("INSERT INTO reservations (name, tableSize, dateReservation, timeReservation) " +
                    "VALUES (?, ?, ?, ?)")){
                stmt.setString(1, reservation.getName());
                stmt.setInt(2, reservation.getTableSize());
                stmt.setDate(3,  Date.valueOf(LocalDate.parse(reservation.getDateReservation(),formatterDate)));
                stmt.setTime(4, Time.valueOf(LocalTime.parse(reservation.getTimeReservation(),formatterTime)));
                int res = stmt.executeUpdate();
                System.out.println("stmt: " + res);


        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Failed to initialize database", e);
            throw new RuntimeException(e);
        }
        return reservation;
    }

        @Override
    public Reservation getReservationById(int idReservation) {

        Reservation reservation = new Reservation();

            try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM reservations where id = ?");

                 ) {
                stmt.setInt(1, idReservation);
                try(ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        reservation.setId(rs.getInt("id"));
                        reservation.setName(rs.getString("name"));
                        reservation.setTableSize(rs.getInt("tableSize"));
                        reservation.setDateReservation(rs.getString("dateReservation"));
                        reservation.setTimeReservation(rs.getString("timeReservation"));
                    }
                }

        }catch (SQLException e){
            e.printStackTrace();
            log.error("Failed to initialize database", e);
            throw new RuntimeException(e);
        }
        return reservation;
    }

    @Override
    public List<Reservation> getReservationsByDate(String dateReservation) {

        List<Reservation> reservations = new ArrayList<>();


            try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM reservations where dateReservation = ?");
                              ) {
                stmt.setDate(1, Date.valueOf(dateReservation));
                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        Reservation reservation = new Reservation();
                        reservation.setId(rs.getInt("id"));
                        reservation.setName(rs.getString("name"));
                        reservation.setTableSize(rs.getInt("tableSize"));
                        reservation.setDateReservation(rs.getString("dateReservation"));
                        reservation.setTimeReservation(rs.getString("timeReservation"));
                        reservations.add(reservation);
                    }
                }

        }catch (SQLException e){
            e.printStackTrace();
            log.error("Failed to initialize database", e);
            throw new RuntimeException(e);
        }
        return reservations;
    }

    @Override
    public List<Reservation> getAllReservations() {

        List<Reservation> reservations = new ArrayList<>();


            try (PreparedStatement stmt = conn.prepareStatement("SELECT * FROM reservations");
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Reservation reservation = new Reservation();
                    reservation.setId(rs.getInt("id"));
                    reservation.setName(rs.getString("name"));
                    reservation.setTableSize(rs.getInt("tableSize"));
                    reservation.setDateReservation(rs.getString("dateReservation"));
                    reservation.setTimeReservation(rs.getString("timeReservation"));
                    reservations.add(reservation);
                }

        }catch (SQLException e){
            e.printStackTrace();
            log.error("Failed to initialize database", e);
            throw new RuntimeException(e);
        }
        return reservations;
    }

    @Override
    public void deleteReservation(int idReservation) {

            try(PreparedStatement stmt = conn.prepareStatement("DELETE FROM reservations where id = ?")){
                stmt.setInt(1, idReservation);
                stmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Failed to initialize database", e);
            throw new RuntimeException(e);
        }

    }

    private void createTable() throws SQLException {

            String createTableSQL = "CREATE TABLE IF NOT EXISTS reservations (id INT AUTO_INCREMENT PRIMARY KEY, " +
                            "name VARCHAR(255), tableSize INT, dateReservation DATE, timeReservation TIME)";
          PreparedStatement createTableStatement = conn.prepareStatement(createTableSQL);
        try {
            createTableStatement.execute();
            createTableStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }




    }
}
