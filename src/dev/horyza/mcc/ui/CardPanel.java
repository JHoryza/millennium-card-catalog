package dev.horyza.mcc.ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
import javax.swing.ToolTipManager;

import dev.horyza.mcc.model.Card;
import dev.horyza.mcc.model.Filter;
import dev.horyza.mcc.services.DatabaseHandler;
import dev.horyza.mcc.ui.MainFrame.CardType;
import dev.horyza.mcc.util.WrapLayout;

public class CardPanel extends JPanel {

	private MainFrame frame;
	private DatabaseHandler db = new DatabaseHandler();
	private CardType cardType;
	private HashMap<JLabel, Card> cardLabels = new HashMap<JLabel, Card>();
	private List<Card> cardList = new ArrayList<>();

	public CardPanel(MainFrame frame, CardType cardList) {
		this.frame = frame;
		this.cardType = cardList;
		addCards(db.selectAll(cardList.getTableName()));
	}

	public void addCards(List<Card> cardsToAdd) {
		cardList.addAll(cardsToAdd);
		int cursor = 0;
		for (int i = 0; i < cardsToAdd.size(); i++) {
			Card card = cardsToAdd.get(i);
			if (i != 0) {
				for (int j = cursor; j < i; j++) {
					cursor++;
					if (cardList.get(j).getId() == card.getId()) {
						continue;
					}
					JLabel cardLabel = createLabel(card);
					add(cardLabel);
					cardLabels.put(cardLabel, card);
				}
			} else {
				JLabel cardLabel = createLabel(card);
				add(cardLabel);
				cardLabels.put(cardLabel, card);
			}
		}
	}

	private int getNumCopies(Card card) {
		int numCopies = 0;
		for (int i = 0; i < cardList.size(); i++) {
			if (cardList.get(i).getId() == card.getId()) {
				numCopies++;
			}
		}
		return numCopies;
	}

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

	public void removeCard(JLabel cardLabel) {
		cardLabels.remove(cardLabel);
		remove(cardLabel);
		revalidate();
		repaint();
	}

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

	private JMenuItem addToDeck(JLabel label) {
		JMenuItem addToDeck = new JMenuItem("Add to deck");
		addToDeck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Card card = cardLabels.get(label);
				frame.getDeckPanel().incrementCount(cardLabels.get(label).getType(), 1);
				frame.getDeckPanel().getCardPanel().addCards(Arrays.asList(card));
				if (frame.getDeckPanel().getCardPanel().getCardLabels().size() > 0)
					frame.getDeckPanel().setVisible(true);
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
