package org.caalpeva.starwars.service;

import java.io.IOException;

public interface ImportService {
//	public List<Film> findAll();
//	public Page<Film> findAll(Pageable pageable);
//	public List<Film> findAllActives();
//	public List<Film> findAllByShowtimeDate(Date date);
//	public Film findById(int movieId);
//	public void save(Film movie);
//	public List<FilmType> getMovieTypes();
//	public void delete(int movieId);
	public void importDataFromWsapi() throws IOException;
}