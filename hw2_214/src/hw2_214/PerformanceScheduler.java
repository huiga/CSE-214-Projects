/**
 * Eddie Xu
 * ID: 112206686
 * Email: eddie.xu@stonybrook.edu
 * Homework 2
 * CSE214
 * Recitation 6: Juan Tarquino
 */

package hw2_214;

import java.io.IOException;
import java.util.Scanner;

/**
 * Main class for running the program of a doubly linked list of concert performances.
 * Creates a user-driven interface to interact with nodes and manipulate the doubly linked list.
 * 
 * 
 * @author Eddie Xu 112206686
 *
 */
public class PerformanceScheduler {
	public static void main(String[]args) throws Exception {
		System.out.println("A) Add to end\nB) Move current node backward\nC) Display current node\nD) Display all nodes\nF) Move current node forward\nI) Insert after current node\nJ) Jump to position\nR) Remove current node\nQ) Exit");
		Scanner scan = new Scanner(System.in);
		String cursor = "->";
		PerformanceList performances = new PerformanceList();
		String cont = "";
		while(!cont.equalsIgnoreCase("Q")) {
			System.out.print("\nChoose an operation: ");
			try {
				switch(scan.next().toLowerCase()) {
				case "a":
					System.out.print("\nEnter name of performance: ");
					scan.nextLine();
					String namePerformance = scan.nextLine();
					System.out.print("Enter name of lead performer: ");
					String namePerformer = scan.nextLine();
					System.out.print("Enter the total participants: ");
					int totalParticipants = scan.nextInt();
					System.out.print("Enter the duration of the performance: ");
					int duration = scan.nextInt();
					PerformanceNode toEnd = new PerformanceNode(namePerformance,namePerformer,totalParticipants,duration);
					performances.addToEnd(toEnd);
					//System.out.println(performances.testHead());
					System.out.print("\nNew performance "+namePerformance+" is added to the end of the list.\n");
					break;
				case "b":
					if(performances.moveCursorBackwards())
						System.out.print("\nCursor has been moved backwards.\n");
					else {
						throw new IOException("The cursor is pointing to the first node. Cannot move cursor backwards.\n");
					}
					break;
				case "c":
					performances.displayCurrentPerformance();
					break;
				case "d":
					System.out.print(performances.toString());
					break;
				case "f":
					if(performances.moveCursorForward())
						System.out.print("\nCursor has been moved forwards.\n");
					else {
						//performances.moveCursorBackwards();
						System.out.print("\nCannot move cursor forward anymore.\n");
					}
					break;
				case "i":
					System.out.print("\nEnter name of performance: ");
					scan.nextLine();
					String namePerformance2 = scan.nextLine();
					System.out.print("Enter name of lead performer: ");
					String namePerformer2 = scan.nextLine();
					System.out.print("Enter the total participants: ");
					int totalParticipants2 = scan.nextInt();
					System.out.print("Enter the duration of the performance: ");
					int duration2 = scan.nextInt();
					PerformanceNode afterCursor = new PerformanceNode(namePerformance2,namePerformer2,totalParticipants2,duration2);
					performances.addAfterCurrent(afterCursor);
					performances.moveCursorForward();
					System.out.print("\nNew performance "+namePerformance2+" is added after the current performance.\n");
					break;
				case "j":
					System.out.print("\nEnter the position: ");
					int position = scan.nextInt();
					if(performances.jumpToPosition(position))
						System.out.print("\nCursor has been moved to position "+position+".\n");				
					else
						System.out.print("\nCursor cannot be moved to position "+position+".\n");
					break;
				case "r":
					String performanceRemovedName = performances.cursor.getPerformance();
					if(performances.removeCurrentNode())
						System.out.print("\nPerformance "+performanceRemovedName+" has been removed.\n");
					else 
						throw new IOException("Error in removing node.");
					break;
				case "q":
					cont = "Q";
					System.out.print("\nProgram terminating normally...");
					break;
				default:
					System.out.print("\nPlease enter in a letter corresponding with the menu options.\n");
				}
			}
			catch(NullPointerException nee) {
				System.out.println("\n"+nee.toString());
			}
			catch(IOException ioe) {
				System.out.println("\n"+ioe.toString());
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
   