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
	}
	
	private JMenu getFileMenu() {
		JMenu fileMenu = new JMenu("File");
		
		JMenuItem viewCatalog = new JMenuItem("View Catalog");
		viewCatalog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getCardScrollPane().setViewportView(frame.getCatalogPanel());
				
			}
		});
		fileMenu.add(viewCatalog);
		
		JMenuItem viewCollection = new JMenuItem("View Collection");
		viewCollection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.getCardScrollPane().setViewportView(frame.getCollectionPanel());
				
			}
		});
		fileMenu.add(viewCollection);
		
		return fileMenu;
	}
}
