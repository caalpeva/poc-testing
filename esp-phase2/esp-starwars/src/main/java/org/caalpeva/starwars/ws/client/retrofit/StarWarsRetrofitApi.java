package org.caalpeva.starwars.ws.client.retrofit;

import org.caalpeva.starwars.ws.dto.FilmDTO;
import org.caalpeva.starwars.ws.dto.PageDTO;
import org.caalpeva.starwars.ws.dto.PeopleDTO;
import org.caalpeva.starwars.ws.dto.StarshipDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface StarWarsRetrofitApi {

    @GET("people/")
    Call<PageDTO<PeopleDTO>> getAllPeople(@Query("page") int page);

    @GET("people/{id}/")
    Call<PeopleDTO> getPeople(@Path("id") int peopleId);

    @GET("films/")
    Call<PageDTO<FilmDTO>> getAllFilms(@Query("page") int page);

    @GET("films/{id}/")
    Call<FilmDTO> getFilm(@Path("id") int filmId);

    @GET("starships")
    Call<PageDTO<StarshipDTO>> getAllStarships(@Query("page") int page);

    @GET("starships/{id}/")
    Call<StarshipDTO> getStarship(@Path("id") int starshipId);

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