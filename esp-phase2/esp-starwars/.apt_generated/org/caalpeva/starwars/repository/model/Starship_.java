package org.caalpeva.starwars.repository.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Starship.class)
public abstract class Starship_ {

	public static volatile SetAttribute<Starship, People> peoples;
	public static volatile SetAttribute<Starship, Film> films;
	public static volatile SingularAttribute<Starship, String> passengers;
	public static volatile SingularAttribute<Starship, Date> edited;
	public static volatile SingularAttribute<Starship, String> consumables;
	public static volatile SingularAttribute<Starship, Date> created;
	public static volatile SingularAttribute<Starship, String> length;
	public static volatile SingularAttribute<Starship, String> costInCredits;
	public static volatile SingularAttribute<Starship, String> cargoCapacity;
	public static volatile SingularAttribute<Starship, String> url;
	public static volatile SingularAttribute<Starship, String> manufacturer;
	public static volatile SingularAttribute<Starship, String> crew;
	public static volatile SingularAttribute<Starship, String> maxAtmospheringSpeed;
	public static volatile SingularAttribute<Starship, String> mglt;
	public static volatile SingularAttribute<Starship, String> starshipClass;
	public static volatile SingularAttribute<Starship, String> name;
	public static volatile SingularAttribute<Starship, String> hyperdriveRating;
	public static volatile SingularAttribute<Starship, String> vehicleClass;
	public static volatile SingularAttribute<Starship, String> model;
	public static volatile SingularAttribute<Starship, Integer> id;

	public static final String PEOPLES = "peoples";
	public static final String FILMS = "films";
	public static final String PASSENGERS = "passengers";
	public static final String EDITED = "edited";
	public static final String CONSUMABLES = "consumables";
	public static final String CREATED = "created";
	public static final String LENGTH = "length";
	public static final String COST_IN_CREDITS = "costInCredits";
	public static final String CARGO_CAPACITY = "cargoCapacity";
	public static final String URL = "url";
	public static final String MANUFACTURER = "manufacturer";
	public static final String CREW = "crew";
	public static final String MAX_ATMOSPHERING_SPEED = "maxAtmospheringSpeed";
	public static final String MGLT = "mglt";
	public static final String STARSHIP_CLASS = "starshipClass";
	public static final String NAME = "name";
	public static final String HYPERDRIVE_RATING = "hyperdriveRating";
	public static final String VEHICLE_CLASS = "vehicleClass";
	public static final String MODEL = "model";
	public static final String ID = "id";

}

