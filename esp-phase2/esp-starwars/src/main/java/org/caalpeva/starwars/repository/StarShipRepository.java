package org.caalpeva.starwars.repository;

import java.util.Optional;

import org.caalpeva.starwars.repository.model.Starship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StarShipRepository extends JpaRepository<Starship, Integer> {
	public Optional<Starship> findByName(String name);
}
