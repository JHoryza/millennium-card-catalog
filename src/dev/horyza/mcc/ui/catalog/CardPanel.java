package dev.horyza.mcc.ui.catalog;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import dev.horyza.mcc.Main;
import dev.horyza.mcc.model.Card;
import dev.horyza.mcc.ui.GUI;
import dev.horyza.mcc.util.WrapLayout;

public class CardPanel extends JPanel {

	private HashMap<JLabel, Card> cards = new HashMap<JLabel, Card>();
	
	public CardPanel(GUI gui) {
		setLayout(new WrapLayout(FlowLayout.CENTER, 5, 5));
		setBackground(Color.DARK_GRAY);

		// Load cards
		for (int i = 10000; i < 15000; i++) {
			try {
				JLabel cardLabel = new JLabel();
				Image image = new ImageIcon(GUI.class.getResource("/dev/horyza/mcc/resources/" + i + ".jpg"))
						.getImage();
				ImageIcon scaledImage = new ImageIcon(image.getScaledInstance(89, 127, Image.SCALE_SMOOTH));
				cardLabel.setIcon(scaledImage);
				
				Card card = new Card(i, "Name of " + i, "Card description for " + i, "", "", "", "", 0, 0, 0);
				cards.put(cardLabel, card);
				
				cardLabel.addMouseListener(new MouseAdapter() {
					public void mouseEntered(MouseEvent evt) {
						gui.getInfoPanel().updateInfo(card);
					}
				});
				
				add(cardLabel);
			} catch (Exception e) {
				continue;
			}
		}
	}
}
