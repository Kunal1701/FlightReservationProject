package com.kunal.flightreservation.service;

import com.kunal.flightreservation.dto.ReservationRequest;
import com.kunal.flightreservation.entities.Reservation;

public interface ReservationService {
    public Reservation bookFlight(ReservationRequest request);
}
