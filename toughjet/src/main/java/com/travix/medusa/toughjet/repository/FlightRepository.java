package com.travix.medusa.toughjet.repository;

import com.travix.medusa.toughjet.domain.Flight;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;


@Lazy
public interface FlightRepository extends JpaRepository<Flight,Long> {
}

