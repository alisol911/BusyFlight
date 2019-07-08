package com.travix.medusa.busyflights;

import com.travix.medusa.crazyair.domain.Flight;
import com.travix.medusa.busyflights.repository.FlightRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
@EntityScan(basePackageClasses=Flight.class)
public class Application {

	@Bean
	CommandLineRunner init(FlightRepository customerRepository) {
		return (evt) ->  {
			customerRepository.save(new Flight("Iran Air", 100, "",
					"AED", "CEA", new Date(2019,6,4),
					new Date(2019,6,5)));
			customerRepository.save(new Flight("Iran Air", 100, "",
					"AED", "GRA", new Date(2019,6,5),
					new Date(2019,6,6)));
			customerRepository.save(new Flight("Iran Air", 100, "",
					"GGA", "CEA", new Date(2019,6,6),
					new Date(2019,6,7)));
			customerRepository.save(new Flight("Mahan", 110, "",
					"AED", "CEA", new Date(2019,6,4),
					new Date(2019,6,5)));
			customerRepository.save(new Flight("Saha", 130, "",
					"AED", "CEA", new Date(2019,6,4),
					new Date(2019,6,5)));
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
