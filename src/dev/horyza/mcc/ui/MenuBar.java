package dev.horyza.mcc.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import dev.horyza.mcc.model.Card;
import dev.horyza.mcc.ui.MainFrame.CardList;

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
		
		return fileMenu;
	}
	
	private JMenu getCatalogMenu() {
		JMenu catalogMenu = new JMenu("Catalog");
		
		openCatalog = new JMenuItem("Open Catalog");
		openCatalog.setEnabled(false);
		openCatalog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getCardScrollPane().setViewportView(frame.getCatalogPanel());	
				frame.setActvCrdLst(CardList.CATALOG);
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
				frame.setActvCrdLst(CardList.COLLECTION);
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
