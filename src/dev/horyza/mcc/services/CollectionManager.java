package dev.horyza.mcc.services;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JLabel;

import dev.horyza.mcc.model.Collection;
import dev.horyza.mcc.model.Filter;

public class CollectionManager {

	private Collection catalog = new Collection();
	
	public CollectionManager() {
		updateCardList(catalog);
	}
	
	private void updateCardList(Collection collection) {
		DatabaseHandler db = new DatabaseHandler();
		collection.setCardList(db.selectAll("cards"));
	}
	
	private void updateCardList(Collection collection, Filter filter) {
		DatabaseHandler db = new DatabaseHandler();
		collection.setCardList(db.selectAll("cards"));
	}
	
	public Collection getCatalog() {
		return catalog;
	}
}
