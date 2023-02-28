package com.kunal.flightreservation.controller;

import com.kunal.flightreservation.dto.ReservationRequest;
import com.kunal.flightreservation.entities.Flight;
import com.kunal.flightreservation.entities.Reservation;
import com.kunal.flightreservation.repos.FlightRepository;
import com.kunal.flightreservation.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReservationController {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    ReservationService reservationService;

    @RequestMapping("/showCompleteReservation")
    public String showCompleteReservation(@RequestParam("flightId") Long flightId, ModelMap modelMap) {
        Flight flight = flightRepository.findById(flightId).get();
        modelMap.addAttribute("flight", flight);
        return "completeReservation";
    }

    @RequestMapping("/completeReservation")
    public String completeReservation(ReservationRequest request, ModelMap modelMap) {
        Reservation reservation = reservationService.bookFlight(request);
        modelMap.addAttribute("msg", "Reservation created successfully and the id is " + reservation.getId());
        return "reservationConfirmation";
    }
}
