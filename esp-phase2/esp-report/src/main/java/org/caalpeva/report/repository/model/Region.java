package org.caalpeva.report.repository.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="REGIONS")
public class Region {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false, unique = true)
	private String name;
	
	@OneToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH },
			mappedBy= "region", fetch = FetchType.LAZY)
	@Column(name = "region_id")
	private Set<Country> country;
	
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
	public Set<Country> getCountry() {
		return country;
	}
	public void setCountry(Set<Country> country) {
		this.country = country;
	}	
}