package org.caalpeva.startwars.ws.client;

import org.aspectj.lang.annotation.Before;
import org.caalpeva.starwars.ws.client.retrofit.StarWarsRetrofitClient;
import org.caalpeva.starwars.ws.model.Film;
import org.caalpeva.starwars.ws.model.Page;
import org.caalpeva.starwars.ws.model.People;
import org.caalpeva.starwars.ws.model.Starship;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StarWarsRetrofitClientTest {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private StarWarsRetrofitClient wsClient;

	@Before
	public void setUp() {
		wsClient = new StarWarsRetrofitClient();
	}

	@Test
	public void getAllFilms() throws Exception {
		int pageNum = 1;
		Page<Film> filmsPage;
		do {
			filmsPage = wsClient.getAllFilms(pageNum++);
			int count = filmsPage.count;
			assertThat(count).isNotZero().isGreaterThan(0);
			for (Film film : filmsPage.results) {
				logger.debug(String.format("Episode %d - %-25s (%s)", film.episodeId, film.title, film.releaseDate));
			} // for
		} while (filmsPage.hasMore());
	}

	@Test
	public void getAllPersons() throws Exception {
		int pageNum = 1;
		Page<People> personsPage;
		do {
			personsPage = wsClient.getAllPeoples(pageNum++);
			int count = personsPage.count;
			assertThat(count).isNotZero().isGreaterThan(0);
			for (People people : personsPage.results) {
				logger.debug(String.format("%-20s %-20s (%s)", people.name, people.gender, people.birthYear));
			} // for
		} while (personsPage.hasMore());
	}

	@Test
	public void getAllStarships() throws Exception {
		int pageNum = 1;
		Page<Starship> starshipsPage;
		do {
			starshipsPage = wsClient.getAllStarships(pageNum++);
			int count = starshipsPage.count;
			assertThat(count).isNotZero().isGreaterThan(0);
			for (Starship starship : starshipsPage.results) {
				logger.debug(String.format("%-30s (%s)", starship.name, starship.manufacturer));
			} // for
		} while (starshipsPage.hasMore());
	}
}