package org.caalpeva.starwars.repository;

import java.util.List;
import java.util.Optional;

import org.caalpeva.starwars.repository.model.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends JpaRepository<People, Integer> {
	public Optional<People> findByName(String name);
	
	/**
	 * Método que se encarga de obtener el listado de películas agrupadas
	 * por cada uno de los personajes que ha participado en ellas.
	 * Este método es más eficiente que realizar una consulta por cada uno
	 * de los personajes para obtener su listado de películas.
	 * @return
	 */
	@Query("select p.name, f.title from People p inner join p.films f order by p.name")
	public List<Object[]> getPeopleWithFilms();

//	select p.name, count(*) from people p
//	inner join PEOPLE_FILMS pf on p.id = pf.characterList_id
//	inner join FILMS f on pf.filmList_id = f.id
//	group by p.name
	
	
	@Query("select p from People p "
			+ "inner join p.starships s "
			+ "where s.name in ("
				+ "select s.name from Film f "
				+ "inner join f.starships s "
				+ "where f.id in :ids "
				+ "group by s.name "
				+ "having count(*) = ("
					+ "select count(s.name) as A from Starship s "
						+ "inner join s.films f "
						+ "where f.id in :ids "
						+ "group by s.name "
						+ "order by A "
						+ "limit 1 "
					+ ")"
				+ ")")
	public List<People> getPilotOfStarshipThatMostHasAppeared(@Param("ids") List<Integer> ids);

}