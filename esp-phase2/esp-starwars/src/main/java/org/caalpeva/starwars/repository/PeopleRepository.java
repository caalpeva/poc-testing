package org.caalpeva.starwars.repository;

import java.util.List;
import java.util.Optional;

import org.caalpeva.starwars.repository.model.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends JpaRepository<People, Integer>, CustomPeopleRepository {
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
	
	//public People getPilotOfStarshipThatMostHasAppeared();
	
//	select p.name from people p
//	inner join PEOPLE_STARSHIPS ps on p.id = ps.people_id
//	inner join STARSHIPS s on ps.starship_id = s.id
//	where s.name = 
//	 (
//		select top 1 s.name from FILMS f
//		inner join PEOPLE_FILMS pf on f.id = pf.film_id
//		inner join PEOPLE p on pf.people_id = p.id
//		inner join PEOPLE_STARSHIPS ps on p.id = ps.people_id
//		inner join STARSHIPS s on ps.starship_id = s.id
//		group by s.name
//		having count(*) = (select distinct(count(*)) from FILMS f
//		inner join PEOPLE_FILMS pf on f.id = pf.film_id
//		inner join PEOPLE p on pf.people_id = p.id
//		inner join PEOPLE_STARSHIPS ps on p.id = ps.people_id
//		inner join STARSHIPS s on ps.starship_id = s.id
//		group by s.name))

}