package org.caalpeva.starwars.api;

import org.caalpeva.starwars.api.model.People;
import org.caalpeva.starwars.api.model.SWModelList;

public interface StarWarsApi {

	SWModelList<People> getPeoples();
    People getPeople(int id);
    //<SWModelList<Film>> getAllFilms(int page);

//    Call<Film> getFilm(@Path("id") int filmId);
//
//    Call<SWModelList<Starship>> getAllStarships(@Query("page") int page);
//
//    Call<Starship> getStarship(@Path("id") int starshipId);
//
//    Call<SWModelList<Vehicle>> getAllVehicles(@Query("page") Integer page);
//
//    Call<Vehicle> getVehicle(@Path("id") int vehicleId);
//
//    Call<SWModelList<Species>> getAllSpecies(@Query("page") int page);
//
//    Call<Species> getSpecies(@Path("id") int speciesId);
//
//    Call<SWModelList<Planet>> getAllPlanets(@Query("page") Integer page);
//
//    Call<Planet> getPlanet(@Path("id") int planetId);
}