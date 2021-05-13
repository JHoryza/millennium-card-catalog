package dev.horyza.mcc.ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
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

import dev.horyza.mcc.model.Card;
import dev.horyza.mcc.model.Filter;
import dev.horyza.mcc.services.DatabaseHandler;
import dev.horyza.mcc.ui.MainFrame.CardList;
import dev.horyza.mcc.util.WrapLayout;

public class CardPanel extends JPanel {

	private MainFrame frame;
	private DatabaseHandler db = new DatabaseHandler();
	private CardList cardList;
	private HashMap<JLabel, Card> cardMap = new HashMap<JLabel, Card>();

	public CardPanel(MainFrame frame, CardList cardList) {
		this.frame = frame;
		this.cardList = cardList;
		setLayout(new WrapLayout(FlowLayout.CENTER, 5, 5));
		setBackground(Color.DARK_GRAY);
		loadCards(db.selectAll(cardList.getTableName()));
	}

	public void loadCards(List<Card> cards) {
		// Load cards
		for (Card card : cards) {
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

				cardMap.put(cardLabel, card);
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
			cardMap.put(cardLabel, card);
			add(cardLabel);
		} catch (Exception e) {

		}
	}

	public void removeCard(JLabel cardLabel) {
		cardMap.remove(cardLabel);
		remove(cardLabel);
		revalidate();
		repaint();
	}

	public void filterCards(Filter filter) {

		Map<Supplier<Boolean>, Predicate<JLabel>> map = new HashMap<>();
		map.put(() -> filter.getName() != null, c -> cardMap.get(c).getName().contains(filter.getName()));
		map.put(() -> filter.getType() != null, c -> cardMap.get(c).getType().equalsIgnoreCase(filter.getType()) && !cardMap.get(c).getType().equalsIgnoreCase(""));
		map.put(() -> filter.getAttribute() != null, c -> cardMap.get(c).getAttribute().equalsIgnoreCase(filter.getAttribute()) && !cardMap.get(c).getAttribute().equalsIgnoreCase(""));
		map.put(() -> filter.getRace() != null, c -> cardMap.get(c).getRace().equalsIgnoreCase(filter.getRace()) && !cardMap.get(c).getRace().equalsIgnoreCase(""));
		map.put(() -> filter.getArchetype() != null, c -> cardMap.get(c).getArchetype().equalsIgnoreCase(filter.getArchetype()) && !cardMap.get(c).getArchetype().equalsIgnoreCase(""));
		map.put(() -> filter.getAtkMin() != -1, c -> cardMap.get(c).getAtk() >= filter.getAtkMin() && cardMap.get(c).getAtk() != -1);
		map.put(() -> filter.getAtkMax() != -1, c -> cardMap.get(c).getAtk() <= filter.getAtkMax() && cardMap.get(c).getAtk() != -1);
		map.put(() -> filter.getDefMin() != -1, c -> cardMap.get(c).getDef() >= filter.getDefMin() && cardMap.get(c).getDef() != -1);
		map.put(() -> filter.getDefMax() != -1, c -> cardMap.get(c).getDef() <= filter.getDefMax() && cardMap.get(c).getDef() != -1);
		map.put(() -> filter.getLevelMin() != -1, c -> cardMap.get(c).getLevel() >= filter.getLevelMin() && cardMap.get(c).getLevel() != -1);
		map.put(() -> filter.getLevelMax() != -1, c -> cardMap.get(c).getLevel() <= filter.getLevelMax() && cardMap.get(c).getLevel() != -1);

		List<JLabel> filterList = cardMap.keySet().stream().filter(map.entrySet()
				.stream().filter(e -> e.getKey().get())
				.map(Entry::getValue)
				.reduce(i -> true, (l, r) -> l.and(r)))
				.collect(Collectors.toList());
		
		for (JLabel label : cardMap.keySet()) {
			if (filterList.contains(label)) {
				label.setVisible(true);
			} else {
				label.setVisible(false);
			}
		}
	}

	private JPopupMenu getPopupMenu(JLabel label) {
		JPopupMenu popupMenu = new JPopupMenu();
		switch (cardList) {
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
				Card card = cardMap.get(label);
				frame.getCollectionPanel().addCard(card);
			}
		});
		return addToCollection;
	}

	private JMenuItem addToDeck(JLabel label) {
		JMenuItem addToDeck = new JMenuItem("Add to deck");
		addToDeck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Card card = cardMap.get(label);
				// TODO add to deck
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
				// TODO remove from deck
			}
		});
		return removeFromDeck;
	}
}
