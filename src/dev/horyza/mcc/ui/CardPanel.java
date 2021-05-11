package dev.horyza.mcc.ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import dev.horyza.mcc.model.Card;
import dev.horyza.mcc.model.Filter;
import dev.horyza.mcc.services.DatabaseHandler;
import dev.horyza.mcc.util.WrapLayout;

public class CardPanel extends JPanel {

	private MainFrame frame;
	private String collectionType;
	private HashMap<JLabel, Card> cards = new HashMap<JLabel, Card>();
	private DatabaseHandler db = new DatabaseHandler();

	public CardPanel(MainFrame frame, String collectionType) {
		this.frame = frame;
		this.collectionType = collectionType;
		setLayout(new WrapLayout(FlowLayout.CENTER, 5, 5));
		setBackground(Color.DARK_GRAY);
		loadCards(db.selectAll(collectionType));
	}

	public void loadCards(ArrayList<Card> cardList) {
		// Load cards
		for (Card card : cardList) {
			try {
				JLabel cardLabel = new JLabel();
				Image image = new ImageIcon(
						MainFrame.class.getResource("/dev/horyza/mcc/resources/" + card.getId() + ".jpg")).getImage();
				ImageIcon scaledImage = new ImageIcon(image.getScaledInstance(89, 127, Image.SCALE_SMOOTH));
				cardLabel.setIcon(scaledImage);
				cardLabel.addMouseListener(new MouseAdapter() {

					@Override
					public void mouseEntered(MouseEvent evt) {
						frame.getInfoPanel().updateInfo(card);
					}

					@Override
					public void mousePressed(MouseEvent e) {
						showPopup(e);
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						showPopup(e);
					}

					private void showPopup(MouseEvent e) {
						if (e.isPopupTrigger()) {
							getPopupMenu(cardLabel).show(e.getComponent(), e.getX(), e.getY());
						}
					}
				});

				cards.put(cardLabel, card);
				add(cardLabel);
			} catch (Exception e) {
				continue;
			}
		}
	}

	public void addCard(Card card) {
		try {
			JLabel cardLabel = new JLabel();
			Image image = new ImageIcon(
					MainFrame.class.getResource("/dev/horyza/mcc/resources/" + card.getId() + ".jpg")).getImage();
			ImageIcon scaledImage = new ImageIcon(image.getScaledInstance(89, 127, Image.SCALE_SMOOTH));
			cardLabel.setIcon(scaledImage);
			cardLabel.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseEntered(MouseEvent evt) {
					frame.getInfoPanel().updateInfo(card);
				}

				@Override
				public void mousePressed(MouseEvent e) {
					showPopup(e);
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					showPopup(e);
				}

				private void showPopup(MouseEvent e) {
					if (e.isPopupTrigger()) {
						getPopupMenu(cardLabel).show(e.getComponent(), e.getX(), e.getY());
					}
				}
			});
			cards.put(cardLabel, card);
			add(cardLabel);
		} catch (Exception e) {

		}
	}

	public void removeCard(JLabel cardLabel) {
		cards.remove(cardLabel);
		remove(cardLabel);
		revalidate();
		repaint();
	}

	public void filterCards(Filter filter) {

		for (JLabel label : cards.keySet()) {
			Card card = cards.get(label);
			boolean filterOut = false;

			if (filter.getName() != null) {
				if (card.getName() != null) {
					if (!card.getName().toLowerCase().contains(filter.getName().toLowerCase())) {
						filterOut = true;
					}
				} else {
					filterOut = true;
				}
			}
			if (filter.getType() != null) {
				if (card.getType() != null) {
					if (!card.getType().equalsIgnoreCase(filter.getType())) {
						filterOut = true;
					}
				} else {
					filterOut = true;
				}
			}
			if (filter.getAttribute() != null) {
				if (card.getAttribute() != null) {
					if (!card.getAttribute().equalsIgnoreCase(filter.getAttribute())) {
						filterOut = true;
					}
				} else {
					filterOut = true;
				}
			}
			if (filter.getRace() != null) {
				if (card.getRace() != null) {
					if (!card.getRace().equalsIgnoreCase(filter.getRace())) {
						filterOut = true;
					}
				} else {
					filterOut = true;
				}
			}
			if (filter.getArchetype() != null) {
				if (card.getArchetype() != null) {
					if (!card.getArchetype().equalsIgnoreCase(filter.getArchetype())) {
						filterOut = true;
					}
				} else {
					filterOut = true;
				}
			}
			if (filter.getAtkMin() != -1 && card.getAtk() != -1) {
				if (card.getAtk() < filter.getAtkMin()) {
					filterOut = true;
				}
			}
			if (filter.getAtkMax() != -1 && card.getAtk() != -1) {
				if (card.getAtk() > filter.getAtkMax()) {
					filterOut = true;
				}
			}
			if (filter.getDefMin() != -1 && card.getDef() != -1) {
				if (card.getDef() < filter.getDefMin()) {
					filterOut = true;
				}
			}
			if (filter.getDefMax() != -1 && card.getDef() != -1) {
				if (card.getDef() > filter.getDefMax()) {
					filterOut = true;
				}
			}
			if (card.getLevel() != -1) {
				if (card.getLevel() < filter.getLevelMin()) {
					filterOut = true;
				}
				if (card.getLevel() > filter.getLevelMax()) {
					filterOut = true;
				}
			}
			if (filterOut) {
				label.setVisible(false);
			} else {
				label.setVisible(true);
			}
		}
	}

	private JPopupMenu getPopupMenu(JLabel label) {
		JPopupMenu popupMenu = new JPopupMenu();

		switch (collectionType) {
		case "catalog":
			popupMenu.add(addToCollection(label));
			popupMenu.add(addToDeck(label));
			break;
		case "collection":
			popupMenu.add(removeFromCollection(label));
			popupMenu.add(addToDeck(label));
			break;
		case "deck":
			popupMenu.add(addToCollection(label));
			popupMenu.add(removeFromDeck(label));
			break;
		}
		
		return popupMenu;
	}
	
	private JMenuItem addToCollection(JLabel label) {
		JMenuItem addToCollection = new JMenuItem("Add to collection");
		addToCollection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Card card = cards.get(label);
				frame.getCollectionPanel().addCard(card);
			}
		});
		return addToCollection;
	}
	
	private JMenuItem addToDeck(JLabel label) {
		JMenuItem addToDeck = new JMenuItem("Add to deck");
		addToDeck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Card card = cards.get(label);
				//TODO add to deck
			}
		});
		return addToDeck;
	}
	
	private JMenuItem removeFromCollection(JLabel label) {
		JMenuItem removeFromCollection = new JMenuItem("Remove from collection");
		removeFromCollection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeCard(label);
			}
		});
		return removeFromCollection;
	}
	
	private JMenuItem removeFromDeck(JLabel label) {
		JMenuItem removeFromDeck = new JMenuItem("Remove from deck");
		removeFromDeck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO remove from deck
			}
		});
		return removeFromDeck;
	}

	public HashMap<JLabel, Card> getCards() {
		return cards;
	}
}
