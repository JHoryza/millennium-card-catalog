package dev.horyza.mcc.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import dev.horyza.mcc.model.Card;

public class MenuBar extends JMenuBar {

	private MainFrame frame;
	
	public MenuBar(MainFrame frame) {
		this.frame = frame;
		add(getFileMenu());
		add(getViewMenu());
	}
	
	private JMenu getFileMenu() {
		JMenu fileMenu = new JMenu("File");
		
		JMenuItem importCollection = new JMenuItem("Import Collection");
		importCollection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ImportOptionPanel(frame);
			}
		});
		fileMenu.add(importCollection);
		
		return fileMenu;
	}
	
	private JMenu getViewMenu() {
		JMenu viewMenu = new JMenu("View");
		
		JMenuItem viewCatalog = new JMenuItem("Catalog");
		viewCatalog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getCardScrollPane().setViewportView(frame.getCatalogPanel());	
			}
		});
		viewMenu.add(viewCatalog);
		
		JMenuItem viewCollection = new JMenuItem("Collection");
		viewCollection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getCardScrollPane().setViewportView(frame.getCollectionPanel());	
			}
		});
		viewMenu.add(viewCollection);
		
		return viewMenu;
	}
}
