package org.caalpeva.starwars.ws.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SpecieDTO implements Serializable {

	private static final long serialVersionUID = 6078501938755717405L;
	
	public String name;
    public String designation;
    public String classification;

    @JsonProperty("average_height")
    public String averageHeight;

    @JsonProperty("eye_colors")
    public String eyeColors;

    @JsonProperty("hair_colors")
    public String hairColors;

    @JsonProperty("skin_colors")
    public String skinColors;

    @JsonProperty("average_lifespan")
    public String averageLifespan;
    
    @JsonProperty("homeworld")
    public String homeWorldUrl;

    public String created;
    public String language;
    public String edited;
    public String url;
    
    @JsonProperty("people")
    public List<String> peopleUrls;

    @JsonProperty("films")
    public List<String> filmsUrls;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getAverageHeight() {
		return averageHeight;
	}

	public void setAverageHeight(String averageHeight) {
		this.averageHeight = averageHeight;
	}

	public String getEyeColors() {
		return eyeColors;
	}

	public void setEyeColors(String eyeColors) {
		this.eyeColors = eyeColors;
	}

	public String getHairColors() {
		return hairColors;
	}

	public void setHairColors(String hairColors) {
		this.hairColors = hairColors;
	}

	public String getSkinColors() {
		return skinColors;
	}

	public void setSkinColors(String skinColors) {
		this.skinColors = skinColors;
	}

	public String getAverageLifespan() {
		return averageLifespan;
	}

	public void setAverageLifespan(String averageLifespan) {
		this.averageLifespan = averageLifespan;
	}

	public String getHomeWorldUrl() {
		return homeWorldUrl;
	}

	public void setHomeWorldUrl(String homeWorldUrl) {
		this.homeWorldUrl = homeWorldUrl;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getEdited() {
		return edited;
	}

	public void setEdited(String edited) {
		this.edited = edited;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<String> getPeopleUrls() {
		return peopleUrls;
	}

	public void setPeopleUrls(List<String> peopleUrls) {
		this.peopleUrls = peopleUrls;
	}

	public List<String> getFilmsUrls() {
		return filmsUrls;
	}

	public void setFilmsUrls(List<String> filmsUrls) {
		this.filmsUrls = filmsUrls;
	}
}