/**
 * Eddie Xu
 * ID: 112206686
 * Email: eddie.xu@stonybrook.edu
 * Homework 2
 * CSE214
 * Recitation 6: Juan Tarquino
 */

package hw3_214;
import java.io.IOException;

/**
 * Card class. Contains values for a deck of cards from Ace -> King as well as respective suit values.
 * Used to create card objects of a certain 'value' and 'suit'. Dtat fields cardValue and cardSuit both
 * declared as int to store the index of the value and suit respectively in the VALUES and SUITS arrays.
 * @author eddie
 *
 */
public class Card {
	private static final String[]VALUES= {" ","A","2","3","4","5","6","7","8","9","10","J","Q","K"};
	private static final char SUITS[]= {' ', '\u2666', '\u2663','\u2665', '\u2660'};   // {' ', '♦', '♣','♥', '♠'};
	private boolean isFaceUp;
	private int cardValue;
	private int cardSuit;
	
	/**
	 * Constructor for a newly created card. Sets the card to be 'hidden' (aka, unknown/face down) and 
	 * sets the initial value and suit to be nothing.
	 */
	public Card() {
		isFaceUp=false;
		cardValue=0;
		cardSuit=0;
	}
	
	/**
	 * Sets the value of the card object.
	 * 
	 * @param cardValue
	 * 		Value of the card (1 = Ace, 2 = 2.... 13 = King). Stored as an int to get the actual value
	 * 		from the data field VALUES array.
	 * @throws IOException
	 * 		If the indicated card value is less than 1 (less than an Ace) or greater than 13 (greater than a King)
	 * 		throws an IOException with respective message. 
	 */
	public void setValue(int cardValue) throws IOException {
		if(cardValue >= 1 && cardValue <= 13)
			this.cardValue = cardValue;
		else
			throw new IOException("Cannot set card value any more than a 'King' (A - K = 1 - 13).");
	}
	
	/**
	 * Gets the value index of the card object.
	 * 
	 * @return
	 * 		Class data field cardValue, the 'value' of the card.
	 */
	public int getValue() {
		return cardValue;
	}
	
	/**
	 * Sets the suit of the card object.
	 * 
	 * @param cardSuit
	 * 		Suit of the card (1 = Diamond...4 = Spade). Stored as an int to get the actual value
	 * 		from the data field SUITS array.
	 * @throws IOException
	 * If the indicated card value is less than 1 (less than a Diamond) or greater than 4 (greater than a Spade)
	 * 		throws an IOException with respective message. 
	 */		
	public void setSuit(int cardSuit) throws IOException {
		if(cardSuit >= 1 && cardSuit <= 4)
			this.cardSuit = cardSuit;
		else
			throw new IOException("Cannot set suit for card greater than spades. (Diamond - Spade = 1-4)");
	}
	
	/**
	 * Gets the suit index of the card object.
	 * 
	 * @return
	 * 		Class data field cardSuit, the 'suit' of the card.
	 */
	public int getSuit() {
		return cardSuit;
	}
	
	/**
	 * Checks if the card object is currently face up (the value and suit is visible to the user).
	 * 
	 * @return
	 * 		true if the object is face up
	 * 		false if the object is face down.
	 */
	public boolean isFaceUp() {
		return isFaceUp;
	}
	
	/**
	 * Flips the card over based on boolean param.
	 * 
	 * @param flip
	 * 		boolean value to flip. If flip=true, flips the card to be face up. If flip=false, flips the card
	 * 		over and hides value and suit.
	 */
	public void setFaceUp(boolean flip) {
		isFaceUp = flip;
	}
	
	/**
	 * Tells whether the card is red or black.
	 * 
	 * @return
	 * 		true if the card is red (Diamonds and Heart suits are red).
	 * 		false if the card is black (Clover and Spade suits are black).
	 */
	public boolean isRed() {
		if(cardSuit%2==0)
			return false;
		else
			return true;
	}
	
	/**
	 * Returns a neatly formatted string of the card and its value, suit.
	 */
	public String toString() {
		return "["+VALUES[cardValue]+SUITS[cardSuit]+"]";
	}
}
