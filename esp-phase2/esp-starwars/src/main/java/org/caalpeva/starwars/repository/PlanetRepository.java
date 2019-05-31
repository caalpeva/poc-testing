package org.caalpeva.starwars.repository;

import java.util.Optional;

import org.caalpeva.starwars.repository.model.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, Integer> {
	public Optional<Planet> findByName(String name);
}
