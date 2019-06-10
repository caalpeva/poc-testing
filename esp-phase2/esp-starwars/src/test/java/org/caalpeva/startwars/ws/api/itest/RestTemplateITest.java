package org.caalpeva.startwars.ws.api.itest;

import static org.junit.Assert.assertEquals;

import org.caalpeva.starwars.configuration.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@WebAppConfiguration
public class RestTemplateITest {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Test
	public void getAllPeople() {
		String urlOverHttps = "https://swapi.co/api/people/";
	    ResponseEntity<String> response 
	      = restTemplate.exchange(urlOverHttps, HttpMethod.GET, null, String.class);
	    System.out.println("Response: " + response.getBody());
	    assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
}