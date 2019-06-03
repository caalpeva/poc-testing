package org.caalpeva.starwars.repository.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(People.class)
public abstract class People_ {

	public static volatile SetAttribute<People, Film> films;
	public static volatile SingularAttribute<People, String> gender;
	public static volatile SingularAttribute<People, String> skinColor;
	public static volatile SingularAttribute<People, Date> edited;
	public static volatile SingularAttribute<People, Date> created;
	public static volatile SingularAttribute<People, String> mass;
	public static volatile SingularAttribute<People, String> url;
	public static volatile SingularAttribute<People, String> birthYear;
	public static volatile SetAttribute<People, Starship> starships;
	public static volatile SingularAttribute<People, String> name;
	public static volatile SingularAttribute<People, Integer> id;
	public static volatile SingularAttribute<People, String> hairColor;
	public static volatile SingularAttribute<People, Planet> homeWorld;
	public static volatile SingularAttribute<People, String> height;

	public static final String FILMS = "films";
	public static final String GENDER = "gender";
	public static final String SKIN_COLOR = "skinColor";
	public static final String EDITED = "edited";
	public static final String CREATED = "created";
	public static final String MASS = "mass";
	public static final String URL = "url";
	public static final String BIRTH_YEAR = "birthYear";
	public static final String STARSHIPS = "starships";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String HAIR_COLOR = "hairColor";
	public static final String HOME_WORLD = "homeWorld";
	public static final String HEIGHT = "height";

}

