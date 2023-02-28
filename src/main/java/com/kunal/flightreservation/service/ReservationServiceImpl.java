package com.kunal.flightreservation.service;

import com.kunal.flightreservation.dto.ReservationRequest;
import com.kunal.flightreservation.entities.Flight;
import com.kunal.flightreservation.entities.Passenger;
import com.kunal.flightreservation.entities.Reservation;
import com.kunal.flightreservation.repos.FlightRepository;
import com.kunal.flightreservation.repos.PassengerRepository;
import com.kunal.flightreservation.repos.ReservationRepository;
import com.kunal.flightreservation.util.EmailUtil;
import com.kunal.flightreservation.util.PDFGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    PDFGenerator pdfGenerator;
    @Autowired
    EmailUtil emailUtil;
    @Autowired
    FlightRepository flightRepository;
    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    ReservationRepository reservationRepository;

    @Override
    public Reservation bookFlight(ReservationRequest request) {
        Long flightId = request.getFlightId();
        Flight flight = flightRepository.findById(flightId).get();

        Passenger passenger = new Passenger();
        passenger.setFirstName(request.getPassengerFirstName());
        passenger.setLastName(request.getPassengerLastName());
        passenger.setEmail(request.getPassengerEmail());
        passenger.setPhone(request.getPassengerPhone());
        Passenger savedPassenger = passengerRepository.save(passenger);

        Reservation reservation = new Reservation();
        reservation.setFlight(flight);
        reservation.setPassenger(savedPassenger);
        reservation.setCheckedIn(false);
        Reservation savedReservation = reservationRepository.save(reservation);
        String filePath ="/Users/kunal/Desktop/reservations/reservation" + savedReservation.getId() + ".pdf";
        pdfGenerator.generateItinerary(savedReservation, filePath);
        emailUtil.sendItinerary(passenger.getEmail(), filePath);

        return savedReservation;
    }
}
