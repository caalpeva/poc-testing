package org.caalpeva.report.repository;

import java.util.Optional;

import org.caalpeva.report.repository.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {
	public Optional<Region> findByName(String name);
}