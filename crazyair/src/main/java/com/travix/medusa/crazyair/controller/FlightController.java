package com.travix.medusa.crazyair.controller;

import com.travix.medusa.crazyair.domain.Flight;
import com.travix.medusa.crazyair.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.criteria.Predicate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class FlightController {

    @Autowired
    FlightRepository repository;

    @Autowired
    Environment env;

    @RequestMapping("flight")
    List<Flight> getFlight(@RequestParam(value = "origin", required = false) String origin,
                           @RequestParam(value = "destination", required = false) String destination,
                           @RequestParam(value = "departureDate", required = false) String departureDate,
                           @RequestParam(value = "returnDate", required = false) String returnDate
    ){
        return repository.findAll((Specification<Flight>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            SimpleDateFormat format = new SimpleDateFormat(env.getProperty("crazyari.date.format"));

            if(origin != null && !origin.isEmpty()) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("departureAirportCode"), origin)));
            }
            if(destination != null && !destination.isEmpty()) {
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("destinationAirportCode"), destination)));
            }

            try {
                if(departureDate != null && !departureDate.isEmpty()) {
                    Date d = format.parse(departureDate);
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("departureDate"), d)));
                }
                if(returnDate != null && !returnDate.isEmpty()) {
                    Date d = format.parse(returnDate);
                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("arrivalDate"), d)));
                }
            } catch (ParseException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "invalid date format");
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        });
    }
}
