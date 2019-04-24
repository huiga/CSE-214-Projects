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
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * CardStack class to hold a Stack of card objects. Extends the Stack class in java for convenience of 
 * push(), pop(), peek(), size(), and other methods and data structures. Data field typeOfStack holds the 
 * type that the stack will be (further elaborated below).
 * @author eddie
 *
 */
public class CardStack extends Stack{
	Stack<Card> stack;
	char typeOfStack;
	
	/**
	 * Constructor to create a new Card stack/stack of Card objects.
	 */
	public CardStack() {
		stack = new Stack<Card>();
	}
	
	/**
	 * Defines what type of stack the card will be by indication of a character. In context of Solitaire:
	 * @param type
	 * 		Character to indicate what type of stack the card stack will be.
	 *		'f' : Foundation card stack
	 *		't' : Tableau card stack
	 *		's' : Stack card stack
	 *		'w' : Waste card stack
	 */
	public CardStack(char type) {
		typeOfStack = type;
		stack = new Stack<Card>();
	}
	
	/**
	 * push method to append a new Card to the card stack.
	 * @param newCard
	 * 		Card object to be added.
	 */
	public void push(Card newCard) {
		stack.push(newCard);
	}
	
	/**
	 * pop method to get the top-most element in the card stack.
	 * @return
	 * 		Card object at the top of stack.
	 * @throws EmptyStackException
	 * 		If the card stack is empty.
	 */
	public Card pop() {
		if(!isEmpty())
			return stack.pop();
		else
			throw new EmptyStackException();
	}
	
	/**
	 * peek method to see the data of the top-most element in the card stack without removing it from 
	 * the stack.
	 * 
	 * @return
	 * 		Reference to the top-most card object in the card stack.
	 * @throws EmptyStackException
	 * 		If the card stack is empty.
	 */
	public Card peek() {
		if(!isEmpty())
			return stack.peek();
		else
			throw new EmptyStackException();
	}
	
	/**
	 * Method to check if the stack is empty.
	 * 
	 * @return
	 * 		true if the stack is empty
	 * 		false if the stack is not empty (that is, there are card objects in the stack).
	 */
	public boolean isEmpty() {
		return stack.isEmpty();
	}
	
	/**
	 * Method to check the size of the current card stack.
	 * 
	 * @return
	 * 		int representation of the amount of elements in the current stack
	 */
	public int size() {
		return stack.size();
	}

	/**
	 * Prints out every element in the indicated card stack by param type.
	 * 
	 * @param type
	 * 		Character to indicate what type of stack the card stack will be.
	 *		'f' : Foundation card stack
	 *		't' : Tableau card stack
	 *		's' : Stack card stack
	 *		'w' : Waste card stack
	 * @throws IOException
	 * 		If the character input for the param are neither of the given options. Prints out an error 
	 * 		message as accordingly. 
	 */
	public void printStack(char type) throws IOException {
		Card toShow;
		switch(Character.toLowerCase(type)){
		case 's':
			if(!stack.isEmpty())
				System.out.print("[XX]"+stack.size());
			else
				System.out.print("[  ]"+stack.size());
			break;
		case 'w':
			if(!stack.isEmpty()) {
				System.out.print("W1 "+stack.peek().toString());
			}			
			else
				System.out.print("W1 [  ]");
			break;
		case 'f':
			if(!stack.isEmpty()) {
				toShow = stack.pop();
				stack.push(toShow);
				System.out.print(toShow.toString());
			}
			break;
		case 't':
			if(!stack.isEmpty())
				for(Card e : stack) {
					if(e.isFaceUp()) { //might cause errors. Think, is the card still in the stack? Why push it back after popping?
						System.out.print(e.toString());
						//System.out.print(e.isRed());
					}
					else
						System.out.print("[XX]");
				}
			else {
				System.out.print(""); //note here!
			}
			break;
		default:
			throw new IOException("There is no stack of type "+type+".");
		}
	}
}
