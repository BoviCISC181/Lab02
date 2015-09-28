package PokerEngine;

import java.util.ArrayList;

public class Hand {
	
	private static Card[] cardHand = new Card[5];
	public static int[] rankTally = new int[13];
	
	public Hand(Deck x){
		for(int i=0; i<=4; i++){
			this.cardHand[i] = x.draw();
		}
		sortHand();
		this.rankTally = tally(this.cardHand);
	}
	
	public void sortHand(){
		for (int i=0;i<this.cardHand.length-1;i++){
			for (int j=1;j<this.cardHand.length-1;j++){
				if (cardHand[i].getRank()>cardHand[j].getRank()){
					//holderCard holds the value, so it's not lost in the switch
					Card holderCard = cardHand[j];
					cardHand[j] = cardHand[i];
					cardHand[i] = holderCard;
				}
			}
		}
	}
	
	public static int[] tally(Card[] cardHand){
		int[] rank = new int [13];
		//int suit[] = new int [4];
		
		
		for(int i = 0; i<=4;i++){
	/*			if(rank[cardHand[i].getRank()] == null){
				rank[cardHand[i].getRank()] = 0;
			}*/
			rank[cardHand[i].getRank()-2]++;
		}
		return rank;
	}
	
	public static boolean isRoyalFlush(Card[] cardHand){
		return((cardHand[0].getRank()==10) && isFlush(cardHand) && isStraight(cardHand));
	}

	public static boolean isStraightFlush(Card[] cardHand){
		return(isFlush(cardHand) && isStraight(cardHand));
	}

	public static boolean isFourOfaKind(Card[] cardHand){
		int[] rank = tally(cardHand);
		for(int i = 0; i<=12; i++){
			if(rank[i] == 4){
				return(true);
			}
		}
		return(false);
	}
	
	public static boolean isFlush(Card[] cardHand){
		//if any don't match the suit of the first, the suits are not all the same
		for (int i=0;i<5;i++){
			if (cardHand[0].getSuit() != cardHand[i].getSuit()){
				return(false);
			}
		}
		return(true);
	}
	
	public static boolean isStraight(Card[] cardHand){
		//once it's sorted, the last card rank - first card rank must equal 4 (since they'd be in order)
		//just make sure to sort in Play class once the hand is populated with cards
		return((cardHand[4].getRank()-cardHand[0].getRank())==4);
	}
	
	public static boolean isFullHouse(int[] rankTally){
		//Check Full House
		boolean threeOfAKind = false;
		boolean pair = false;
		double hiHand = 0;
		double lowHand = 0;
		for(int i = 0; i<=12; i++){
			if(rankTally[i] == 3){
				threeOfAKind = true;
				hiHand = i+2;
			}
			if(rankTally[i] == 2){
				pair = true;
				lowHand = i+2;
			}
		}
		if (pair && threeOfAKind){
			return(true);
		}
		else{
			return(false);
		}
		//End Check Full House		
	}
	
	public static boolean isThreeOfaKind(int[] rankTally){
		boolean three = false;
		boolean two = false;
		for (int i=0;i<=12;i++){
			if (rankTally[i] == 3){
				three = true;
			}
			else if (rankTally[i] == 2){
				two = true;
			}
		}
		return(three && (!two));
	}
	
	public static boolean isTwoPair(int[] rankTally){
		//Check Two Pair
		int paircount = 0;
		for(int numberOfRanks : rankTally){
			if(numberOfRanks==2)
				paircount++;
		}
		if(paircount==2){
			return(true);
		}
		else{
			return(false);
		}
		//End Check Two Pair
	}
	
	public static boolean isOnePair(int[] rankTally){
		int paircount = 0;
		for(int numberOfRanks : rankTally){
			if(numberOfRanks==2)
				paircount++;
		}
		if(paircount==1){
			return(true);
		}
		else{
			return(false);
		}
	}
	
	public static int score(Hand hand){
		if (hand.isRoyalFlush(hand.cardHand)){
			return(10);
		}
		else if (hand.isStraightFlush(cardHand)){
			return(9);
		}
		else if (hand.isFourOfaKind(cardHand)){
			return(8);
		}
		else if (hand.isFlush(cardHand)){
			return(7);
		}
		else if (hand.isStraight(cardHand)){
			return(6);
		}
		else if (hand.isFullHouse(rankTally)){
			return(5);
		}
		else if (hand.isThreeOfaKind(rankTally)){
			return(4);
		}
		else if (hand.isTwoPair(rankTally)){
			return(3);
		}
		else if (hand.isOnePair(rankTally)){
			return(2);
		}
		else{
			return(1);
		}
	}

	public static int fourOfaKindTie(Hand hand){
		int highestRank = 0;
		for (int i=0;i<=12;i++){
			if (hand.rankTally[i] == 4){
				return(i);
			}
		}
		//just have to return something
		return(0); 
	}
	
	public static int straightTie(Hand hand){
		return(hand.cardHand[0].getRank());
	}
	
	public static int threeOfaKindTie(Hand hand){
		int highestRank = 0;
		for (int i=0;i<=12;i++){
			if (hand.rankTally[i] == 3){
				return(i);
			}
		}
		//just have to return something
		return(0);
	}
	
	public static int twoPairTie(Hand hand){
		int highestPairRank = 0;
		for (int i=0;i<=12;i++){
			if ((hand.rankTally[i] == 2) && (i>highestPairRank)){
				highestPairRank = i;
			}
		}
		return(highestPairRank);
	}
	
