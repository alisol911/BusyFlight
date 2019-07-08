package com.travix.medusa.busyflights.controller;

import com.travix.medusa.busyflights.domain.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusyFlightsController {

    @Autowired
    Environment env;

    @RequestMapping("flight")
    Flight getBusyFlightsResponse(){
        Flight result = new Flight();
        return result;
    }

}
