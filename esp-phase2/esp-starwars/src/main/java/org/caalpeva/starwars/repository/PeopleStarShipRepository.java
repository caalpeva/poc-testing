package org.caalpeva.starwars.repository;

import java.util.Optional;

import org.caalpeva.starwars.repository.model.PeopleStarship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleStarShipRepository extends JpaRepository<PeopleStarship, Integer> {
	public Optional<PeopleStarship> findByPerson_idAndStarship_id(int peopleId, int starshipId);
}
