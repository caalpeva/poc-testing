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
import org.caalpeva.starwars.repository.PeopleStarshipRepository;
import org.caalpeva.starwars.repository.PlanetRepository;
import org.caalpeva.starwars.repository.StarShipRepository;
import org.caalpeva.starwars.repository.model.Film;
import org.caalpeva.starwars.repository.model.People;
import org.caalpeva.starwars.repository.model.PeopleStarship;
import org.caalpeva.starwars.repository.model.Planet;
import org.caalpeva.starwars.repository.model.Starship;
import org.caalpeva.starwars.service.ImportService;
import org.caalpeva.starwars.service.StarWarsApiService;
import org.caalpeva.starwars.ws.dto.FilmDTO;
import org.caalpeva.starwars.ws.dto.PageDTO;
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
	private PeopleStarshipRepository peopleStarShipRepository;

	@Override
	// @Transactional
	public void importDataFromWsapi() throws IOException {
		int page = 1;
//		PageDTO<PeopleDTO> peoplePageDTO;
//		do {
//			peoplePageDTO = starWarsApi.getAllPeople(page++);
//			List<PeopleDTO> peopleList = peoplePageDTO.results;
//			if (peopleList != null && peopleList.size() > 0) {
//				for (PeopleDTO peopleDTO : peopleList) {
//					// Se insertan o actualizan las starships de un individuo
//					Type targetListType=new TypeToken<List<Starship>>(){}.getType();
//					List<Starship> starships = modelMapper.map(extractStarshipDtoList(peopleDTO), targetListType);
//					Set<Starship> starshipsOfPeople = saveOrUpdate(starships);
//					
//					// Se inserta la información de un individuo
//					People people = modelMapper.map(peopleDTO, People.class);
//					PlanetDTO planetDTO = starWarsApi.getPlanet(peopleDTO.getHomeWorldUrl());
//					people.setHomeWorld(findOrSavePlanet(modelMapper.map(planetDTO, Planet.class)));
//					people.setFilmList(findOrSaveFilms(peopleDTO));
//					peopleRepository.save(people);
//
//					// Se relaciona un individuo con sus starships
//					saveOrUpdate(people, starshipsOfPeople);
//				} // for
//			}
//		} while (peoplePageDTO.hasMore());

		page = 1;
		PageDTO<StarshipDTO> starshipPageDTO;
		do {
			starshipPageDTO = starWarsApi.getAllStarships(page++);
			List<StarshipDTO> starshipList = starshipPageDTO.results;
			if (starshipList != null && starshipList.size() > 0) {
				for (StarshipDTO starshipDTO : starshipList) {
					Starship starship = findOrSaveStarship(modelMapper.map(starshipDTO, Starship.class));
					if (starshipDTO.getPilotsUrls() != null && starshipDTO.getPilotsUrls().size() > 0) {
						vecespilotos += starshipDTO.getPilotsUrls().size();
						for(String url: starshipDTO.getPilotsUrls()) {
							PeopleDTO peopleDTO = starWarsApi.getPeople(url);
							People people = findOrSavePeople(modelMapper.map(peopleDTO, People.class));
							saveOrUpdate(people, starship, true);
						} // for
					}
				} // for
			}
		} while (starshipPageDTO.hasMore());
		System.out.println("VECES PILOTOS: " + vecespilotos);
	}

	public static int vecespilotos = 0;
	
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

	private List<StarshipDTO> extractStarshipDtoList(PeopleDTO peopleDTO) throws IOException {
		List<StarshipDTO> starshipDtoList = new ArrayList<StarshipDTO>();
		for (String startshipUrl : peopleDTO.getStarshipsUrls()) {
			starshipDtoList.add(starWarsApi.getStarship(startshipUrl));
		} // for

		return starshipDtoList;
	}

	private <D> List<D> convertList(Object list, Class<D> clazz) {
		Type targetListType = new TypeToken<List<D>>() {
		}.getType();
		return modelMapper.map(list, targetListType);
	}

	// Se convierte una lista de objetos DTO a objetos de modelo
//	Type targetListType=new TypeToken<List<Starship>>(){}.getType();
//	List<Starship> starships = modelMapper.map(starshipDTOList, targetListType);

	private Set<Starship> saveOrUpdate(List<Starship> starships) {
		Set<Starship> starshipSet = new HashSet<Starship>();
		for(Starship starship: starships) {
			starshipSet.add(findOrSaveStarship(starship));
		} // for
		
		return starshipSet;
	}

	private Set<PeopleStarship> saveOrUpdate(People people, Set<Starship> starshipSet) throws IOException {
		Set<PeopleStarship> peopleStarshipSet = new HashSet<PeopleStarship>();
		if (starshipSet != null && starshipSet.size() > 0) {
			for (Starship starship: starshipSet) {
				saveOrUpdate(people, starship, false);
			} // for
		}

		return peopleStarshipSet;
	}
	
	private PeopleStarship saveOrUpdate(People people, Starship starship, boolean isPilot) throws IOException {
		PeopleStarship peopleStarship = new PeopleStarship();
		peopleStarship.setPeople(people);
		peopleStarship.setStarship(starship);
		
		peopleStarship = findOrSavePeopleStarShip(peopleStarship);
		peopleStarship.setPilot(isPilot);
		peopleStarShipRepository.save(peopleStarship);

		return peopleStarship;
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

	private PeopleStarship findOrSavePeopleStarShip(PeopleStarship peopleStarship) {
		Optional<PeopleStarship> optional = peopleStarShipRepository.findByPeople_IdAndStarship_Id(
				peopleStarship.getPeople().getId(), peopleStarship.getStarship().getId());
		if (optional.isPresent()) {
			return optional.get();
		}

		return peopleStarShipRepository.save(peopleStarship);
	}

}