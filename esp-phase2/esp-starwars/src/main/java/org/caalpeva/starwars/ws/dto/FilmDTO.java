package org.caalpeva.starwars.ws.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.caalpeva.starwars.ws.databind.LocalDateDeserializer;
import org.caalpeva.starwars.ws.databind.LocalDateSerializer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class FilmDTO implements Serializable {

	private static final long serialVersionUID = -3688502534233875305L;

	public String title;

    @JsonProperty("episode_id")
    public int episodeId;

    @JsonProperty("opening_crawl")
    public String openingCrawl;

    public String director;
    public String producer;
    public String url;
    public Date created;
    public Date edited;

    @JsonProperty("release_date")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public LocalDate releaseDate;

    @JsonProperty("species")
    public List<String> speciesUrls;

    @JsonProperty("starships")
    public List<String> starshipsUrls;

    @JsonProperty("vehicles")
    public List<String> vehiclesUrls;

    @JsonProperty("characters")
    public List<String> charactersUrls;
    
    @JsonProperty("planets")
    public List<String> planetsUrls;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getEpisodeId() {
		return episodeId;
	}

	public void setEpisodeId(int episodeId) {
		this.episodeId = episodeId;
	}

	public String getOpeningCrawl() {
		return openingCrawl;
	}

	public void setOpeningCrawl(String openingCrawl) {
		this.openingCrawl = openingCrawl;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
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

	public List<String> getPlanetsUrls() {
		return planetsUrls;
	}

	public void setPlanetsUrls(List<String> planetsUrls) {
		this.planetsUrls = planetsUrls;
	}

	public List<String> getCharactersUrls() {
		return charactersUrls;
	}

	public void setCharactersUrls(List<String> charactersUrls) {
		this.charactersUrls = charactersUrls;
	}        
}