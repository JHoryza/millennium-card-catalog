package dev.horyza.mcc.model;

import java.util.ArrayList;

public class Collection {

	private ArrayList<Card> cardList;
	
	public Collection() {
		this.cardList = new ArrayList<Card>();
	}
	
	public Collection(ArrayList<Card> cardList) {
		this.cardList = cardList;
	}
	
	public void addCard(Card card) {
		cardList.add(card);
	}
	
	public void removeCard(Card card) {
		cardList.remove(cardList.indexOf(card));
	}
	
	public ArrayList<Card> getCardList() {
		return cardList;
	}
	
	public void setCardList(ArrayList<Card> cardList) {
		this.cardList = cardList;
	}
}
