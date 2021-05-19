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
	private JMenuItem openCatalog;
	private JMenuItem openCollection;
	
	public MenuBar(MainFrame frame) {
		this.frame = frame;
		add(getFileMenu());
		add(getCatalogMenu());
		add(getCollectionMenu());
		add(getDeckMenu());
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
		
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		fileMenu.add(exit);
		
		return fileMenu;
	}
	
	private JMenu getCatalogMenu() {
		JMenu catalogMenu = new JMenu("Catalog");
		
		openCatalog = new JMenuItem("Open Catalog");
		openCatalog.setEnabled(false);
		openCatalog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getCardScrollPane().setViewportView(frame.getCatalogPanel());	
				frame.setActiveCardType(CardType.CATALOG);
				openCatalog.setEnabled(false);
				openCollection.setEnabled(true);
			}
		});
		catalogMenu.add(openCatalog);
		
		return catalogMenu;
	}
	
	private JMenu getCollectionMenu() {
		JMenu collectionMenu = new JMenu("Collection");
		
		openCollection = new JMenuItem("Open Collection");
		openCollection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getCardScrollPane().setViewportView(frame.getCollectionPanel());	
				frame.setActiveCardType(CardType.COLLECTION);
				openCatalog.setEnabled(true);
				openCollection.setEnabled(false);
			}
		});
		collectionMenu.add(openCollection);
		
		JMenuItem importCollection = new JMenuItem("Import");
		importCollection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ImportOptionPanel(frame);
			}
		});
		collectionMenu.add(importCollection);
		
		JMenuItem exportCollection = new JMenuItem("Export");
		exportCollection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		collectionMenu.add(exportCollection);
		
		return collectionMenu;
	}
	
	private JMenu getDeckMenu() {
		JMenu deckMenu = new JMenu("Deck");
		
		JMenuItem importDeck = new JMenuItem("Import");
		importDeck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		deckMenu.add(importDeck);
		
		JMenuItem exportDeck = new JMenuItem("Export");
		exportDeck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		deckMenu.add(exportDeck);
		
		return deckMenu;
	}
}
