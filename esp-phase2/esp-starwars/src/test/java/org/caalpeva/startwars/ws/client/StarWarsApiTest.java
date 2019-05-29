package org.caalpeva.startwars.ws.client;


import org.caalpeva.starwars.ws.client.retrofit.StarWarsRetrofitClient;
import org.caalpeva.starwars.ws.model.Film;
import org.caalpeva.starwars.ws.model.Page;
import org.caalpeva.starwars.ws.model.People;
import org.caalpeva.starwars.ws.model.Starship;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.assertj.core.api.Assertions.assertThat;

public class StarWarsApiTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private StarWarsRetrofitClient wsClient;

    @Before
    public void setUp() {
        wsClient = new StarWarsRetrofitClient();
    }

    @Test
    public void getAllFilms() throws Exception {
        Page<Film> films = wsClient.getAllFilms(1);
        int count = films.count;
        assertThat(count).isNotZero().isGreaterThan(0);
        for (Film film: films.results) {
            logger.debug(String.format("Episode %d - %-25s (%s)", film.episodeId, film.title, film.releaseDate));
        }
    }
    
    @Test
    public void getAllPersons() throws Exception {
        Page<People> persons = wsClient.getAllPeoples(1);
        int count = persons.count;
        assertThat(count).isNotZero().isGreaterThan(0);
        for (People people: persons.results) {
            logger.debug(String.format("%-20s %-20s (%s)", people.name, people.gender, people.birthYear));
        }
    }
    
    @Test
    public void getAllStarships() throws Exception {
        Page<Starship> starships = wsClient.getAllStarships(1);
        int count = starships.count;
        assertThat(count).isNotZero().isGreaterThan(0);
        for (Starship starship: starships.results) {
            logger.debug(String.format("%-30s (%s)", starship.name, starship.manufacturer));
        }
    }
}