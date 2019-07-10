package com.travix.medusa.crazyair;

import com.travix.medusa.crazyair.domain.Flight;
import com.travix.medusa.crazyair.repository.FlightRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

import java.util.GregorianCalendar;

@SpringBootApplication
@EntityScan(basePackageClasses=Flight.class)
public class Application {

	@Bean
	CommandLineRunner init(FlightRepository customerRepository) {
		return (evt) ->  {
			customerRepository.save(new Flight("Iran Air", 100, "",
					"AED", "CEA", new GregorianCalendar(2019,6,4).getTime(),
					new GregorianCalendar(2019,6,5).getTime()));
			customerRepository.save(new Flight("Iran Air", 100, "",
					"AED", "GRA", new GregorianCalendar(2019,6,5).getTime(),
					new GregorianCalendar(2019,6,6).getTime()));
			customerRepository.save(new Flight("Iran Air", 100, "",
					"GGA", "CEA", new GregorianCalendar(2019,6,6).getTime(),
					new GregorianCalendar(2019,6,7).getTime()));
			customerRepository.save(new Flight("Mahan", 110, "",
					"AED", "CEA", new GregorianCalendar(2019,6,4).getTime(),
					new GregorianCalendar(2019,6,5).getTime()));
			customerRepository.save(new Flight("Saha", 130, "",
					"AED", "CEA", new GregorianCalendar(2019,6,4).getTime(),
					new GregorianCalendar(2019,6,5).getTime()));
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
