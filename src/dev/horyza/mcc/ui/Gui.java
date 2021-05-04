package dev.horyza.mcc.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dev.horyza.mcc.util.WrapLayout;

public class GUI extends JFrame {

	public GUI() {
		setTitle("YU-GI-OH");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setMinimumSize(new Dimension(800, 600));
		setContentPane(createContentPane());
	}

	private JPanel createContentPane() {
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setOpaque(true);

		// Filter panel
		JPanel filterPanel = createFilterPanel();
		contentPane.add(filterPanel, BorderLayout.NORTH);
		
		// Info panel
		JPanel infoPanel = createInfoPanel();
		contentPane.add(infoPanel, BorderLayout.WEST);

		// Card panel
		JPanel cardPanel = createCardPanel();
		JScrollPane cardScrollPane = createScrollPane(cardPanel);
		contentPane.add(cardScrollPane, BorderLayout.CENTER);
		
		// Deck panel
		/*JPanel deckPanel = createCardPanel();
		JScrollPane deckScrollPane = createScrollPane(cardPanel);
		contentPane.add(deckScrollPane, BorderLayout.CENTER);*/

		return contentPane;
	}

	private JPanel createFilterPanel() {
		JPanel filterPanel = new JPanel();
		filterPanel.setLayout(new WrapLayout(FlowLayout.CENTER, 5, 5));
		filterPanel.setBorder(new EmptyBorder(0, 50, 0, 50));
		
		// Name
		JLabel nameLabel = new JLabel();
		nameLabel.setText("Name:");
		filterPanel.add(nameLabel);
		JTextField nameText = new JTextField();
		nameText.setPreferredSize(new Dimension(125, 25));
		filterPanel.add(nameText);
		
		// Type
		String[] types = {"-- All --", "Normal Monster", "Effect Monster", "Fusion Monster", "Ritual Monster", "Spell Card", "Trap Card"};
		JLabel typeLabel = new JLabel();
		typeLabel.setText("Type:");
		filterPanel.add(typeLabel);
		JComboBox<String> typeCombo = new JComboBox<String>(types);
		typeCombo.setPreferredSize(new Dimension(125, 25));
		filterPanel.add(typeCombo);
		
		// Attribute
		String[] attributes = {"-- All --", "Dark", "Divine", "Earth", "Fire", "Light", "Water", "Wind"};
		JLabel attributeLabel = new JLabel();
		attributeLabel.setText("Attribute:");
		filterPanel.add(attributeLabel);
		JComboBox<String> attributeCombo = new JComboBox<String>(attributes);
		attributeCombo.setPreferredSize(new Dimension(75, 25));
		filterPanel.add(attributeCombo);
		
		// Race
		String[] races = {"-- All --", "Aqua", "Beast", "Beast-Warrior", "Creator God", "Cyberse", "Dinosaur", "Divine-Beast", "Dragon", "Fairy", "Fiend", "Fish", "Insect", "Machine", "Plant", "Psychic", "Pyro", "Reptile", "Rock", "Sea Serpent", "Spellcaster", "Thunder", "Warrior", "Winged-Beast", "Wyrm", "Zombie"};
		JLabel raceLabel = new JLabel();
		raceLabel.setText("Race:");
		filterPanel.add(raceLabel);
		JComboBox<String> raceCombo = new JComboBox<String>(races);
		raceCombo.setPreferredSize(new Dimension(125, 25));
		filterPanel.add(raceCombo);
		
		// Archetype
		String[] archetypes = {"-- All --", "A.I.", "ABC", "Abhyss", "Abyss Actor", "Adamancipator", "Aesir", "Aether", "Alien", "Alligator", "Allure Queen", "Ally of Justice", "Altergeist", "Amazement", "Amazoness", "Amorphage", "Ancient Gear", "Ancient Warriors", "Angel", "Anti", "Apoqliphort", "Appliancer", "Aquaactress", "Arcana Force", "Archfiend", "Armed Dragon", "Aroma", "Artifact", "Assault Mode", "Atlantean", "B.E.S.", "Bamboo Sword", "Barbaros", "Batteryman", "Battleguard", "Battlewasp", "Battlin' Boxer", "Black Luster Soldier", "Blackwing", "Blaze Accelerator", "Blue-Eyes", "Bonding", "Book of", "Boot-Up", "Borrel", "Bounzer", "Bujin", "Burning Abyss", "Butterfly", "Butterspy", "Cataclysmic", "Celtic Guard", "Chaos", "Chaos Phantom", "Charmer", "Chemicritter", "Chronomaly", "Chrysalis", "Cipher", "Clear Wing", "Cloudian", "Code Talker", "Codebreaker", "Constellar", "Crusadia", "Crystal Beast", "Crystron", "Cubic", "Cupid", "CXyz", "Cyber Angel", "Cyber Dragon", "Cyberdark", "D.D.", "D/D", "Danger!", "Dark Contract", "Dark Magician", "Dark Scorpion", "Dark World", "Darklord", "Deep Sea", "Demise", "Deskbot", "Destiny HERO", "Destruction Sword", "Dice", "Digital Bug", "Dinomist", "Dinowrestler", "Djinn", "Dododo", "Dogmatika", "Doll", "Doremichord", "Doriado", "Dracoslayer", "Dracoverlord", "Dragonmaid", "Dragunity", "Dream Mirror", "Drytron", "Dual Avatar", "Duston", "Earthbound", "Edge Imp", "Egyptian God", "Eldlich", "Elemental HERO", "Elemental Lord", "Elementsaber", "Empowered Warrior", "Endymion", "Evil Eye", "Evil HERO", "Evil Twin", "Evilswarm", "Evolsaur", "Evoltile", "Evolzar", "Exodia", "Eyes Restrict", "F.A.", "Fabled", "Fairy Tail", "Fire Fist", "Fire Formation", "Fire King", "Fishborg", "Flamvell", "Flower Cardian", "Fluffal", "Forbidden", "Fortune Fairy", "Fortune Lady", "Fossil", "Frightfur", "Frog", "Fur Hire", "Gadget", "Gagaga", "Gaia The Fierce Knight", "Galaxy-Eyes", "Gandora", "Geargia", "Gem-", "Generaider", "Genex", "Ghostrick", "Gimmick Puppet", "Gishki", "Gizmek", "Glacial Beast", "Glacial Beast Penguin", "Gladiator Beast", "Gogogo", "Golden Castle of Stromberg", "Golden Land", "Gorgonic", "Gouki", "Gravekeeper's", "Graydle", "Greed", "Grepher", "Guardian", "Guardragon", "Gusto", "Harpie", "Hazy", "Herald", "Heraldic", "Heraldry", "HERO", "Heroic", "Hieratic", "Hi-Speedroid", "Hole", "Holy Knight", "Holy Night", "Horus the Black Flame Dragon", "Ice Barrier", "Igknight", "Impcantation", "Infernity", "Infernoble Knight", "Infernoid", "Infestation", "Infinitrack", "Invoked", "Inzektor", "Iron Chain", "Jester", "Jinzo", "Junk", "Jurrac", "Kaiju", "Karakuri", "Knightmare", "Koa'ki Meiru", "Koala", "Kozmo", "Krawler", "Kuriboh", "Laval", "Legendary Knight", "Lightsworn", "Live Twin", "Live☆Twin", "Lunalight", "Lyrilusc", "Machina", "Madolche", "Magical Musket", "Magician", "Magician Girl", "Magikey", "Magistus", "Magnet Warrior", "Majespecter", "Majestic", "Maju", "Malefic", "Malicevorous", "Marincess", "Mask", "Masked HERO", "Materiactor", "Mathmech", "Mayakashi", "Mecha Phantom Beast", "Megalith", "Mekk-Knight", "Meklord", "Melffy", "Melodious", "Mermail", "Metalfoes", "Metaphys", "Mist Valley", "Monarch", "Morphtronic", "Myutant", "Naturia", "Nekroz", "Nemesys", "Neo-Spacian", "Nephthys", "Nimble", "Ninja", "Ninjitsu Art", "Noble Knight", "Nordic", "Numeron", "Odd-Eyes", "Ojama", "Onomato", "Orcust", "Paladins of Dragons", "Paleozoic", "Parasite", "Parshath", "Pendulum Dragon", "Penguin", "Performage", "Performapal", "Phantasm Spiral", "Phantom Knights", "Photon", "Plunder Patroll", "Possessed", "Potan", "Power Tool", "Prank-Kids", "Predaplant", "Prediction Princess", "Priestess", "PSY-Frame", "Qli", "Raidraptor", "Red-Eyes", "Reptilianne", "Resonator", "Rikka", "Ritual Beast", "Roid", "Rokket", "Roland", "Rose", "Rose Dragon", "Sacred Beast", "Salamangreat", "Scrap", "Secret Six Samurai", "S-Force", "Shaddoll", "Shark", "Shinobird", "Shiranui", "Silent Magician", "Silent Swordsman", "Simorgh", "Six Samurai", "Skull Servant", "Sky Scourge", "Sky Striker", "Slime", "Solemn", "Speedroid", "Spellbook", "Sphinx", "Spirit Message", "Spiritual Art", "Springans", "SPYRAL", "Star Seraph", "Stardust", "Starliege", "Steelswarm", "Stellarknight", "Stigmata", "Subterror", "Sunavalon", "Sunseed", "Sunvine", "Super Defense Robot", "Super Quant", "Superheavy", "Supreme King", "Suship", "Sylvan", "Symphonic Warrior", "T.G.", "Tellarknight", "Tenyi", "The Agent", "The Weather", "Thunder Dragon", "Time Thief", "Timelord", "Tindangle", "Toon", "Trap Monster", "Traptrix", "Triamid", "Tri-Brigade", "Trickstar", "True Draco", "U.A.", "Umbral Horror", "Umi", "Unchained", "Ursarctic", "Utopia", "Valkyrie", "Vampire", "Vassal", "Vendread", "Venom", "Virtual World", "Vision HERO", "Void", "Volcanic", "Vylon", "War Rock", "Watt", "Wicked God", "Wind-Up", "Windwitch", "Witchcrafter", "World Chalice", "World Legacy", "Worm", "X-Saber", "Yang Zing", "Yosenju", "Yubel", "Zefra", "ZEXAL", "Zoodiac"};
		JLabel archetypeLabel = new JLabel();
		archetypeLabel.setText("Archetype:");
		filterPanel.add(archetypeLabel);
		JComboBox<String> archetypeCombo = new JComboBox<String>(archetypes);
		archetypeCombo.setPreferredSize(new Dimension(150, 25));
		filterPanel.add(archetypeCombo);
		
		// ATK
		JLabel atkLabel = new JLabel();
		atkLabel.setText("ATK:");
		filterPanel.add(atkLabel);
		JTextField atkMinText = new JTextField();
		atkMinText.setPreferredSize(new Dimension(50, 25));
		filterPanel.add(atkMinText);
		filterPanel.add(new JLabel("-"));
		JTextField atkMaxText = new JTextField();
		atkMaxText.setPreferredSize(new Dimension(50, 25));
		filterPanel.add(atkMaxText);
		
		
		// DEF
		JLabel defLabel = new JLabel();
		defLabel.setText("DEF:");
		filterPanel.add(defLabel);
		JTextField defMinText = new JTextField();
		defMinText.setPreferredSize(new Dimension(50, 25));
		filterPanel.add(defMinText);
		filterPanel.add(new JLabel("-"));
		JTextField defMaxText = new JTextField();
		defMaxText.setPreferredSize(new Dimension(50, 25));
		filterPanel.add(defMaxText);
		
		// Level
		Integer[] levels = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		JLabel levelLabel = new JLabel();
		levelLabel.setText("Level:");
		filterPanel.add(levelLabel);
		JComboBox<Integer> levelMinCombo = new JComboBox<Integer>(levels);
		levelMinCombo.setSelectedIndex(0);
		levelMinCombo.setPreferredSize(new Dimension(50, 25));
		filterPanel.add(levelMinCombo);
		filterPanel.add(new JLabel("-"));
		JComboBox<Integer> levelMaxCombo = new JComboBox<Integer>(levels);
		levelMaxCombo.setSelectedIndex(9);
		levelMaxCombo.setPreferredSize(new Dimension(50, 25));
		filterPanel.add(levelMaxCombo);
		
		return filterPanel;
	}
	
