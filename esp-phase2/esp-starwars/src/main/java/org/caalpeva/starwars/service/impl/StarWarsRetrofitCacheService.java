package org.caalpeva.starwars.service.impl;

import java.io.IOException;

import org.caalpeva.starwars.service.StarWarsApiCacheService;
import org.caalpeva.starwars.service.StarWarsApiService;
import org.caalpeva.starwars.ws.dto.FilmDTO;
import org.caalpeva.starwars.ws.dto.PageDTO;
import org.caalpeva.starwars.ws.dto.PeopleDTO;
import org.caalpeva.starwars.ws.dto.PlanetDTO;
import org.caalpeva.starwars.ws.dto.StarshipDTO;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("cacheRetrofit")
public class StarWarsRetrofitCacheService implements StarWarsApiCacheService {

	@Autowired
	@Qualifier("retrofit")
	private StarWarsApiService swapiService;

	@Autowired
	private CacheManager cacheManager;

	private Cache<Integer, FilmDTO> filmCache;
	private Cache<Integer, StarshipDTO> starshipCache;
	private Cache<Integer, PeopleDTO> peopleCache;
	private Cache<Integer, PlanetDTO> planetCache;

	@Override
	public void cleanCache() {
		if (filmCache == null) {
			filmCache = cacheManager.createCache("filmCache", CacheConfigurationBuilder
					.newCacheConfigurationBuilder(Integer.class, FilmDTO.class, ResourcePoolsBuilder.heap(10)));
		}

		if (starshipCache == null) {
			starshipCache = cacheManager.createCache("starshipCache", CacheConfigurationBuilder
					.newCacheConfigurationBuilder(Integer.class, StarshipDTO.class, ResourcePoolsBuilder.heap(40)));
		}

		if (peopleCache == null) {
			peopleCache = cacheManager.createCache("peopleCache", CacheConfigurationBuilder
					.newCacheConfigurationBuilder(Integer.class, PeopleDTO.class, ResourcePoolsBuilder.heap(90)));
		}

		if (planetCache == null) {
			planetCache = cacheManager.createCache("planetCache", CacheConfigurationBuilder
					.newCacheConfigurationBuilder(Integer.class, PlanetDTO.class, ResourcePoolsBuilder.heap(65)));
		}

		filmCache.clear();
		starshipCache.clear();
		peopleCache.clear();
		planetCache.clear();
	}

//	   if (cache.getSquareNumberCache().containsKey(input)) {
//           return cache.getSquareNumberCache().get(input);
//       }
//
//       System.out.println("Calculating square value of " + input + 
//         " and caching result.");
//
//       int squaredValue = (int) Math.pow(input, 2);
//       cache.getSquareNumberCache().put(input, squaredValue);
//
//       return squaredValue;

	@Override
	public PageDTO<PeopleDTO> getAllPeople(int page) throws IOException {
		PageDTO<PeopleDTO> pageDto = swapiService.getAllPeople(page);
		for (PeopleDTO peopleDto: pageDto.results) {
			peopleCache.put(extractIdentifier(peopleDto.getUrl()), peopleDto);
		} // for

		return pageDto;
	}

	@Override
	public PeopleDTO getPeople(int id) throws IOException {
		if (peopleCache.containsKey(id)) {
			return peopleCache.get(id);
		}
		
		PeopleDTO peopleDto = swapiService.getPeople(id);
		peopleCache.put(id, peopleDto);
		return peopleDto;
	}

	@Override
	public PeopleDTO getPeople(String url) throws IOException {
		return getPeople(extractIdentifier(url));
	}

	@Override
	public PageDTO<FilmDTO> getAllFilms(int page) throws IOException {
		PageDTO<FilmDTO> pageDto = swapiService.getAllFilms(page);
		for (FilmDTO filmDto: pageDto.results) {
			filmCache.put(extractIdentifier(filmDto.getUrl()), filmDto);
		} // for

		return pageDto;
	}

	@Override
	public FilmDTO getFilm(int id) throws IOException {
		if (filmCache.containsKey(id)) {
			return filmCache.get(id);
		}
		
		FilmDTO filmDto = swapiService.getFilm(id);
		filmCache.put(id, filmDto);
		return filmDto;
	}

	@Override
	public FilmDTO getFilm(String url) throws IOException {
		return getFilm(extractIdentifier(url));
	}

	@Override
	public PageDTO<StarshipDTO> getAllStarships(int page) throws IOException {
		PageDTO<StarshipDTO> pageDto = swapiService.getAllStarships(page);
		for (StarshipDTO starshipDto: pageDto.results) {
			starshipCache.put(extractIdentifier(starshipDto.getUrl()), starshipDto);
		} // for

		return pageDto;
	}

	@Override
	public StarshipDTO getStarship(int id) throws IOException {
		if (starshipCache.containsKey(id)) {
			return starshipCache.get(id);
		}
		
		StarshipDTO starshipDto = swapiService.getStarship(id);
		starshipCache.put(id, starshipDto);
		return starshipDto;
	}

	@Override
	public StarshipDTO getStarship(String url) throws IOException {
		return getStarship(extractIdentifier(url));
	}

	@Override
	public PageDTO<PlanetDTO> getAllPlanets(int page) throws IOException {
		PageDTO<PlanetDTO> pageDto = swapiService.getAllPlanets(page);
		for (PlanetDTO planetDto: pageDto.results) {
			planetCache.put(extractIdentifier(planetDto.getUrl()), planetDto);
		} // for

		return pageDto;
	}

	@Override
	public PlanetDTO getPlanet(int id) throws IOException {
		if (planetCache.containsKey(id)) {
			return planetCache.get(id);
		}
		
		PlanetDTO planetDto = swapiService.getPlanet(id);
		planetCache.put(id, planetDto);
		return planetDto;
	}

	@Override
	public PlanetDTO getPlanet(String url) throws IOException {
		return getPlanet(extractIdentifier(url));
	}

	/**
	 * Método encargado de extraer el identificador de la url
	 * @param url
	 * @return
	 */
	private Integer extractIdentifier(String url) {
		if (url.charAt(url.length() - 1) == '/') {
			url = url.substring(0, url.length() - 1);
		}
		
		//FIXME: Mejorar esta solución
		return Integer.parseInt(url.substring(url.lastIndexOf('/') + 1));
	}
}