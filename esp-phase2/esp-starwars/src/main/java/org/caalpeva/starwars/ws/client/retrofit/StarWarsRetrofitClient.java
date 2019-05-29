package org.caalpeva.starwars.ws.client.retrofit;

import java.io.IOException;

import org.caalpeva.starwars.ws.api.StarWarsApi;
import org.caalpeva.starwars.ws.exception.HttpCommunicationException;
import org.caalpeva.starwars.ws.model.Film;
import org.caalpeva.starwars.ws.model.Page;
import org.caalpeva.starwars.ws.model.People;
import org.caalpeva.starwars.ws.model.Starship;

import retrofit2.Call;
import retrofit2.Response;

public class StarWarsRetrofitClient implements StarWarsApi {

	private StarWarsRetrofitClientApi wsClient;
	
	public StarWarsRetrofitClient() {
        wsClient = RetrofitClient.getClient(StarWarsRetrofitClientApi.class);
	}

	@Override
	public Page<People> getAllPeoples(int page) throws IOException {
		Call<Page<People>> call = wsClient.getAllPeople(page);
        Response<Page<People>> response = call.execute();
        checkResponse(response, call);
        return response.body();
	}
	
	@Override
	public People getPeople(int id) throws IOException {
		Call<People> call = wsClient.getPeople(id);
        Response<People> response = call.execute();
        checkResponse(response, call);
        return response.body();
	}

	@Override
	public Page<Film> getAllFilms(int page) throws IOException {
		Call<Page<Film>> call = wsClient.getAllFilms(page);
        Response<Page<Film>> response = call.execute();
        checkResponse(response, call);
        return response.body();
	}

	@Override
	public Film getFilm(int id) throws IOException {
		Call<Film> call = wsClient.getFilm(id);
        Response<Film> response = call.execute();
        checkResponse(response, call);
        return response.body();
	}

	@Override
	public Page<Starship> getAllStarships(int page) throws IOException {
		Call<Page<Starship>> call = wsClient.getAllStarships(page);
        Response<Page<Starship>> response = call.execute();
        checkResponse(response, call);
        return response.body();
	}

	@Override
	public Starship getStarship(int id) throws IOException {
		Call<Starship> call = wsClient.getStarship(id);
        Response<Starship> response = call.execute();
        checkResponse(response, call);
        return response.body();
	}
	
	private void checkResponse(Response response, Call call) throws HttpCommunicationException {
		if (!response.isSuccessful()) {
			throw new HttpCommunicationException(response.code(),
					call.request().url().url());
		}
	}
}