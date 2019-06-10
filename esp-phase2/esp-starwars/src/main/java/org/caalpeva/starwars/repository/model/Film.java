package org.caalpeva.starwars.repository.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "FILMS")
public class Film {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	public String title;
	
	@Column(nullable = false, unique = true)
    public int episodeId;
	
	@Lob
    public String openingCrawl;
    public String director;
    public String producer;
    public String url;
    public Date created;
    public Date edited;
    public LocalDate releaseDate;

	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH },
			mappedBy= "films", fetch = FetchType.LAZY)
    public Set<People> characters;
	
	@ManyToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE },
			mappedBy= "films", fetch = FetchType.LAZY)
    public Set<Starship> starships;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public Set<People> getCharacters() {
		return characters;
	}

	public void setCharacters(Set<People> characters) {
		this.characters = characters;
	}
	
	public Set<Starship> getStarships() {
		return starships;
	}

	public void setStarships(Set<Starship> starships) {
		this.starships = starships;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Film)) return false;
        Film film = (Film) o;
        return Objects.equals(getEpisodeId(), film.getEpisodeId());
    }
	
	@Override
	public int hashCode() {
		return Objects.hash(getEpisodeId());
	}
}