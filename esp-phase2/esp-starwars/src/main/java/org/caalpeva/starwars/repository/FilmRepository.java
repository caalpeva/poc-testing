package org.caalpeva.starwars.repository;

import java.util.Optional;

import org.caalpeva.starwars.repository.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<Film, Integer> {
	public Optional<Film> findByEpisodeId(int episodeId);
}
