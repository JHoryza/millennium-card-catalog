package dev.horyza.mcc.services;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import dev.horyza.mcc.model.Card;
import dev.horyza.mcc.model.Collection;
import dev.horyza.mcc.ui.MainFrame;

public class CollectionManager {

	private MainFrame frame;
	private Collection catalog = new Collection();
	private Collection userCollection = new Collection();
	private HashMap<JLabel, Card> cardList = new HashMap<JLabel, Card>();
	
	public CollectionManager(MainFrame frame) {
		this.frame = frame;
		DatabaseHandler db = new DatabaseHandler();
		catalog.setCardList(db.selectAll("cards"));
	}
	
	public Collection getCatalog() {
		return catalog;
	}
	
	public Collection getUserCollection() {
		return userCollection;
	}
}
