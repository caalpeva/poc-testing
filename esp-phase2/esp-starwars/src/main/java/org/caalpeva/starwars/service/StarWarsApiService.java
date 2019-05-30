package org.caalpeva.starwars.service;

import java.io.IOException;

import org.caalpeva.starwars.ws.dto.FilmDTO;
import org.caalpeva.starwars.ws.dto.PageDTO;
import org.caalpeva.starwars.ws.dto.PeopleDTO;
import org.caalpeva.starwars.ws.dto.StarshipDTO;

/**
 * Interfaz encargada de declarar los métodos para acceder al webservice
 * @author Alberto
 */
public interface StarWarsApiService {

	public static final String BASE_URL = "https://swapi.co/api/";
	
	PageDTO<PeopleDTO> getAllPeoples(int page) throws IOException;
    PeopleDTO getPeople(int id) throws IOException;
    
    PageDTO<FilmDTO> getAllFilms(int page) throws IOException;
    FilmDTO getFilm(int id) throws IOException;

    PageDTO<StarshipDTO> getAllStarships(int page) throws IOException;
    StarshipDTO getStarship(int id) throws IOException;
}