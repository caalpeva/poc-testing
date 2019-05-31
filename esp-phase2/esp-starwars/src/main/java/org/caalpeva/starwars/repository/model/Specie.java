package org.caalpeva.starwars.repository.model;

//@Entity
//@Table(name = "PLANETS")
public class Specie {
	//@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
	public String name;
    public String designation;
    public String classification;

    public String averageHeight;
    public String eyeColors;
    public String hairColors;
    public String skinColors;
    public String averageLifespan;
    //public Planet homeWorld;

    public String created;
    public String language;
    public String edited;
    public String url;
}