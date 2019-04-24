/**
 * Eddie Xu
 * ID: 112206686
 * Email: eddie.xu@stonybrook.edu
 * Homework 4
 * CSE214
 * Recitation 6: Juan Tarquino
 */
package hw4_214;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * PassengerQueue class that represents a real-life queue of people. Implemented by extending
 * 		the LinkedList API. Holds a LinkedList of Passenger objects in the order that the
 * 		Passengers were enqueued to the line.
 * people : Linked list of Passenger objects.
 * @author Eddie Xu
 *
 */
public class PassengerQueue extends LinkedList<Passenger>{
	private LinkedList<Passenger> people;
	
	/**
	 * Constructor for instantiating a PassengerQueue object. Initializes 
	 * 		class variable linked list people.
	 */
	public PassengerQueue() {
		people = new LinkedList<Passenger>();
	}
	
	/**
	 * Enqueues a Passenger to var people. 
	 * @param p
	 * 		Passenger object to be added to var people.
	 */
	public void enqueue(Passenger p) {
		people.add(p);
	}
	
	/**
	 * Dequeues (removes) the very first Passenger object in var people.
	 * @return
	 * 		Passenger object that was removed.
	 */
	public Passenger dequeue() {
		return people.removeFirst();
	}
	
	/**
	 * Searches through var people for Passenger object provided in the param and
	 * 		dequeues the Passenger from var people.
	 * @param p
	 * 		Passenger object to be searched for and removed.
	 */
	public void dequeue(Passenger p) {
		for(int i = 0;i<sizeOfList();i++) {
			Passenger inLine = people.get(i);
			if(inLine.equals(p))
				people.remove(i);
		}
	}
	
	/**
	 * 'Looks' at the very first element in var people.
	 * @return
	 * 		Returns a copy of the very first Passenger in var people.
	 */
	public Passenger peek() {
		return people.peekFirst();
	}

	/**
	 * Obtains the number of Passenger objects in var people.
	 * @return
	 * 		Number of Passenger objects in var people.
	 */
	public int sizeOfList() {
		return people.size();
	}
	
	/**
	 * Loops through every Passenger object in var people and obtains the group size
	 * 		of every Passenger.
	 * @return
	 * 		The total group size (number of PEOPLE in class var people).
	 * 		If var people is empty, return 0.
	 */
	public int size() {
		if(!people.isEmpty()) {
			int numPassengers=0;
			for(Passenger p : people) {
				numPassengers+=p.getGroupSize();
			}
			return numPassengers;
		}
		return 0;
	}
	
	/**
	 * Returns the Passenger object of var people at the given index.
	 * @param
	 * 		Index desired to obtain Passenger object from var people.
	 * @return
	 * 		Passenger object and indicated index.
	 * 
	 */
	public Passenger get(int indx) {
		return people.get(indx);
	}
	
	/**
	 * Checks if var people is empty or not.
	 * @return
	 * 		true is var people is empty (no Passenger objects in it)
	 * 		false if var people is NOT empty (contains Passengers.)
	 */
	public boolean isEmpty() {
		return people.isEmpty();
	}
}