package org.caalpeva.starwars.ws.model;

import java.io.Serializable;
import java.util.List;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Starship implements Serializable {

	private static final long serialVersionUID = -9040485442700351176L;
	
	public String name;
	public String model;

	@JsonProperty("vehicle_class")
	public String vehicleClass;

	public String manufacturer;

	@JsonProperty("cost_in_credits")
	public String costInCredits;

	public String length;
	public String crew;
	public String passengers;

	@JsonProperty("max_atmosphering_speed")
	public String maxAtmospheringSpeed;

	@JsonProperty("cargo_capacity")
	public String cargoCapacity;

	public String consumables;
	public DateTime created;
	public DateTime edited;
	public String url;

	@JsonProperty("pilots")
	public List<String> pilotsUrls;

	@JsonProperty("films")
	public List<String> filmsUrls;

	@JsonProperty("starship_class")
	public String starshipClass;

	@JsonProperty("hyperdrive_rating")
	public String hyperdriveRating;

	@JsonProperty("MGLT")
	public String mglt;

}