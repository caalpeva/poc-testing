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
		starWarsApi.cleanCache();
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
					starship.setFilms(findOrSaveFilms(starshipDTO));
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
					people.setFilms(findOrSaveFilms(peopleDTO));
					people.setStarships(findOrSaveStarships(peopleDTO));
					peopleRepository.save(people);

				} // for
			}
		} while (peoplePageDTO.hasMore());
	}

	private Film findOrSaveFilm(Film film) {
		Optional<Film> optional = filmRepository.findByEpisodeId(film.getEpisodeId());
		if (optional.isPresent()) {
			return optional.get();
		}

		return filmRepository.save(film);
	}

	private Set<Film> findOrSaveFilms(PeopleDTO peopleDTO) throws IOException {
		List<FilmDTO> filmDtos = new ArrayList<FilmDTO>();
		if (peopleDTO.getFilmsUrls() != null && peopleDTO.getFilmsUrls().size() > 0) {
			for (String url : peopleDTO.getFilmsUrls()) {
				filmDtos.add(starWarsApi.getFilm(url));
			} // for
		}

		return convertList(filmDtos);
	}
	
	private Set<Film> findOrSaveFilms(StarshipDTO starshipDTO) throws IOException {
		List<FilmDTO> filmDtos = new ArrayList<FilmDTO>();
		if (starshipDTO.getFilmsUrls() != null && starshipDTO.getFilmsUrls().size() > 0) {
			for (String url: starshipDTO.getFilmsUrls()) {
				filmDtos.add(starWarsApi.getFilm(url));
			} // for
		}
		
		return convertList(filmDtos);
	}
	
	private Set<Starship> findOrSaveStarships(PeopleDTO peopleDTO) throws IOException {
		List<StarshipDTO> starshipDtos = new ArrayList<StarshipDTO>();
		if (peopleDTO.getStarshipsUrls() != null && peopleDTO.getStarshipsUrls().size() > 0) {
			for (String url: peopleDTO.getStarshipsUrls()) {
				starshipDtos.add(starWarsApi.getStarship(url));
			} // for
		}
		
		return convertStarshipList(starshipDtos);
	}

	private Set<Film> convertList(List<FilmDTO> filmDtos) {
		Set<Film> filmSet = new HashSet<Film>();
		Type targetListType = new TypeToken<List<Film>>() {}.getType();
		List<Film> films = modelMapper.map(filmDtos, targetListType);
		for (Film film: films) {
			filmSet.add(findOrSaveFilm(film));
		} // for
		
		return filmSet;
	}
	
	private Set<Starship> convertStarshipList(List<StarshipDTO> starshipDtos) {
		Set<Starship> starshipSet = new HashSet<Starship>();
		Type targetListType = new TypeToken<List<Starship>>() {}.getType();
		List<Starship> starships = modelMapper.map(starshipDtos, targetListType);
		for (Starship starship: starships) {
			starshipSet.add(findOrSaveStarship(starship));
		} // for
		
		return starshipSet;
	}
	
	private Set<Starship> saveOrUpdate(List<Starship> starships) {
		Set<Starship> starshipSet = new HashSet<Starship>();
		for(Starship starship: starships) {
			starshipSet.add(findOrSaveStarship(starship));
		} // for
		
		return starshipSet;
	}

	private Planet findOrSavePlanet(Planet planet) {
		Optional<Planet> optional = planetRepository.findByName(planet.getName());
		if (optional.isPresent()) {
			return optional.get();
		}

		return planetRepository.save(planet);
	}
	
	private People findOrSavePeople(People people) {
		Optional<People> optional = peopleRepository.findByName(people.getName());
		if (optional.isPresent()) {
			return optional.get();
		}

		return peopleRepository.save(people);
	}

	private Starship findOrSaveStarship(Starship starship) {
		Optional<Starship> optional = starShipRepository.findByName(starship.getName());
		if (optional.isPresent()) {
			return optional.get();
		}

		return starShipRepository.save(starship);
	}

	/**
	 * 
	 * @param peopleStarship
	 * @return
	 */
//	private PeopleStarship findOrSavePeopleStarShip(PeopleStarship peopleStarship) {
//		Optional<PeopleStarship> optional = peopleStarShipRepository.findByPeople_IdAndStarship_Id(
//				peopleStarship.getPeople().getId(), peopleStarship.getStarship().getId());
//		if (optional.isPresent()) {
//			return optional.get();
//		}
//
//		return peopleStarShipRepository.save(peopleStarship);
//	}

	/**
	 * Método encargado de proporcionar la información de la consulta en un formato mas adecuado para su utilización
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
	public List<Film> findAllFilms() {
		return filmRepository.findAll();
	}

	@Override
	public List<People> getPilotOfStarshipThatMostHasAppeared(List<Integer> filmsIds) {
		// TODO: Descomentar cuando esté resuelta esta consulta
		//return peopleRepository.getPilotOfStarshipThatMostHasAppeared(filmsIds);
		return null;
	}
}