package dev.horyza.mcc.model;

public class Card {

	private int id;
	private String name;
	private String description;
	private String type;
	private String attribute;
	private String race;
	private String archetype;
	private int atk;
	private int def;
	private int level;
	
	public Card(int id, String name, String description, String type, String attribute, String race, String archetype, int atk, int def, int level) {
		this.id = id;
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
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getType() {
		return type;
	}
	
	public String getAttribute() {
		return attribute;
	}
	
	public String getRace() {
		return race;
	}
	
	public String getArchetype() {
		return archetype;
	}
	
	public int getAtk() {
		return atk;
	}
	
	public int getDef() {
		return def;
	}
	
	public int getLevel() {
		return level;
	}
}
