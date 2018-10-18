package memoryGame;

public class Player {
	private int matching_times;
	private int nonmatching_times;
	private int id;
	
	Card lastCard;
	Card currCard;
	
	Player(){ //default: Player 1
		this(1);
	}
	
	Player(int id){
		this.id = id;
		matching_times = 0;
		nonmatching_times = 0;
		lastCard = new Card();
	}
	
	public void updateMathcing(){
		this.matching_times++;
	}

	public void updateNonmatching(){
	    this.nonmatching_times++;
    }

    public void emptyCards(){
	    lastCard = new Card();
	    currCard = new Card();
    }

    public boolean isMatching_2_Colors(){
	    if(!isMatchingRank()) return false;

	    int lastC = lastCard.getColor();
	    int currC = currCard.getColor();

	    if(lastC == 1 || lastC == 2){   //TODO: TEST CASES
	        if(currC == 1 || currC == 2){
	            return true;
            }else{
	            return false;
            }
        }else if(lastC == 3 || lastC == 4){
	        if(currC == 3 || currC == 4){
	            return true;
            }else{
	            return false;
            }
        }else{ //TODO: Edge case: color 0 (blank card with blank card)
	        return false;
        }
    }

    public boolean isMatching_4_Colors(){
	    if(!isMatchingRank()) return false;

        int lastC = lastCard.getColor();
        int currC = currCard.getColor();

	    if(lastC == 0 || currC == 0) return false;

	    return lastC == currC;
    }

    public boolean isMatchingRank(){
	    return currCard.getRank() == lastCard.getRank();
    }
	
	public int getID(){
	    return id;
	}

	public int getMatching_times() {
	    return matching_times;
	}

	public int getNonmatching_times() {
	    return nonmatching_times;
	}

	public Card getLastCard(){
	    return lastCard;
    }

    public void setLastCard(Card lastCard){
	    this.lastCard = lastCard;
    }

    public Card getCurrCard(){
        return currCard;
    }

    public void setCurrCard(Card currCard){
        this.currCard = currCard;
    }
}
