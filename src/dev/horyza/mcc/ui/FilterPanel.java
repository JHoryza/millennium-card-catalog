package dev.horyza.mcc.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dev.horyza.mcc.model.Card;
import dev.horyza.mcc.model.Filter;
import dev.horyza.mcc.util.WrapLayout;
import javax.swing.JButton;

public class FilterPanel extends JPanel {
	
	public FilterPanel(MainFrame frame) {
		setLayout(new WrapLayout(FlowLayout.CENTER, 5, 5));
		setBorder(new EmptyBorder(0, 50, 0, 50));

		// Name
		JLabel nameLabel = new JLabel();
		nameLabel.setText("Name:");
		add(nameLabel);
		JTextField nameText = new JTextField();
		nameText.setPreferredSize(new Dimension(125, 25));
		add(nameText);

		// Type
		String[] types = { "-- All --", "Normal Monster", "Effect Monster", "Fusion Monster", "Ritual Monster",
				"Spell Card", "Trap Card" };
		JLabel typeLabel = new JLabel();
		typeLabel.setText("Type:");
		add(typeLabel);
		JComboBox typeCombo = new JComboBox(types);
		typeCombo.setPreferredSize(new Dimension(125, 25));
		add(typeCombo);

		// Attribute
		String[] attributes = { "-- All --", "Dark", "Divine", "Earth", "Fire", "Light", "Water", "Wind" };
		JLabel attributeLabel = new JLabel();
		attributeLabel.setText("Attribute:");
		add(attributeLabel);
		JComboBox attributeCombo = new JComboBox(attributes);
		attributeCombo.setPreferredSize(new Dimension(75, 25));
		add(attributeCombo);

		// Race
		String[] races = { "-- All --", "Aqua", "Beast", "Beast-Warrior", "Creator God", "Cyberse", "Dinosaur",
				"Divine-Beast", "Dragon", "Fairy", "Fiend", "Fish", "Insect", "Machine", "Plant", "Psychic", "Pyro",
				"Reptile", "Rock", "Sea Serpent", "Spellcaster", "Thunder", "Warrior", "Winged-Beast", "Wyrm",
				"Zombie" };
		JLabel raceLabel = new JLabel();
		raceLabel.setText("Race:");
		add(raceLabel);
		JComboBox raceCombo = new JComboBox(races);
		raceCombo.setPreferredSize(new Dimension(125, 25));
		add(raceCombo);

		// Archetype
		String[] archetypes = { "-- All --", "A.I.", "ABC", "Abhyss", "Abyss Actor", "Adamancipator", "Aesir", "Aether",
				"Alien", "Alligator", "Allure Queen", "Ally of Justice", "Altergeist", "Amazement", "Amazoness",
				"Amorphage", "Ancient Gear", "Ancient Warriors", "Angel", "Anti", "Apoqliphort", "Appliancer",
				"Aquaactress", "Arcana Force", "Archfiend", "Armed Dragon", "Aroma", "Artifact", "Assault Mode",
				"Atlantean", "B.E.S.", "Bamboo Sword", "Barbaros", "Batteryman", "Battleguard", "Battlewasp",
				"Battlin' Boxer", "Black Luster Soldier", "Blackwing", "Blaze Accelerator", "Blue-Eyes", "Bonding",
				"Book of", "Boot-Up", "Borrel", "Bounzer", "Bujin", "Burning Abyss", "Butterfly", "Butterspy",
				"Cataclysmic", "Celtic Guard", "Chaos", "Chaos Phantom", "Charmer", "Chemicritter", "Chronomaly",
				"Chrysalis", "Cipher", "Clear Wing", "Cloudian", "Code Talker", "Codebreaker", "Constellar", "Crusadia",
				"Crystal Beast", "Crystron", "Cubic", "Cupid", "CXyz", "Cyber Angel", "Cyber Dragon", "Cyberdark",
				"D.D.", "D/D", "Danger!", "Dark Contract", "Dark Magician", "Dark Scorpion", "Dark World", "Darklord",
				"Deep Sea", "Demise", "Deskbot", "Destiny HERO", "Destruction Sword", "Dice", "Digital Bug", "Dinomist",
				"Dinowrestler", "Djinn", "Dododo", "Dogmatika", "Doll", "Doremichord", "Doriado", "Dracoslayer",
				"Dracoverlord", "Dragonmaid", "Dragunity", "Dream Mirror", "Drytron", "Dual Avatar", "Duston",
				"Earthbound", "Edge Imp", "Egyptian God", "Eldlich", "Elemental HERO", "Elemental Lord", "Elementsaber",
				"Empowered Warrior", "Endymion", "Evil Eye", "Evil HERO", "Evil Twin", "Evilswarm", "Evolsaur",
				"Evoltile", "Evolzar", "Exodia", "Eyes Restrict", "F.A.", "Fabled", "Fairy Tail", "Fire Fist",
				"Fire Formation", "Fire King", "Fishborg", "Flamvell", "Flower Cardian", "Fluffal", "Forbidden",
				"Fortune Fairy", "Fortune Lady", "Fossil", "Frightfur", "Frog", "Fur Hire", "Gadget", "Gagaga",
				"Gaia The Fierce Knight", "Galaxy-Eyes", "Gandora", "Geargia", "Gem-", "Generaider", "Genex",
				"Ghostrick", "Gimmick Puppet", "Gishki", "Gizmek", "Glacial Beast", "Glacial Beast Penguin",
				"Gladiator Beast", "Gogogo", "Golden Castle of Stromberg", "Golden Land", "Gorgonic", "Gouki",
				"Gravekeeper's", "Graydle", "Greed", "Grepher", "Guardian", "Guardragon", "Gusto", "Harpie", "Hazy",
				"Herald", "Heraldic", "Heraldry", "HERO", "Heroic", "Hieratic", "Hi-Speedroid", "Hole", "Holy Knight",
				"Holy Night", "Horus the Black Flame Dragon", "Ice Barrier", "Igknight", "Impcantation", "Infernity",
				"Infernoble Knight", "Infernoid", "Infestation", "Infinitrack", "Invoked", "Inzektor", "Iron Chain",
				"Jester", "Jinzo", "Junk", "Jurrac", "Kaiju", "Karakuri", "Knightmare", "Koa'ki Meiru", "Koala",
				"Kozmo", "Krawler", "Kuriboh", "Laval", "Legendary Knight", "Lightsworn", "Live Twin", "Live☆Twin",
				"Lunalight", "Lyrilusc", "Machina", "Madolche", "Magical Musket", "Magician", "Magician Girl",
				"Magikey", "Magistus", "Magnet Warrior", "Majespecter", "Majestic", "Maju", "Malefic", "Malicevorous",
				"Marincess", "Mask", "Masked HERO", "Materiactor", "Mathmech", "Mayakashi", "Mecha Phantom Beast",
				"Megalith", "Mekk-Knight", "Meklord", "Melffy", "Melodious", "Mermail", "Metalfoes", "Metaphys",
				"Mist Valley", "Monarch", "Morphtronic", "Myutant", "Naturia", "Nekroz", "Nemesys", "Neo-Spacian",
				"Nephthys", "Nimble", "Ninja", "Ninjitsu Art", "Noble Knight", "Nordic", "Numeron", "Odd-Eyes", "Ojama",
				"Onomato", "Orcust", "Paladins of Dragons", "Paleozoic", "Parasite", "Parshath", "Pendulum Dragon",
				"Penguin", "Performage", "Performapal", "Phantasm Spiral", "Phantom Knights", "Photon",
				"Plunder Patroll", "Possessed", "Potan", "Power Tool", "Prank-Kids", "Predaplant",
				"Prediction Princess", "Priestess", "PSY-Frame", "Qli", "Raidraptor", "Red-Eyes", "Reptilianne",
				"Resonator", "Rikka", "Ritual Beast", "Roid", "Rokket", "Roland", "Rose", "Rose Dragon", "Sacred Beast",
				"Salamangreat", "Scrap", "Secret Six Samurai", "S-Force", "Shaddoll", "Shark", "Shinobird", "Shiranui",
				"Silent Magician", "Silent Swordsman", "Simorgh", "Six Samurai", "Skull Servant", "Sky Scourge",
				"Sky Striker", "Slime", "Solemn", "Speedroid", "Spellbook", "Sphinx", "Spirit Message", "Spiritual Art",
				"Springans", "SPYRAL", "Star Seraph", "Stardust", "Starliege", "Steelswarm", "Stellarknight",
				"Stigmata", "Subterror", "Sunavalon", "Sunseed", "Sunvine", "Super Defense Robot", "Super Quant",
				"Superheavy", "Supreme King", "Suship", "Sylvan", "Symphonic Warrior", "T.G.", "Tellarknight", "Tenyi",
				"The Agent", "The Weather", "Thunder Dragon", "Time Thief", "Timelord", "Tindangle", "Toon",
				"Trap Monster", "Traptrix", "Triamid", "Tri-Brigade", "Trickstar", "True Draco", "U.A.",
				"Umbral Horror", "Umi", "Unchained", "Ursarctic", "Utopia", "Valkyrie", "Vampire", "Vassal", "Vendread",
				"Venom", "Virtual World", "Vision HERO", "Void", "Volcanic", "Vylon", "War Rock", "Watt", "Wicked God",
				"Wind-Up", "Windwitch", "Witchcrafter", "World Chalice", "World Legacy", "Worm", "X-Saber", "Yang Zing",
				"Yosenju", "Yubel", "Zefra", "ZEXAL", "Zoodiac" };
		JLabel archetypeLabel = new JLabel();
		archetypeLabel.setText("Archetype:");
		add(archetypeLabel);
		JComboBox archetypeCombo = new JComboBox(archetypes);
		archetypeCombo.setPreferredSize(new Dimension(150, 25));
		add(archetypeCombo);

		// ATK
		JLabel atkLabel = new JLabel();
		atkLabel.setText("ATK:");
		add(atkLabel);
		JTextField atkMinText = new JTextField();
		atkMinText.setPreferredSize(new Dimension(50, 25));
		add(atkMinText);
		add(new JLabel("-"));
		JTextField atkMaxText = new JTextField();
		atkMaxText.setPreferredSize(new Dimension(50, 25));
		add(atkMaxText);

		// DEF
		JLabel defLabel = new JLabel();
		defLabel.setText("DEF:");
		add(defLabel);
		JTextField defMinText = new JTextField();
		defMinText.setPreferredSize(new Dimension(50, 25));
		add(defMinText);
		add(new JLabel("-"));
		JTextField defMaxText = new JTextField();
		defMaxText.setPreferredSize(new Dimension(50, 25));
		add(defMaxText);

		// Level
		Integer[] levels = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		JLabel levelLabel = new JLabel();
		levelLabel.setText("Level:");
		add(levelLabel);
		JComboBox levelMinCombo = new JComboBox(levels);
		levelMinCombo.setSelectedIndex(0);
		levelMinCombo.setPreferredSize(new Dimension(50, 25));
		add(levelMinCombo);
		add(new JLabel("-"));
		JComboBox levelMaxCombo = new JComboBox(levels);
		levelMaxCombo.setSelectedIndex(9);
		levelMaxCombo.setPreferredSize(new Dimension(50, 25));
		add(levelMaxCombo);

		// Apply filters button
		JButton applyButton = new JButton("Apply");
		applyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nameText.getText().equals("") ? null : String.valueOf(nameText.getText());
				String type = String.valueOf(typeCombo.getSelectedItem()).equalsIgnoreCase("-- ALL --") ? null : String.valueOf(typeCombo.getSelectedItem());
				String attribute = String.valueOf(attributeCombo.getSelectedItem()).equalsIgnoreCase("-- ALL --") ? null : String.valueOf(attributeCombo.getSelectedItem());
				String race = String.valueOf(raceCombo.getSelectedItem()).equalsIgnoreCase("-- ALL --") ? null : String.valueOf(raceCombo.getSelectedItem());
				String archetype = String.valueOf(archetypeCombo.getSelectedItem()).equalsIgnoreCase("-- ALL --") ? null : String.valueOf(archetypeCombo.getSelectedItem());
				int atkMin = atkMinText.getText().equalsIgnoreCase("") ? -1 : Integer.parseInt(String.valueOf(atkMinText.getText()));
				int atkMax = atkMaxText.getText().equalsIgnoreCase("") ? -1 : Integer.parseInt(String.valueOf(atkMaxText.getText()));
				int defMin = defMinText.getText().equalsIgnoreCase("") ? -1 : Integer.parseInt(String.valueOf(defMinText.getText()));
				int defMax = defMaxText.getText().equalsIgnoreCase("") ? -1 : Integer.parseInt(String.valueOf(defMaxText.getText()));
				int levelMin = Integer.parseInt(String.valueOf(levelMinCombo.getSelectedItem()));
				int levelMax = Integer.parseInt(String.valueOf(levelMaxCombo.getSelectedItem()));
				System.out.println(name + " " + type + " " + attribute + " " + race + " " + archetype + " " + atkMin + " " + atkMax + " " + defMin + " " + defMax + " " + levelMin + " " + levelMax);
				Filter filter = new Filter(name, type, attribute, race, archetype, atkMin, atkMax, defMin, defMax, levelMin, levelMax);
				frame.getCatalogPanel().filterCards(filter);
			}
		});
		add(applyButton);
	}
}
