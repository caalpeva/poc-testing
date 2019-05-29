package org.caalpeva.starwars.ws.client.retrofit;

import org.caalpeva.starwars.ws.model.Film;
import org.caalpeva.starwars.ws.model.Page;
import org.caalpeva.starwars.ws.model.People;
import org.caalpeva.starwars.ws.model.Starship;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface StarWarsRetrofitApi {

    @GET("people/")
    Call<Page<People>> getAllPeople(@Query("page") int page);

    @GET("people/{id}/")
    Call<People> getPeople(@Path("id") int peopleId);

    @GET("films/")
    Call<Page<Film>> getAllFilms(@Query("page") int page);

    @GET("films/{id}/")
    Call<Film> getFilm(@Path("id") int filmId);

    @GET("starships")
    Call<Page<Starship>> getAllStarships(@Query("page") int page);

    @GET("starships/{id}/")
    Call<Starship> getStarship(@Path("id") int starshipId);

//    @GET("vehicles/")
//    Call<Page<Vehicle>> getAllVehicles(@Query("page") Integer page);
//
//    @GET("vehicles/{id}/")
//    Call<Vehicle> getVehicle(@Path("id") int vehicleId);
//
//    @GET("species/")
//    Call<Page<Species>> getAllSpecies(@Query("page") int page);
//
//    @GET("species/{id}/")
//    Call<Species> getSpecies(@Path("id") int speciesId);
//
//    @GET("planets/")
//    Call<Page<Planet>> getAllPlanets(@Query("page") Integer page);
//
//    @GET("planets/{id}/")
//    Call<Planet> getPlanet(@Path("id") int planetId);

}