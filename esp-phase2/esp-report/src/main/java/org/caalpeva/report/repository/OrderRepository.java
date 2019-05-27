package org.caalpeva.report.repository;

import org.caalpeva.report.model.Order;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order, Integer> {
}