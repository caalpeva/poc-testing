package org.caalpeva.report.repository;

import java.util.List;
import java.util.Optional;

import org.caalpeva.report.repository.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer> {
	public Optional<Region> findByName(String name);
	
	/**
	 * Método que se encarga de obtener el numero de pedidos por cada región.
	 * @return
	 */
	@Query("select r.name, count(*) from Order o "
			+ "inner join o.country c "
			+ "inner join c.region r "
			+ "group by r.name "
			+ "order by r.name")
	public List<Object[]> getOrderCountGroupByRegion();
}