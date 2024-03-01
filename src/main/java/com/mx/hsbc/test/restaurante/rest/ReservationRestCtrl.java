package com.mx.hsbc.test.restaurante.rest;

import com.mx.hsbc.test.restaurante.RestauranteController;
import com.mx.hsbc.test.restaurante.model.Reservation;
import com.mx.hsbc.test.restaurante.service.ReservationService;
import com.mx.hsbc.test.restaurante.service.impl.ReservationServiceImpl;
import io.muserver.rest.ApiResponse;
import io.muserver.rest.Description;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Path("/booking")
@Description(value = "A Restaurante Booking", details = "Use this API to get and create bookings")
public class ReservationRestCtrl {

    private static final Logger log = LoggerFactory.getLogger(RestauranteController.class);
    private final ReservationService service = new ReservationServiceImpl();

    @GET
    @Produces("application/json")
    @ApiResponse(code = "200", message = "Success")
    @ApiResponse(code = "404", message = "No reservation with that ID found")
    public Reservation sayHello() {
        return new Reservation(1, "Esteban", 11, LocalDate.now().toString(), LocalTime.now().toString());
    }

    @GET
    @Path("all")
    @Description("Get all reservations created")
    @Produces("application/json")
    @ApiResponse(code = "200", message = "Success")
    public Response getAllReservations() {
        return Response.ok(service.getAllReservations()).build();
    }

    @GET
    @Path("/{id}")
    @Description("Get reservation by identifier")
    @Produces("application/json")
    @ApiResponse(code = "200", message = "Success")
    @ApiResponse(code = "404", message = "No reservation with that ID found")
    public Response getReservationById(@Description("The ID of the reservation")
                                                    @PathParam("id") int id) {
        Reservation reservation = service.getReservationById(id);
        System.out.println(reservation);
        if (reservation.getName() != null && !reservation.getName().isEmpty()){
            return Response.ok(reservation).build();
        }else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/date/")
    @Description("Get all reservations created in a specific Date")
    @Produces("application/json")
    @ApiResponse(code = "200", message = "Success")
    @ApiResponse(code = "404", message = "No reservation with that date found")
    public Response getReservationById(@Description("The date of the reservations created")
                                          @QueryParam("date") String dateReservation) {
        List<Reservation> reservations = service.getReservationsByDate(dateReservation);
        if (!reservations.isEmpty()){
            return  Response.ok(reservations).build();
        }else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    @POST
    @Consumes("application/json")
    @Produces("text/plain")
    @Description("Creates a new reservation")
    @ApiResponse(code = "201", message = "The reservation was created")
    @ApiResponse(code = "400", message = "The ID or name was not specified")
    public String createReservation(Reservation reservation){
        try {

            System.out.println("Antes de guardar:::: " + reservation);
            Reservation reservationSaved = service.saveReservation(reservation);
            System.out.println("despues de guardar");
            log.debug("Se guardo la reservación");
            return "Reservacion creada con exito";
            //return reservationSaved;
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Reservacion creada con exito";
    }


}
