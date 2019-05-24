package org.caalpeva.report.repository;

import java.util.Optional;

import org.caalpeva.report.model.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemTypeRepository extends JpaRepository<ItemType, Integer> {
	public Optional<ItemType> findByName(String name);
}