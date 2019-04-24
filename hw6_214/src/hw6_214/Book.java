/**
 * Eddie Xu
 * ID: 112206686
 * Email: eddie.xu@stonybrook.edu
 * Homework 6
 * CSE214
 * Recitation 6: Juan Tarquino
 */

package hw6_214;

import java.io.Serializable;

/**
 * Book class that represents a Book to be recorded and saved in a hashbtable. Each book contains
 * a title, author, publisher, and isbn number. In the hashtable, the isbn will be used as the 
 * key to access the book element. 
 * 
 * title: Title of book.
 * author: Author of book,
 * publisher: Publisher of book,
 * isbn: (International Standard Book Number) unique to each book. To be used as key in hashtable.
 * 
 * @author eddie
 *
 */
public class Book implements Serializable {
	private String title;
	private String author;
	private	String publisher;
	private String isbn;
	
	/**
	 * Constructor for a new Book object. Takes in parameters to fill in class vars.
	 * 
	 * @param title
	 * 		Title of book to be made.
	 * @param author
	 * 		Author of book to be made.
	 * @param publisher
	 * 		Publisher of book to be made.
	 * @param isbn
	 * 		ISBN number of specific book.
	 */
	public Book(String title, String author, String publisher, String isbn) {
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.isbn = isbn;
	}
	
	/**
	 * Setter method to set a new title for an existing book object.
	 * @param title
	 * 		New title to set the current book title to.
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * Getter method to retrieve class var title of current book.
	 * @return
	 * 		Title of the current book object.
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Setter method to set a new author for an existing book object.
	 * @param author
	 * 		New author to set the current book's author to.
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	
	/**
	 * Getter method to retrieve class var author of current book.
	 * @return
	 * 		Author of the current book object.
	 */
	public String getAuthor() {
		return author;
	}
	
	/**
	 * Setter method to set a new publisher for an existing book object.
	 * @param publisher
	 * 		New publisher to set the current book's publisher to.
	 */
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	/**
	 * Getter method to retrieve class var publisher of current book.
	 * @return
	 * 		Publisher of the current book object.
	 */
	public String getPublisher() {
		return publisher;
	}
	
	/**
	 * Setter method to set a new isbn for an existing book object.
	 * @param isbn
	 * 		New isbn to set the current book's isbn to.
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	/**
	 * Getter method to retrieve class var isbn of current book.
	 * @return
	 * 		isbn of the current book object.
	 */
	public String getIsbn() {
		return isbn;
	}
	
	/**
	 * toString method to neatly format all information about the book in a single line. 
	 * @return
	 * 		Formatted information about the book.
	 */
	public String toString() {
		return String.format("%-16s%-36s%-32s%-38s",isbn,title,author,publisher);
	}
}
