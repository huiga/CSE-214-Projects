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
import java.util.ArrayList;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;


/**
 * Stackotaire class that simulated a Solitaire game using the concept of Stacks. Uses classes Card and
 * CardStack. Has methods to initiate the game (create a deck of 52 cards with correct values, suits, and
 * card colors. Distributed cards accordingly to rules of Solitaire.
 * Data field tableau: Array of card stacks. Each index holds one card stack, up to 7 total stacks.
 * 					   Main playing field of Solitaire.
 * Data field foundation: Array of card stacks. Each index holds one card stack, up to 4 total stacks.
 * 						  Holds the 4 suits of the deck from A -> K as per rules of Solitaire.
 * Data field stock: Leftover cards after distributing cards to the tableau piles.
 * Data field main: ArrayList to hold the 52 cards before distribution.
 * Data field dummyStock: ArrayList to hold a shallow copy to the stock stack.
 * @author eddie
 *
 */
public class Stackotaire {
	private CardStack[]tableau = new CardStack[7];
	private CardStack[]foundation = new CardStack[4];
	private CardStack stock= new CardStack('s'), waste = new CardStack('w'); 
	private ArrayList<Card>main = new ArrayList<Card>(), dummyStock = new ArrayList<Card>();
	private static final int MAX_CARDS = 52;
	
	/**
	 * Method to initialize the deck of cards. Creates an entire deck of 52 cards as normal:
	 * Cards Ace to King, each with the 4 suits Diamond, Clover, Heart, and Spade. 
	 * Half red, half black. Shuffles the deck then distributes to the tableau piles
	 * as per the rules of Solitaire.
	 * 
	 * @throws IOException
	 * 		IOException based on setValue and setSuit methods in the Card class.
	 */
	public void initializeGame() throws IOException {
		int count=1, count2 = 1, cardCount = 0, tableauIndex = 0, suitIndex=1;
		while(cardCount<=52) {
			if(count==14)
				break;
			if(suitIndex<=4) {
				Card toAddToDeck = new Card();
				toAddToDeck.setValue(count);
				toAddToDeck.setSuit(suitIndex++);
				main.add(toAddToDeck);
				cardCount++; 
			}
			else {
				suitIndex=1;
				count++;
			}
		}
		count=1;
		Collections.shuffle(main);
		//dummyMain = main;
		for(int i=0;i<tableau.length;i++) {
			tableau[i]= new CardStack('t');
			if(i<4)
				foundation[i]= new CardStack('f');
		}
		count=1; count2=0;
		while(count<=28) {
			if(count2 == tableauIndex) {
				Card toShow = main.get(main.size()-1);
				main.remove(toShow);
				toShow.setFaceUp(true);
				tableau[tableauIndex].push(toShow);
				tableauIndex++;
				count++;
				count2=0;
				continue;
			}
			Card toAdd = main.get(main.size()-1);
			tableau[tableauIndex].push(toAdd);
			main.remove(toAdd);
			count++;
			count2++;
		}
		for(Card e : main) {
			dummyStock.add(e);
			stock.push(e);
		}
		
	}
	
	/**
	 * Prints out a neatly formatted table representing the stacks of cards as one would see in Solitaire.
	 * Tableau piles indicated by T(x), foundation piles indicated by F(y), waste pile indicated by W1, 
	 * and stock pile shown on top right of display.
	 * 
	 * @throws IOException
	 * 		IOException based on methods used from the CardStack class.
	 */
	public void displayGameBoard() throws IOException {
		for(int i=0;i<foundation.length;i++) {
			if(!foundation[i].isEmpty())
				foundation[i].printStack('f');
			else
				System.out.print("[F"+(i+1)+"]");
		}
		System.out.print("     ");
		waste.printStack('w');
		System.out.print("    ");
		stock.printStack('s');
		System.out.println("\n");
		for(int i=7;i>0;i--) {
			System.out.print("T"+i+" ");
			tableau[(i-1)].printStack('t');
			System.out.println("");
		}
	}
	
