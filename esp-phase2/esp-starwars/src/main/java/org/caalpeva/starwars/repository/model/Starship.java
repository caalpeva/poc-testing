package org.caalpeva.starwars.repository.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.joda.time.DateTime;

@Entity
@Table(name = "STARSHIPS")
public class Starship {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	public String name;
	public String model;
	public String vehicleClass;
	public String manufacturer;
	public String costInCredits;
	public String length;
	public String crew;
	public String passengers;
	public String maxAtmospheringSpeed;
	public String cargoCapacity;
	public String consumables;
	public DateTime created;
	public DateTime edited;
	public String url;
	public String starshipClass;
	public String hyperdriveRating;
	public String mglt;
	
//	public List<String> pilotsUrls;
//
//	public List<String> filmsUrls;

}