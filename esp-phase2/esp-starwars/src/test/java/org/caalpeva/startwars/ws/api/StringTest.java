package org.caalpeva.startwars.ws.api;

import java.io.IOException;

import org.junit.Test;

public class StringTest {

	@Test
	public void testRepository() throws IOException {
		String text = "https://swapi.co4/api/films/20";
		if (text.charAt(text.length() - 1) == '/') {
			text = text.substring(0, text.length() - 1);
		}
		
		text = text.substring(text.lastIndexOf('/') + 1);
		
		System.out.println(text);
	}
}