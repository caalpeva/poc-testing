package org.caalpeva.starwars.ws.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PeopleDTO implements Serializable {

	private static final long serialVersionUID = 8789187728815417019L;

	public String name;

    @JsonProperty("birth_year")
    public String birthYear;

    @JsonProperty("films")
    public List<String> filmsUrls;

    public String gender;

    @JsonProperty("hair_color")
    public String hairColor;

    public String height;

    @JsonProperty("homeworld")
    public String homeWorldUrl;

    public String mass;

    @JsonProperty("skin_color")
    public String skinColor;

    public Date created;
    public Date edited;
    public String url;

    @JsonProperty("species")
    public List<String> speciesUrls;

    @JsonProperty("starships")
    public List<String> starshipsUrls;

    @JsonProperty("vehicles")
    public List<String> vehiclesUrls;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}

	public List<String> getFilmsUrls() {
		return filmsUrls;
	}

	public void setFilmsUrls(List<String> filmsUrls) {
		this.filmsUrls = filmsUrls;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHairColor() {
		return hairColor;
	}

	public void setHairColor(String hairColor) {
		this.hairColor = hairColor;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getHomeWorldUrl() {
		return homeWorldUrl;
	}

	public void setHomeWorldUrl(String homeWorldUrl) {
		this.homeWorldUrl = homeWorldUrl;
	}

	public String getMass() {
		return mass;
	}

	public void setMass(String mass) {
		this.mass = mass;
	}

	public String getSkinColor() {
		return skinColor;
	}

	public void setSkinColor(String skinColor) {
		this.skinColor = skinColor;
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

	public List<String> getSpeciesUrls() {
		return speciesUrls;
	}

	public void setSpeciesUrls(List<String> speciesUrls) {
		this.speciesUrls = speciesUrls;
	}

	public List<String> getStarshipsUrls() {
		return starshipsUrls;
	}

	public void setStarshipsUrls(List<String> starshipsUrls) {
		this.starshipsUrls = starshipsUrls;
	}

	public List<String> getVehiclesUrls() {
		return vehiclesUrls;
	}

	public void setVehiclesUrls(List<String> vehiclesUrls) {
		this.vehiclesUrls = vehiclesUrls;
	}
}