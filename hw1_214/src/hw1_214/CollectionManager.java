package hw1_214;

/**
 * Eddie Xu
 * ID: 112206686
 * Email: eddie.xu@stonybrook.edu
 * Homework 1
 * CSE214
 * Recitation 6: Juan Tarquino
 */

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Main class for running the program.
 * Creates a user-driven interface to interact with BaseballCard objects and the CardCollections.
 * 
 * @author Eddie Xu 112206686
 *
 */
public class CollectionManager {
	public static void main(String[]args) {
		Scanner scan = new Scanner(System.in);
		CardCollection a = new CardCollection();
		CardCollection b = new CardCollection();
		System.out.println("Main menu:\n\nA) Add Card\nC) Copy\nE) Update price\nG) Get Card\nL) Locate Card\nN) Update name\nP) Print All Cards\nR) Remove Card\nS) Size\nT) Trade\nV) Value of collections\nQ) Quit");
		String run = "";
		while(!run.equalsIgnoreCase("Q")) {
			System.out.print("\nSelect an operation: ");
			try{
				switch(scan.next().toLowerCase()) {
				case "a":
					System.out.print("\nEnter the collection: ");
					String collec = scan.next().toLowerCase();
					while(!(collec.equals("a")||collec.equals("b"))) {
						System.out.print("\nPlease enter either collection A or B.\n");
						System.out.print("\nEnter the collection: ");
						collec = scan.next().toLowerCase();
					}
					System.out.print("Enter the name: ");
					scan.nextLine();
					String name = scan.nextLine();
					System.out.print("Enter the manufacturer: ");
					String manuf  = scan.nextLine();
					System.out.print("Enter the year: ");
					int year = scan.nextInt();
					while(year<0) {
						System.out.println("Please enter in a valid year.");
						System.out.print("Enter the year: ");
						year = scan.nextInt();
					}
					System.out.print("Enter the size: ");
					scan.nextLine();
					String sizeIn = scan.nextLine();
					int xSize = Integer.parseInt(sizeIn.substring(0, sizeIn.indexOf(" ")));
					int ySize = Integer.parseInt(sizeIn.substring(sizeIn.indexOf(" ")+1, sizeIn.length()));
					System.out.print("Enter the price: ");
					double price = scan.nextDouble();
					while(price<0) {
						System.out.println("Please enter a positive price.");
						System.out.print("Enter the price: ");
						price = scan.nextDouble();
					}
					System.out.print("Enter the position: ");
					int pos = scan.nextInt();
					if(collec.equals("a")&&a.size()==0&&pos!=1) {
						while(pos!=1) {
							System.out.println("\nYou cannot place it at a position greater/less than the current deck size.");
							System.out.print("Enter the position: ");
							pos=scan.nextInt();
						}
					}
					if(collec.equals("b")&&b.size()==0&&pos!=1) {
						while(pos!=1) {
							System.out.println("\nYou cannot place it at a position greater/less than the current deck size.");
							System.out.print("Enter the position: ");
							pos=scan.nextInt();
						}
					}
					if(collec.equalsIgnoreCase("a")) {
						a.addCard(new BaseballCard(name, manuf, year, price, xSize, ySize),pos);
						System.out.println("\nAdded "+name+", "+manuf+" "+year+", "+xSize+"x"+ySize+", $"+price+" at position "+pos+" of collection "+collec.toUpperCase()+"\n");
					} else { 
						b.addCard(new BaseballCard(name, manuf, year, price, xSize, ySize),pos);
						System.out.println("\nAdded "+name+", "+manuf+" "+year+", "+xSize+"x"+ySize+", $"+price+" at position "+pos+" of collection "+collec.toUpperCase()+"\n");
					}
					break;
				case "c":
					System.out.print("\nEnter the collection to copy from: ");
					collec = scan.next().toLowerCase();
					while(!(collec.equals("a")||collec.equals("b"))) {
						System.out.print("\nPlease enter either collection A or B.\n");
						System.out.print("\nEnter the collection: ");
						collec = scan.next().toLowerCase();
					}
					System.out.print("Enter the position of the card to copy: ");
					pos = scan.nextInt();
					if(collec.equals("a"))
						while(pos>a.size()||pos<=0) {
							System.out.println("\nYou cannot copy at a position that is not within the current deck size.");
							System.out.print("Enter the position: ");
							pos=scan.nextInt();
						}
					else
						while(pos>b.size()||pos<=0) {
							System.out.println("\nYou cannot copy at a position that is not within the current deck size.");
							System.out.print("Enter the position: ");
							pos=scan.nextInt();
						}
					System.out.print("Enter the collection to copy to: ");
					String collec2= scan.next().toLowerCase();
					while(!(collec2.equals("a")||collec2.equals("b"))) {
						System.out.print("\nPlease enter either collection A or B.\n");
						System.out.print("\nEnter the collection: ");
						collec2 = scan.next().toLowerCase();
					}
					BaseballCard toCopy;
					if(collec.equals("a")) {
						toCopy = (BaseballCard) a.getCard(pos).clone();
						if(collec2.equals(collec)) {
							a.addCard(toCopy);
							System.out.println("\nCopied "+toCopy.getName()+", "+toCopy.getManufacturer()+" "+toCopy.getYear()+", "+toCopy.getX()+"x"+toCopy.getY()+", $"+toCopy.getPrice()+" into position "+a.size()+" of collection "+collec2.toUpperCase());
						} else if(collec2.equals("b")) {
							b.addCard(toCopy);
							System.out.println("\nCopied "+toCopy.getName()+", "+toCopy.getManufacturer()+" "+toCopy.getYear()+", "+toCopy.getX()+"x"+toCopy.getY()+", $"+toCopy.getPrice()+" into position "+b.size()+" of collection "+collec2.toUpperCase());
						} else {
							throw new IOException("Error in copying card.");
						}
					}
					else if(collec.equals("b")) {
						toCopy = b.getCard(pos);
						if(collec2.equals(collec)) {
							b.addCard(toCopy);
							System.out.println("\nCopied "+toCopy.getName()+", "+toCopy.getManufacturer()+" "+toCopy.getYear()+", "+toCopy.getX()+"x"+toCopy.getY()+", $"+toCopy.getPrice()+" into position "+b.size()+" of collection "+collec2.toUpperCase());
						} else if(collec2.equals("a")) { 
							a.addCard(toCopy);
							System.out.println("\nCopied "+toCopy.getName()+", "+toCopy.getManufacturer()+" "+toCopy.getYear()+", "+toCopy.getX()+"x"+toCopy.getY()+", $"+toCopy.getPrice()+" into position "+a.size()+" of collection "+collec2.toUpperCase());
						} else {
							throw new IOException("Error in copying card.");
						}
					}
					break;
				case "e":
					System.out.print("\nEnter the collection: ");
					collec = scan.next().toLowerCase();
					while(!(collec.equals("a")||collec.equals("b"))) {
						System.out.print("\nPlease enter either collection A or B.\n");
						System.out.print("\nEnter the collection: ");
						collec = scan.next().toLowerCase();
					}
					System.out.print("Enter the position: ");
					pos = scan.nextInt();
					if(collec.equals("a")&&pos>a.size()||pos<=0) {
						while(pos!=1) {
							System.out.println("\nYou cannot update the price of a card at a position that is not within the current deck size.");
							System.out.print("Enter the position: ");
							pos=scan.nextInt();
						}
					}
					if(collec.equals("b")&&pos>b.size()||pos<=0) {
						while(pos!=1) {
							System.out.println("\nYou cannot update the price of a card at a position that is not within the current deck size.");
							System.out.print("Enter the position: ");
							pos=scan.nextInt();
						}
					}
					BaseballCard cardToUpdate;
					if(collec.equals("a")) 
						cardToUpdate = a.getCard(pos);
					else
						cardToUpdate = b.getCard(pos);
					System.out.print("Enter the new price: ");
					price = scan.nextDouble();
					while(price<0) {
						System.out.println("Please enter a positive price.");
						System.out.print("Enter the price: ");
						price = scan.nextDouble();
					}
					cardToUpdate.setPrice(price);
					System.out.println("\nUpdated the price of card "+cardToUpdate.getName()+" of collection "+collec.toUpperCase()+" to "+price);
					break;
				case "g":
					System.out.print("\nEnter the collection: ");
					collec = scan.next().toLowerCase();
					while(!(collec.equals("a")||collec.equals("b"))) {
						System.out.print("\nPlease enter either collection A or B.\n");
						System.out.print("\nEnter the collection: ");
						collec = scan.next().toLowerCase();
					}
					System.out.print("Enter the position: ");
					pos = scan.nextInt();
					if(collec.equals("a")&&pos>a.size()||pos<=0) {
						while(pos!=1) {
							System.out.println("\nYou cannot get a card at a position that is not within the current deck size.");
							System.out.print("Enter the position: ");
							pos=scan.nextInt();
						}
					}
					if(collec.equals("b")&&pos>b.size()||pos<=0) {
						while(pos!=1) {
							System.out.println("\nYou cannot get a card at a position that is not within the current deck size.");
							System.out.print("Enter the position: \n");
							pos=scan.nextInt();
						}
					}
					if(collec.equals("a")&&pos<=a.size()) {
						BaseballCard card = a.getCard(pos);
						System.out.println();
						String head = "#  Name                    Manufacturer  Year  Price  Size";
						String head2= "-- ----                    ------------  ----  -----  ----";
						String print = String.format("%-3d%-24s%-14s%-6d$%-6.2f%dx%d\n",pos,card.name,card.manufacturer,card.year,card.price,card.getX(),card.getY());
						System.out.println(head+"\n"+head2+"\n"+print);
					} else if(collec.equals("b")&&pos<=b.size()) {
						BaseballCard card = b.getCard(pos);
						String head = "#  Name                    Manufacturer  Year  Price  Size";
						String head2= "-- ----                    ------------  ----  -----  ----";
						String print = String.format("%-3d%-24s%-14s%-6d$%-6.2f%dx%d\n",pos,card.name,card.manufacturer,card.year,card.price,card.getX(),card.getY());
						System.out.println(head+"\n"+head2+"\n"+print);
					}
						break;
				case "l":
					System.out.print("Enter the name: ");
					scan.nextLine();
					name = scan.nextLine();
					System.out.print("Enter the manufacturer: ");
					manuf = scan.nextLine();
					System.out.print("Enter the year: ");
					year = scan.nextInt();
					while(year<0) {
						System.out.println("Please enter in a valid year.");
						System.out.print("Enter the year: ");
						year = scan.nextInt();
					}
					System.out.print("Enter the size: ");
					scan.nextLine();
					sizeIn = scan.nextLine();
					xSize = Integer.parseInt(sizeIn.substring(0, sizeIn.indexOf(" ")));
					ySize = Integer.parseInt(sizeIn.substring(sizeIn.indexOf(" ")+1, sizeIn.length()));
					System.out.print("Enter the price: ");
					price = scan.nextDouble();
					while(price<0) {
						System.out.println("Please enter a positive price.");
						System.out.print("Enter the price: ");
						price = scan.nextDouble();
					}
					BaseballCard find = new BaseballCard(name,manuf,year,price,xSize,ySize);
					if(a.exists(find))
						System.out.println("\nThe card is in collection A. The card is not in collection B.");
					else if(b.exists(find))
						System.out.println("\nThe card is in collection B. The card is not in collection A.");
					else 
						System.out.println("\nThe card does not in exist in either collection A or B.\n");
					break;
				case "n":
					System.out.print("\nEnter the collection: ");
					collec = scan.next().toLowerCase();
					while(!(collec.equals("a")||collec.equals("b"))) {
						System.out.print("\nPlease enter either collection A or B.\n");
						System.out.print("\nEnter the collection: ");
						collec = scan.next().toLowerCase();
					}
					System.out.print("Enter the position: ");
					pos = scan.nextInt();
					if(collec.equals("a")&&pos>a.size()||pos<=0) {
						while(pos!=1) {
							System.out.println("\nYou cannot change the name of a card at a position that is not within the current deck size.");
							System.out.print("Enter the position: ");
							pos=scan.nextInt();
						}
					}
					if(collec.equals("b")&&pos>b.size()||pos<=0) {
						while(pos!=1) {
							System.out.println("\nYou cannot change the name of a card at a position that is not within the current deck size.");
							System.out.print("Enter the position: ");
							pos=scan.nextInt();
						}
					}
					System.out.print("Enter the new name: ");
					scan.nextLine();
					String newName = scan.nextLine();
					System.out.println(newName);
					System.out.println("\nChanged name of collection "+collec.toUpperCase()+" position "+pos+" from \""+a.getCard(pos).getName()+"\" to \""+newName+"\"");
					if(collec.equals("a")&&pos<=a.size())
						a.getCard(pos).setName(newName);
					else if(collec.equals("b")&&pos<=b.size())
						b.getCard(pos).setName(newName);
					break;
				case "p":
					System.out.println("\nCollection A:");
					a.printAllCard();
					System.out.println("\nCollection B:");
					b.printAllCard();
					break;
				case "r":
					System.out.print("\nEnter the collection to remove from: ");
					collec = scan.next().toLowerCase();
					while(!(collec.equals("a")||collec.equals("b"))) {
						System.out.print("\nPlease enter either collection A or B.\n");
						System.out.print("\nEnter the collection: ");
						collec = scan.next().toLowerCase();
					}
					System.out.print("Enter the position to remove: ");
					pos = scan.nextInt();
					if(collec.equals("a")&&pos>a.size()||pos<=0) {
						while(pos!=1) {
							System.out.println("\nYou cannot remove a card at a position that is not within the current deck size.");
							System.out.print("Enter the position: ");
							pos=scan.nextInt();
						}
					}
					if(collec.equals("b")&&pos>b.size()||pos<=0) {
						while(pos!=1) {
							System.out.println("\nYou cannot remove a card at a position that is not within the current deck size.");
							System.out.print("Enter the position: ");
							pos=scan.nextInt();
						}
					}
					if(collec.equals("a")&&pos<=a.size()) {
						BaseballCard clone = (BaseballCard) a.getCard(pos).clone();
						a.removeCard(pos);
						System.out.println("\nRemoved "+clone.getName()+", "+clone.getManufacturer()+" "+clone.getYear()+", "+clone.getX()+"x"+clone.getY()+", $"+clone.getPrice()+" from collection "+collec.toUpperCase());
					}
					else {
						BaseballCard clone = (BaseballCard) b.getCard(pos).clone();
						b.removeCard(pos);
						System.out.println("\nRemoved "+clone.getName()+", "+clone.getManufacturer()+" "+clone.getYear()+", "+clone.getX()+"x"+clone.getY()+", $"+clone.getPrice()+" from collection "+collec.toUpperCase());
					}
					break;
				case "s":
					System.out.println("\nCollection A has "+a.size()+" cards. Collection B has "+b.size()+" cards.");
					break;
				case "t":
					System.out.print("\nEnter the position of the card to trade from collection A: ");
					int posA = scan.nextInt();
					while(posA>a.size()||posA<=0) {
						System.out.println("There is no card at the indicated position.");
						System.out.print("Enter the position: ");
						posA=scan.nextInt();
					}
					System.out.print("Enter the position of the card to trade from collection B: ");
					int posB = scan.nextInt();
					while(posB>b.size()||posB<=0) {
						System.out.println("There is no card at the indicated position.");
						System.out.print("Enter the position: ");
						posB=scan.nextInt();
					}
					BaseballCard aCard = a.getCard(posA);
					BaseballCard bCard = b.getCard(posB);
					a.trade(b, posA, posB);
					System.out.println("\nTraded "+bCard.getName()+", "+bCard.getManufacturer()+" "+bCard.getYear()+", "+bCard.getX()+"x"+bCard.getY()+", $"+bCard.getPrice()+" for "+aCard.getName()+", "+aCard.getManufacturer()+" "+aCard.getYear()+", "+aCard.getX()+"x"+aCard.getY()+", $"+aCard.getPrice());
					break;
				case "v":
					double priceA=0, priceB=0;
					for(int i = 1; i<a.size() + 1;i++) {
						priceA+=a.getCard(i).getPrice();
					}
					for(int i = 1;i<b.size() + 1;i++) 
						priceB+=b.getCard(i).getPrice();
					System.out.println(String.format("\nThe total value of collection A is $%.2f. The total value of collection B is $%.2f.\n",priceA,priceB));
					break;
				case "q":
					System.out.println("\nQuitting.");
					run="q";
					break;
				default:
					System.out.println("\nPlease enter in a letter corresponding with the menu options.");
				}
			}
			catch(FullCollectionException fce) {
				System.out.println("\n"+fce.toString());
			}
			catch(StringIndexOutOfBoundsException siob) {
				System.out.println("\nPlease enter in the size in the order of x followed by a space then y. (Ex: 100 300)");
			}
			catch(InputMismatchException ime) {
				System.out.println("\nPlease enter in the correct data field (String, int, double, bool, etc.).");
				scan.nextLine();
			}
			catch(IllegalArgumentException iae) {
				System.out.println(iae.toString());
			}
			catch(Exception e) {
				System.out.println("\n"+e.toString());
			}
		}
	}
}
