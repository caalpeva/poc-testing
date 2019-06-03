package org.caalpeva.starwars.repository;

import java.util.Optional;

import org.caalpeva.starwars.repository.model.Starship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StarShipRepository extends JpaRepository<Starship, Integer>, CustomStarshipRepository {
	public Optional<Starship> findByName(String name);
	
//	@Query("select s.name, count(*) as numTimes from Starship s inner join s.films f where f.id in :ids group by s.name order by numTimes")
//	public List<Object[]> getPilotOfStarshipThatMostHasAppeared(@Param("ids") List<Integer> ids);
}
