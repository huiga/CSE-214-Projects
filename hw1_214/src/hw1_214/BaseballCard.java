/**
 * Eddie Xu
 * ID: 112206686
 * Email: eddie.xu@stonybrook.edu
 * Homework 1
 * CSE214
 * Recitation 6: Juan Tarquino
 */ 
import java.io.*;

/**
 * BaseballCard class that allows creation of BaseballCard objects.
 * All BaseballCard objects have a name, manufacturer, year made, price, length, and width.
 * 
 * @author Eddie Xu
 *
 */
public class BaseballCard{
	public String name;
	public String manufacturer;
	public int year;
	public double price;
	public int[]imgSize=new int[2];
	
	/**
	 *  Creates a new BaseballCard object and instantiates class data fields.
	 */
	public BaseballCard() {
		name="N/A";
		manufacturer="N/A";
		year=0;
		price=0;
	}
	
	/**
	 *  Creates a new BaseballCard object and instantiates class data fields.
	 * @param name
	 *      name of the card
	 * @param manufacturer
	 *		manufacturer of the card.
	 * @param year
	 * 		year that the card was made.
	 * @param price: 
	 *		the price of the card.
	 */
	public BaseballCard(String name, String manufacturer, int year, double price) {
		this.name=name;
		this.manufacturer=manufacturer;
		this.year=year;
		this.price=price;
	}
	
	/**
	 *  Creates a new BaseballCard object and instantiates class data fields.
	 * @param name 
	 * 		name of the card
	 * @param manufacturer
	 * 		manufacturer of the card
	 * @param year
	 * 		year that the card was made.
	 * @param price
	 * 		the price of the card.
	 * @param x
	 * 		The width of the card.
	 * @param y
	 * 		The length of the card.
	 */
	public BaseballCard(String name, String manufacturer, int year, double price, int x, int y) {
		this.name=name;
		this.manufacturer=manufacturer;
		this.year=year;
		this.price=price;
		imgSize[0]=x;
		imgSize[1]=y;
	}
	
	/**
	 *  Creates a new BaseballCard object and instantiates class data fields with a given BaseballCard object.
	 * @param card
	 * 		Another BaseballCard object with instantiated data fields.
	 */
	public BaseballCard(BaseballCard card) {
		this.name=card.name;
		this.manufacturer=card.manufacturer;
		this.year=card.year;
		this.price=card.price;
		imgSize[0]=card.getX();
		imgSize[1]=card.getY();
	}
	
	/**
	 *  Sets a new name for an existing BaseballCard object.
	 * @param name
	 * 		The name to change the BaseballCard object to.
	 */
	public void setName(String name) {
		this.name=name;
	}
	
	/**
	 *  Getter method for the name of an existing BaseballCard object.
	 * @return
	 * 		the string representation of the name of the BaseballCard object.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets a new manufacturer for an existing BaseballCard object.
	 * @param manufacturer
	 * 		The manufacturer name to change the BaseballCard object to.
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer=manufacturer;
	}
	
	/**
	 *  Getter method for the name of an existing BaseballCard object.
	 * @return
	 * 		the string representation of the manufacturer.
	 */	
	public String getManufacturer() {
		return manufacturer;
	}
	
	/**
	 * Sets a new year for an existing BaseballCard object.
	 * @param year
	 * 		the int representation of the year of the BaseballCard of the object.
	 */
	public void setYear(int year) throws IOException {
		this.year=year;
	}
	
	/**
	 * Getter method for the year of an existing BaseballCard object.
	 * @return
	 * 		the int representation of the year the of the BaseballCard object.
	 */
	public int getYear() {
		return year;
	}
	
	/**
	 * Sets a new price for an existing BaseballCard object.
	 * @param price
	 * 		the double representation of the card's new price to.
	 */
	public void setPrice(double price) {
			this.price=price;
	}
	
	/**
	 * Getter method for the price of an existing BaseballCard object.
	 * @return
	 * 		the double of a price of the BaseballCard.
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * Sets a new width for an existing BaseballCard object, stored in the 0th index of 2-length array named imgSize.
	 * @param x
	 * 		the int representation of the width of the BaseballCard object.
	 */	
	public void setX(int x) {
		imgSize[0]=x;
	}
	
	/**
	 * Getter method for the length of an existing BaseballCard object.
	 * @return
	 * 		the int width of the BaseballCard
	 */
	public int getX() {
		return imgSize[0];
	}
	
	/**
	 * Sets a new length for an existing BaseballCard object, stored in the 1st index of 2-length array named imgSize.
	 * @param x
	 * 		the int representation of the length of the BaseballCard object.
	 */	
	public void setY(int y) {
		imgSize[1]=y;
	}
	
	/**
	 * Getter method for the length of an existing BaseballCard object.
	 * @return
	 * 		the int length of the BaseballCard
	 */
	public int getY() {
		return imgSize[1];
	}
	
	/**
	 * Instantiates and copies the elements of an existing BaseballCard object into a new BaseballCard object.
	 * @return
	 * 		the BaseballCard object with the exact same elements.
	 */
	public Object clone() {
		BaseballCard clone = new BaseballCard(name,manufacturer,year,price,getX(),getY());
		return clone;
	}
	
	/**
	 * Compares every element of two BaseballCard objects and returns a boolean value if they're the same or not.
	 * @param obj
	 * 		An object to compare the BaseballCard object to.
	 * @returns
	 * 		boolean value representing wether the objects are equal or not (T = equal, F = not equal).
	 */
	public boolean equals(Object obj) {
		if(obj instanceof BaseballCard) {
			BaseballCard baseball = (BaseballCard) obj;
			return (getName().equals(baseball.getName())&&getManufacturer().equals(baseball.getManufacturer())&&getYear()==baseball.getYear()&&getPrice()==baseball.getPrice()&&getX()==baseball.getX()&&getY()==baseball.getY());
		}
		else
			return false;
	}
}
