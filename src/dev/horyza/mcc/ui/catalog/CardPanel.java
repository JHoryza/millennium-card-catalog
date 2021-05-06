package dev.horyza.mcc.ui.catalog;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dev.horyza.mcc.database.DatabaseHandler;
import dev.horyza.mcc.model.Card;
import dev.horyza.mcc.model.Collection;
import dev.horyza.mcc.model.Filter;
import dev.horyza.mcc.ui.GUI;
import dev.horyza.mcc.util.WrapLayout;

public class CardPanel extends JPanel {

	private HashMap<JLabel, Card> cards = new HashMap<JLabel, Card>();
	
	public CardPanel(GUI gui) {
		setLayout(new WrapLayout(FlowLayout.CENTER, 5, 5));
		setBackground(Color.DARK_GRAY);

		ArrayList<Card> cardList = getCards();
		
		// Load cards
		for (Card card : cardList) {
			try {
				JLabel cardLabel = new JLabel();
				Image image = new ImageIcon(GUI.class.getResource("/dev/horyza/mcc/resources/" + card.getId() + ".jpg"))
						.getImage();
				ImageIcon scaledImage = new ImageIcon(image.getScaledInstance(89, 127, Image.SCALE_SMOOTH));
				cardLabel.setIcon(scaledImage);
				
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
	
	private ArrayList<Card> getCards() {
		ArrayList<Card> cardList = new ArrayList<Card>();
		DatabaseHandler db = new DatabaseHandler();
		cardList = db.selectAll();
		return cardList;
	}
	
	private ArrayList<Card> getCards(Filter filter) {
		ArrayList<Card> cardList = new ArrayList<Card>();
		DatabaseHandler db = new DatabaseHandler();
		cardList = db.selectFiltered(filter);
		return cardList;
	}
}
