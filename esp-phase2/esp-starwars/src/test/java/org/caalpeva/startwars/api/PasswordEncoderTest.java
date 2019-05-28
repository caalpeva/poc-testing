package org.caalpeva.startwars.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.web.client.RestTemplate;

@SpringJUnitConfig(TestContext.class)
//@ContextConfiguration(locations = {"classpath:spring-security-encryption.xml"})
public class PasswordEncoderTest {
	
	@Test
	public void whenHttpsUrlIsConsumed_thenException() {
	    String urlOverHttps = "https://swapi.co/api/people/1";
	    ResponseEntity<String> response 
	      = new RestTemplate().exchange(urlOverHttps, HttpMethod.GET, null, String.class);
	    assertEquals(response.getStatusCode().value(), 200);
	}
}