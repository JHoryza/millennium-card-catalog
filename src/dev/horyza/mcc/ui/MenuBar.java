package dev.horyza.mcc.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import dev.horyza.mcc.model.Card;
import dev.horyza.mcc.services.DatabaseHandler;
import dev.horyza.mcc.ui.MainFrame.CardType;

public class MenuBar extends JMenuBar {

	private MainFrame frame;
	
	public MenuBar(MainFrame frame) {
		this.frame = frame;
		add(getFileMenu());
	}
	
	private JMenu getFileMenu() {
		JMenu fileMenu = new JMenu("File");
		
		JMenuItem save = new JMenuItem("Save");
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatabaseHandler db = new DatabaseHandler();
				
				String collectionTable = CardType.COLLECTION.getTableName();
				List<Card> collectionCards = frame.getCollectionPanel().getCardList();
				db.clear(collectionTable);
				db.add(collectionTable, collectionCards);
				
				String deckTable = CardType.DECK.getTableName();
				List<Card> deckCards = frame.getDeckPanel().getCardPanel().getCardList();
				db.clear(deckTable);
				db.add(deckTable, deckCards);
			}
		});
		fileMenu.add(save);
		
		JMenu importMenu = new JMenu("Import");
		JMenuItem importCollection = new JMenuItem("Collection");
		importCollection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ImportOptionPanel(frame);
			}
		});
		importMenu.add(importCollection);
		
		JMenuItem importDeck = new JMenuItem("Deck");
		importDeck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		importMenu.add(importDeck);
		fileMenu.add(importMenu);
		
		JMenu exportMenu = new JMenu("Export");
		JMenuItem exportCollection = new JMenuItem("Collection");
		exportCollection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		exportMenu.add(exportCollection);
		
		JMenuItem exportDeck = new JMenuItem("Deck");
		exportDeck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		exportMenu.add(exportDeck);
		fileMenu.add(exportMenu);
		
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		fileMenu.add(exit);
		
		return fileMenu;
	}
}
