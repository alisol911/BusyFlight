package com.travix.medusa.crazyair.repository;

import com.travix.medusa.crazyair.domain.Flight;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;


@Lazy
public interface FlightRepository extends JpaRepository<Flight,Long> {
}

