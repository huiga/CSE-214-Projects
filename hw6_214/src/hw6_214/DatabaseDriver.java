/**
 * Eddie Xu
 * ID: 112206686
 * Email: eddie.xu@stonybrook.edu
 * Homework 6
 * CSE214
 * Recitation 6: Juan Tarquino
 */

package hw6_214;

import java.io.*;
import java.util.Scanner;

/**
 * DatabaseDriver class. Main class for creating a library of books that were implemented using the
 * hashtable api in java. Prints out user interface for use. On first run, creates a HashedLibrary
 * object named 'library' (library.obj) that will hold all book objects in a hashtable. If the library
 * already exists in the directory, the class will load up the elements in the library.obj and 
 * continue to append information to the object. Upon quitting (Q), the activities during the 
 * compilation get saved and written to the library.obj.   
 * 
 * @author eddie
 *
 */
public class DatabaseDriver {
	public static void main(String[]args) throws Exception {
		File libObj = new File("library.obj");
		HashedLibrary library = new HashedLibrary();
		if(libObj.exists()) {
			FileInputStream file = new FileInputStream("library.obj");
			ObjectInputStream inStream = new ObjectInputStream(file);
			try {
				library=(HashedLibrary)inStream.readObject();
				System.out.println("Successfully loaded contents if library.obj.\n");
			}catch(EOFException eof) {
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else {
			System.out.println("library.obj is not found. Using a new HashedLibrary.\n");
			FileOutputStream file = new FileOutputStream("library.obj");
			ObjectOutputStream outStream = new ObjectOutputStream(file);
		}
		String y = "y";
		Scanner scan = new Scanner(System.in);
		while(y.equalsIgnoreCase("y")) {
			System.out.print("(D) Displays Books\n(G) Get Book\n(L) Load File\n(R) Record Book"
					+"\n(Q) Quit\n");
			try {
				System.out.print("\nEnter your selection: ");
				switch(scan.next().toLowerCase()) {
					case "d":
						System.out.println();
						library.printCatalog();
						System.out.println();
						break;
					case "g":
						System.out.print("\nEnter Book ISBN: ");
						String isbn = scan.next();
						if(library.getBookByisbn(isbn)!=null) {
							System.out.println(String.format("\n%-16s%-36s%-32s%-38s","Book ISBN","Title","Author","Publisher"));
							System.out.println("--------------------------------------------------------------------------------------------------------------------------");
							System.out.print(library.getBookByisbn(isbn).toString());
							System.out.println("\n");
						}
						else {
							System.out.print("There is no book associated with the isbn "+isbn);
						}
						break;
					case "l":
						System.out.print("\nEnter the file to load :");
						String txtFile = scan.next();
						library.addAllBookInfo(txtFile);
						System.out.println();
						break;
					case "r":
						System.out.print("\nEnter book title: ");
						scan.nextLine();
						String title = scan.nextLine();
						System.out.print("Enter book author: ");
						String author = scan.nextLine();
						System.out.print("Enter book publisher: ");
						String publisher = scan.nextLine();
						System.out.print("Enter book ISBN: ");
						isbn = scan.nextLine();
						try {
							library.addBook(title, author, publisher, isbn);
							System.out.print("\n"+isbn+": "+title+" by "+author+" recorded.\n\n");
						}catch(Exception e) {
							System.out.println("\n"+e.toString());
						}
						break;
					case "q":
						FileOutputStream file = new FileOutputStream("library.obj");
						ObjectOutputStream fout = new ObjectOutputStream(file);
						fout.writeObject(library);
						fout.close();
						y = "q";
						System.out.println("\nHashedLibrary is saved into file library.obj.");
						System.out.println("\nProgram terminating normally...");
						break;
					default:
						System.out.print("\nPlease enter in a choice with the interface.\n\n");
					}
			} catch(IOException ioe) {
				System.out.print("\n"+ioe.toString()+"\n\n");
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
