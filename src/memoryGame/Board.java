package memoryGame;

//TODO Change IntelliJ's setting to make sure it only imports the specific class that's being used
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Board extends JPanel implements ActionListener {
    private int num_decks = 1;
    int num_cards = 52;
    int num_remaining_cards = 52; //TODO: If allowing users to chose how many decks: initiate these three fields in constructor
    int num_players;
    ArrayList<Card> cards;
    CardBtn[] buttons;
    boolean isDelayed;
    boolean isFinished;
    JLabel[][] scores;
    
    private static Player[] players;
    Player currentPlayer;
    JLabel currPlayerLabel;
    
    private JPanel buttonPanel = new JPanel();
    GridLayout oneDeckLayout = new GridLayout(6, 9);
    GridLayout twoDecksLayout = new GridLayout(8, 13);
    private JPanel playerPanel = new JPanel();

    public Board(){ //default: single player playing a single deck of 52 cards
        this(new Player[1]); 
    }
    
    public Board(Player[] players) { //TODO: Need to add edge + test cases to check the inputs in Game.java
        this.players = players;
        this.num_players = players.length;
        
        if(num_players==1) {
        		players[0] = new Player(1);
        }
        this.currentPlayer = players[0];
        scores = new JLabel[num_players][2];
        
        currPlayerLabel = new JLabel("Current Player is Player " + currentPlayer.getID());
        add(currPlayerLabel);
        
        GridLayout playersLayout = new GridLayout(num_players+1,3);
        playerPanel.setLayout(playersLayout);
        addPlayers(playerPanel);
        add(playerPanel);
        
        isDelayed = false;
        isFinished = false;

        oneDeckLayout.setHgap(2);
        oneDeckLayout.setVgap(2);

        buttonPanel.setLayout(oneDeckLayout);
        addDecks(buttonPanel);

        add(buttonPanel);

        //printCards();
    }

    private void addPlayers(JPanel playerPanel) {
        playerPanel.add(new JLabel("Player"));
        playerPanel.add(new JLabel("Matching times"));
        playerPanel.add(new JLabel("Non-matching times"));
    		
        for(int i = 0; i < players.length; i ++) {
            Player p = players[i];
            JLabel playerName = new JLabel("Player " + p.getID());
            JLabel playerMatches = new JLabel("0");
            JLabel playerNonMatches = new JLabel("0");
            
            scores[i][0] = playerMatches;
            scores[i][1] = playerNonMatches;
            
            playerPanel.add(playerName);
            playerPanel.add(playerMatches);
            playerPanel.add(playerNonMatches);
        }
    }

    private void addDecks(JPanel cardPanel){
        if(num_decks == 1){
            addOneDeck(cardPanel);
        }else{
            //TODO: a general method to add many decks
            //TODO: Update Deck.java to include a method that takes @num_of_decks and returns 52*@num_of_decks shuffled cards
        }
    }
    
    private void addOneDeck(JPanel cardPanel) {
        Deck deck = new Deck();
        cards = deck.getCards();
        buttons = new CardBtn[cards.size()];
         
        for(int btnIndex = 0; btnIndex < cards.size(); btnIndex++) {
            Card card = cards.get(btnIndex);
            card.setIndex(btnIndex);

            CardBtn cardBtn = new CardBtn();
            //CardBtn cardBtn = new CardBtn(card, 2); //Showing the cards when starts, for testing purpose
            cardBtn.addActionListener(new MyActionListener(btnIndex));
            buttons[btnIndex] = cardBtn;
    		
            cardPanel.add(cardBtn);
        }
    }

    private class CardBtn extends JButton{
        CardBtn(){ //default blank button (Yellow background, no text)
            setText("");
            setBackground(Color.YELLOW);
            setOpaque(true);
            setBorderPainted(false);
            setForeground(Color.white);
            setPreferredSize(new Dimension(60,80));
        }
    		
        CardBtn(Card card, int colors){
            this();
            setText(card.getRankStr());
    			
            if(colors==2) {
                setBackground(card.getColor2());
            }else if(colors==4) {
                setBackground(card.getColor4());
            }else { //TODO: add edge + test cases for other coloring methods (number of input colors other than 2 or 4)
                setBackground(Color.WHITE);
            }
        }
    }

    private class MyActionListener implements ActionListener {
        private int buttonIndex; 
        private Card card;
         
        public MyActionListener(int buttonIndex) {
            this.buttonIndex = buttonIndex;
            this.card = cards.get(buttonIndex);
        }
         
        public void actionPerformed(ActionEvent event) {
            System.out.println("Card " + getCard().getRank() + ", Colour: " + getCard().getColor() + ", index: " + getCard().getIndex() + " has been pressed by Player " + currentPlayer.getID());

            //TODO: Add Test case: when game is already finished but user attempts to keep clicking the cards
            if(num_remaining_cards == 0 || isFinished) {
                System.out.println("I'm sorry, but the game has already finished.");
                return;
            } //TODO: edge cases: add Test to make sure both variables are updated at the same time, so we can change condition OR (||) to AND (&&)

            //TODO: Add Test cases: when it is during an delay, UI won't respond anything
            if(isDelayed){
                return;
            }

            JButton btn = (JButton) event.getSource();

            //TODO: Add Edge + Test cases: when the clicked card is already finished (shown as GREY), can't touch
            if(card.isFinished()) {
                return;
            }else if (!card.isShown()) { //currently facing down, now turn around //TODO: add test: clicking a card facing down (YELLOW background with no text), should show the actual card
                card.showCard();
          		btn.setBackground(card.getColor2());
          		btn.setText(card.getRankStr());
           		btn.setForeground(Color.WHITE);

           		updatePlayer(card);
            }else { //currently facing up ==> invalid touch //TODO: add test: clicking the same card should not change anything
                System.out.println("Please choose a different card to see if you find a pair!");
                return;
            }
        }
        
        public Card getCard() {
        		return card;
        }
    }
    
    private void updatePlayer(Card pickedCard) {
        //TODO: add test: clicking Grey card won't update anything
        if(pickedCard.isFinished()) { //keep going. don't do anything
            System.out.print("Invalid pick. Please choose another unchecked card");
            return;
        }
    		
        Card lastCard = currentPlayer.getLastCard();
        CardBtn currCardBtn = buttons[pickedCard.getIndex()];

        //TODO: add edge case + test: if lastCard was empty (rank==0), then should be updated with currently picked one
        if(lastCard.getRank()==0) { //empty lastCard: first time picker
            currentPlayer.setLastCard(pickedCard);
            //Keep on picking second card --- not doing anything
        }else { //already picked one, now compare
            currentPlayer.setCurrCard(pickedCard);
            CardBtn lastCardBtn = buttons[lastCard.getIndex()];
        		
            if(currentPlayer.isMatching_2_Colors()) {
                currentPlayer.updateMathcing();
                System.out.println("Congrats on matching! You have " + currentPlayer.getMatching_times() + " pairs of matching pairs now.s Keep going!");
                int index = currentPlayer.getID()-1;
                JLabel playerMatches = scores[index][0];
                playerMatches.setText(String.valueOf(currentPlayer.getMatching_times()));
                
                //Procedures:
                //1. Mark that two cards as finished
                //2. Still this player's turn! But need to check if the game has finished yet
                //3. If game continues: This player starts a new 2-chance picking (empty the current pair)

                lastCardBtn.setBackground(Color.WHITE);
                lastCardBtn.setOpaque(true);
                //lastCardBtn.setEnabled(false); //TODO Test Edge Cases: future attempts on clicking Grey card shouldn't be allowed anymore
                lastCardBtn.setText(lastCard.getRankStr());
                System.out.println("Finishes: "+lastCard.getRankStr());
                lastCardBtn.setForeground(Color.WHITE);
                lastCard.finish();
                lastCardBtn.setEnabled(false);

                currCardBtn.setBackground(Color.WHITE);
                currCardBtn.setEnabled(false);
                currCardBtn.setOpaque(true);
                currCardBtn.setText(pickedCard.getRankStr());
                System.out.println("Finishes: "+pickedCard.getRankStr());
                currCardBtn.setForeground(Color.YELLOW);
                pickedCard.finish();

                num_remaining_cards -= 2; //update the remaining available cards.
                if(num_remaining_cards == 0) { //TODO: add Test on attempting to click cards after Game Finishes
                    finishGame();
                }

                currentPlayer.emptyCards();

    			}else { //Not matching:
                        //1. Hide these two cards,
                        //2. update board,
                        //3. switch to next player; or keep the same if there is only one player
    				currentPlayer.updateNonmatching();
    				System.out.println("I'm sorry!! Start again! Or switch to the next player if in multi-player mode");
    				int index = currentPlayer.getID()-1;
                JLabel playerNonMatches = scores[index][1];
                playerNonMatches.setText(String.valueOf(currentPlayer.getNonmatching_times()));
                    
                lastCard.hideCard();
                pickedCard.hideCard();
                currentPlayer.emptyCards();

                switchToNextPlayer();

                //TODO: Delay for a bit before hiding the two cards (leave 2 seconds for players to remember the card)
                //TODO: Maybe customize the length of the delay in the future.
                //Method 1: Using Timer (Swing library) -- needs to add another listener
                //Source: https://stackoverflow.com/questions/12767367/how-could-i-add-a-simple-delay-in-a-java-swing-application

				int delay = 2000;
    				Timer timer = new Timer( delay, new ActionListener(){
    				  @Override
    				  public void actionPerformed( ActionEvent e ){
    				    isDelayed = false;
        				lastCardBtn.setBackground(Color.YELLOW);
        				lastCardBtn.setText("");
        				
        				currCardBtn.setBackground(Color.YELLOW);
        				currCardBtn.setText("");
    				  }
    				} );

    				timer.setRepeats( false );
    				isDelayed = true;
    				timer.start();

                 //Method 2: Using TimeUnit -- but need to handle exception
                 /*
                 TimeUnit.SECONDS.sleep(2);

                 lastCardBtn.setBackground(Color.YELLOW);
                 lastCardBtn.setText("");

                 currCardBtn.setBackground(Color.YELLOW);
                 currCardBtn.setText("");
                 //However didn't work correctly, because it also sleeps the main thread and delays other clicking
                 */
    			}
    		}
    }
    
    private void finishGame() {
    		isFinished = true;
    		
    		if(players.length==1) {
    			//TODO Make a prompt
    			System.out.println("Congrats! Player " + 1 + "is the winner!!");
    			return;
    		}
    	
    		//For multi-players:
        //1. Determine the player with the largest matching times
        //2. If step 1 has ties, then compare the non-matching times: the smaller, the better
        //3. Congrats the winner, and froze the game
        //4. (in the future) prompt to restart the game
    		ArrayList<Player> winners = new ArrayList<Player>();
    		
    		int max_Matches = 0;
    		int min_NonMatches = 0; //need to initialize it with 1st winner
    		
    		for(Player p : players) {
    			int matches = p.getMatching_times();
    			
    			if(matches > max_Matches) { //absolute new winner
    				winners.clear();
    				winners.add(p);
    				
    				max_Matches = matches;
    				min_NonMatches = p.getNonmatching_times();
    				
    			}else if(matches == max_Matches) { //need to check non-matching scores
    				int non_Matches = p.getNonmatching_times();
    				if(winners.isEmpty()) { //in the case of matches == 0
    					winners.add(p);
    					min_NonMatches = non_Matches;
    				}else {
    					if(non_Matches < min_NonMatches) { //absolute new winner
    						winners.clear();
    						winners.add(p);
    						
    						min_NonMatches = non_Matches;
    					}else if(non_Matches == min_NonMatches) { //tie
    						winners.add(p);
    					}
    				}
    			}
    		}
    		
    		System.out.println("Total numbers of winners: " + winners.size());
    		for(Player w: winners) {
    			System.out.println("Congrats Player " + w.getID() + " on winning!!!");
    		}
    		
    		//TODO: make an alert or prompt or something to congrats the winner(s)!!
    }
    
    public void printCards() {
        System.out.println("Number of cards: " + cards.size());
        for(Card c : cards) {
            System.out.println("Rank: " + c.getRank() + ", Colour: " + c.getColor());
        }
    }

    private void switchToNextPlayer(){
        int currID = currentPlayer.getID();
        int total_num_players = players.length;

        if(currID < total_num_players){ //move to the next one on the list
            currentPlayer = players[currID]; //index starts with 0, but ID starts with 1. TODO Add Test for this
        }else{ //rotate back to beginning (Player1)
            currentPlayer = players[0];
        }
        
        currPlayerLabel.setText("Current Player is Player " + currentPlayer.getID());
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
}
