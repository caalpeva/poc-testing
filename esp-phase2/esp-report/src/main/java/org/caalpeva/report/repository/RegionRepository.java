package org.caalpeva.report.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.caalpeva.report.model.Country;
import org.caalpeva.report.model.ItemType;
import org.caalpeva.report.model.Order;
import org.caalpeva.report.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {
	public Optional<Region> findByName(String name);
}