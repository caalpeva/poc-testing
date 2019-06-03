package org.caalpeva.starwars.repository;

import java.util.List;

public interface CustomStarshipRepository {
    public String getStarshipThatAppearsMostInTheFilms(List<Integer> filmIds);
}
