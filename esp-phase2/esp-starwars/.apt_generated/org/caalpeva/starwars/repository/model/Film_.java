package org.caalpeva.starwars.repository.model;

import java.time.LocalDate;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Film.class)
public abstract class Film_ {

	public static volatile SetAttribute<Film, People> characters;
	public static volatile SingularAttribute<Film, Date> edited;
	public static volatile SingularAttribute<Film, LocalDate> releaseDate;
	public static volatile SetAttribute<Film, Starship> starships;
	public static volatile SingularAttribute<Film, String> director;
	public static volatile SingularAttribute<Film, Date> created;
	public static volatile SingularAttribute<Film, String> openingCrawl;
	public static volatile SingularAttribute<Film, String> producer;
	public static volatile SingularAttribute<Film, Integer> id;
	public static volatile SingularAttribute<Film, String> title;
	public static volatile SingularAttribute<Film, Integer> episodeId;
	public static volatile SingularAttribute<Film, String> url;

	public static final String CHARACTERS = "characters";
	public static final String EDITED = "edited";
	public static final String RELEASE_DATE = "releaseDate";
	public static final String STARSHIPS = "starships";
	public static final String DIRECTOR = "director";
	public static final String CREATED = "created";
	public static final String OPENING_CRAWL = "openingCrawl";
	public static final String PRODUCER = "producer";
	public static final String ID = "id";
	public static final String TITLE = "title";
	public static final String EPISODE_ID = "episodeId";
	public static final String URL = "url";

}

