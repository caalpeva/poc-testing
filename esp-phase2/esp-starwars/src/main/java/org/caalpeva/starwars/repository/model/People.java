package org.caalpeva.starwars.repository.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PEOPLE")
public class People {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	public String name;
    public String birthYear;
    public String gender;
    public String hairColor;
    public String height;
    public String mass;
    public String skinColor;
    public Date created;
    public Date edited;
    public String url;

    @OneToOne(cascade={CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
	@JoinColumn(nullable=false)
    public Planet homeWorld;
    
	@ManyToMany(cascade = CascadeType.MERGE, fetch= FetchType.LAZY)
	private Set<Film> filmList = new HashSet<Film>();
	
	@ManyToMany(cascade = CascadeType.MERGE, fetch= FetchType.LAZY)
	private Set<Starship> starshipList = new HashSet<Starship>();
	    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	public Planet getHomeWorld() {
		return homeWorld;
	}
	public void setHomeWorld(Planet homeWorld) {
		this.homeWorld = homeWorld;
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
	public Set<Film> getFilmList() {
		return filmList;
	}
	public void setFilmList(Set<Film> filmList) {
		this.filmList = filmList;
	}
	public Set<Starship> getStarshipList() {
		return starshipList;
	}
	public void setStarshipList(Set<Starship> starshipList) {
		this.starshipList = starshipList;
	}	
}