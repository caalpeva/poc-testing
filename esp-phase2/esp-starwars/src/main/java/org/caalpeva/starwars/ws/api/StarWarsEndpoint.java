package org.caalpeva.starwars.ws.api;

/**
 * Enumerado que mantiene las url de los diferentes endpoints del webservices 
 * @author Alberto
 */
public enum StarWarsEndpoint {
	
    FILMS		(StarWarsApi.BASE_URL + "films/"),
    PEOPLE		(StarWarsApi.BASE_URL + "people/"),
    PLANETS		(StarWarsApi.BASE_URL + "planets/"),
    SPECIES		(StarWarsApi.BASE_URL + "species/"),
    STARSHIPS	(StarWarsApi.BASE_URL + "starships/"),
    VEHICLES	(StarWarsApi.BASE_URL + "vehicles/");
	
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