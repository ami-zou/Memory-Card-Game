# Memory Card Game
A card game for playing memory

## Start the game
1. Download this package and open it in an IDE (e.g. Eclipse, IntelliJ)
2. Run `Game.java`
3. You will have a pop up java awt application looks like this:
![alt text](https://github.com/ami-zou/Memory-Card-Game/blob/master/screenshots/Start.png)

Now enjoy playing the game!

## Rules
By default there are 4 players
Each player take terms in picking two cards
Only scores when the two cards match perfectly, and this player can continue playing, otherwise, will switch to the next player. All scores are recorded in the board.

* Not matched (nothing matching ➡️ not scoring, switch to next player)
![alt text](https://github.com/ami-zou/Memory-Card-Game/blob/master/screenshots/Unmatched.png)

* Matched! (Scoring 1 point, and this player continues to play🙌🏼)
![alt text](https://github.com/ami-zou/Memory-Card-Game/blob/master/screenshots/Matching.png)

* Partially match (only matches number or colour; not scoring ➡️ switch to next player)
![alt text](https://github.com/ami-zou/Memory-Card-Game/blob/master/screenshots/Partially%20matched.png)

The game finishes when all cards are matched💯✨. According to Wikipedia, on average, it takes 44 times to finish a deck of 52 cards (without jokers). Good luck on playing🚀! 

## Development
I coded this little game in a weekend☕️. A good practice for using JPanel and java application designs. Right now this version has a set value of 4 players with a deck of 2-coloured cards. Next step would be taking inputs from users to decide the number of players, ways of colouring (either 2-coloured or 4-coloured), and the number of decks. 
