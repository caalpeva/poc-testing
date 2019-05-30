package org.caalpeva.starwars.repository;

import org.caalpeva.starwars.repository.model.Starship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StarshipRepository extends JpaRepository<Starship, Integer> {
	//public Optional<Country> findByName(String name);
}
