package org.caalpeva.report.repository;

import java.util.List;
import java.util.Optional;

import org.caalpeva.report.repository.model.SalesChannel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesChannelRepository extends JpaRepository<SalesChannel, Integer> {
	public Optional<SalesChannel> findByName(String name);
	
	/**
	 * Método que se encarga de obtener el numero de pedidos por cada canal de venta.
	 * @return
	 */
	@Query("select s.name, count(*) from Order o inner join o.salesChannel s group by s.name order by s.name")
	public List<Object[]> getOrderCountGroupBySalesChannel();
}