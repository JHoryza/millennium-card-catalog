package dev.horyza.mcc.ui;

import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import dev.horyza.mcc.model.Card;
import dev.horyza.mcc.model.Filter;
import dev.horyza.mcc.services.DatabaseHandler;
import dev.horyza.mcc.ui.MainFrame.CardType;

public class CardPanel extends JPanel {

	private MainFrame frame;
	private DatabaseHandler db = new DatabaseHandler();
	private CardType cardType;
	private HashMap<JLabel, Card> cardLabels = new HashMap<JLabel, Card>();
	private List<Card> cardList = new ArrayList<>();

	public CardPanel(MainFrame frame, CardType cardList, LayoutManager layout) {
		this.frame = frame;
		this.cardType = cardList;
		setLayout(layout);
		addCards(db.selectAll(cardList.getTableName()));
	}

	/**
	 * Adds cards to cardList and draws to panel
	 * @param cardsToAdd List of Card objects to add
	 */
	public void addCards(List<Card> cardsToAdd) {
		cardList.addAll(cardsToAdd);
		for (int i = 0; i < cardsToAdd.size(); i++) {
			Card card = cardsToAdd.get(i);
			if (cardType == CardType.COLLECTION) {
				if (labelExists(card)) {
					continue;
				}
			}
			JLabel cardLabel = createLabel(card);
			cardLabels.put(cardLabel, card);
			add(cardLabel);
		}
	}
	
	/**
	 * Removes card from panel
	 * @param cardLabel Label of card to be removed
	 */
	public void removeCard(JLabel cardLabel) {
		Card cardToRemove = cardLabels.get(cardLabel);
		List<Card> cards = new ArrayList<>(cardList);
		for (Card card : cards) {
			if (card.getId() == cardToRemove.getId()) {
				cardList.remove(cardList.indexOf(card));
			}
		}
		cardLabels.remove(cardLabel);
		remove(cardLabel);
		revalidate();
		repaint();
	}
	
	/**
	 * Filters cards in card panel
	 * @param filter Filter object based on current state of filter fields
	 */
	public void filterCards(Filter filter) {
		Map<Supplier<Boolean>, Predicate<JLabel>> map = new HashMap<>();
		map.put(() -> filter.getName() != null, c -> cardLabels.get(c).getName().contains(filter.getName()));
		map.put(() -> filter.getType() != null, c -> cardLabels.get(c).getType().equalsIgnoreCase(filter.getType())
				&& !cardLabels.get(c).getType().equalsIgnoreCase(""));
		map.put(() -> filter.getAttribute() != null,
				c -> cardLabels.get(c).getAttribute().equalsIgnoreCase(filter.getAttribute())
						&& !cardLabels.get(c).getAttribute().equalsIgnoreCase(""));
		map.put(() -> filter.getRace() != null, c -> cardLabels.get(c).getRace().equalsIgnoreCase(filter.getRace())
				&& !cardLabels.get(c).getRace().equalsIgnoreCase(""));
		map.put(() -> filter.getArchetype() != null,
				c -> cardLabels.get(c).getArchetype().equalsIgnoreCase(filter.getArchetype())
						&& !cardLabels.get(c).getArchetype().equalsIgnoreCase(""));
		map.put(() -> filter.getAtkMin() != -1,
				c -> cardLabels.get(c).getAtk() >= filter.getAtkMin() && cardLabels.get(c).getAtk() != -1);
		map.put(() -> filter.getAtkMax() != -1,
				c -> cardLabels.get(c).getAtk() <= filter.getAtkMax() && cardLabels.get(c).getAtk() != -1);
		map.put(() -> filter.getDefMin() != -1,
				c -> cardLabels.get(c).getDef() >= filter.getDefMin() && cardLabels.get(c).getDef() != -1);
		map.put(() -> filter.getDefMax() != -1,
				c -> cardLabels.get(c).getDef() <= filter.getDefMax() && cardLabels.get(c).getDef() != -1);
		map.put(() -> filter.getLevelMin() != -1,
				c -> cardLabels.get(c).getLevel() >= filter.getLevelMin() && cardLabels.get(c).getLevel() != -1);
		map.put(() -> filter.getLevelMax() != -1,
				c -> cardLabels.get(c).getLevel() <= filter.getLevelMax() && cardLabels.get(c).getLevel() != -1);

		List<JLabel> filterList = cardLabels.keySet().stream().filter(map.entrySet().stream()
				.filter(e -> e.getKey().get()).map(Entry::getValue).reduce(i -> true, (l, r) -> l.and(r)))
				.collect(Collectors.toList());

		for (JLabel label : cardLabels.keySet()) {
			if (filterList.contains(label)) {
				label.setVisible(true);
			} else {
				label.setVisible(false);
			}
		}
	}
	
