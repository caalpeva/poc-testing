package org.caalpeva.starwars.ws.client.rest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.caalpeva.starwars.ws.api.StarWarsApi;
import org.caalpeva.starwars.ws.api.StarWarsEndpoint;
import org.caalpeva.starwars.ws.model.Film;
import org.caalpeva.starwars.ws.model.Page;
import org.caalpeva.starwars.ws.model.People;
import org.caalpeva.starwars.ws.model.Starship;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StarWarsApiClient implements StarWarsApi {

    public final String USER_AGENT_NAME = "Java-Client"; // "SWAPI-Java-Client/1.0";
	
	//@Autowired
	//private RestTemplate restTemplate;
	
	@Override
	public Page<People> getAllPeoples(int page) {
//		ResponseEntity<Page<Person>> personEntity = new RestTemplate().exchange(
//		StarWarsEndpoint.PEOPLE.getAllResourcesUrl(),
//		HttpMethod.GET, getHttpEntity(),
//		Page<Person>.class);
//		return personEntity.getBody();

		return null;
	}
    
	@Override
	public People getPeople(int id) {
	    Map<String, String> params = new HashMap<String, String>();
	    params.put("id", String.valueOf(id));
	    return new RestTemplate().exchange(
	    		StarWarsEndpoint.PEOPLE.getSpecificResourceUrl(),
	    		HttpMethod.GET, getHttpEntity(),
	    		People.class, params).getBody();
	}
	
	private HttpEntity<String> getHttpEntity() {
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    headers.add("user-agent", USER_AGENT_NAME);
	    return new HttpEntity<String>("parameters", headers);
	}

	@Override
	public Page<Film> getAllFilms(int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Film getFilm(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Starship> getAllStarships(int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Starship getStarship(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}