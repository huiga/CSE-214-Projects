/**
 * Eddie Xu
 * ID: 112206686
 * Email: eddie.xu@stonybrook.edu
 * Homework 1
 * CSE214
 * Recitation 6: Juan Tarquino
 */

/**
 * CardCollection class that holds an array of BaseballCard objects.
 * Every CardCollection object holds a maximum of 100 BaseballCard objects.
 * 
 * @author Eddie Xu 112206686
 *
 */
public class CardCollection {
	private final int MAX_CARDS=100;
	public BaseballCard[]cardArr;
	private int size=0;
	
	/**
	 * Default constructor that initializes the BaseballCard array cardArr and sets the max length to be 100.
	 */
	public CardCollection() {
		cardArr = new BaseballCard[MAX_CARDS];
	}
	
	/**
	 * Getter method for the size of the BaseballCard array, cardArr.
	 * @return
	 * 		the int representation of the length of the array (number of BaseballCards in the array.)
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Method to add a BaseballCard object at a certain position to the BaseballCard array.
	 * @param newCard
	 * 		the BaseballCard object to add to the array.
	 * @param position
	 * 		the position in the card array to add it to.
	 * @throws IllegalArgumentException
	 * 		if the position indicated is out of range of the deck or negative, it will prompt the user so.
	 * @throws FullCollectionException
	 * 		if the card array is full (100 BaseballCards max), the exception prints a message indicating so.
	 */
	public void addCard(BaseballCard newCard, int position) throws FullCollectionException {
		if(position<1||position>size+1) {
			throw new IllegalArgumentException("The position indicated is out of range of the deck.");
		}
		if(size>=MAX_CARDS) {
			throw new FullCollectionException("The deck of cards is full. Cannot add anymore cards.");
		}
		for(int i=size-1;i>position-2;i--) {
			cardArr[i+1]=cardArr[i];
		}
		cardArr[position-1]=newCard;
		size++;
	}
	
	/**
	 *  Method to add a BaseballCard object to the end of the card array.
	 * @param newCard
	 * 		the BaseballCard object to add to the array.
	 * @throws FullCollectionException
	 * 		if the card array is full (100 BaseballCards max), the exception prints a message indicating so.
	 */
	public void addCard(BaseballCard newCard) throws FullCollectionException{
		addCard(newCard,size);
	}
	
	/**
	 * Method to remove a BaseballCard object at a given position in the card array.
	 * @param position
	 * 		the position in the card array to remove.
	 */
	public void removeCard(int position) {
		cardArr[position-1]=null; 
		size--;
		for(int i=position-1;i<size;i++) {
			cardArr[i]=cardArr[i+1];
		}
	}
	
	/**
	 * Method to get a BaseballCard object at a given position in the baseball card array.
	 * @param position
	 * 		the position in the card array the desired card is at.
	 * @return
	 * 		the reference to the BaseballCard object at the given position. //is it a reference??
	 */
	public BaseballCard getCard(int position) {
		return cardArr[position-1];
	}
	
	/**
	 * Method to swap cards at given positions of two CardCollection objects.
	 * @param other
	 * 		the other card collection to swap cards to.
	 * @param myPosition
	 * 		int representation of the position of the card you would like to swap from your card array.
	 * @param theirPosition
	 * 		int representation of the position of the card you would like to swap from the OTHER card array.
	 * @throws FullCollectionException 
	 * 		if the card array is full (100 BaseballCards max), the exception prints a message indicating so.
	 * 		
	 */
	public void trade(CardCollection other, int myPosition, int theirPosition) throws FullCollectionException { //supposed to swap at the given positions 
		BaseballCard myCard = (BaseballCard)getCard(myPosition).clone();
		BaseballCard theirCard = (BaseballCard)other.getCard(theirPosition).clone();
		removeCard(myPosition);
		addCard(theirCard, myPosition);
		other.removeCard(theirPosition);
		other.addCard(myCard,theirPosition);
	}
	
	/**
	 * Method to check whether a BaseballCard object currently exists in the card collection array or not.
	 * @param card
	 * 		BaseballCard object to check for.
	 * @return
	 * 		boolean representing if the card exists or not. (T = exists, F = does not exist).
	 */
	public boolean exists(BaseballCard card) {
		for(BaseballCard deck:cardArr) {
			if(deck.equals(card)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Method to print all elements of all BaseballCard objects in the card array.
	 */
	public void printAllCard() {
		System.out.println(toString());
	}
	
	/**
	 * Method to obtain and format all elements of the BasballCard objects in the card array and append them to a string to return.
	 * @return
	 * 		formatted string representation of all elements of all BaseballCards 
	 */
	public String toString() {
		String head = "#  Name                    Manufacturer  Year  Price  Size";
		String head2= "-- ----                    ------------  ----  -----  ----";
		String print ="";
		for(int i=0; i<size;i++) {			
			print+= String.format("%-3d%-24s%-14s%-6d$%-6.2f%dx%d\n",i+1,cardArr[i].name,cardArr[i].manufacturer,cardArr[i].year,cardArr[i].price,cardArr[i].getX(),cardArr[i].getY());
		}		
		return head+"\n"+head2+"\n"+print;
	}
}
