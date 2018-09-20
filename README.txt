COMP3004 Assignment 1 - Noah Beeney
-----------------------------------
A simple text-based blackjack game. 
Go to comp3004a1\src\main\java\core and run main.java to run game.
Put any input files into src\ directory.

Card.java:
- card object with suit and rank attributes

Deck.java:
- deck object that maintains an array of 52 Cards
- deck is complete and unshuffled upon initialization
- shuffle() shuffles deck
- cannot shuffle deck with null spaces as a result of removing cards (incomplete deck)
- nextCard() returns the next unique card in the deck, ignoring "null" cards
- nextCard() returns null when the end of the deck has been reached

Player.java:
- player object that mainains an ArrayList hand of Cards and an integer score
- hand is empty and score is 0 upon intialization
- can add a Card to the player's hand by providing an existing Card object or specifying the suit and rank of a new Card
- reset() emptys player's hand and sets score to 0
- updateScore() recalculates the player's score according to the player's current hand, taking into account aces can hold a value of 1 or 11
- updateScore() will set the score to the value of the current hand while trying to maximize (or minimize) it to 21 according to how many aces the player holds
- getScore() updates the score and returns it

BlackJack.java:
- blackjack game obect that maintains a deck, player, and dealer
- init() creates and shuffles deck (if console input) and deals first two cards to player and dealer
- provides controls such as hit and stay
- isOver() returns true if the game has a winner in its current state
- show() prints a nice textual representation of the current game to the console

main.java
- facilitates gameplay
- greets user
- implements blackjack rules and turns
- provides user interface and allows for input/output
