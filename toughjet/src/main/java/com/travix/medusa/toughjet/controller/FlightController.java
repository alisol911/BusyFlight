package com.travix.medusa.toughjet.controller;

import com.travix.medusa.toughjet.domain.Flight;
import com.travix.medusa.toughjet.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@RestController
public class FlightController {


    @Autowired
    FlightRepository repository;

    @Autowired
    Environment env;

    @RequestMapping("flight")
    List<Flight> getFlight(@RequestParam("from") String from,
                           @RequestParam("to") String to,
                           @RequestParam("outboundDate") String outboundDate,
                           @RequestParam("inboundDate") String inboundDate
    ){
        SimpleDateFormat format = new SimpleDateFormat(env.getProperty("toughject.date.format"));
        try {
            return repository.find(from, to, format.parse(outboundDate), format.parse(inboundDate));
        } catch (ParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid date format");
        }
    }
}
