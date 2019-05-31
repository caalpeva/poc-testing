package org.caalpeva.starwars.service.impl;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.caalpeva.starwars.repository.FilmRepository;
import org.caalpeva.starwars.repository.PeopleRepository;
import org.caalpeva.starwars.repository.PeopleStarShipRepository;
import org.caalpeva.starwars.repository.PlanetRepository;
import org.caalpeva.starwars.repository.StarShipRepository;
import org.caalpeva.starwars.repository.model.Film;
import org.caalpeva.starwars.repository.model.Planet;
import org.caalpeva.starwars.repository.model.Starship;
import org.caalpeva.starwars.service.ImportService;
import org.caalpeva.starwars.service.StarWarsApiService;
import org.caalpeva.starwars.ws.dto.FilmDTO;
import org.caalpeva.starwars.ws.dto.PeopleDTO;
import org.caalpeva.starwars.ws.dto.StarshipDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ImportServiceImpl implements ImportService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	@Qualifier("retrofit")
	private StarWarsApiService starWarsApi;

	@Autowired
	private FilmRepository filmRepository;

	@Autowired
	private PeopleRepository peopleRepository;

	@Autowired
	private PlanetRepository planetRepository;

	@Autowired
	private StarShipRepository starShipRepository;
	
	@Autowired
	private PeopleStarShipRepository peopleStarShipRepository;

	@Override
	// @Transactional
	public void importDataFromWsapi() throws IOException {
//		int page = 1;
//		PageDTO<PeopleDTO> peoplePageDTO;
//		do {
//			peoplePageDTO = starWarsApi.getAllPeople(page++);
//			List<PeopleDTO> peopleList = peoplePageDTO.results;
//			if (peopleList != null && peopleList.size() > 0) {
//				for (PeopleDTO peopleDTO : peopleList) {
//					People people = modelMapper.map(peopleDTO, People.class);
//					PlanetDTO planetDTO = starWarsApi.getPlanet(peopleDTO.getHomeWorldUrl());
//					people.setHomeWorld(findOrSavePlanet(modelMapper.map(planetDTO, Planet.class)));
//					people.setFilmList(findOrSaveFilms(peopleDTO));
//					//people.setStarshipList(findOrSavePersonStarShip(peopleDTO));
//					peopleRepository.save(people);
//				} // for
//			}
//		} while (peoplePageDTO.hasMore());

//		page = 1;
//		PageDTO<StarshipDTO> starshipPageDTO;
//		do {
//			starshipPageDTO = starWarsApi.getAllStarships(page++);
//			List<StarshipDTO> starshipList = starshipPageDTO.results;
//			if (starshipList != null && starshipList.size() > 0) {
//				for (StarshipDTO starshipDTO : starshipList) {
//					if (starshipDTO.getPilotsUrls() != null) {
//						pilotadas += starshipDTO.getPilotsUrls().size();
//					}
//				} // for
//			}
//		} while (peoplePageDTO.hasMore());
		System.out.println("Pilotadas: " + pilotadas);
		System.out.println("Pilotos: " + pilotos.size());
	}

	/************************************************/
	/*************** METODOS PRIVADOS ***************/
	/************************************************/

	private Film findOrSaveFilm(Film film) {
		Optional<Film> optional = filmRepository.findByEpisodeId(film.getEpisodeId());
		if (optional.isPresent()) {
			return optional.get();
		}

		return filmRepository.save(film);
	}

	private Set<Film> findOrSaveFilms(PeopleDTO peopleDTO) throws IOException {
		List<FilmDTO> filmDTOList = new ArrayList<FilmDTO>();
		for (String filmUrl : peopleDTO.getFilmsUrls()) {
			filmDTOList.add(starWarsApi.getFilm(filmUrl));
		} // for

		// Se convierte una lista de objetos DTO a objetos de modelo
		Type targetListType = new TypeToken<List<Film>>() {
		}.getType();
		List<Film> films = modelMapper.map(filmDTOList, targetListType);
		Set<Film> filmSet = new HashSet<Film>();
		for (Film film : films) {
			filmSet.add(findOrSaveFilm(film));
		} // for

		return filmSet;
	}

	static int pilotadas = 0;
	static Set<String> pilotos = new HashSet<String>(); 

	private Set<Starship> findOrSavePersonStarShip(PeopleDTO peopleDTO) throws IOException {
		List<StarshipDTO> starShipDTOList = new ArrayList<StarshipDTO>();
		for (String startShipUrl : peopleDTO.getStarshipsUrls()) {
			pilotadas++;
			pilotos.add(startShipUrl);
			starShipDTOList.add(starWarsApi.getStarship(startShipUrl));
		} // for

		// Se convierte una lista de objetos DTO a objetos de modelo
		Type targetListType = new TypeToken<List<Starship>>() {
		}.getType();
		List<Starship> starships = modelMapper.map(starShipDTOList, targetListType);
		Set<Starship> starShipSet = new HashSet<Starship>();
		for (Starship starShip : starships) {
			starShipSet.add(findOrSaveStarShip(starShip));
		}
		
		//TODO: En este punto se debe crear la entidad intermedia
		// findByPerson_idAndStarship_id

		return starShipSet;
	}

	private Planet findOrSavePlanet(Planet planet) {
		Optional<Planet> optional = planetRepository.findByName(planet.getName());
		if (optional.isPresent()) {
			return optional.get();
		}

		return planetRepository.save(planet);
	}

	private Starship findOrSaveStarShip(Starship starship) {
		Optional<Starship> optional = starShipRepository.findByName(starship.getName());
		if (optional.isPresent()) {
			return optional.get();
		}

		return starShipRepository.save(starship);
	}

}