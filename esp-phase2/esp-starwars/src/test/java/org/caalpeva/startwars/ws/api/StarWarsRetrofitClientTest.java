package org.caalpeva.startwars.ws.api;

import static org.assertj.core.api.Assertions.assertThat;

import org.caalpeva.starwars.service.impl.StarWarsRetrofitService;
import org.caalpeva.starwars.ws.dto.FilmDTO;
import org.caalpeva.starwars.ws.dto.PageDTO;
import org.caalpeva.starwars.ws.dto.PeopleDTO;
import org.caalpeva.starwars.ws.dto.StarshipDTO;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StarWarsRetrofitClientTest {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private StarWarsRetrofitService wsClient;

	@Before
	public void setUp() {
		wsClient = new StarWarsRetrofitService();
	}

	@Test
	public void getAllFilms() throws Exception {
		int pageNum = 1;
		PageDTO<FilmDTO> filmsPage;
		do {
			filmsPage = wsClient.getAllFilms(pageNum++);
			int count = filmsPage.count;
			assertThat(count).isNotZero().isGreaterThan(0);
			for (FilmDTO film : filmsPage.results) {
				logger.debug(String.format("Episode %d - %-25s (%s)", film.episodeId, film.title, film.releaseDate));
			} // for
		} while (filmsPage.hasMore());
	}

	@Test
	public void getAllPersons() throws Exception {
		int pageNum = 1;
		PageDTO<PeopleDTO> personsPage;
		do {
			personsPage = wsClient.getAllPeople(pageNum++);
			int count = personsPage.count;
			assertThat(count).isNotZero().isGreaterThan(0);
			for (PeopleDTO people : personsPage.results) {
				logger.debug(String.format("%-20s %-20s (%s)", people.name, people.gender, people.birthYear));
			} // for
		} while (personsPage.hasMore());
	}

	@Test
	public void getAllStarships() throws Exception {
		int pageNum = 1;
		PageDTO<StarshipDTO> starshipsPage;
		do {
			starshipsPage = wsClient.getAllStarships(pageNum++);
			int count = starshipsPage.count;
			assertThat(count).isNotZero().isGreaterThan(0);
			for (StarshipDTO starship : starshipsPage.results) {
				logger.debug(String.format("%-30s (%s)", starship.name, starship.manufacturer));
			} // for
		} while (starshipsPage.hasMore());
	}
}