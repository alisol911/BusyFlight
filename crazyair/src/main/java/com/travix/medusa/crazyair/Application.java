package com.travix.medusa.crazyair;

import com.travix.medusa.crazyair.domain.Flight;
import com.travix.medusa.crazyair.repository.FlightRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

import java.util.Calendar;
import java.util.GregorianCalendar;

@SpringBootApplication
@EntityScan(basePackageClasses=Flight.class)
public class Application {

	@Bean
	CommandLineRunner init(FlightRepository customerRepository) {
		return (evt) ->  {
			customerRepository.save(new Flight("Iran Air", 100, "",
					"AED", "CEA",
					new GregorianCalendar(2019, Calendar.JULY,4, 10, 0).getTime(),
					new GregorianCalendar(2019,Calendar.JULY,5, 11, 0).getTime()));
			customerRepository.save(new Flight("Saha", 90, "",
					"AED", "CEA",
					new GregorianCalendar(2019, Calendar.JULY,4, 10, 0).getTime(),
					new GregorianCalendar(2019,Calendar.JULY,5, 11, 0).getTime()));
			customerRepository.save(new Flight("Iran Air", 100, "",
					"GGA", "CEA",
					new GregorianCalendar(2019,Calendar.JULY,6, 15, 0).getTime(),
					new GregorianCalendar(2019,Calendar.JULY,7, 12, 0).getTime()));
			customerRepository.save(new Flight("Mahan", 110, "",
					"AED", "BBA",
					new GregorianCalendar(2019,Calendar.JULY,4, 19, 0).getTime(),
					new GregorianCalendar(2019,Calendar.JULY,5, 4, 0).getTime()));
			customerRepository.save(new Flight("Saha", 130, "",
					"DDC", "CEA",
					new GregorianCalendar(2019,Calendar.JULY,4, 11, 0).getTime(),
					new GregorianCalendar(2019,Calendar.JULY,5, 11, 0).getTime()));
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
