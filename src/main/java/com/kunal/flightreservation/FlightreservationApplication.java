package com.kunal.flightreservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class FlightreservationApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlightreservationApplication.class, args);
    }

}
