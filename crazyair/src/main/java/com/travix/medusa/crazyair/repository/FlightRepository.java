package com.travix.medusa.crazyair.repository;

import com.travix.medusa.crazyair.domain.Flight;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


@Lazy
public interface FlightRepository extends JpaRepository<Flight,Long>, JpaSpecificationExecutor<Flight> {
}

