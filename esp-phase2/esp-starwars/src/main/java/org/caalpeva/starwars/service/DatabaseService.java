package org.caalpeva.starwars.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.caalpeva.starwars.repository.model.Film;

/**
 * Interfaz con los métodos de acceso a base de datos 
 * @author Alberto
 */
public interface DatabaseService {
	public void importData() throws IOException;
	public void deleteData();
	public List<Film> findAllFilms();
	public Map<String, List<String>> getPeopleWithFilms();
	public String getFirstStarshipThatAppearsMostInTheFilms(List<Integer> filmIds);
	public List<String> getPilotsOfStarship(String starshipName);
}