package org.caalpeva.startwars.ws.client;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

//import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

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