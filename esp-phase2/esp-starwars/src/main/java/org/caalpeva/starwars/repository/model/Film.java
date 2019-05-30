package org.caalpeva.starwars.repository.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.joda.time.DateTime;

@Entity
@Table(name = "FILMS")
public class Film {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	public String title;
    public int episodeId;
    public String openingCrawl;
    public String director;
    public String producer;
    public String url;
    public DateTime created;
    public DateTime edited;
    public LocalDate releaseDate;

//    public List<String> starshipsUrls;
//
//    public List<String> charactersUrls;
}