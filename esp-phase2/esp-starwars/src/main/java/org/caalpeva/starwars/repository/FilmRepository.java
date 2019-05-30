package org.caalpeva.starwars.repository;

import org.caalpeva.starwars.repository.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<Film, Integer> {
	//public Optional<Country> findByName(String name);
}
