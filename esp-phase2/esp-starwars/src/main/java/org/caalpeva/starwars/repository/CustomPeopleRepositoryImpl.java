package org.caalpeva.starwars.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.caalpeva.starwars.repository.model.People;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomPeopleRepositoryImpl implements CustomPeopleRepository {
  
    @Autowired
    private EntityManager entityManager;
 
	@Override
	public List<People> getPilotOfStarshipThatMostHasAppeared(List<Integer> filmIds) {
		 CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		    CriteriaQuery<People> cQuery = builder.createQuery(People.class);
		    Root<People> root = cQuery.from(People.class);
		    cQuery
		      .select(root)
		      .where(builder
		        .like(root.<String> get("name"), "%uke%"));
		    TypedQuery<People> query = entityManager.createQuery(cQuery);
		    return query.getResultList();
	}
}