package memoryGame;

import java.awt.Color;

/*Explanation for variables:
* @rank: from 0 to 13
*       0: default blank card, nothing shown
*       when rendering in Board, 1 will be shown as 'A'
*       11 as 'J', 12 as 'Q', 13 as 'K', and the rest will be shown as the original number
* @rankStr: the String version of @rank
*
* @color: from 0 to 4
*       0: default blank card (Yellow background)
*       1 = Hearts, rendered as red
*       2 = Diamonds, rendered as red in 2-colour deck, and as blue in 4-colour deck
*       3 = Spades, rendered as black
*       4 = Clubs, rendered as black in 2-colour deck, and as green in 4-colour deck
* @color2 and @color4 are the actual Color of this card depending on the colouring rule (2-colours or 4-colors)
*
* TODO: maybe consider adding jokers in the future
*
* @shown: if true, meaning that this card is currently facing up
*         otherwise, this card is facing down (showing the blank card - YELLOW background with no texts)
*
* @finished: a boolean value to track if this card has been matched with another card already.
*            If so, will be shown as Grey, and disable all future actions
*
* @index: a unique value for each card, depending on its order in the shuffled deck
*
* Author: @amigomushroom (my GitHub account name)
 */

public class Card {
	private int rank;
	private int rankStr;
	private int index;
	private int color;
	private Color color2;
	private Color color4;
	private boolean shown;
	private boolean finished;
	
	Card(){ //default blank card
		this(0, 0);
	}
	
	Card(int rank, int color){
		this.rank = rank;
		this.color = color;
		shown = false;
		finished = false;
	}

	public int getRank() {
		return rank;
	}
	
	public String getRankStr() {
		if(rank==0) {
			return "A";	
		}else if(rank==11) {
			return "J";
		}else if(rank==12) {
			return "Q";
		}else if(rank==13) {
			return "K";
		}else {
			return String.valueOf(rank);
		}
	}

	public int getColor() {
		return color;
	}

	public Color getColor2() {
		if(color==1 || color==2) {
			return Color.RED;
		}else if(color== 3 || color==4) {
			return Color.BLACK;
		}else {
			return Color.YELLOW;
		}
	}
	
	public Color getColor4() {
		if(color==1 || color==3 || color==0) {
			return getColor2();
		}else if(color==2) {
			return Color.blue;
		}else {
			return Color.green;
		}
	}
	public boolean isFinished() {
		return finished;
	}
	
	public void finish() {
		finished = true;
	}
	
	public boolean isShown(){
	    return shown;
    }
	
    public void showCard(){
	    shown = true;
    }

    public void hideCard(){
	    shown = false;
    }
    
    public int getIndex() {
    		return index;
    }
    
    public void setIndex(int index) {
    		this.index = index;
    }
}
