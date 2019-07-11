package com.travix.medusa.busyflight.controller;

import com.travix.medusa.busyflight.domain.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.Comparator;

@RestController
public class Controller {

    @Autowired
    Environment env;

    @RequestMapping("flight")
    Flux<Flight> getFlight(@RequestParam(value = "origin", required = false) String origin,
                            @RequestParam(value = "destination", required = false) String destination,
                            @RequestParam(value = "departureDate", required = false) String departureDate,
                            @RequestParam(value = "returnDate", required = false) String returnDate) {

        WebClient crazyairClient = WebClient
                .builder()
                .baseUrl(env.getProperty("busyflight.supplier,url.crazyair"))
                .build();
        Flux<com.travix.medusa.crazyair.domain.Flight> crazyair = crazyairClient.get()
            .uri(uriBuilder -> uriBuilder
                .queryParam("origin", origin)
                .queryParam("destination", destination)
                .queryParam("departureDate", departureDate)
                .queryParam("returnDate", returnDate).build())
            .retrieve().bodyToFlux(com.travix.medusa.crazyair.domain.Flight.class)
            .onErrorReturn(new com.travix.medusa.crazyair.domain.Flight());

        WebClient toughjetClient = WebClient
                .builder()
                .baseUrl(env.getProperty("busyflight.supplier,url.toughjet"))
                .build();
        Flux<com.travix.medusa.toughjet.domain.Flight> toughjet = toughjetClient.get()
            .uri(uriBuilder -> uriBuilder
                .queryParam("from", origin)
                .queryParam("to", destination)
                .queryParam("outboundDate", departureDate)
                .queryParam("inboundDate", returnDate).build())
            .retrieve().bodyToFlux(com.travix.medusa.toughjet.domain.Flight.class)
            .onErrorReturn(new com.travix.medusa.toughjet.domain.Flight());
        return Flux.merge(
                crazyair.filter(ca -> ca.getAirline() != null).map(ca -> new Flight(
                                ca.getAirline(),
                        "CrazyAir",
                                ca.getPrice(),
                                ca.getDepartureAirportCode(),
                                ca.getDestinationAirportCode(),
                                ca.getDepartureDate(),
                                ca.getArrivalDate()
                        )
                ),
                toughjet.filter(tj -> tj.getCarrier() != null).map(tj -> new Flight(
                        tj.getCarrier(),
                        "ToughJet",
                        tj.getBasePrice() + tj.getTax() - tj.getDiscount(),
                        tj.getDepartureAirportName(),
                        tj.getArrivalAirportName(),
                        tj.getOutboundDateTime(),
                        tj.getInboundDateTime()
                ))

        ).sort(Comparator.comparingDouble(Flight::getFare));
    }
}