	/**
	 * Checks whether a label has been created for a given Card
	 * by comparing card id's
	 * @param card The card which will be used to find a matching label
	 * @return true if a label with a matching card id is found
	 */
	private boolean labelExists(Card card) {
		for (Card label : cardLabels.values()) {
			if (label.getId() == card.getId()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Counts the number of copies of a given Card in the cardList
	 * @return number of copies
	 */
	private int getNumCopies(Card card) {
		int numCopies = 0;
		for (int i = 0; i < cardList.size(); i++) {
			if (cardList.get(i).getId() == card.getId()) {
				numCopies++;
			}
		}
		return numCopies;
	}

	/**
	 * Creates a JLabel for a given Card
	 * @param card The Card object to create a JLabel for
	 * @return The new JLabel object
	 */
	private JLabel createLabel(Card card) {
		JLabel cardLabel = new JLabel();
		try {
			Image image = new ImageIcon(
					MainFrame.class.getResource("/dev/horyza/mcc/resources/" + card.getId() + ".jpg")).getImage();
			ImageIcon scaledImage = new ImageIcon(image.getScaledInstance(89, 127, Image.SCALE_SMOOTH));
			cardLabel.setIcon(scaledImage);
			cardLabel.addMouseListener(createMouseAdapter(cardLabel, card));
		} catch (Exception e) {

		}
		return cardLabel;
	}

	/**
	 * Creates a mouse adapter for a given JLabel
	 * @param cardLabel The JLabel object to create an adapter for
	 * @param card The Card object associated with the JLabel
	 * @return The new MouseAdapter object
	 */
	private MouseAdapter createMouseAdapter(JLabel cardLabel, Card card) {
		return (new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent evt) {
				frame.getInfoPanel().updateInfo(card);
				if (cardType == CardType.COLLECTION) {
					cardLabel.setToolTipText("Copies: " + getNumCopies(card));
				}
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
	}

	/**
	 * Gets the popup menu for a JLabel for the current panel type
	 * @param label JLabel object associated with the menu
	 * @return Popup menu with options for the current panel type
	 */
	private JPopupMenu getPopupMenu(JLabel label) {
		JPopupMenu popupMenu = new JPopupMenu();
		switch (cardType) {
		case CATALOG:
			popupMenu.add(addToCollection(label));
			popupMenu.add(addToDeck(label));
			break;
		case COLLECTION:
			popupMenu.add(removeFromCollection(label));
			popupMenu.add(addToDeck(label));
			break;
		case DECK:
			popupMenu.add(addToCollection(label));
			popupMenu.add(removeFromDeck(label));
			break;
		}
		return popupMenu;
	}

	/**
	 * Creates a JMenuItem for adding a given JLabel representing 
	 * a Card to the collection panel
	 * @param label JLabel to be added to the collection panel
	 * @return The new JMenuItem object
	 */
	private JMenuItem addToCollection(JLabel label) {
		JMenuItem addToCollection = new JMenuItem("Add to collection");
		addToCollection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatabaseHandler db = new DatabaseHandler();
				Card card = cardLabels.get(label);
				db.add(cardType.getTableName(), Arrays.asList(card));
				frame.getCollectionPanel().addCards(Arrays.asList(card));
			}
		});
		return addToCollection;
	}

	/**
	 * Creates a JMenuItem for adding a given JLabel representing
	 * a Card to the deck panel
	 * @param label JLabel to be added to the deck panel
	 * @return The new JMenuItem object
	 */
	private JMenuItem addToDeck(JLabel label) {
		JMenuItem addToDeck = new JMenuItem("Add to deck");
		addToDeck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Card card = cardLabels.get(label);
				frame.getDeckPanel().incrementCount(cardLabels.get(label).getType(), 1);
				frame.getDeckPanel().getCardPanel().addCards(Arrays.asList(card));
				if (frame.getDeckPanel().getCardPanel().getCardLabels().size() > 0) {
					frame.getDeckPanel().setVisible(true);
				}
			}
		});
		return addToDeck;
	}

	/**
	 * Creates a JMenuItem for removing a given JLabel representing
	 * a Card from the collection panel
	 * @param label JLabel to be removed from the collection panel
	 * @return The new JMenuItem object
	 */
	private JMenuItem removeFromCollection(JLabel label) {
		JMenuItem removeFromCollection = new JMenuItem("Remove from collection");
		removeFromCollection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeCard(label);
			}
		});
		return removeFromCollection;
	}

	/**
	 * Creates a JMenuItem for removing a given JLabel representing
	 * a Card from the deck panel
	 * @param label JLabel to be removed from the deck panel
	 * @return The new JMenuItem object
	 */
	private JMenuItem removeFromDeck(JLabel label) {
		JMenuItem removeFromDeck = new JMenuItem("Remove from deck");
		removeFromDeck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getDeckPanel().decrementCount(cardLabels.get(label).getType(), 1);
				removeCard(label);
				if (frame.getDeckPanel().getCardPanel().getCardLabels().size() == 0)
					frame.getDeckPanel().setVisible(false);
			}
		});
		return removeFromDeck;
	}

	public HashMap<JLabel, Card> getCardLabels() {
		return cardLabels;
	}
	
	public List<Card> getCardList() {
		return cardList;
	}
}
