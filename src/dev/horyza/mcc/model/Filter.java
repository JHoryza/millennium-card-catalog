package dev.horyza.mcc.model;

public class Filter {

	private int id;
	private String name;
	private String type;
	private String attribute;
	private String race;
	private String archetype;
	private int atkMin;
	private int atkMax;
	private int defMin;
	private int defMax;
	private int levelMin;
	private int levelMax;
	
	public Filter(String name, String type, String attribute, String race, String archetype, int atkMin, int atkMax, int defMin, int defMax, int levelMin, int levelMax) {
		this.name = name;
		this.type = type;
		this.attribute = attribute;
		this.race = race;
		this.archetype = archetype;
		this.atkMin = atkMin;
		this.atkMax = atkMax;
		this.defMin = defMin;
		this.defMax = defMax;
		this.levelMin = levelMin;
		this.levelMax = levelMax;
	}
	
	public Filter(int id, String name, String type, String attribute, String race, String archetype, int atkMin, int atkMax, int defMin, int defMax, int levelMin, int levelMax) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.attribute = attribute;
		this.race = race;
		this.archetype = archetype;
		this.atkMin = atkMin;
		this.atkMax = atkMax;
		this.defMin = defMin;
		this.defMax = defMax;
		this.levelMin = levelMin;
		this.levelMax = levelMax;
	}
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
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
	
	public int getAtkMin() {
		return atkMin;
	}
	
	public int getAtkMax() {
		return atkMax;
	}
	
	public int getDefMin() {
		return defMin;
	}
	
	public int getDefMax() {
		return defMax;
	}
	
	public int getLevelMin() {
		return levelMin;
	}
	
	public int getLevelMax() {
		return levelMax;
	}
}
