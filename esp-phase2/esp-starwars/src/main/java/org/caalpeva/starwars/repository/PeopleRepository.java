package org.caalpeva.starwars.repository;

import org.caalpeva.starwars.repository.model.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends JpaRepository<People, Integer> {
	//public Optional<Country> findByName(String name);
}
