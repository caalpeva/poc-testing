package org.caalpeva.startwars.ws.api.itest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.caalpeva.starwars.configuration.AppConfig;
import org.caalpeva.starwars.repository.PeopleRepository;
import org.caalpeva.starwars.repository.StarShipRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@WebAppConfiguration
public class RepositoryITest {

	@Autowired
	private StarShipRepository starshipRepository;
	
	@Autowired
	private PeopleRepository peopleRepository;
	
	@Test
	public void testRepository() throws IOException {
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(1);
		ids.add(3);
		String name = starshipRepository.getStarshipThatAppearsMostInTheFilms(ids);
		if (name != null) {
			System.out.println(name + "-" + peopleRepository.getPilotsOfStarship(name));
		} else {
			System.out.println("NO DATA");
		}
	}
}