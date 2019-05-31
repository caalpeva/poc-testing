package org.caalpeva.starwars.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.caalpeva.starwars.repository.PeopleRepository;
import org.caalpeva.starwars.repository.PlanetRepository;
import org.caalpeva.starwars.repository.model.People;
import org.caalpeva.starwars.repository.model.Planet;
import org.caalpeva.starwars.service.ImportService;
import org.caalpeva.starwars.service.StarWarsApiService;
import org.caalpeva.starwars.ws.dto.PageDTO;
import org.caalpeva.starwars.ws.dto.PeopleDTO;
import org.caalpeva.starwars.ws.dto.PlanetDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ImportServiceImpl implements ImportService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	@Qualifier("retrofit")
	private StarWarsApiService starWarsApi;
	
	@Autowired
	private PeopleRepository peopleRepository;
	
	@Autowired
	private PlanetRepository planetRepository;
	
	@Override
	//@Transactional
	public void importDataFromWsapi() throws IOException {
		int page = 1;
		PageDTO<PeopleDTO> peoplePageDTO;
		do {
			peoplePageDTO = starWarsApi.getAllPeople(page++);
			List<PeopleDTO> peopleList = peoplePageDTO.results;
			if (peopleList != null && peopleList.size() > 0) {
				for(PeopleDTO peopleDTO: peopleList) {
					People people = modelMapper.map(peopleDTO, People.class);
					PlanetDTO planetDTO = starWarsApi.getPlanet(peopleDTO.getHomeWorldUrl());
					people.setHomeWorldUrl(findOrSavePlanet(modelMapper.map(planetDTO, Planet.class)));
					
					
					
					peopleRepository.save(people);					
				} // for
			}
		} while(peoplePageDTO.hasMore());
	}

	public Planet findOrSavePlanet(Planet planet) {
		Optional<Planet> optional = planetRepository.findByName(planet.getName());
		if (optional.isPresent()) {
			return optional.get();
		}
		
		return planetRepository.save(planet);
	}

}