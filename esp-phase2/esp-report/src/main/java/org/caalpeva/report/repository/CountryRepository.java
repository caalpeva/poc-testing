package org.caalpeva.report.repository;

import java.util.Optional;

import org.caalpeva.report.repository.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

	public Optional<Country> findByName(String name);
}