package org.caalpeva.starwars.repository.model;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

//@Entity
//@Table(name = "PEOPLE_STARSHIPS", uniqueConstraints = {
//		@UniqueConstraint(columnNames = {"people_id", "starship_id"})})
public class PeopleStarship {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	public boolean pilot;

	@ManyToOne(cascade = {CascadeType.MERGE}, fetch= FetchType.EAGER)
	@JoinColumn(name = "people_id", nullable = false)  
	private People people;
	
	@ManyToOne(cascade = {CascadeType.MERGE}, fetch= FetchType.EAGER)
	@JoinColumn(name = "starship_id", nullable = false)  
	private Starship starship;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isPilot() {
		return pilot;
	}

	public void setPilot(boolean pilot) {
		this.pilot = pilot;
	}

	public People getPeople() {
		return people;
	}

	public void setPeople(People people) {
		this.people = people;
	}

	public Starship getStarship() {
		return starship;
	}

	public void setStarship(Starship starship) {
		this.starship = starship;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PeopleStarship)) return false;
        PeopleStarship pStarship = (PeopleStarship) o;
        return Objects.equals(getPeople(), pStarship.getPeople())
        		&& Objects.equals(getStarship(), pStarship.getStarship());
    }
	
	@Override
	public int hashCode() {
		return Objects.hash(getPeople(), getStarship());
	}
}