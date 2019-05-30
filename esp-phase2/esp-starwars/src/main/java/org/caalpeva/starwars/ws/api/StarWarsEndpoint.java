package org.caalpeva.starwars.ws.api;

import org.caalpeva.starwars.service.StarWarsApiService;

/**
 * Enumerado que mantiene las url de los diferentes endpoints del webservices 
 * @author Alberto
 */
public enum StarWarsEndpoint {
	
    FILMS		(StarWarsApiService.BASE_URL + "films/"),
    PEOPLE		(StarWarsApiService.BASE_URL + "people/"),
    PLANETS		(StarWarsApiService.BASE_URL + "planets/"),
    SPECIES		(StarWarsApiService.BASE_URL + "species/"),
    STARSHIPS	(StarWarsApiService.BASE_URL + "starships/"),
    VEHICLES	(StarWarsApiService.BASE_URL + "vehicles/");
	
	private String url;
	
	private StarWarsEndpoint(String url) {
		this.url = url;
	}

	public String getAllResourcesUrl() {
		return url;
	}
	
	public String getSpecificResourceUrl() {
		return url + "{id}";
	}
}