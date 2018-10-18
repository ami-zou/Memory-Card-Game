package memoryGame;

import javax.swing.*;
import java.util.ArrayList;
import java.awt.EventQueue;
import javax.swing.JFrame;

/* This is the Main class. Please run it to start the game
 * Currently it is only single player by default, but I've put related code to enable multi-players in the future
 * Users can choose single or double decks (or maybe input numbers of decks if they want)
 */

public class Game extends JFrame {
	private static Player[] players;
    private static int num_players;
    private static int num_decks;

	public Game(){
	    createGui();
    }

    private static void createGui() {
        JFrame frame = new JFrame("Memory Card Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new Board(players));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

	public static void main(String[] args) {
		System.out.println("Start Playing Game");

		//TODO: Prompt to ask the number of players (using TextField and JDialogue) and decks
        //TODO: need to write edge cases + tests to validate the inputs

        //Default: single player with single deck
		//createPlayers(1);
		int input_players = 4;
		createPlayers(input_players);
		
		//createDecks();
		System.out.println("Number of Players: " + players.length + " , with first player ID: " + players[0].getID());
		
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createGui();
            }
        });
   
	}
	
	private static void createPlayers(int num) {
		num_players = num;

		players = new Player[num_players]; 
		for(int i = 0 ; i < num_players; i++) {
			players[i] = new Player(i+1);
		}
	}

	private static void createDecks(int num){

    }

}
