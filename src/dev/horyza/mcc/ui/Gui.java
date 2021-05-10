package dev.horyza.mcc.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import dev.horyza.mcc.services.CollectionManager;

public class GUI extends JFrame {
	
	public GUI() {
		setTitle("YU-GI-OH");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setMinimumSize(new Dimension(800, 600));
		setContentPane(createContentPane());
		setJMenuBar(menuBar);
	}

	private JPanel createContentPane() {
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setOpaque(true);

		// Filter panel
		contentPane.add(filterPanel, BorderLayout.NORTH);

		// Info panel
		contentPane.add(infoPanel, BorderLayout.WEST);

		// Card panel
		JScrollPane cardScrollPane = new JScrollPane(cardPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		cardScrollPane.getVerticalScrollBar().setUnitIncrement(32);
		contentPane.add(cardScrollPane, BorderLayout.CENTER);
		
		// Deck panel
		contentPane.add(deckPanel, BorderLayout.SOUTH);

		return contentPane;
	}
	
	private MenuBar menuBar = new MenuBar();
	private CollectionManager collectionManager = new CollectionManager(this);
	private FilterPanel filterPanel = new FilterPanel(this);
	private InfoPanel infoPanel = new InfoPanel();
	private CardPanel cardPanel = new CardPanel(this);
	private DeckPanel deckPanel = new DeckPanel(this);
	
	public InfoPanel getInfoPanel() {
		return infoPanel;
	}
	
	public CardPanel getCardPanel() {
		return cardPanel;
	}
	
	public CollectionManager getCollectionManager() {
		return collectionManager;
	}
}
