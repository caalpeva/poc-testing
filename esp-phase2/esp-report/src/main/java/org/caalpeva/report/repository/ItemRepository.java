package org.caalpeva.report.repository;

import java.util.Optional;

import org.caalpeva.report.repository.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {
	
//	@Query("select s.movie.id from Showtimes s where s.movie.status = 'ACTIVE' and s.date = :date group by s.movie.id order by s.movie.id asc")
	public Optional<Item> findByItemType_IdAndUnitCostAndUnitPrice(int id, double unitCost, double unitPrice);
}