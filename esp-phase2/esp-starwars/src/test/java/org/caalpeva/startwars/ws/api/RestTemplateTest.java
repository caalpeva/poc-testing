package org.caalpeva.startwars.ws.api;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/spring-service.xml"})
//@ContextConfiguration(classes = AppConfig.class, loader = AnnotationConfigContextLoader.class)
public class RestTemplateTest {
	
	@Test
	public void getAllPeople() {
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    headers.add("user-agent", "RestTemplate-Java-Client/1.0");
	    
		String urlOverHttps = "https://swapi.co/api/people/";
	    ResponseEntity<String> response 
	      = new RestTemplate().exchange(urlOverHttps, HttpMethod.GET,
	    		  new HttpEntity<String>("parameters", headers), String.class);
	    System.out.println("Response: " + response.getBody());
	    assertEquals(response.getStatusCode().value(), 200);
	}
}