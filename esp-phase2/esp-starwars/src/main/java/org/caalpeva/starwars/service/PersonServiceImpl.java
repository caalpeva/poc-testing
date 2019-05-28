package org.caalpeva.starwars.service;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PersonServiceImpl implements PersonService {

	@Override
	public void importDataFromWsapi() {

		
	}

//	@Autowired
//	private MovieRepository movieRepository;
//	
//	@Override
//	public List<Film> findAll() {
//		return movieRepository.findAll();
//	}
//
//	@Override
//	public Page<Film> findAll(Pageable pageable) {
//		return movieRepository.findAll(pageable);
//	}
//	
//	@Override
//	public List<Film> findAllActives() {
//		return movieRepository.findByStatusOrderByTitle(Status.ACTIVE);
//	}
//
//	@Override
//	public List<Film> findAllByShowtimeDate(Date date) {
//		List<Integer> ids = movieRepository.findMovieIdsByShowtimeDate(date);
//		return movieRepository.findAllById(ids);
//	}
//
//	@Override
//	public Film findById(int movieId) {
//		Optional<Film> optional = movieRepository.findById(movieId);
//		return optional.isPresent()
//				? optional.get()
//				: null;
//	}
//
//	@Override
//	public void save(Film movie) {
//		movieRepository.save(movie);
//	}
//
//	@Override
//	public List<FilmType> getMovieTypes() {
//		return Arrays.asList(FilmType.values());
//	}
//
//	@Override
//	public void delete(int movieId) {
//		movieRepository.deleteById(movieId);
//	}
}