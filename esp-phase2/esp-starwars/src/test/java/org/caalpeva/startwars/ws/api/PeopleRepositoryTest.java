package org.caalpeva.startwars.ws.api;

import java.io.IOException;
import java.util.List;

import org.caalpeva.starwars.configuration.AppConfig;
import org.caalpeva.starwars.repository.PeopleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@WebAppConfiguration
public class PeopleRepositoryTest {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PeopleRepository peopleRepository;
	
	@Test
	public void queryTest() throws IOException {
		List<Object[]> result = peopleRepository.getPeopleWithFilms();
		if (result != null) {
			for(Object[] objects: result) {
				for (Object object: objects) {
					System.out.print(object + " ");
				}
				System.out.println("");
			} // for
		}
	}
}