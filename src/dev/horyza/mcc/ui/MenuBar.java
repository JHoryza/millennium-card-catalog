package dev.horyza.mcc.ui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar {

	public MenuBar() {
		add(getFileMenu());
	}
	
	private JMenu getFileMenu() {
		JMenu fileMenu = new JMenu("File");
		
		JMenuItem viewCatalog = new JMenuItem("View Catalog");
		fileMenu.add(viewCatalog);
		
		JMenuItem viewCollection = new JMenuItem("View Collection");
		fileMenu.add(viewCollection);
		
		return fileMenu;
	}
}
