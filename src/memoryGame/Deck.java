package memoryGame;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
	private int num_decks;
	ArrayList<Card> deck;
	
	Deck(){
		this(1);
	}
	
	Deck(int num_decks){
		this.num_decks = num_decks;
		
		deck = new ArrayList<Card>();
		createDecks();
	}
	
	private void createDecks() {
		int times = 1;
		
		while(times <= num_decks) {
			createOneDeck();
			times++;
		}
		
		shuffle();
	}
	
	private void createOneDeck() {
		for(int num = 1; num <= 13; num++) {
			for(int color = 1; color <= 4; color++) {
				Card card = new Card(num, color);
				deck.add(card);
			}
		}

	//TODO: Next step: maybe consider adding Jokers

	}
	
	private void shuffle() {
	    //https://stackoverflow.com/questions/39557701/shuffle-a-deck-of-cards-in-java
		
		//Method 1: using library Collections to shuffle the deck
		Collections.shuffle(deck);
		
		//Method 2: using random to generate a random index to switch the current card with
		//Will need to change the return type to be an ArrayList
        /*
		ArrayList<Integer> shuffledDeck = new ArrayList<Integer>();

        while (deck.size() > 0) {
            int index = (int) (Math.random() * deck.size());
            shuffledDeck.add(deck.remove(index));
        }

		return shuffledDeck;
		 */
	}
	
	public void printDecks() {
		System.out.println("Number of cards: " + deck.size());
		for(Card c : deck) {
			System.out.println("Rank: " + c.getRank() + ", Colour: " + c.getColor());
		}
	}


    public ArrayList<Card> getCards(){
	    return deck;
	}
}