	public static int onePairTie(Hand hand){
		for (int i=0;i<=12;i++){
			if (hand.rankTally[i] == 2){
				return(i);
			}
		}
		//it will return i since we already know that it's a one pair tie
		return(0);
	}

	public static int onePairKicker(Hand hand){
		int high = 0;
		for (int i=0;i<=12;i++){
			if ((hand.rankTally[i] != 2) && (hand.rankTally[i] != 0) && (hand.rankTally[i]>high)){
				high = hand.rankTally[i];
			}
		}
		return(high);
	}
	
	public static int twoPairKicker(Hand hand){
		int high = 0;
		for (int i=0;i<=12;i++){
			if ((hand.rankTally[i] != 2) && (hand.rankTally[i] != 0)){
				high = hand.rankTally[i];
			}
		}
		return(high);
	}
	
	public static String highCardTie2(Hand hand1, Hand hand2){
		for (int i=4;i>=0;i--){
			if (hand1.cardHand[i].getRank() > hand2.cardHand[i].getRank()){
				return ("Hand 1 wins");
			}
			else if (hand1.cardHand[i].getRank() < hand2.cardHand[i].getRank()){
				return ("Hand 2 wins");
			}
			else{
				return("tie");
			}
		}
		return("");
	}
	
	public static int highCardTie(Hand hand){
		int highestRank = 0;
		for (Card c : hand.cardHand){
			if (c.getRank()>highestRank){
				highestRank = c.getRank();
			}
		}
		return(highestRank);
	}
	
	public String toString(){
		String whatsInTheHand = "";
		for(Card inTheHand : cardHand){
			whatsInTheHand += inTheHand.toString() + "\n";
		}
		return whatsInTheHand;
	}

	
//	public static int pairTieKickers(Hand hand){
//		ArrayList<Card> kickers = new ArrayList<Card>();
//		for (int i=0;i<=12;i++){
//			if ((hand.rankTally[i] != 0) && (hand.rankTally[i] != 2)){
//				kickers.add(hand.rankTally[i]);
//			}
//		}
//		
//	}
	
	
//	public double score(int[] rank){
//		
//
//		
//		//Hand scoring begins here.
//		
//		//Check Royal Flush
//	
//
//		
//		boolean straight = false;
//		
//		int sortedRanks[] = new int [5];
//		for(int i = 0; i<=4; i++){
//			sortedRanks[i] = cardHand[i].getRank();
//		}
//		int straightArray[] = new int [5];
//		
//		Arrays.sort(sortedRanks);
//		
//		int firstCard = sortedRanks[0];
//		
//		for(int i = 0; i<=4; i++){
//			straightArray[i] = i;
//			sortedRanks[i] -= firstCard;
//		}
//		if(sortedRanks.equals(straightArray))
//			straight=true;
//		
//		if(firstCard == 10 && straight && flush)
//			return 10;
//		//End Check Royal Flush
//		
//		//Check Straight Flush
//		if(straight && flush)
//			return 9;
//		//End Check Straight Flush
//		
//		//Check Four of a Kind
//		for(int i = 0; i<=12; i++){
//			if(rank[i] == 4){
//				return 8;
//			}
//		}
//		//End Check Four of a Kind
//		
//		
//		//Check Flush
//		if(flush)
//			return 6;
//		//End Check Flush
//		
//		//Check Straight
//		if(straight)
//			return 5;
//		//End Check Straight
//		
//		//Check Three of a Kind
//		if(threeOfAKind)
//			return 4;
//		//End Check Three of a Kind
//		
//		//Check Two Pair
//		int paircount = 0;
//		for(int numberOfRanks : rank){
//			if(numberOfRanks==2)
//				paircount++;
//		}
//		if(paircount==2)
//			return 3;
//		//End Check Two Pair
//		
//		//Check Pair
//		if(pair)
//			return 2;
//		//End Check Pair
//		
//		//Check High Card
//		for(int i = 12; i>=0; i--){
//			if(rank[i] > 0){
//				if(i+2 == 14)
//					return 1.14;
//				else if(i+2 == 13)
//					return 1.13;
//				else if(i+2 == 12)
//					return 1.12;
//				else if(i+2 == 11)
//					return 1.11;
//				else if(i+2 == 10)
//					return 1.10;
//				else if(i+2 == 9)
//					return 1.09;
//				else if(i+2 == 8)
//					return 1.08;
//				else if(i+2 == 7)
//					return 1.07;
//				else if(i+2 == 6)
//					return 1.06;
//				else if(i+2 == 5)
//					return 1.05;
//				else if(i+2 == 4)
//					return 1.04;
//				else if(i+2 == 3)
//					return 1.03;
//				else if(i+2 == 2)
//					return 1.02;
//				else
//					return 1;
//			}
//		}
//		return 0;
//	}
	
	/*
	 * dont understand sorting by ID
	public static void sort(Card[] cardHand){
		for(int i=0; i<cardHand.length -1 ;i++){
			int index = i;
			for (int j = i+1; j<cardHand.length; j++)
				if(cardHand[j].getID()<cardHand[index].getID())
					index = j;
			Card smallerCard = cardHand[index];
			cardHand[index] = cardHand[i];
			cardHand[i] = smallerCard;
		}
	}*/

}