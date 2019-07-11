package com.travix.medusa.crazyair;

import com.travix.medusa.crazyair.domain.Flight;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URL;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EntityScan(basePackageClasses=Flight.class)
public class ApplicationTests {

	@LocalServerPort
	private int port;

	private URL base;

	@Before
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + port + "/");
	}

	@Test
	public void testService1() {
		RestTemplate restTemplate = new RestTemplate();
		UriComponentsBuilder builder = UriComponentsBuilder
				.fromUriString("http://localhost:" + port + "/flight")
				.queryParam("origin", "AED");
		Flight[] list = restTemplate.getForObject(builder.toUriString(),  Flight[].class);
		Assert.assertEquals(3, list.length);
		Assert.assertEquals("Iran Air", list[0].getAirline());
	}

	@Test
	public void testService2() {
		RestTemplate restTemplate = new RestTemplate();
		UriComponentsBuilder builder = UriComponentsBuilder
				.fromUriString("http://localhost:" + port + "/flight")
				.queryParam("origin", "AED")
				.queryParam("destination", "CEA");
		Flight[] list = restTemplate.getForObject(builder.toUriString(),  Flight[].class);
		Assert.assertEquals(2, list.length);
		Assert.assertEquals("Iran Air", list[0].getAirline());
	}

}

