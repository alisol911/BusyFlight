package com.travix.medusa.crazyair.controller;

import com.travix.medusa.crazyair.domain.Flight;
import com.travix.medusa.crazyair.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FlightController {


    @Autowired
    FlightRepository repository;

    @RequestMapping("flight")
    List<Flight> getFlight(@Param("origin") String origin,
                           @Param("destination") String destination,
                           @Param("departureDate") String departureDate,
                           @Param("returnDate") String returnDate
    ){
        return repository.findAll();
    }
}