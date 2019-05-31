package org.caalpeva.starwars.ws.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StarshipDTO implements Serializable {

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
	public Date created;
	public Date edited;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getVehicleClass() {
		return vehicleClass;
	}

	public void setVehicleClass(String vehicleClass) {
		this.vehicleClass = vehicleClass;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getCostInCredits() {
		return costInCredits;
	}

	public void setCostInCredits(String costInCredits) {
		this.costInCredits = costInCredits;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getCrew() {
		return crew;
	}

	public void setCrew(String crew) {
		this.crew = crew;
	}

	public String getPassengers() {
		return passengers;
	}

	public void setPassengers(String passengers) {
		this.passengers = passengers;
	}

	public String getMaxAtmospheringSpeed() {
		return maxAtmospheringSpeed;
	}

	public void setMaxAtmospheringSpeed(String maxAtmospheringSpeed) {
		this.maxAtmospheringSpeed = maxAtmospheringSpeed;
	}

	public String getCargoCapacity() {
		return cargoCapacity;
	}

	public void setCargoCapacity(String cargoCapacity) {
		this.cargoCapacity = cargoCapacity;
	}

	public String getConsumables() {
		return consumables;
	}

	public void setConsumables(String consumables) {
		this.consumables = consumables;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getEdited() {
		return edited;
	}

	public void setEdited(Date edited) {
		this.edited = edited;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<String> getPilotsUrls() {
		return pilotsUrls;
	}

	public void setPilotsUrls(List<String> pilotsUrls) {
		this.pilotsUrls = pilotsUrls;
	}

	public List<String> getFilmsUrls() {
		return filmsUrls;
	}

	public void setFilmsUrls(List<String> filmsUrls) {
		this.filmsUrls = filmsUrls;
	}

	public String getStarshipClass() {
		return starshipClass;
	}

	public void setStarshipClass(String starshipClass) {
		this.starshipClass = starshipClass;
	}

	public String getHyperdriveRating() {
		return hyperdriveRating;
	}

	public void setHyperdriveRating(String hyperdriveRating) {
		this.hyperdriveRating = hyperdriveRating;
	}

	public String getMglt() {
		return mglt;
	}

	public void setMglt(String mglt) {
		this.mglt = mglt;
	}
}