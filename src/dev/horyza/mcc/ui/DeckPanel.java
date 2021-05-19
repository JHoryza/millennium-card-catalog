package dev.horyza.mcc.ui;

import java.awt.Color;
import java.awt.Point;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import dev.horyza.mcc.services.DatabaseHandler;
import dev.horyza.mcc.ui.MainFrame.CardType;
import dev.horyza.mcc.util.OverlapLayout;

public class DeckPanel extends JPanel {

	private MainFrame frame;
	private CardPanel cardPanel;
	private JPanel infoPanel;

	private int monsterCount = 0;
	private int spellCount = 0;
	private int trapCount = 0;
	private int fusionCount = 0;

	private JLabel monsterLabel = new JLabel("Monsters: --");
	private JLabel spellLabel = new JLabel("Spells: --");
	private JLabel trapLabel = new JLabel("Traps: --");
	private JLabel fusionLabel = new JLabel("Fusions: --");

	public DeckPanel(MainFrame frame) {
		this.frame = frame;
		this.cardPanel = createCardPanel();
		this.infoPanel = createInfoPanel();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(Color.GRAY);

		// Info panel
		add(infoPanel);

		// Card panel
		JScrollPane deckScrollPane = new JScrollPane(cardPanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(deckScrollPane);
	}

	private JPanel createInfoPanel() {
		JPanel infoPanel = new JPanel();
		infoPanel.add(monsterLabel);
		infoPanel.add(spellLabel);
		infoPanel.add(trapLabel);
		infoPanel.add(fusionLabel);
		return infoPanel;
	}

	private CardPanel createCardPanel() {
		CardPanel cardPanel = new CardPanel(frame, CardType.DECK, new OverlapLayout(new Point(20, 0)));
		cardPanel.setBackground(Color.LIGHT_GRAY);
		return cardPanel;
	}
	
	public void incrementCount(String cardType, int count) {
		switch (cardType) {
		case "Normal Monster":
		case "Effect Monster":
		case "Flip Effect Monster":
		case "Ritual Effect Monster":
		case "Union Effect Monster":
		case "XYZ Monster":
			monsterCount += count;
			monsterLabel.setText("Monsters: " + monsterCount);
			break;
		case "Fusion Monster":
			fusionCount += count;
			fusionLabel.setText("Fusions: " + fusionCount);
			break;
		case "Spell Card":
			spellCount += count;
			spellLabel.setText("Spells: " + spellCount);
			break;
		case "Trap Card":
			trapCount += count;
			trapLabel.setText("Traps: " + trapCount);
			break;
		default:
			break;
		}
	}

	public void decrementCount(String cardType, int count) {
		String text;
		switch (cardType) {
		case "Normal Monster":
		case "Effect Monster":
		case "Flip Effect Monster":
		case "Ritual Effect Monster":
		case "Union Effect Monster":
		case "XYZ Monster":
			monsterCount -= count;
			text = monsterCount == 0 ? "Monsters: --" : "Monsters: " + monsterCount;
			monsterLabel.setText(text);
			break;
		case "Fusion Monster":
			fusionCount -= count;
			text = fusionCount == 0 ? "Fusions: --" : "Fusions: " + fusionCount;
			fusionLabel.setText(text);
			break;
		case "Spell Card":
			spellCount -= count;
			text = spellCount == 0 ? "Spells: --" : "Spells: " + spellCount;
			spellLabel.setText(text);
			break;
		case "Trap Card":
			trapCount -= count;
			text = trapCount == 0 ? "Traps: --" : "Traps: " + trapCount;
			trapLabel.setText(text);
			break;
		default:
			break;
		}
	}

	public CardPanel getCardPanel() {
		return cardPanel;
	}
}
