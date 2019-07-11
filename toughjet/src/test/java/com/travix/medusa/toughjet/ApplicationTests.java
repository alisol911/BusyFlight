package com.travix.medusa.toughjet;

import com.travix.medusa.toughjet.domain.Flight;
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

import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EntityScan(basePackageClasses=Flight.class)
public class ApplicationTests {

	@LocalServerPort
	private int port;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void contextLoads() {
	}

	@Test
	public void testService() {
		RestTemplate restTemplate = new RestTemplate();
		UriComponentsBuilder builder = UriComponentsBuilder
				.fromUriString("http://localhost:" + port + "/flight")
				.queryParam("from", "AED")
				.queryParam("to", "CEA")
				.queryParam("outboundDate", "2019-07-04-10:00")
				.queryParam("inboundDate", "2019-07-05-11:00");
		Flight[] list = restTemplate.getForObject(builder.toUriString(),  Flight[].class);
		Assert.assertEquals(1, list.length);
		Assert.assertEquals("Iran Air", list[0].getCarrier());
	}

}

