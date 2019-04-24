/**
 * Eddie Xu
 * ID: 112206686
 * Email: eddie.xu@stonybrook.edu
 * Homework 4
 * CSE214
 * Recitation 6: Juan Tarquino
 */

package hw4_214;

/**
 * Passenger class. Represents a person OR a group of person(s) to ride a Bus route suimulation.
 * 		Contains appropriate getter and setter methods.
 * groupSize : the size of the Passenger object.
 * destination : the desired location to travel to for the group (represented as an int to get 
 * 		the respective In/Out route (array) element for the array (to be instantiated in Simulator class.)
 * timeArrival : time that the Passenger object arrived at a bus stop.
 * 
 * @author Eddie Xu
 *
 */
public class Passenger {
	private int groupSize;
	private int destination;
	private int timeArrival;
	
	/**
	 * Constructor for Passenger class. Initializes all data fields to be 'nothing' (unusable).
	 */
	public Passenger() {
		groupSize=-1;
		destination=-1;
		timeArrival=-1;
	}
	
	/**
	 * Constructor for Passenger class. Takes in parameters to initialize data fields for a Passenger.
	 * @param groupSize
	 * 		Size of group to set class variable to.
	 * @param destination
	 * 		Int representation of destination to set class varaible to.
	 * @param timeArrival
	 * 		Time that the Passenger arrived at a bus stop in minutes.
	 */
	public Passenger(int groupSize,int destination,int timeArrival) {
		this.groupSize = groupSize;
		this.destination = destination;
		this.timeArrival = timeArrival;
	}
	
	/**
	 * Setter method to set class variable groupSize
	 * @param newSize
	 * 		New size to set the groupSize to.
	 */
	public void setGroupSize(int newSize) {
		groupSize = newSize;
	}
	
	/**
	 * Getter method to get value of class variable groupSize
	 * @return
	 * 		Size of the passenger group.
	 */
	public int getGroupSize() {
		return groupSize;
	}
	
	/**
	 * Setter method to set the 
	 * @param newDestination
	 */
	public void setDestination(int newDestination) {
		destination = newDestination;
	}
	
	/**
	 * Getter method to get value of class variable destination
	 * @return
	 * 		Destination as an int (to be used in String array in Simulator class).
	 */
	public int getDestination() {
		return destination;
	}
	
	/**
	 * Setter method to set class variable timeArrival.
	 * @param newTime
	 * 		Time that a Passenger was added to a stop.
	 */	
	public void setTimeArrival(int newTime) { //might not be needed?
		timeArrival = newTime;
	}
	
	/**
	 * Getter method to get value of the time that Passenger arrived at a bus stop.
	 * @return
	 * 		Time that a Passenger was added to a stop.
	 */
	public int getTimeArrival() {
		return timeArrival;
	}
	
	/**
	 * Method to compare all class variables of two Passenger objects.
	 * @param p
	 * 		Passenger to compare class variables to.
	 * @return
	 * 		true if all class variables of both Passengers are equivalent
	 * 		false if class variables differ from each other, respective to the variable.
	 */
	public boolean equals(Passenger p) {
		if(this.getDestination() == p.getDestination() && this.getGroupSize() == p.getGroupSize() && this.getTimeArrival() == p.getTimeArrival())
			return true;
		else
			return false;
	}
}
