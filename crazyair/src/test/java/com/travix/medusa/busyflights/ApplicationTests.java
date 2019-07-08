package com.travix.medusa.busyflights;

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

import java.net.URL;
import java.util.List;

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
	public void contextLoads() {
	}

	@Test
	public void testCrazyAirService() {
		RestTemplate restTemplate = new RestTemplate();
		Flight[] list = restTemplate.getForObject(base.toString() + "flight",  Flight[].class);
		Assert.assertEquals("Iran Air", list[0].getAirline());
	}

}

