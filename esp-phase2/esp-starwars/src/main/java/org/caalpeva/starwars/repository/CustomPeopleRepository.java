package org.caalpeva.starwars.repository;

import java.util.List;

import org.caalpeva.starwars.repository.model.People;

public interface CustomPeopleRepository {
	public List<People> getPilotOfStarshipThatMostHasAppeared(List<Integer> filmIds);
}