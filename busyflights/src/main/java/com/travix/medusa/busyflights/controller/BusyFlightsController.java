package com.travix.medusa.busyflights.controller;

import com.travix.medusa.busyflights.domain.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@RestController
public class BusyFlightsController {

    @Autowired
    Environment env;

    @RequestMapping("flight")
    Flux<Flight> getFlights() {

        WebClient crazyairClient = WebClient
                .builder()
                .baseUrl(env.getProperty("busyflights.supplier,url.crazyair"))
                .build();
        Flux<com.travix.medusa.crazyair.domain.Flight> crazyair = crazyairClient.get()
            .uri(uriBuilder -> uriBuilder
                .queryParam("origin", "")
                .queryParam("destination", "")
                .queryParam("departureDate", "")
                .queryParam("returnDate", "")
                .queryParam("passengerCount", 0).build())
            .retrieve().bodyToFlux(com.travix.medusa.crazyair.domain.Flight.class)
            .onErrorReturn(new com.travix.medusa.crazyair.domain.Flight());

        WebClient toughjetClient = WebClient
                .builder()
                .baseUrl(env.getProperty("busyflights.supplier,url.toughjet"))
                .build();
        Flux<com.travix.medusa.toughjet.domain.Flight> toughjet = toughjetClient.get()
            .uri(uriBuilder -> uriBuilder
                .queryParam("from", "")
                .queryParam("to", "")
                .queryParam("outboundDate", "")
                .queryParam("inboundDate", "")
                .queryParam("numberOfAdults", 0).build())
            .retrieve().bodyToFlux(com.travix.medusa.toughjet.domain.Flight.class)
            .onErrorReturn(new com.travix.medusa.toughjet.domain.Flight());
        return Flux.merge(
                crazyair.filter(ca -> ca.getAirline() != null).map(ca -> new Flight(
                                ca.getAirline(),
                                ca.getPrice(),
                                ca.getDepartureAirportCode(),
                                ca.getDestinationAirportCode(),
                                ca.getDepartureDate(),
                                ca.getArrivalDate()
                        )
                ),
                toughjet.filter(tj -> tj.getCarrier() != null).map(tj -> new Flight(
                        tj.getCarrier(),
                        tj.getBasePrice() + tj.getTax() - tj.getDiscount(),
                        tj.getDepartureAirportName(),
                        tj.getArrivalAirportName(),
                        tj.getOutboundDateTime(),
                        tj.getInboundDateTime()
                ))

        ).sort(Comparator.comparingDouble(Flight::getPrice));
    }
}
