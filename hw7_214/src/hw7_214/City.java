/**
 * Eddie Xu
 * ID: 112206686
 * Email: eddie.xu@stonybrook.edu
 * Homework 7
 * CSE214
 * Recitation 6: Juan Tarquino
 */
package hw7_214;


import java.io.Serializable;

import com.cse214.geocoder.*;
import latlng.LatLng;

/**
 * City class modeling an actual city. Uses a modified package from Google's Geocoder and 
 * registers/records cities within the imported package. Every city has: 
 * city : name of the city
 * location : Latitude and longitude of the city
 * indexPos : position in the ArrayList<City> (to be implemented in SigmaAir
 * cityCount : total number of cities recorded.
 * 
 * @author eddie
 *
 */
public class City implements Serializable {
	private String city;
	private LatLng location;
	private int indexPos;
	private static int cityCount;
	
	
	/**
	 * Default constructor. Initializes all class variables and created a new City object.
	 */
	public City() {
	}
	
	/**
	 * Constructor to create new City object.
	 * @param city
	 * 		City name.
	 * @param location
	 * 		LatLng object from the Geocoder. Tells latitude/longitude of city. 
	 */
	public City(String city, LatLng location) {
		this.city = city;
		this.location = location;
		cityCount++;
	}
	
	/**
	 * Setter method to set new name for the City obj,
	 * @param city
	 * 		New name to set the city to.
	 */
	public void setCity(String city){
		this.city = city;
	}
	
	/**
	 * Getter method to return the name of the City.
	 * @return
	 * 		City name.
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * Setter method to ser a new location for the City obj.
	 * @param location
	 * 		LatLng obj of the new location of the city.
	 */
	public void setLocation(LatLng location) {
		this.location = location;
	}
	
	/**
	 * Getter method to return the location of the City.
	 * @return
	 * 		LatLng obj of the current location of the city.
	 */
	public LatLng getLocation() {
		return location;
	}
	
	/**
	 * Setter method to set the index position of the city object in the SigmaAir arraylist.
	 * @param indexPos
	 * 		Index to set the index position to.
	 */
	public void setIndexPos(int indexPos) {
		this.indexPos = indexPos;
	}
	
	/**
	 * Getter method to return the index position of the city object.
	 * @return
	 * 		Index of the current City object.
	 */
	public int getIndexPos() {
		return indexPos;
	}
	
	/**
	 * Setter method to set the amount of city to.
	 * @param cityCount
	 * 		New 'amount' of cities.
	 */
	public void setCityCount(int cityCount) {
		this.cityCount = cityCount;
	}
	
	/**
	 * Getter method to return the cityCount of the City class.
	 * @return
	 * 		Total amount of City objects.
	 */
	public int getCityCount() {
		return cityCount;
	}
	
	/**
	 * Neatly formats the information of a City object.
	 * @return
	 * 		Neatly formatted string of the City object's data fields.
	 */
	public String toString() {
		return String.format("%-28s%-16f%-17f", city,location.getLat(),location.getLng());
	}
}
