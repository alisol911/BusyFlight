package com.travix.medusa.toughjet.controller;

import com.travix.medusa.toughjet.domain.Flight;
import com.travix.medusa.toughjet.repository.FlightRepository;
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
    List<Flight> getFlight(@RequestParam("from") String from,
                           @RequestParam("to") String to,
                           @RequestParam("outboundDate") String outboundDate,
                           @RequestParam("inboundDate") String inboundDate,
                           @RequestParam("numberOfAdults") Optional<Integer> numberOfAdults
    ){
        return repository.findAll();
    }
}
