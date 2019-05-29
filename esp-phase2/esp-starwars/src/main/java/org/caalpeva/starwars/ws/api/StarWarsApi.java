package org.caalpeva.starwars.ws.api;

import java.io.IOException;

import org.caalpeva.starwars.ws.model.Film;
import org.caalpeva.starwars.ws.model.Page;
import org.caalpeva.starwars.ws.model.People;
import org.caalpeva.starwars.ws.model.Starship;

/**
 * Interfaz encargada de declarar los métodos para acceder al webservice
 * @author Alberto
 */
public interface StarWarsApi {

	public static final String BASE_URL = "https://swapi.co/api/";
	
	Page<People> getAllPeoples(int page) throws IOException;
    People getPeople(int id) throws IOException;
    
    Page<Film> getAllFilms(int page) throws IOException;
    Film getFilm(int id) throws IOException;

    Page<Starship> getAllStarships(int page) throws IOException;
    Starship getStarship(int id) throws IOException;
}