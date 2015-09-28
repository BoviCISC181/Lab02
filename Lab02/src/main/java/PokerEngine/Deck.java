package PokerEngine;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	//private ArrayList deck;
	private ArrayList<Card> cardDeck = new ArrayList<Card>();
	
	public Deck(){
		String[] suits = {"diamonds","hearts","clubs","spade"};
		int j = 0;
		for(String suit : suits){
			for (int i=2;i<=14;i++){
				Card c = new Card(i, suit, j);
				this.cardDeck.add(c);
				j++;
			}
		}
	}
	
	public void Shuffle(ArrayList<Card> deck){
		Collections.shuffle(deck);
	}
	
	public ArrayList<Card> getCardDeck(){
		return(this.cardDeck);
	}
	
	public Card draw(){
		
		//return(cardDeck.remove(0));
		//Card nextCard1 = this.getCardDeck().get(0);
		//this.getCardDeck().remove(0);
		return(this.getCardDeck().remove(0));
		
//		Card nextCard = cardDeck.get(0);
//		cardDeck.remove(0);
//		return nextCard;
	}
	
	public int cardsLeft(){
		return cardDeck.size();
	}
}