package org.caalpeva.starwars.service.impl;

import java.io.IOException;

import org.caalpeva.starwars.repository.PeopleRepository;
import org.caalpeva.starwars.repository.model.People;
import org.caalpeva.starwars.service.ImportService;
import org.caalpeva.starwars.service.StarWarsApiService;
import org.caalpeva.starwars.ws.dto.PeopleDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ImportServiceImpl implements ImportService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	@Qualifier("retrofit")
	private StarWarsApiService starWarsApi;
	
	@Autowired
	private PeopleRepository peopleRepository;
	
	@Override
	public void importDataFromWsapi() throws IOException {
		PeopleDTO peopleDto = starWarsApi.getPeople(1);
		People people = modelMapper.map(peopleDto, People.class);
		peopleRepository.save(people);
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