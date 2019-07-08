package com.travix.medusa.crazyair.controller;

import com.travix.medusa.crazyair.domain.Flight;
import com.travix.medusa.crazyair.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class FlightController {


    @Autowired
    FlightRepository repository;

    @RequestMapping("flight")
    List<Flight> getFlight(@RequestParam("origin") String origin,
                           @RequestParam("destination") String destination,
                           @RequestParam("departureDate") String departureDate,
                           @RequestParam("returnDate") String returnDate,
                           @RequestParam("passengerCount") int passengerCount
    ){
        return repository.findAll();
    }
}
