package org.caalpeva.starwars.ws.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.caalpeva.starwars.ws.databind.LocalDateDeserializer;
import org.caalpeva.starwars.ws.databind.LocalDateSerializer;
import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Film implements Serializable {

	private static final long serialVersionUID = -3688502534233875305L;

	public String title;

    @JsonProperty("episode_id")
    public int episodeId;

    @JsonProperty("opening_crawl")
    public String openingCrawl;

    public String director;
    public String producer;
    public String url;
    public DateTime created;
    public DateTime edited;

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

    @JsonProperty("planets")
    public List<String> planetsUrls;

    @JsonProperty("characters")
    public List<String> charactersUrls;
}