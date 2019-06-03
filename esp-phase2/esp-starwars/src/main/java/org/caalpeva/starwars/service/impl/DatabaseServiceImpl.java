package org.caalpeva.starwars.service.impl;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;

import javax.transaction.Transactional;

import org.caalpeva.starwars.repository.FilmRepository;
import org.caalpeva.starwars.repository.PeopleRepository;
import org.caalpeva.starwars.repository.PlanetRepository;
import org.caalpeva.starwars.repository.StarShipRepository;
import org.caalpeva.starwars.repository.model.Film;
import org.caalpeva.starwars.repository.model.People;
import org.caalpeva.starwars.repository.model.Planet;
import org.caalpeva.starwars.repository.model.Starship;
import org.caalpeva.starwars.service.DatabaseService;
import org.caalpeva.starwars.service.StarWarsApiCacheService;
import org.caalpeva.starwars.ws.dto.FilmDTO;
import org.caalpeva.starwars.ws.dto.PageDTO;
import org.caalpeva.starwars.ws.dto.PeopleDTO;
import org.caalpeva.starwars.ws.dto.PlanetDTO;
import org.caalpeva.starwars.ws.dto.StarshipDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Clase encargada de proporcionar una implementación para la interfaz de base de datos 
 * @author Alberto
 */
