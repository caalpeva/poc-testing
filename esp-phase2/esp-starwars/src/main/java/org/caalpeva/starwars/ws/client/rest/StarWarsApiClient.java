package org.caalpeva.starwars.ws.client.rest;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.caalpeva.starwars.service.StarWarsApiService;
import org.caalpeva.starwars.ws.api.StarWarsEndpoint;
import org.caalpeva.starwars.ws.dto.FilmDTO;
import org.caalpeva.starwars.ws.dto.PageDTO;
import org.caalpeva.starwars.ws.dto.PeopleDTO;
import org.caalpeva.starwars.ws.dto.PlanetDTO;
import org.caalpeva.starwars.ws.dto.StarshipDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StarWarsApiClient implements StarWarsApiService {

    public final String USER_AGENT_NAME = "RestTemplate-Java-Client/1.0";
	
	//@Autowired
	//private RestTemplate restTemplate;
	
	@Override
	public PageDTO<PeopleDTO> getAllPeople(int page) {
//		ResponseEntity<Page<Person>> personEntity = new RestTemplate().exchange(
//		StarWarsEndpoint.PEOPLE.getAllResourcesUrl(),
//		HttpMethod.GET, getHttpEntity(),
//		Page<Person>.class);
//		return personEntity.getBody();

		return null;
	}
    
	@Override
	public PeopleDTO getPeople(int id) {
	    Map<String, String> params = new HashMap<String, String>();
	    params.put("id", String.valueOf(id));
	    return new RestTemplate().exchange(
	    		StarWarsEndpoint.PEOPLE.getSpecificResourceUrl(),
	    		HttpMethod.GET, getHttpEntity(),
	    		PeopleDTO.class, params).getBody();
	}
	
	private HttpEntity<String> getHttpEntity() {
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    headers.add("user-agent", USER_AGENT_NAME);
	    return new HttpEntity<String>("parameters", headers);
	}

	@Override
	public PageDTO<FilmDTO> getAllFilms(int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FilmDTO getFilm(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageDTO<StarshipDTO> getAllStarships(int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StarshipDTO getStarship(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageDTO<PlanetDTO> getAllPlanets(int page) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlanetDTO getPlanet(int id) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlanetDTO getPlanet(String url) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FilmDTO getFilm(String url) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StarshipDTO getStarship(String url) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PeopleDTO getPeople(String url) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}
}