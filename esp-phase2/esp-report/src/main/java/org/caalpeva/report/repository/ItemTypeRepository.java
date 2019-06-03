package org.caalpeva.report.repository;

import java.util.List;
import java.util.Optional;

import org.caalpeva.report.repository.model.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemTypeRepository extends JpaRepository<ItemType, Integer> {
	public Optional<ItemType> findByName(String name);
	
	/**
	 * Método que se encarga de obtener el numero de pedidos agrupados por cada tipo de item.
	 * @return
	 */
	@Query("select t.name, count(*) from ItemType t "
			+ "inner join t.items i "
			+ "inner join i.orders o "
			+ "group by t.name "
			+ "order by t.name")
	public List<Object[]> getOrderCountGroupByItemType();
}