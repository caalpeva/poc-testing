package org.caalpeva.starwars.service.impl;

import java.io.IOException;

import org.caalpeva.starwars.service.StarWarsApiService;
import org.caalpeva.starwars.ws.client.retrofit.RetrofitServiceGenerator;
import org.caalpeva.starwars.ws.client.retrofit.StarWarsRetrofitApi;
import org.caalpeva.starwars.ws.dto.FilmDTO;
import org.caalpeva.starwars.ws.dto.PageDTO;
import org.caalpeva.starwars.ws.dto.PeopleDTO;
import org.caalpeva.starwars.ws.dto.PlanetDTO;
import org.caalpeva.starwars.ws.dto.StarshipDTO;
import org.caalpeva.starwars.ws.exception.HttpCommunicationException;
import org.springframework.stereotype.Service;

import retrofit2.Call;
import retrofit2.Response;

@Service("retrofit")
public class StarWarsRetrofitService implements StarWarsApiService {

	private StarWarsRetrofitApi wsClient;
	
	public StarWarsRetrofitService() {
        wsClient = RetrofitServiceGenerator.createService(StarWarsApiService.BASE_URL, StarWarsRetrofitApi.class);
	}

	@Override
	public PageDTO<PeopleDTO> getAllPeople(int page) throws IOException {
		Call<PageDTO<PeopleDTO>> call = wsClient.getAllPeople(page);
        Response<PageDTO<PeopleDTO>> response = call.execute();
        checkResponse(response, call);
        return response.body();
	}
	
	@Override
	public PeopleDTO getPeople(int id) throws IOException {
		Call<PeopleDTO> call = wsClient.getPeople(id);
        Response<PeopleDTO> response = call.execute();
        checkResponse(response, call);
        return response.body();
	}

	@Override
	public PageDTO<FilmDTO> getAllFilms(int page) throws IOException {
		Call<PageDTO<FilmDTO>> call = wsClient.getAllFilms(page);
        Response<PageDTO<FilmDTO>> response = call.execute();
        checkResponse(response, call);
        return response.body();
	}

	@Override
	public FilmDTO getFilm(int id) throws IOException {
		Call<FilmDTO> call = wsClient.getFilm(id);
        Response<FilmDTO> response = call.execute();
        checkResponse(response, call);
        return response.body();
	}
	
	@Override
	public FilmDTO getFilm(String url) throws IOException {
		Call<FilmDTO> call = wsClient.getFilm(url);
        Response<FilmDTO> response = call.execute();
        checkResponse(response, call);
        return response.body();
	}

	@Override
	public PageDTO<StarshipDTO> getAllStarships(int page) throws IOException {
		Call<PageDTO<StarshipDTO>> call = wsClient.getAllStarships(page);
        Response<PageDTO<StarshipDTO>> response = call.execute();
        checkResponse(response, call);
        return response.body();
	}

	@Override
	public StarshipDTO getStarship(int id) throws IOException {
		Call<StarshipDTO> call = wsClient.getStarship(id);
        Response<StarshipDTO> response = call.execute();
        checkResponse(response, call);
        return response.body();
	}
	
	@Override
	public StarshipDTO getStarship(String url) throws IOException {
		Call<StarshipDTO> call = wsClient.getStarship(url);
        Response<StarshipDTO> response = call.execute();
        checkResponse(response, call);
        return response.body();
	}
	
	@Override
	public PageDTO<PlanetDTO> getAllPlanets(int page) throws IOException {
		Call<PageDTO<PlanetDTO>> call = wsClient.getAllPlanets(page);
        Response<PageDTO<PlanetDTO>> response = call.execute();
        checkResponse(response, call);
        return response.body();
	}

	@Override
	public PlanetDTO getPlanet(int id) throws IOException {
		Call<PlanetDTO> call = wsClient.getPlanet(id);
        Response<PlanetDTO> response = call.execute();
        checkResponse(response, call);
        return response.body();
	}
	
	@Override
	public PlanetDTO getPlanet(String url) throws IOException {
		Call<PlanetDTO> call = wsClient.getPlanet(url);
        Response<PlanetDTO> response = call.execute();
        checkResponse(response, call);
        return response.body();
	}
	
	private void checkResponse(Response<?> response, Call<?> call) throws HttpCommunicationException {
		if (!response.isSuccessful()) {
			throw new HttpCommunicationException(response.code(),
					call.request().url().url());
		}
	}
}