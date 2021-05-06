package dev.horyza.mcc.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import dev.horyza.mcc.services.CollectionManager;
import dev.horyza.mcc.ui.catalog.CardPanel;
import dev.horyza.mcc.ui.catalog.DeckPanel;
import dev.horyza.mcc.ui.catalog.FilterPanel;
import dev.horyza.mcc.ui.catalog.InfoPanel;

public class GUI extends JFrame {

	private CollectionManager collectionManager = new CollectionManager();
	private FilterPanel filterPanel = new FilterPanel();
	private InfoPanel infoPanel = new InfoPanel();
	private CardPanel cardPanel = new CardPanel(this);
	private DeckPanel deckPanel = new DeckPanel(this);
	
	public GUI() {
		setTitle("YU-GI-OH");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		setMinimumSize(new Dimension(800, 600));
		setContentPane(createContentPane());
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
		contentPane.add(cardScrollPane, BorderLayout.CENTER);
		
		// Deck panel
		contentPane.add(deckPanel, BorderLayout.SOUTH);

		return contentPane;
	}
	
	public InfoPanel getInfoPanel() {
		return infoPanel;
	}
	
	public CollectionManager getCollectionManager() {
		return collectionManager;
	}
}
