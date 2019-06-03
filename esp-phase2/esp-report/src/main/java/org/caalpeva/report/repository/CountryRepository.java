package org.caalpeva.report.repository;

import java.util.List;
import java.util.Optional;

import org.caalpeva.report.repository.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

	public Optional<Country> findByName(String name);
	
	/**
	 * Método que se encarga de obtener el numero de pedidos por cada pais.
	 * @return
	 */
	@Query("select c.name, count(*) from Order o inner join o.country c group by c.name order by c.name")
	public List<Object[]> getOrderCountGroupByCountry();
}