@Service
public class DatabaseServiceImpl implements DatabaseService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	@Qualifier("cacheRetrofit")
	private StarWarsApiCacheService starWarsApi;

	@Autowired
	private FilmRepository filmRepository;

	@Autowired
	private PeopleRepository peopleRepository;

	@Autowired
	private PlanetRepository planetRepository;

	@Autowired
	private StarShipRepository starShipRepository;
	
	/**
	 * Método encargado de realizar la importación de datos
	 */
	@Override
	@Transactional
	public void importData() throws IOException {
		starWarsApi.clearCache();
		deleteData();
		importFilms();
		importStarShips();
		importPlanets();
		importPeopleAndRelations();
	}

	/**
	 * Método encargado de eliminar los datos completamente
	 */
	@Override
	@Transactional
	public void deleteData() {
		peopleRepository.deleteAll();
		filmRepository.deleteAll();
		planetRepository.deleteAll();
		starShipRepository.deleteAll();
	}
	
	/**
	 * Método encargado de proporcionar todas las películas
	 */
	@Override
	public List<Film> findAllFilms() {
		return filmRepository.findAll();
	}

	/**
	 * Método encargado de formatear la información de la
	 * consulta en un formato mas adecuado para su recorrido
	 */
	@Override
	public Map<String, List<String>> getPeopleWithFilms() {
		Map<String, List<String>> map = new TreeMap<String, List<String>>();
		List<Object[]> resultSet = peopleRepository.getPeopleWithFilms();
		if (resultSet != null && resultSet.size() > 0) {
			String lastName = null;
			for (Object[] result: resultSet) {
				if (lastName == null || !lastName.equals(result[0])) {
					lastName = (String) result[0];
					map.put(lastName, new ArrayList<String>());
				}
				
				map.get(lastName).add((String) result[1]);
			} // for
		}
			
		return map;
	}
	
	@Override
	public String getFirstStarshipThatAppearsMostInTheFilms(List<Integer> filmIds) {
		return starShipRepository.getStarshipThatAppearsMostInTheFilms(filmIds);
	}
	
	/**
	 * Método encargado de proporcionar la lista de pilotos
	 * que conducen la nave que mas aparece en las películas seleccionadas.
	 */
	@Override
	public List<String> getPilotsOfStarship(String starshipName) {
		return peopleRepository.getPilotsOfStarship(starshipName);
	}
	
	/************************************************/
	/*************** METODOS PRIVADOS ***************/
	/************************************************/
	
	/**
	 * Método encargado de realizar la importación de datos a la tabla Films
	 * @throws IOException
	 */
	private void importFilms() throws IOException {
		int page = 1;
		PageDTO<FilmDTO> pageDTO;
		do {
			pageDTO = starWarsApi.getAllFilms(page++);
			List<FilmDTO> filmDtos = pageDTO.results;
			if (filmDtos != null && filmDtos.size() > 0) {
				for (FilmDTO filmDTO: filmDtos) {
					findOrSaveFilm(modelMapper.map(filmDTO, Film.class));
				} // for
			}
		} while (pageDTO.hasMore());
	}
	
	/**
	 * Método encargado de realizar la importación de datos
	 * a la tabla Starship y a la tabla intermedia con la tabla Film 
	 * @throws IOException
	 */
	private void importStarShips() throws IOException {
		int page = 1;
		PageDTO<StarshipDTO> pageDTO;
		do {
			pageDTO = starWarsApi.getAllStarships(page++);
			List<StarshipDTO> starshipDtos = pageDTO.results;
			if (starshipDtos != null && starshipDtos.size() > 0) {
				for (StarshipDTO starshipDTO: starshipDtos) {
					// Se inserta la información de un individuo
					Starship starship = modelMapper.map(starshipDTO, Starship.class);
					starship.setFilms(extractFilms(starshipDTO));
					starShipRepository.save(starship);
				} // for
			}
		} while (pageDTO.hasMore());
	}
	
	/**
	 * Método encargado de realizar la importación de datos a la tabla Planet  
	 * @throws IOException
	 */
	private void importPlanets() throws IOException {
		int page = 1;
		PageDTO<PlanetDTO> pageDTO;
		do {
			pageDTO = starWarsApi.getAllPlanets(page++);
			List<PlanetDTO> planetDtos = pageDTO.results;
			if (planetDtos != null && planetDtos.size() > 0) {
				for (PlanetDTO planetDTO: planetDtos) {
					planetRepository.save(modelMapper.map(planetDTO, Planet.class));
				} // for
			}
		} while (pageDTO.hasMore());
	}
	
	/**
	 * Método encargado de realizar la importación de datos
	 * a la tabla People y a sus tablas relacionadas Film y Starship
	 * @throws IOException
	 */
	public void importPeopleAndRelations() throws IOException {
		int page = 1;
		PageDTO<PeopleDTO> peoplePageDTO;
		do {
			peoplePageDTO = starWarsApi.getAllPeople(page++);
			List<PeopleDTO> peopleList = peoplePageDTO.results;
			if (peopleList != null && peopleList.size() > 0) {
				for (PeopleDTO peopleDTO: peopleList) {
					People people = modelMapper.map(peopleDTO, People.class);
					PlanetDTO planetDTO = starWarsApi.getPlanet(peopleDTO.getHomeWorldUrl());
					people.setHomeWorld(findOrSavePlanet(modelMapper.map(planetDTO, Planet.class)));
					people.setFilms(extractFilms(peopleDTO));
					people.setStarships(findOrSaveStarships(peopleDTO));
					peopleRepository.save(people);

				} // for
			}
		} while (peoplePageDTO.hasMore());
	}

	/**
	 * Método auxiliar encargado de encontrar o guardar una película
	 * @param film
	 * @return
	 */
	private Film findOrSaveFilm(Film film) {
		Optional<Film> optional = filmRepository.findByEpisodeId(film.getEpisodeId());
		if (optional.isPresent()) {
			return optional.get();
		}

		return filmRepository.save(film);
	}

	/**
	 * Método auxiliar encargado de encontrar o guardar una planeta
	 * @param planet
	 * @return
	 */
	private Planet findOrSavePlanet(Planet planet) {
		Optional<Planet> optional = planetRepository.findByName(planet.getName());
		if (optional.isPresent()) {
			return optional.get();
		}

		return planetRepository.save(planet);
	}
	
	/**
	 * Método auxiliar encargado de encontrar o guardar una nave
	 * @param starship
	 * @return
	 */
	private Starship findOrSaveStarship(Starship starship) {
		Optional<Starship> optional = starShipRepository.findByName(starship.getName());
		if (optional.isPresent()) {
			return optional.get();
		}

		return starShipRepository.save(starship);
	}
	
	/**
	 * Método auxiliar encargado de extraer la información de películas
	 * @param film
	 * @return
	 */
	private Set<Film> extractFilms(PeopleDTO peopleDTO) throws IOException {
		List<FilmDTO> filmDtos = new ArrayList<FilmDTO>();
		if (peopleDTO.getFilmsUrls() != null && peopleDTO.getFilmsUrls().size() > 0) {
			for (String url : peopleDTO.getFilmsUrls()) {
				filmDtos.add(starWarsApi.getFilm(url));
			} // for
		}

		return convertList(filmDtos);
	}
	
	/**
	 * Método auxiliar encargado de extraer la información de películas
	 * @param film
	 * @return
	 */
	private Set<Film> extractFilms(StarshipDTO starshipDTO) throws IOException {
		List<FilmDTO> filmDtos = new ArrayList<FilmDTO>();
		if (starshipDTO.getFilmsUrls() != null && starshipDTO.getFilmsUrls().size() > 0) {
			for (String url: starshipDTO.getFilmsUrls()) {
				filmDtos.add(starWarsApi.getFilm(url));
			} // for
		}
		
		return convertList(filmDtos);
	}
	
	/**
	 * Método auxiliar encargado de extraer la información de naves
	 * @param peopleDTO
	 * @return
	 * @throws IOException
	 */
	private Set<Starship> findOrSaveStarships(PeopleDTO peopleDTO) throws IOException {
		List<StarshipDTO> starshipDtos = new ArrayList<StarshipDTO>();
		if (peopleDTO.getStarshipsUrls() != null && peopleDTO.getStarshipsUrls().size() > 0) {
			for (String url: peopleDTO.getStarshipsUrls()) {
				starshipDtos.add(starWarsApi.getStarship(url));
			} // for
		}
		
		return convertStarshipList(starshipDtos);
	}

	/**
	 * Método auxiliar que mapea una lista de objetos FilmDTO a una lista de Film
	 * @param filmDtos
	 * @return
	 */
	private Set<Film> convertList(List<FilmDTO> filmDtos) {
		Set<Film> filmSet = new HashSet<Film>();
		Type targetListType = new TypeToken<List<Film>>() {}.getType();
		List<Film> films = modelMapper.map(filmDtos, targetListType);
		for (Film film: films) {
			filmSet.add(findOrSaveFilm(film));
		} // for
		
		return filmSet;
	}

	/**
	 * Método auxiliar que mapea una lista de objetos StarshipDTO a una lista de Starship
	 * @param starshipDtos
	 * @return
	 */
	private Set<Starship> convertStarshipList(List<StarshipDTO> starshipDtos) {
		Set<Starship> starshipSet = new HashSet<Starship>();
		Type targetListType = new TypeToken<List<Starship>>() {}.getType();
		List<Starship> starships = modelMapper.map(starshipDtos, targetListType);
		for (Starship starship: starships) {
			starshipSet.add(findOrSaveStarship(starship));
		} // for
		
		return starshipSet;
	}
}