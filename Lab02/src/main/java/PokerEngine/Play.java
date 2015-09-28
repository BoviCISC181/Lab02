package PokerEngine;
import java.util.ArrayList;
import java.util.Comparator;

public class Play {
	public static void main(String[] args){
		Deck a = new Deck();
		a.Shuffle(a.getCardDeck());
		
		for (Card c : a.getCardDeck()){
			System.out.println(c.getRank());
		}
		
		System.out.println("cards left : "+a.cardsLeft());
		
		for (Card c : a.getCardDeck()){
			System.out.println(c.getRank());
		}
		
		Hand h1 = new Hand(a);
		
		System.out.println("cards left : "+a.cardsLeft());
		
		for (Card c : a.getCardDeck()){
			System.out.println(c.getRank());
		}
		
		Hand h2 = new Hand(a);
		System.out.println(a.cardsLeft());
		System.out.println(h1);
		System.out.println(Hand.score(h1));
		System.out.println(h2);
		System.out.println(Hand.score(h2));
		compare(h1,h2);
	}
	
	public static void compare(Hand h1, Hand h2){
		double result = 0;
		double hiHandWinner = 0;
		
		result = Hand.score(h2) - Hand.score(h1);
		
		if(result > 0){
			System.out.println("Hand 2 wins");
		}
		else if (result < 0){
			System.out.println("Hand 1 wins");
		}
		else if (result == 0){
			/*
			 * the tiebreakers should be handled here.
			 * a method has to be written for each case
			 */
			if (Hand.score(h1)==10){
				System.out.println("Royal flush: tie.");
			}
			int h1Score = Hand.score(h1);
			if (h1Score == 9){
				if (Hand.straightTie(h1)>Hand.straightTie(h2)){
					System.out.println("Hand 1 wins");
				}
				else if (Hand.straightTie(h1)<Hand.straightTie(h2)){
					System.out.println("Hand 2 wins");
				}
				else{
					System.out.println("tie");
				}
			}
			if (h1Score == 8){
				if (Hand.fourOfaKindTie(h1)>Hand.fourOfaKindTie(h2)){
					System.out.println("Hand 1 wins");
				}
				else if (Hand.fourOfaKindTie(h1)<Hand.fourOfaKindTie(h2)){
					System.out.println("Hand 2 wins");
				}
			}
			if (h1Score == 7){
				if (Hand.threeOfaKindTie(h1)>Hand.threeOfaKindTie(h2)){
					System.out.println("Hand 1 wins");
				}
				else if (Hand.threeOfaKindTie(h1)<Hand.threeOfaKindTie(h2)){
					System.out.println("Hand 2 wins");
				}
			}
			if (h1Score == 6){
				if (Hand.highCardTie(h1)>Hand.highCardTie(h2)){
					System.out.println("Hand 1 wins");
				}
				else if (Hand.highCardTie(h1)<Hand.highCardTie(h2)){
					System.out.println("Hand 2 wins");
				}
				else{
					System.out.println("tie");
				}
			}
			if (h1Score == 5){
				if (Hand.straightTie(h1)>Hand.straightTie(h2)){
					System.out.println("Hand 1 wins");
				}
				else if (Hand.straightTie(h1)<Hand.straightTie(h2)){
					System.out.println("Hand 2 wins");
				}
				else{
					System.out.println("tie");
				}
			}
			if (h1Score == 4){
				if (Hand.threeOfaKindTie(h1)>Hand.threeOfaKindTie(h2)){
					System.out.println("Hand 1 wins");
				}
				else if (Hand.threeOfaKindTie(h1)<Hand.threeOfaKindTie(h2)){
					System.out.println("Hand 2 wins");
				}
			}
			if (h1Score == 3){
				if (Hand.twoPairTie(h1)>Hand.twoPairTie(h2)){
					System.out.println("Hand 1 wins");
				}
				else if (Hand.twoPairTie(h1)<Hand.twoPairTie(h2)){
					System.out.println("Hand 2 wins");
				}
				else{
					if (Hand.twoPairKicker(h1)>Hand.twoPairKicker(h2)){
						System.out.println("Hand 1 wins");
					}
					else if (Hand.twoPairKicker(h1)<Hand.twoPairKicker(h2)){
						System.out.println("Hand 2 wins");
					}
					else{
						System.out.println("tie");
					}
				}
			}
			if (h1Score == 2){
				if (Hand.onePairTie(h1)>Hand.onePairTie(h2)){
					System.out.println("Hand 1 wins");
				}
				else if (Hand.onePairTie(h1)<Hand.onePairTie(h2)){
					System.out.println("Hand 2 wins");
				}
				else{
					if (Hand.onePairKicker(h1)>Hand.onePairKicker(h2)){
						System.out.println("Hand 1 wins");
					}
					else if (Hand.onePairKicker(h1)<Hand.onePairKicker(h2)){
						System.out.println("Hand 2 wins");
					}
					else{
						System.out.println("tie");
					}
				}
			}
			if (h1Score == 1){
				if (Hand.highCardTie(h1)>Hand.highCardTie(h2)){
					System.out.println("Hand 1 wins");
				}
				else if (Hand.highCardTie(h1)<Hand.highCardTie(h2)){
					System.out.println("Hand 2 wins");
				}
				else{
					String winner = (Hand.highCardTie2(h1, h2));
					System.out.println(winner);
				}
			}
		}
	}
}