	private JPanel createInfoPanel() {
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		infoPanel.setBorder(new EmptyBorder(20, 20, 5, 20));
		infoPanel.setBackground(Color.GRAY);
		infoPanel.setPreferredSize(new Dimension(250, 600));
		
		// Card image
		JLabel cardImage = new JLabel();
		cardImage.setIcon(new ImageIcon(GUI.class.getResource("/dev/horyza/mcc/resources/10000.jpg")));
		cardImage.setAlignmentX(Component.CENTER_ALIGNMENT);
		infoPanel.add(cardImage);
		
		// Card name
		JLabel cardName = new JLabel();
		cardName.setPreferredSize(new Dimension(250, 50));
		cardName.setFont(new Font(cardName.getFont().getName(), Font.PLAIN, Math.min(20, 20)));
		cardName.setText("Ten Thousand Dragon");
		cardName.setAlignmentX(Component.CENTER_ALIGNMENT);
		infoPanel.add(cardName);
		
		// Card description
		JTextArea cardDesc = new JTextArea();
		cardDesc.setBackground(Color.GRAY);
		cardDesc.setWrapStyleWord(true);
		cardDesc.setLineWrap(true);
		cardDesc.setEditable(false);
		cardDesc.setText("Cannot be Normal Summoned/Set. Must be Special Summoned by Tributing monsters you control whose combined ATK & DEF is 10,000 or more. If Summoned this way, the ATK/DEF of this card becomes 10,000.");
		cardDesc.setAlignmentX(Component.CENTER_ALIGNMENT);
		infoPanel.add(cardDesc);
		
		return infoPanel;
	}
	
	private JPanel createCardPanel() {
		JPanel cardPanel = new JPanel();
		cardPanel.setLayout(new WrapLayout(FlowLayout.CENTER, 5, 5));
		cardPanel.setBackground(Color.DARK_GRAY);

		// Load cards
		for (int i = 10000; i < 15000; i++) {
			try {
				JLabel lblNewLabel = new JLabel();
				Image image = new ImageIcon(GUI.class.getResource("/dev/horyza/mcc/resources/" + i + ".jpg"))
						.getImage();
				ImageIcon scaledImage = new ImageIcon(image.getScaledInstance(89, 127, Image.SCALE_SMOOTH));
				lblNewLabel.setIcon(scaledImage);
				cardPanel.add(lblNewLabel);
			} catch (Exception e) {
				continue;
			}
		}
		return cardPanel;
	}
	
	private JPanel createDeckPanel() {
		JPanel deckPanel = new JPanel();
		
		return deckPanel;
	}

	private JScrollPane createScrollPane(JPanel panel) {
		JScrollPane scrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		return scrollPane;
	}
}
