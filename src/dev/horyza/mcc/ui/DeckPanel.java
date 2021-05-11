package dev.horyza.mcc.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import dev.horyza.mcc.model.Card;

public class DeckPanel extends JPanel {

	private MainFrame frame;
	
	private HashMap<JLabel, Card> cards = new HashMap<JLabel, Card>();
	
	public DeckPanel(MainFrame frame) {
		
		this.frame = frame;
		
		JPanel infoPanel = createInfoPanel();
		JPanel cardPanel = createCardPanel();
		JScrollPane cardScrollPane = new JScrollPane(cardPanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBackground(Color.LIGHT_GRAY);
		
		add(infoPanel);
		add(cardScrollPane);
	}

	private JPanel createInfoPanel() {
		JPanel infoPanel = new JPanel();

		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		infoPanel.setBorder(new EmptyBorder(0, 25, 0, 25));
		infoPanel.setBackground(Color.LIGHT_GRAY);

		// Deck info
		JLabel monsterCount = new JLabel();
		monsterCount.setText("Monsters: 22");
		infoPanel.add(monsterCount);

		JLabel spellCount = new JLabel();
		spellCount.setText("Spells: 22");
		infoPanel.add(spellCount);

		JLabel trapCount = new JLabel();
		trapCount.setText("Traps: 22");
		infoPanel.add(trapCount);

		JLabel fusionCount = new JLabel();
		fusionCount.setText("Fusions: 22");
		infoPanel.add(fusionCount);

		return infoPanel;
	}

	private JPanel createCardPanel() {
		JPanel cardPanel = new JPanel();
		cardPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 5));
		cardPanel.setBackground(Color.LIGHT_GRAY);

		// Load cards
		int deckCards[] = { 237717162, 20277860, 16768387, 87564352, 41396436, 11384280, 93889755, 76446915, 90219263,
				60862676, 58314394, 89272878, 2118022, 28546905, 94773007, 62340868, 99551425, 99551425, 87756343,
				93900406, 7805359, 58861941, 50152549, 51371017, 10071456, 88279736, 88279736, 24611934, 5758500,
				13599884, 40453765, 40453765, 98495314, 51275027, 71625222, 46918794, 2483611 };
		for (int i = 0; i < deckCards.length; i++) {
			try {
				JLabel cardLabel = new JLabel();
				Image image = new ImageIcon(MainFrame.class.getResource("/dev/horyza/mcc/resources/" + deckCards[i] + ".jpg"))
						.getImage();
				ImageIcon scaledImage = new ImageIcon(image.getScaledInstance(89, 127, Image.SCALE_SMOOTH));
				cardLabel.setIcon(scaledImage);
				if (i != deckCards.length - 1) {
					cardLabel.setPreferredSize(new Dimension(33, 127));
				}
				
				Card card = new Card(deckCards[i], "Name of " + deckCards[i], "Card description for " + deckCards[i], "", "", "", "", 0, 0, 0);
				cards.put(cardLabel, card);
				
				cardLabel.addMouseListener(new MouseAdapter() {
					public void mouseEntered(MouseEvent evt) {
						frame.getInfoPanel().updateInfo(card);
					}
				});
				
				cardPanel.add(cardLabel);
			} catch (Exception e) {
				continue;
			}
		}

		return cardPanel;
	}
}
