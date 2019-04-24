/**
 * Eddie Xu
 * ID: 112206686
 * Email: eddie.xu@stonybrook.edu
 * Homework 4
 * CSE214
 * Recitation 6: Juan Tarquino
 */
package hw4_214;

import java.io.IOException;
import java.util.Iterator;

/**
 * Bus class that models a Bus vehicle. Every bus has a certain maximum capacity (amount of people
 * 		it can hold), the type of Bus it is (in the case of Stony Brook University, Ineer/Outer),
 * 		the next stop, the time to next stop, the time to rest at a terminal stop, and if it is
 * 		operating (not at rest).
 * capacity : Maximum amount of Passengers a bus can hold (Note, a Passenger can have multiple people
 * 		The size of a Passenger object is what's considered, not how many Passenger objects.)
 * route : Type of Bus (refer to class description).
 * nextStop : The next location the bus is heading to represented as an int. To be elaborated in
 * 		Simulator class.
 * toNextStop : Time until the Bus reaches the next stop.
 * timeToRest : Time Bus is resting at a terminal (last) stop.
 * peopleOnBus : PassengerQueue of Passengers.
 * @author Eddie Xu
 *
 */
public class Bus {
	public static int capacity;
	public String route;
	private int nextStop;
	private int toNextStop;
	private int timeToRest;
	private boolean isOperating;
	public PassengerQueue peopleOnBus = new PassengerQueue();
	
	/**
	 * Constructor to instantiate all class variables to 'nothing' (unusable).
	 */
	public Bus() {
		capacity = -1;
		route = "";
		nextStop = -1;
		toNextStop = -1;
		timeToRest = -1;
	}
	
	/**
	 * Constructor to instantiate all class variables to the parameters.
	 * @param capacity
	 * 		Capacity (max amount of PEOPLE) the Bus can hold.
	 * @param route
	 * 		Type of bus (refer to class description).
	 * @param nextStop
	 * 		Next stop of the Bus.
	 * @param toNextStop
	 * 		Time to arrive at next stop.
	 * @param timeToRest
	 * 		Time the Bus is resting at a terminal stop.
	 */
	public Bus(int capacity, String route, int nextStop, int toNextStop, int timeToRest) {
		this.capacity = capacity;
		this.route = route;
		this.nextStop = nextStop;
		this.toNextStop = toNextStop;
		this.timeToRest = timeToRest;
	}
	
	/**
	 * Setter method to change Bus type.
	 * @param route
	 * 		Desired type of bus.
	 */
	public void setRoute(String route) {
		this.route=route;
	}
	
	/**
	 * Getter method to return the Bus type.
	 * @return
	 * 		class variable route (type of Bus).
	 */
	public String getRoute() {
		return route;
	}
	
	/**
	 * Setter method to set the next stop of the bus.
	 * @param nextStop
	 * 		The int value of the nextStop (to be elaborated in Simulator class).
	 */
	public void setNextStop(int nextStop) {
		this.nextStop=nextStop;
	}
	
	/**
	 * Getter method to get the next stop of the bus.
	 * @return
	 * 		The int value of the nextStop (to be elaborated in simulator class).
	 */		
	public int getNextStop() {
		return nextStop;
	}
	
	/**
	 * Setter method to set the time until bus arrives at the next stop.
	 * @param toNextStop
	 * 		The time to get to the next stop in minutes.
	 */
	public void setTimeToNext(int toNextStop) {
		this.toNextStop=toNextStop;
	}
	
	/**
	 * Getter method to get the time to the next stop.
	 * @return
	 * 		Time until bus arrives at next stop in minutes.
	 */
	public int getTimeToNext() {
		return toNextStop;
	}
	
	/**
	 * Setter method to set the time for the bus to rest at a terminal stop.
	 * @param timeToRest
	 * 		Time that the bus will be resting at the terminal stop.
	 */
	public void setTimeToRest(int timeToRest) {
		this.timeToRest=timeToRest;
	}
	
	/**
	 * Getter method to get the time that the bus is resting at a terminal stop.
	 * @return
	 * 		Time that the bus is resting at the terminal stop in minutes. 
	 */
	public int getTimeToRest() {
		return timeToRest;
	}
	
	/**
	 * Sets the isOperating class variable.
	 * @param isOperating
	 * 		If the bus is to be operating or not.
	 */
	public void setOperating(boolean isOperating) {
		this.isOperating = isOperating;
	}
	
	/**
	 * Gets the isOperating class variable.
	 * @return
	 * 		true if the bus is Operating.
	 * 		false if the bus is NOT operating (at rest at terminal).
	 */
	public boolean isOperating() {
		return isOperating;
	}
	
	/**
	 * Dequeues the Passenger objects currently on the bus that have a desired destination
	 * 		that is the same as the parameter destination.
	 * @param dest
	 * 		Location that the bus is currently at. 
	 * @return
	 * 		Number of PEOPLE removed (not number of Passenger objects).
	 */
	public int unload(int dest) {
		Iterator<Passenger>itr = peopleOnBus.iterator();
		int removed = 0;
		for(int i = 0;i < peopleOnBus.sizeOfList();i++) {
			Passenger p = peopleOnBus.get(i);
			if(p.getDestination()==dest){
				removed+=p.getGroupSize();
				peopleOnBus.dequeue(p);
				i--;
			}
		}
		return removed;
	}
	
	
	/**
	 * Dequeues every Passenger object from the bus regardless of destination.
	 * @return
	 * 		Number of PEOPLE removed (not number of Passenger objects).
	 */
	public int unloadEveryone() {
		int removed = 0;
		for(int i = 0;i < peopleOnBus.sizeOfList();i++) {
			Passenger p = peopleOnBus.get(i);
			removed+=p.getGroupSize();
			peopleOnBus.dequeue(p);
			i--;
			}
		return removed;
	}
	
	/**
	 * Checks is a Passenger object can be added to the Bus. If the Passenger group size
	 * 		(number of people) exceeds the indicated capacity for the bus, will not add the Passenger.
	 * @param p
	 * 		Passenger to check if it can be added to the Bus.
	 * @return
	 * 		true if the Passenger was added.
	 * 		false if the Passenger was NOT added.
	 */
	public boolean canBeAdded(Passenger p) {
		if(p.getGroupSize()+peopleOnBus.size()>capacity)
			return false;
		return true;
	}
	
	/**
	 * Enqueues a Passenger to the Bus.
	 * @param p
	 * 		Passenger to be added to the Bus.
	 */
	public void addToBus(Passenger p) {
		peopleOnBus.enqueue(p);
	}
	
	/**
	 * Sets the time to rest of the Bus to 30 minutes. (Rests at terminal for 30 min) 
	 * 		and sets whether the bus is operating or not to be false (if bus is resting at
	 * 		a terminal, it is NOT operating.
	 */
	public void setToRest() {
		this.setTimeToRest(30);
		this.setOperating(false);
	}
	
	/**
	 * Checks if the Bus is empty (contains no Passenger objects).
	 * @return
	 * 		true if the Bus is empty
	 * 		false if the Bus contains Passengers.
	 */
	public boolean isEmpty() {
		return peopleOnBus.isEmpty();
	}
}