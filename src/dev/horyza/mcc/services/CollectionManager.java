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
	private Collection collection = new Collection();
	private DatabaseHandler db = new DatabaseHandler();
	
	public CollectionManager(MainFrame frame) {
		this.frame = frame;
	}
	
	public Collection getCollection() {
		return collection;
	}
}
