/**
 * Eddie Xu
 * ID: 112206686
 * Email: eddie.xu@stonybrook.edu
 * Homework 6
 * CSE214
 * Recitation 6: Juan Tarquino
 */

package hw6_214;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Hashtable;
import java.util.Scanner;
import big.data.*;

/**
 * HashedLibrary class. Represents a library of books implemented using the Hashtable api. 
 * 
 * @author eddie
 *
 */
public class HashedLibrary implements Serializable{
	private Hashtable<String, Book> library;
	
	/**
	 * Default constructor to create a new HashedLibrary.
	 */
	public HashedLibrary() {
		library = new Hashtable();
	}
	
	/**
	 * Method to add a single Book object into the class var library. 
	 * 
	 * @param title
	 * 		Title of the book to add.
	 * @param author
	 * 		Author of the book to add.
	 * @param publisher
	 * 		Publisher of the book to add.
	 * @param isbn
	 * 		ISBN of the book to add.
	 * @throws Exception
	 * 		If the book is already recorded in the hashtable.
	 */
	public void addBook(String title, String author, String publisher, String isbn) throws Exception {
		if(library.get(isbn)==null) {
			Book toAdd = new Book(title,author,publisher,isbn);
			library.put(isbn, toAdd);
		}
		else {
			throw new Exception(isbn+": Book already recorded.\n");
		}
	}
	
	/**
	 * Parses a .txt file of names of .xml book files. Connects each name in the .txt file to a database
	 * offered by the bigdata.jar file and gets information in the .xml file for the book.
	 * 
	 * @param fileName
	 * 		Name of the .txt file to parse.
	 * @throws Exception
	 * 		If file is not found or the book was already recorded once. 
	 */
	public void addAllBookInfo(String fileName) throws Exception {
		File f = new File(fileName);
		if(!f.exists()) {
			throw new IOException("File not found.");
		}
		else {
			Scanner scan = new Scanner(f);
			System.out.println();
			while(scan.hasNextLine()) {
				String bigFile = scan.nextLine();
				DataSource ds = DataSource.connect("http://www3.cs.stonybrook.edu/~cse214/hw/hw6/"+bigFile+".xml").load();
				String title = ds.fetchString("title");
				String author = ds.fetchString("author");
				String publisher = ds.fetchString("publisher");
				String isbn = ds.fetchString("isbn");
				try {
					this.addBook(title,author,publisher,isbn);
				}catch(Exception e) {
					System.out.print(isbn+": Book already recorded.\n");
					continue;
				}
				System.out.print(isbn+": "+title+" by "+author+" recorded.\n");
			}
		}
	}
	
	/**
	 * Gets the book from the hashtable with the 'key': the isbn number.
	 * 
	 * @param isbn
	 * 		The isbn number of the book.
	 * @return
	 * 		The book object with the unique isbn number.
	 * @throws IOException
	 * 		If the isbn does not exist in the current records in the hashtable.
	 */
	public Book getBookByisbn(String isbn) throws IOException {
		if(library.get(isbn)==null) {
			throw new IOException("They book by the isbn "+isbn+" does not exist in records.");
		}
		else
			return (Book)library.get(isbn);
	}
	
	/**
	 * Prints out a neatly formatted layout of the entire hashtable. 
	 */
	public void printCatalog() {
		System.out.println(String.format("%-16s%-36s%-32s%-38s","Book ISBN","Title","Author","Publisher"));
		System.out.println("--------------------------------------------------------------------------------------------------------------------------");
		library.forEach((isbn, Book)->System.out.println(Book.toString()));
	}
}
