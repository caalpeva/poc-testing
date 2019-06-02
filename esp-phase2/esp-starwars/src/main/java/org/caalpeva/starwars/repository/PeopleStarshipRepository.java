package org.caalpeva.starwars.repository;

import java.util.Optional;

import org.caalpeva.starwars.repository.model.PeopleStarship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface PeopleStarshipRepository extends JpaRepository<PeopleStarship, Integer> {
	public Optional<PeopleStarship> findByPeople_IdAndStarship_Id(int peopleId, int starshipId);
}
