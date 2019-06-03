package org.caalpeva.report.repository;

import java.util.List;

import org.caalpeva.report.repository.model.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order, Integer> {
	
	/**
	 * Método que se encarga de obtener el numero de pedidos por cada tipo de prioridad.
	 * @return
	 */
	@Query("select o.priority, count(*) from Order o group by o.priority order by o.priority")
	public List<Object[]> getOrderCountGroupByPriority();
}