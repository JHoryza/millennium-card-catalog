package dev.horyza.mcc.model;

import java.util.ArrayList;

public class Collection {

	private int id;
	private ArrayList<Card> cardList;
	
	public Collection(int id) {
		this.id = id;
		this.cardList = new ArrayList<Card>();
	}
	
	public void addCard(Card card) {
		cardList.add(card);
	}
	
	public void removeCard(Card card) {
		cardList.remove(cardList.indexOf(card));
	}
	
	public void setCardList(ArrayList<Card> cardList) {
		this.cardList = cardList;
	}
}