	/**
	 * Main string to run the game of Solitaire. Checks for all available moves as per rules of Solitare
	 * and performs the moves accordingly. 

	 */
	public static void main(String[]args) throws IOException {
		Scanner scan = new Scanner(System.in);
		String gameContinue = "";
		System.out.println("This is a game of Solitaire based on the concept of stacks.\n");
		Stackotaire game = new Stackotaire();
		game.initializeGame();
		game.displayGameBoard();
		int faceUpCards=0;
		while(!gameContinue.equalsIgnoreCase("quit")) {
			for(Card e: game.main) {
				if(e.isFaceUp())
					faceUpCards++;
			}
			for(Card e: game.dummyStock) {
				if(e.isFaceUp())
					faceUpCards++;
			}
			if(faceUpCards==52) {
				System.out.print("\nYou win! Play again? (Y/N): ");
				String cont = scan.next().toLowerCase();
				while(!(cont.equals("y") || cont.equals("n"))) {
					if(cont.equals("y")) {
						System.out.print("\nStarting new game...");
						game = new Stackotaire();
						game.initializeGame();
						game.displayGameBoard();
					}
					else if(cont.equals("n")) {
						System.out.print("\nQuitting...");
						gameContinue="quit";
					}
					else {
						System.out.print("Please enter Y/N.");
					}
				}
			}
			System.out.print("\nEnter a command: ");
			try {
				switch(scan.next().toLowerCase()) {
				case "draw":
					if(!game.stock.isEmpty()) {
						Card toWasteStack = game.stock.pop();
						toWasteStack.setFaceUp(true);
						game.waste.push(toWasteStack);
						System.out.print("\n");
						game.displayGameBoard();
					}
					else {
						System.out.print("\nThe stock stack of cards is empty. Moving waste pile back to stock deck and drawing a stock card to waste pile...\n\n");
						while(!game.waste.isEmpty()) {
							Card backToStock = game.waste.pop();
							backToStock.setFaceUp(false);
							game.stock.push(backToStock);
						}
						if(game.waste.isEmpty())
							throw new EmptyStackException();
						else {
							Card toWasteStack = game.stock.pop();
							toWasteStack.setFaceUp(true);
							game.waste.push(toWasteStack);
							game.displayGameBoard();
						}
					}
					break;
				case "move":
					String toRemoveFrom = scan.next();
					String toPlaceTo = scan.next();
					if(toRemoveFrom.substring(0, 1).equalsIgnoreCase("w")) {
						int wasteNumber= Integer.parseInt(toRemoveFrom.substring(1));
						if(!game.waste.isEmpty() && wasteNumber==1) {
							Card wasteCard = game.waste.peek();
							if(toPlaceTo.substring(0, 1).equalsIgnoreCase("t")) {
								int tableauStack = Integer.parseInt(toPlaceTo.substring(1));
								if(tableauStack>=1 && tableauStack<=7 && !game.tableau[tableauStack-1].isEmpty()) {
									tableauStack--;
									Card topOfTableau = game.tableau[tableauStack].peek();
									if( !(wasteCard.isRed() == topOfTableau.isRed()) && (wasteCard.getValue() == topOfTableau.getValue()-1)) {
										game.tableau[tableauStack].push(wasteCard);
										game.waste.pop();
										game.displayGameBoard();
									}
									else if(wasteCard.isRed() == topOfTableau.isRed())
										System.out.print("\nInvalid move. The card at tableau stack "+(tableauStack+1)+" and the waste card is the same color.\n");
									else if(!(wasteCard.getValue() == topOfTableau.getValue()-1)) 
										System.out.print("\nInvalid move. The waste card does not have a value one less than card at the tableau stack.\n");
								}
								else if(game.tableau[tableauStack-1].isEmpty()) {
									if(wasteCard.getValue()==13) {
										game.tableau[tableauStack-1].push(wasteCard);
										game.waste.pop();
										game.displayGameBoard();
									}
									else {
										System.out.print("\nInvalid move. Cannot move "+wasteCard.toString()+" to tableau pile "+(tableauStack));
									}
								}
								else {
									System.out.print("\nThe indicated tableau stack is out of range (1-7).\n");
								}
								
							}
							else if(toPlaceTo.substring(0,1).equalsIgnoreCase("f")) {
								int foundationStack = Integer.parseInt(toPlaceTo.substring(1));
								if((foundationStack>=1 && foundationStack<=4) && !game.foundation[foundationStack-1].isEmpty()) {
									foundationStack--;
									Card topOfFoundation = game.foundation[foundationStack].peek();
									if(topOfFoundation.getSuit()==wasteCard.getSuit() && topOfFoundation.getValue()+1 == wasteCard.getValue()) {
										game.foundation[foundationStack].push(wasteCard);
										game.waste.pop();
										game.displayGameBoard();
									}
									else if(!(topOfFoundation.getSuit()==wasteCard.getSuit()))
										System.out.print("\nInvalid move. Waste pile card "+wasteCard.toString()+" and the foundation deck do not match suits.\n");
									else if(!(topOfFoundation.getValue()+1 == wasteCard.getValue()))
										System.out.print("\nInvalid move. Waste pile card "+wasteCard.toString()+" and the foundation deck do not have consecutive card values.\n");
									else
										System.out.print("\nInvalid move. Cannot move waste card "+wasteCard.toString()+" to foundation stack.\n");
								}
								else if(game.foundation[foundationStack-1].isEmpty()) {
									if(game.foundation[foundationStack-1].isEmpty() && wasteCard.getValue()==1) {
										game.foundation[foundationStack-1].push(wasteCard);
										game.waste.pop();
										game.displayGameBoard();
									}
									else
										System.out.print("\nInvalid move. Cannot move waste card "+wasteCard.toString()+" to foundation stack.\n");
								}
								else
									System.out.print("\nThe indicated foundation stack is out of range (1-4).\n");
							}
						}
						else if(wasteNumber!=1)
							System.out.print("\nNo such waste stack exists.\n");
						else if(game.waste.isEmpty())
							System.out.print("\nInvalid move. The waste stack is empty.\n");
					}
					else if(toRemoveFrom.substring(0, 1).equalsIgnoreCase("f")) {
						int foundationStack = Integer.parseInt(toRemoveFrom.substring(1));
						if(foundationStack>=1 && foundationStack<=4 && !game.foundation[foundationStack-1].isEmpty()) {
							foundationStack--;
							Card foundationCard = game.foundation[foundationStack].peek();
							if(toPlaceTo.substring(0,1).equalsIgnoreCase("t")) {
								int tableauStack = Integer.parseInt(toPlaceTo.substring(1));
								if(tableauStack>=1 && tableauStack<=7 && !game.tableau[tableauStack-1].isEmpty()) {
									tableauStack--;
									Card tableauCard = game.tableau[tableauStack].peek();
									if(!(foundationCard.isRed() == tableauCard.isRed()) && tableauCard.getValue()-1 == foundationCard.getValue()) {
										game.tableau[tableauStack].push(foundationCard);
										game.foundation[foundationStack].pop();
										if(!game.foundation[foundationStack].isEmpty()) {
											game.foundation[foundationStack].peek().setFaceUp(true);
											game.displayGameBoard();
										}
										else
											game.displayGameBoard();
									}
									else if(foundationCard.isRed() == tableauCard.isRed())
										System.out.print("\nInvalid move. The card at tableau stack "+(tableauStack+1)+" and the foundation card is the same color.\n");
									else if(tableauCard.getValue()-1 == foundationCard.getValue())
										System.out.print("\nInvalid move. Foundation pile card "+foundationCard.toString()+" and thetableau deck do not have consecutive card values.\n");
								}
								else if((!game.tableau[tableauStack-1].isEmpty() && foundationCard.getSuit()==13)) {
									System.out.print("\n(Console speaking: You can move "+foundationCard.toString()+" to the empty tableau stack, but theres no point!)\n");
									game.tableau[tableauStack-1].push(foundationCard);
									game.foundation[foundationStack].pop();
									if(!game.foundation[foundationStack].isEmpty()) {
										game.foundation[foundationStack].peek().setFaceUp(true);
										game.displayGameBoard();
									}
									else
										game.displayGameBoard();
								}
								else if(tableauStack>=1 && tableauStack<=7) {
									System.out.print("\nThe indicated tableau stack is out of range (1-7).\n");
								}
							}
							else 
								System.out.print("\nThe stack to move to does not exist, or the move is invalid.\n");
						}
						else if(game.foundation[foundationStack-1].isEmpty())
							System.out.print("\nThe indicated foundation stack is empty.\n");
						else if(!(foundationStack>=1 && foundationStack<=4))
							System.out.print("\nThe indicated foundation stack is out of range (1-4).\n");
						else
							System.out.print("\nInvalid move.\n");
					}
					else if(toRemoveFrom.substring(0,1).equals("t")) {
						int tableauStack = Integer.parseInt(toRemoveFrom.substring(1));
						if(tableauStack >= 1 && tableauStack <=7 && !game.tableau[tableauStack-1].isEmpty()) {
							tableauStack--;
							Card tableauCard = game.tableau[tableauStack].peek();
							if(toPlaceTo.substring(0,1).equals("f")) {
								int foundationStack = Integer.parseInt(toPlaceTo.substring(1));
								if(foundationStack >=1 && foundationStack <=4 && !game.foundation[foundationStack-1].isEmpty()) {
									foundationStack--;
									Card foundationCard = game.foundation[foundationStack].peek();
									if((tableauCard.isRed() == foundationCard.isRed()) && (tableauCard.getSuit() == foundationCard.getSuit()) && (foundationCard.getValue()+1 == tableauCard.getValue())){
										game.foundation[foundationStack].push(tableauCard);
										game.tableau[tableauStack].pop();
										if(!game.tableau[tableauStack].isEmpty()) {
											game.tableau[tableauStack].peek().setFaceUp(true);
											game.displayGameBoard();
										}
										else
											game.displayGameBoard();
									}
									else
										System.out.print("\nInvalid move.");
								}
								else if(game.foundation[foundationStack-1].isEmpty() && tableauCard.getValue()==1) {
										game.foundation[foundationStack-1].push(tableauCard);
										game.tableau[tableauStack].pop();
										if(!game.tableau[tableauStack].isEmpty()) {
											game.tableau[tableauStack].peek().setFaceUp(true);
											game.displayGameBoard();
										}
										else
											game.displayGameBoard();
								}
								else if(game.foundation[foundationStack-1].isEmpty() && tableauCard.getValue()!=1)
									System.out.print("\nInvalid move. The first card of a foundation stack must me an Ace.\n");
								else if(!(tableauCard.isRed() == game.foundation[foundationStack-1].peek().isRed()))
										System.out.print("\nInvalid move. The card at tableau stack "+(tableauStack+1)+" and the foundation card is the same color.\n");
								else
									System.out.print("\nInvalid move.");
							}
							else if(toPlaceTo.substring(0,1).equals("t")) {
								int tableauStack2 = Integer.parseInt(toPlaceTo.substring(1));
								if(tableauStack2 >=1 && tableauStack2 <=7 && !game.tableau[(tableauStack2)-1].isEmpty()) {
									tableauStack2--;
									Card tableauCard2 = game.tableau[tableauStack2].peek();
									if(!(tableauCard.isRed() == tableauCard2.isRed()) && tableauCard2.getValue()-1 == tableauCard.getValue()) {
										game.tableau[tableauStack2].push(tableauCard);
										game.tableau[tableauStack].pop();
										if(!game.tableau[tableauStack].isEmpty()) {
											game.tableau[tableauStack].peek().setFaceUp(true);
											game.displayGameBoard();
										}
										else
											game.displayGameBoard();
									}
									else if(tableauCard.isRed() == tableauCard2.isRed())
										System.out.print("\nInvalid move. The card at tableau stack "+(tableauStack+1)+" and the tableau stack "+(tableauStack2+1)+" are the same color.\n");
									else if(!(tableauCard2.getValue()-1 == tableauCard.getValue()))
										System.out.print("\nInvalid move. The cards indicated do not have consecutive descending values.\n");
								}
								else if(game.tableau[(tableauStack2)-1].isEmpty()) {
									if(tableauCard.getValue()==13) {
										game.tableau[tableauStack2-1].push(tableauCard);
										game.tableau[tableauStack].pop();
										if(!game.tableau[tableauStack].isEmpty()) {
											game.tableau[tableauStack].peek().setFaceUp(true);
											game.displayGameBoard();
										}
										else
											game.displayGameBoard();
									}
									else
										System.out.print("\nInvalid move.\n");
								}
								else if(!(tableauStack2 >=1 && tableauStack2 <=7))
									System.out.print("\nThe indicated tableau stack to move to is out of range (1-7).\n");
							}
							else
								System.out.print("\nThe stack to move to does not exist, or the move is invalid.\n");
						}
						else if(!(tableauStack >= 1 && tableauStack <=7))
							System.out.print("\nThe indicated tableau stack is out of range (1-7).");
						else if(game.tableau[tableauStack-1].isEmpty())
							System.out.print("\nThe indicated tableau stack is empty. Cannot perform move.\n");
						else
							System.out.print("\nInvalid move.\n");
					}
					else
						System.out.print("\nNo such move exists.\n");
					break;
				case "moven": //broken. IF you move hella cards to a deck and try to move all the stack, it gives error that card is facedown aka its reading only from the original faceup card.
					toRemoveFrom = scan.next();
					toPlaceTo = scan.next();
					int numberOfCards = Integer.parseInt(scan.next());
					if(toRemoveFrom.substring(0,1).equals("t")) {
						int removeTableauStack = Integer.parseInt(toRemoveFrom.substring(1));
						if(removeTableauStack >=1 && removeTableauStack <=7) {
							removeTableauStack--;
							if(!game.tableau[removeTableauStack].isEmpty() && game.tableau[removeTableauStack].size()>=numberOfCards && numberOfCards>0) {
								Stack<Card> toRemoveCards = new Stack<Card>();	
								while(numberOfCards>0) {
									if(game.tableau[removeTableauStack].peek().isFaceUp()) {
										toRemoveCards.push(game.tableau[removeTableauStack].pop());
										numberOfCards--;
									}
									else {
										while(!toRemoveCards.isEmpty())
											game.tableau[removeTableauStack].push(toRemoveCards.pop());
										game.displayGameBoard();
										throw new IOException("\nCannot move a card that is not currently shown (face up).\n");
									}
								}
								boolean wasFaceDown = false;
								if(!game.tableau[removeTableauStack].isEmpty())
									if(!game.tableau[removeTableauStack].peek().isFaceUp()) {
										game.tableau[removeTableauStack].peek().setFaceUp(true);
										wasFaceDown=true;
									}
								if(toPlaceTo.substring(0,1).equals("t")) { 
									int placeTableauStack = Integer.parseInt(toPlaceTo.substring(1));
									if(placeTableauStack >=1 && placeTableauStack <=7) {
										placeTableauStack--;
										if(!game.tableau[placeTableauStack].isEmpty()) {
											Card topOfPlaceTableau = game.tableau[placeTableauStack].peek();
											if((!topOfPlaceTableau.isRed() == toRemoveCards.peek().isRed()) && topOfPlaceTableau.getValue()-1 == toRemoveCards.peek().getValue()) {
												while(!toRemoveCards.isEmpty())
													game.tableau[placeTableauStack].push(toRemoveCards.pop());
												game.displayGameBoard();
											}
											else if(topOfPlaceTableau.isRed() == toRemoveCards.peek().isRed()) {
												System.out.print("\nInvalid move. Card "+toRemoveCards.peek().toString()+" are of the same color.\n");
												if(wasFaceDown)
													game.tableau[removeTableauStack].peek().setFaceUp(false);
												while(!toRemoveCards.isEmpty())
													game.tableau[removeTableauStack].push(toRemoveCards.pop());
												game.displayGameBoard();
											}
											else if(!(topOfPlaceTableau.getValue()-1 == toRemoveCards.peek().getValue())) {
												System.out.print("\nInvalid move. The card values are not in consecutive descending order.\n\n");
												if(wasFaceDown)
													game.tableau[removeTableauStack].peek().setFaceUp(false);
												while(!toRemoveCards.isEmpty())
													game.tableau[removeTableauStack].push(toRemoveCards.pop());
												game.displayGameBoard();
											}
										}
										else if(game.tableau[placeTableauStack].isEmpty())
											if(toRemoveCards.peek().getValue()==13) {
												while(!toRemoveCards.isEmpty())
													game.tableau[placeTableauStack].push(toRemoveCards.pop());
												game.displayGameBoard();
											}
									}
									else {
										System.out.print("\nThe indicated tableau stack to put cards to is out of range (1-7).\n");
										if(wasFaceDown)
											game.tableau[removeTableauStack].peek().setFaceUp(false);
										while(!toRemoveCards.isEmpty())
											game.tableau[removeTableauStack].push(toRemoveCards.pop());
										game.displayGameBoard();
									}
								}
								else {
									System.out.print("\nThe indicated stack to put cards to does not exist.\n");
									while(!toRemoveCards.isEmpty()) {
										game.tableau[removeTableauStack].push(toRemoveCards.pop());
									}
								}
							}
							else if(game.tableau[removeTableauStack].isEmpty())
								System.out.print("\nThere are no cards to remove from tableau stack "+toRemoveFrom+"\n");
							else if(game.tableau[removeTableauStack].size()<numberOfCards)
								System.out.print("\nThere are not enough cards in tableau deck "+toRemoveFrom+" to remove the indicated amount of "+numberOfCards+".\n");
							else if(!(numberOfCards>0))
								System.out.print("\nCannot remove a negative number "+numberOfCards+" of cards.\n");
							else
								System.out.print("\nInvalid move.\n");
						}
						else
							System.out.print("\nThe indicated tableau stack to remove cards from is out of range (1-7).\n");
					}
					else
						System.out.print("\nThe indicated stack to remove cards from does not exist.\n");
					break;
				case "restart":
					System.out.print("\nDo you want to start a new game? (Y/N) :");
					String restart = scan.next().toLowerCase();
					if(restart.equals("y")) {
						System.out.print("\nSorry, you lose. Starting new game.\n");
						game = new Stackotaire();
						game.initializeGame();
						game.displayGameBoard();
					}
					else if(restart.equals("n"))
						continue;
					else
						System.out.print("\nYour choice was neither Y or N. Continuing game.\n");
					break;
				case "quit":
					System.out.print("\nDo you want to quit? (Y/N): ");
					String quit = scan.next().toLowerCase();
					if(quit.equals("y")) {
						System.out.print("\nSorry, you lose.");
						gameContinue = "quit";
					}
					else if(quit.equals("n"))
						System.out.print("\nContinuing game.\n");
					else
						System.out.print("\nYour choice was neither Y or N. Continuing game.\n");
					break;
				default:
					scan.nextLine();
					System.out.print("\nPlease enter a valid move.\n");
				}
			} catch(EmptyStackException ese) {
				System.out.print(ese.toString());
			} catch(NullPointerException npe) {
				npe.printStackTrace();
			} catch(IOException ioe) {
				System.out.print(ioe.toString());
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
//28