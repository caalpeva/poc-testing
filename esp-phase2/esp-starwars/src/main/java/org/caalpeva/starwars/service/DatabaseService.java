package org.caalpeva.starwars.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface DatabaseService {
	public void importData() throws IOException;
	public void deleteData();
	public Map<String, List<String>> getPeopleWithFilms();
}