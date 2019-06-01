package org.caalpeva.starwars.service;

import java.io.IOException;

public interface DatabaseService {
	public void importData() throws IOException;
	public void deleteData();
}