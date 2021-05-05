package dev.horyza.mcc.ui.catalog;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import dev.horyza.mcc.ui.GUI;

public class DeckPanel extends JPanel {

	public DeckPanel() {

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
		int cards[] = { 237717162, 20277860, 16768387, 87564352, 41396436, 11384280, 93889755, 76446915, 90219263,
				60862676, 58314394, 89272878, 2118022, 28546905, 94773007, 62340868, 99551425, 99551425, 87756343,
				93900406, 7805359, 58861941, 50152549, 51371017, 10071456, 88279736, 88279736, 24611934, 5758500,
				13599884, 40453765, 40453765, 98495314, 51275027, 71625222, 46918794, 2483611 };
		for (int i = 0; i < cards.length; i++) {
			try {
				JLabel lblNewLabel = new JLabel();
				Image image = new ImageIcon(GUI.class.getResource("/dev/horyza/mcc/resources/" + cards[i] + ".jpg"))
						.getImage();
				ImageIcon scaledImage = new ImageIcon(image.getScaledInstance(89, 127, Image.SCALE_SMOOTH));
				lblNewLabel.setIcon(scaledImage);
				if (i != cards.length - 1) {
					lblNewLabel.setPreferredSize(new Dimension(33, 127));
				}
				cardPanel.add(lblNewLabel);
			} catch (Exception e) {
				continue;
			}
		}

		return cardPanel;
	}
}
