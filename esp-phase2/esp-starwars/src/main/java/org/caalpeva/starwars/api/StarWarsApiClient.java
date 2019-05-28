package org.caalpeva.starwars.api;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.caalpeva.starwars.api.model.People;
import org.caalpeva.starwars.api.model.PersonList;
import org.caalpeva.starwars.api.model.SWModelList;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StarWarsApiClient implements StarWarsApi {

    public final String BASE_URL = "https://swapi.co/api/";
    public final String USER_AGENT_NAME = "Java-Client"; // "SWAPI-Java-Client/1.0";
	
	//@Autowired
	//private RestTemplate restTemplate;
	
	@Override
	public SWModelList<People> getPeoples() {
		ResponseEntity<PersonList> personEntity = new RestTemplate().exchange(
	    		getUrl("people"), HttpMethod.GET, getHttpEntity(),
	    		PersonList.class);
		return personEntity.getBody();
	}
    
	@Override
	public People getPeople(int id) {
	    Map<String, String> params = new HashMap<String, String>();
	    params.put("id", String.valueOf(id));
	    return new RestTemplate().exchange(
	    		getUrl("people/{id}"), HttpMethod.GET, getHttpEntity(),
	    		People.class, params).getBody();
	}
	
	private String getUrl(String relativePath) {
		return BASE_URL + relativePath;
	}
	
	private HttpEntity<String> getHttpEntity() {
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    headers.add("user-agent", USER_AGENT_NAME);
	    return new HttpEntity<String>("parameters", headers);
	}
}