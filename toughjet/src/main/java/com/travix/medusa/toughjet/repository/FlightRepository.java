package com.travix.medusa.toughjet.repository;

import com.travix.medusa.toughjet.domain.Flight;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;


@Lazy
public interface FlightRepository extends JpaRepository<Flight,Long> {

    @Query("SELECT f FROM Flight f WHERE " +
            "f.departureAirportName = :departureAirportName and " +
            "f.arrivalAirportName = :arrivalAirportName and " +
            "f.outboundDateTime = :outboundDateTime and " +
            "f.inboundDateTime = :inboundDateTime")
    List<Flight> find(@Param("departureAirportName") String departureAirportName,
                           @Param("arrivalAirportName") String arrivalAirportName,
                           @Param("outboundDateTime") Date outboundDateTime,
                           @Param("inboundDateTime") Date inboundDateTime);

}

