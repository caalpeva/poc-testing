package org.caalpeva.report.repository;

import org.caalpeva.report.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
	
//	@Query("select s.movie.id from Showtimes s where s.movie.status = 'ACTIVE' and s.date = :date group by s.movie.id order by s.movie.id asc")
//	public List<Integer> findMovieIdsByShowtimeDate(@Param("date") Date date);
}