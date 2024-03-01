package com.mx.hsbc.test.restaurante;

import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.google.gson.Gson;
import com.mx.hsbc.test.restaurante.model.Reservation;
import com.mx.hsbc.test.restaurante.rest.ReservationRestCtrl;
import com.mx.hsbc.test.restaurante.service.ReservationService;
import com.mx.hsbc.test.restaurante.service.impl.ReservationServiceImpl;
import io.muserver.Method;
import io.muserver.MuServer;
import io.muserver.MuServerBuilder;
import io.muserver.openapi.OpenAPIObjectBuilder;
import io.muserver.rest.RestHandlerBuilder;
import org.h2.jdbcx.JdbcDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static io.muserver.openapi.ExternalDocumentationObjectBuilder.externalDocumentationObject;
import static io.muserver.openapi.InfoObjectBuilder.infoObject;
import static io.muserver.rest.CORSConfigBuilder.corsConfig;

public class RestauranteController {

    private static final Logger log = LoggerFactory.getLogger(RestauranteController.class);


    public static void main(String[] args) {

        MuServer server = MuServerBuilder.httpServer()
                .addHandler(createRestHandler()
                )
                .start();

        System.out.println("API example: " + server.uri().resolve("/booking"));
        System.out.println("API HTML: " + server.uri().resolve("/api.html"));
        System.out.println("API JSON: " + server.uri().resolve("/openapi.json"));
    }

    public static RestHandlerBuilder createRestHandler() {

        return RestHandlerBuilder.restHandler(new ReservationRestCtrl())
                .addCustomWriter(new JacksonJaxbJsonProvider())
                .addCustomReader(new JacksonJaxbJsonProvider())
                .withCORS(corsConfig().withAllowedOriginRegex(".*"))
                .withOpenApiHtmlUrl("/api.html")
                .withOpenApiJsonUrl("/openapi.json")
                .withOpenApiDocument(
                        OpenAPIObjectBuilder.openAPIObject()
                                .withInfo(
                                        infoObject()
                                                .withTitle("User API Documentation")
                                                .withDescription("This is just a demo API that doesn't actually work!")
                                                .withVersion("1.0")
                                                .build())
                                .withExternalDocs(
                                        externalDocumentationObject()
                                                .withDescription("Documentation docs")
                                                .withUrl(URI.create("https://muserver.io/jaxrs"))
                                                .build()
                                )
                );
    }
}
