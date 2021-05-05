package dev.horyza.mcc.model;

public class Card {

	private String name;
	private String description;
	private String type;
	private String attribute;
	private String race;
	private String archetype;
	private int atk;
	private int def;
	private int level;
	
	public Card(String name, String description, String type, String attribute, String race, String archetype, int atk, int def, int level) {
		this.name = name;
		this.description = description;
		this.type = type;
		this.attribute = attribute;
		this.race = race;
		this.archetype = archetype;
		this.atk = atk;
		this.def = def;
		this.level = level;
	}
}
