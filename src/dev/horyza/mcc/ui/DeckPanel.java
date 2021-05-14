package dev.horyza.mcc.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import dev.horyza.mcc.model.Card;
import dev.horyza.mcc.services.DatabaseHandler;
import dev.horyza.mcc.ui.MainFrame.CardList;
import dev.horyza.mcc.util.OverlapLayout;

public class DeckPanel extends JPanel {
	
	private MainFrame frame;
	private HashMap<JLabel, Card> cardMap = new HashMap<JLabel, Card>();
	private JPanel cardPanel = new JPanel();

	public DeckPanel(MainFrame frame) {
		this.frame = frame;
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBackground(Color.LIGHT_GRAY);

		DatabaseHandler db = new DatabaseHandler();
		JScrollPane cardScrollPane = new JScrollPane(cardPanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		cardPanel.setLayout(new OverlapLayout(new Point(20, 0)));
		cardPanel.setPreferredSize(new Dimension(800, 127));
		add(cardScrollPane);
		addCards(db.selectAll("deck"));
	}

	public void addCards(List<Card> cardsToAdd) {
		for (int i = 0; i < cardsToAdd.size(); i++) {
			Card card = cardsToAdd.get(i);
			JLabel cardLabel = createLabel(card);

			List<Card> temp = cardsToAdd.subList(0, i);
			if (temp.contains(card)) {
				continue;
			}

			cardPanel.add(cardLabel);
			cardMap.put(cardLabel, card);
		}
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
			e.printStackTrace();
		}
		return cardLabel;
	}

	private MouseAdapter createMouseAdapter(JLabel cardLabel, Card card) {
		return (new MouseAdapter() {
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
	}

	public void removeCard(JLabel cardLabel) {
		cardMap.remove(cardLabel);
		cardPanel.remove(cardLabel);
		cardPanel.revalidate();
		cardPanel.repaint();
	}

	private JPopupMenu getPopupMenu(JLabel label) {
		JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem removeFromDeck = new JMenuItem("Remove from deck");
		removeFromDeck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeCard(label);
			}
		});
		popupMenu.add(removeFromDeck);
		return popupMenu;
	}
}
