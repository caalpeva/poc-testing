package org.caalpeva.starwars.service;

/**
 * Interfaz encargada de declarar los métodos para acceder al webservice
 * @author Alberto
 */
public interface StarWarsApiCacheService extends StarWarsApiService {
	public void cleanCache();
}