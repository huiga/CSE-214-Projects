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
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Simulator class that runs a simulation of a real-world Bus system (further elaborated in simulate() method below). 
 * 		In general, the class encapsulates a method that models the Stony Brook University bus system with an
 * 		Inner and Outer loop bus. 
 * numInBusses : Number of Inner route busses.
 * numOutBusses: Number of Outer route busses.
 * minGroupSize: Minimum number of people in a Passenger group.
 * maxGroupSize: Maximum number of people in a Passenger group.
 * capacity: Number of PEOPLE that a Bus can hold (not number of Passenger objects). 
 * arrivalProb: The probability that a passenger will arrive at a bus stop.
 * 
 * @author Eddie Xu
 *
 */
public class Simulator {
	private int numInBusses;
	private int numOutBusses;
	private int minGroupSize;
	private int maxGroupSize;
	private int capacity;
	private double arrivalProb;
	
	/**
	 * Main method that initializes and starts the entire Bus simulation. Takes in user input to initialize
	 * 		class variables and calls the simulate() method that ultimately runs the actual simulation.
	 */
	public static void main(String[]args) throws Exception {
		int numInBusses,numOutBusses,minGroupSize,maxGroupSize,capacity, duration;
		double arrivalProb;
		String cont = "y";
		Scanner scan = new Scanner(System.in);
		while(cont.equalsIgnoreCase("y")) {
			try {
				System.out.print("Enter the number of In Route busses: ");
				numInBusses = scan.nextInt();
				System.out.print("Enter the number of Out Route busses: ");
				numOutBusses = scan.nextInt();
				System.out.print("Enter the minumum group size of passengers: ");
				minGroupSize = scan.nextInt();
				System.out.print("Enter the maximum group size of passengers: ");
				maxGroupSize = scan.nextInt();
				System.out.print("Enter the capacity of a bus: ");
				capacity = scan.nextInt();
				System.out.print("Enter the arrival probability: ");
				arrivalProb = scan.nextDouble();
				System.out.print("Enter the duration of the simulation: ");
				duration = scan.nextInt();
				if(numInBusses < 0 || numOutBusses < 0 || minGroupSize < 0 || maxGroupSize < 0 || capacity < 0 || arrivalProb < 0)
					throw new IllegalArgumentException("Cannot have negative values for simulation.\n");
				else if(minGroupSize>maxGroupSize)
					throw new IllegalArgumentException("Cannot have a bigger minGroupSize than maxGroupSize.\n");
				else if(arrivalProb<=0 || arrivalProb>=1)
					throw new IllegalArgumentException("Arrival probability cannot be less than/equal to 0 or equal to/over 1.\n");
				else {
					Simulator sim = new Simulator(numInBusses, numOutBusses, minGroupSize, maxGroupSize, capacity, arrivalProb);
					double[]groupsAndTime = sim.simulate(duration);
					double avgWait;
					if(groupsAndTime[0]==0)
						avgWait=0;
					else
						avgWait = groupsAndTime[1]/groupsAndTime[0];
					System.out.print("\n"+groupsAndTime[0]+" groups of passengers served. Average wait time is "
							+avgWait+" minutes.\n");
					System.out.print("\nPerform another simulation? (Y/N): ");
					String another = scan.next();
					if(another.equalsIgnoreCase(cont))
						continue;
					else
						break;
				}
			} catch(InputMismatchException ime) {
				System.out.print("\nPlease enter in the correct data types.\n\n");
				scan.next();
			} catch(IndexOutOfBoundsException ioob) {
				ioob.printStackTrace();
			} catch(IllegalArgumentException iae) {
				System.out.print("\n"+iae.toString()+"\n");
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		System.out.print("\nProgram terminating...");
	}
	
	/**
	 * Constructor for Simulator class. Takes in parameters to initialize class variables.
	 * @param numInBusses
	 * 		number of Inner route busses.
	 * @param numOutBusses
	 * 		number of Outer route busses.
	 * @param minGroupSize
	 * 		minimum number of people to be generated for a Passenger object.
	 * @param maxGroupSize
	 * 		maximum number of people to be generated for a Passenger object.
	 * @param capacity
	 * 		maximum number of PEOPLE the bus object can hold (not number of Passenger objects.)
	 * @param arrivalProb
	 * 		probability that a Passenger will arrive at a bus stop.
	 */
	public Simulator(int numInBusses,int numOutBusses,int minGroupSize,int maxGroupSize,int capacity,double arrivalProb) {
		this.numInBusses=numInBusses;
		this.numOutBusses=numOutBusses;
		this.minGroupSize=minGroupSize;
		this.maxGroupSize=maxGroupSize;
		this.capacity=capacity;
		this.arrivalProb=arrivalProb;
	}
	
	/**
	 * Method to run the actual simulation of a Bus system. In general, 
	 * 		The method first creates 8 bus stops all which are PassengerQueues. 
	 * 		Two destinations are created: inRoute and outRoute, both which are string arrays that hold the 
	 * 				bus stop names for each respective type of bus (inner/outer).
	 * 		The method then creates an array of Inner and Outer Busses based on user input. The first bus 
	 * 				created will always be operating as soon as the method is run where the next few Busses will be at rest at the terminal stop, South P. 
	 * 		After the amount of Busses are created and initialized, the method randomly generates Passengers and enqueues them
	 * 				to a bus stop based on the input of arrivalProbability.
	 * 		Then, the method runs the Busses. There will be three cases:
	 * 				A bus is resting at terminal stop,
	 * 				A bus is moving towards a stop,
	 * 				A bus arrives at a stop and dequeues the passengers that desire to get off at the current stop and enqueues the passengers at the bus stop.
	 * @param duration
	 * 		Time that the simulation will run.
	 * @return
	 * 		double array with the amount of groups that got on the bus and the sum of all the times that every Passenger waited for the bus.
	 * @throws IOException
	 * 		If error in function occurs.
	 */
	public double[] simulate(int duration) throws IOException {
		int totalTimeWaited = 0;
		int groupsServed = 0;
		int durationDummy = 1;
		PassengerQueue[]busStops = new PassengerQueue[8];
		for(int i = 0; i < busStops.length; i++) {
			busStops[i] = new PassengerQueue();
		}
		String inRoute[] = {"South P","West","SAC","Chapin"};
		String outRoute[] =  {"South P","PathMart","Walmart","Target"};
		int inIndex = 1;
		Bus[] inBus = new Bus[0];
		if(this.numInBusses>0) {
			inBus = new Bus[this.numInBusses];
			if(inBus.length>1) {
				inBus[0] = new Bus(capacity,"in",0,0,0);
				inBus[0].setOperating(true);
				for(int i = 1 ;i<inBus.length;i++) {
					inBus[i] = new Bus(capacity,"in",0,0, inBus[i-1].getTimeToRest()+30);
				}
			}
			else
				inBus[0] = new Bus(capacity,"in",0,0,0);
		}
		int outIndex = 1;
		Bus[] outBus = new Bus[0];
		if(this.numOutBusses>0) {
			outBus = new Bus[this.numOutBusses];
			if(outBus.length>1) {
				outBus[0] = new Bus(capacity,"out",0,0,0);
				outBus[0].setOperating(true);
				for(int i = 1 ;i<outBus.length;i++) {
					outBus[i] = new Bus(capacity,"in",0, 0, outBus[i-1].getTimeToRest()+30);
				}
			}
			else
				outBus[0] = new Bus(capacity,"out",0,0,0);
		}
		
		while(durationDummy<=duration) {
			System.out.print("\nMinute "+durationDummy+"\n");
			//generates amount of people for stop if arrives
			for(int i = 0; i<busStops.length; i++) {
				int numPeople, destination, timeArrived;
				if(arriveAtStop()) {
					if(i<4) {
						numPeople = randInt(minGroupSize, maxGroupSize);
						destination = randDestination();
						if(destination == i || destination < i) {
							if (destination<i)
								destination=0;
							else {
								while(destination==i)
									destination = randDestination();
							}
						}
						timeArrived = durationDummy;
					}
					else {
						int dummyI = i-4;
						numPeople = randInt(minGroupSize, maxGroupSize);
						destination = randDestination();
						if(destination == dummyI || destination < dummyI) {
							if(destination<dummyI)
								destination = 0;
							else {
								while(destination==dummyI)
									destination = randDestination();
							}
						}
						timeArrived = durationDummy;
					}
					Passenger p = new Passenger(numPeople, destination, timeArrived);
					busStops[i].enqueue(p);
					if(i<4 && numInBusses>0)
						System.out.print("A group of "+p.getGroupSize()+" passengers arrived at "
								+inRoute[i]+" heading to "+inRoute[p.getDestination()]+"\n");
					else if(i>=4 && numOutBusses>0)
						System.out.print("A group of "+p.getGroupSize()+" passengers arrived at "
								+outRoute[i-4]+" heading to "+outRoute[p.getDestination()]+"\n");
				}
			}
			
			boolean lastStop = false;
			if(numInBusses<=0) {
				System.out.print("There are no In Route Busses.\n");
			}
			else {
				for(int i=0;i<numInBusses;i++) {
					if(inBus[i].getTimeToRest()==0) {
						inBus[i].setOperating(true);
					}
					if(inBus[i].isOperating()) {
						
						if(inBus[i].getTimeToNext()==0) {
							int currentStop = inBus[i].getNextStop();
							if(inBus[i].getNextStop()==3) {
								inBus[i].setNextStop(0);
								inBus[i].setTimeToNext(19);
								inBus[i].setTimeToRest(30); 
							}
							else {
								inBus[i].setNextStop(inBus[i].getNextStop()+1);
								inBus[i].setTimeToNext(19);
							}
							int addedOn=0;
							int amountOfPassengers=0;
							int unload = inBus[i].unload(currentStop);
							if(!(busStops[currentStop].isEmpty())) {
								for(int j = 0; j< busStops[currentStop].sizeOfList() ;j++) {
									Passenger p = busStops[currentStop].get(j);
									if(inBus[i].canBeAdded(p)) {
										addedOn++;
										amountOfPassengers+=p.getGroupSize();
										totalTimeWaited += durationDummy - p.getTimeArrival();
										inBus[i].addToBus(p);
										busStops[currentStop].dequeue(p);
										j--;
									}
								}
								groupsServed+=addedOn;
								if(unload>0) {
									System.out.print("In Route Bus "+(i+1)+" arrives at "+inRoute[currentStop]
											+". Unloads "+unload+" passengers. Picks up "+amountOfPassengers+" passengers.\n");
								}
								else
									System.out.print("In Route Bus "+(i+1)+" arrives at "+inRoute[currentStop]
											+". Picks up "+amountOfPassengers+" passengers.\n");
							}
							else
								System.out.print("In Route Bus "+(i+1)+" arrives at "+inRoute[currentStop]
										+".\n");
						}
						else {
							System.out.print("In Route Bus "+(i+1)+" moving towards "+inRoute[inBus[i].getNextStop()]
									+". Arriving in "+inBus[i].getTimeToNext()+" minutes.\n");
							inBus[i].setTimeToNext((inBus[i].getTimeToNext()-1));
							if(inBus[i].getNextStop()==0 && inBus[i].getTimeToNext()==0) {
								inBus[i].setOperating(false);
							}
						}
					}
					else {
						inBus[i].setTimeToRest(inBus[i].getTimeToRest()-1);
						int peopleUnloaded;
						if(!(inBus[i].isEmpty())) {
							peopleUnloaded = inBus[i].unloadEveryone();
							System.out.print("In Route Bus "+(i+1)+" arrived at last stop South P. Unloaded "+peopleUnloaded
									+ " people. In Route Bus "+(i+1)+" is resting at "+inRoute[inBus[i].getNextStop()]+" for "
									+inBus[i].getTimeToRest()+" minutes.\n");
						}
						else {
							System.out.print("In Route Bus "+(i+1)+" is resting at "+inRoute[inBus[i].getNextStop()]+" for "
									+inBus[i].getTimeToRest()+" minutes.\n");
						}
					}
				}
			}
			if(numOutBusses<=0) {
				System.out.print("There are no Out Route Busses.\n");
			}
			else {
				for(int i=0;i<numOutBusses;i++) {
					if(outBus[i].getTimeToRest()==0) {
						outBus[i].setOperating(true);
					}
					if(outBus[i].isOperating()) {
						
						if(outBus[i].getTimeToNext()==0) {
							int currentStop = outBus[i].getNextStop();
							if(outBus[i].getNextStop()==3) {
								outBus[i].setNextStop(0);
								outBus[i].setTimeToNext(19);
								outBus[i].setTimeToRest(30); 
							}
							else {
								outBus[i].setNextStop(outBus[i].getNextStop()+1);
								outBus[i].setTimeToNext(19);
							}
							int addedOn=0;
							int amountOfPassengers=0;
							int unload = outBus[i].unload(currentStop);
							if(!(busStops[currentStop].isEmpty())) {
								for(int j = 0; j< busStops[currentStop].sizeOfList() ;j++) {
									Passenger p = busStops[currentStop].get(j);
									if(outBus[i].canBeAdded(p)) {
										addedOn++;
										amountOfPassengers+=p.getGroupSize();
										totalTimeWaited += durationDummy - p.getTimeArrival();
										outBus[i].addToBus(p);
										busStops[currentStop].dequeue(p);
										j--;
									}
								}
								groupsServed+=addedOn;
								if(unload>0) {
									System.out.print("Out Route Bus "+(i+1)+" arrives at "+outRoute[currentStop]
											+". Unloads "+unload+" passengers. Picks up "+amountOfPassengers+" passengers.\n");
								}
								else
									System.out.print("Out Route Bus "+(i+1)+" arrives at "+outRoute[currentStop]
											+". Picks up "+amountOfPassengers+" passengers.\n");
							}
							else
								System.out.print("Out Route Bus "+(i+1)+" arrives at "+outRoute[currentStop]
										+".\n");
						}
						else {
							System.out.print("Out Route Bus "+(i+1)+" moving towards "+outRoute[outBus[i].getNextStop()]
									+". Arriving in "+outBus[i].getTimeToNext()+" minutes.\n");
							outBus[i].setTimeToNext((outBus[i].getTimeToNext()-1));
							if(outBus[i].getNextStop()==0 && outBus[i].getTimeToNext()==0) {
								outBus[i].setOperating(false);
							}
						}
					}
					else {
						outBus[i].setTimeToRest(outBus[i].getTimeToRest()-1);
						int peopleUnloaded;
						if(!(outBus[i].isEmpty())) {
							peopleUnloaded = outBus[i].unloadEveryone();
							System.out.print("Out Route Bus "+(i+1)+" arrived at last stop South P. Unloaded "+peopleUnloaded
									+ " people. Out Route Bus "+(i+1)+" is resting at "+outRoute[outBus[i].getNextStop()]+" for "
									+outBus[i].getTimeToRest()+" minutes.\n");
						}
						else {
							System.out.print("Out Route Bus "+(i+1)+" is resting at "+outRoute[outBus[i].getNextStop()]+" for "
									+outBus[i].getTimeToRest()+" minutes.\n");
						}
					}
				}
			}
			String passengers="";
			for(int i=0;i<busStops.length;i++) {
				if(i<4 && numInBusses>0) {
					for(int j = 0; j<busStops[i].sizeOfList();j++) {
						Passenger p = busStops[i].get(j);
						passengers+= " ["+p.getGroupSize()+", "+p.getDestination()+" ("+inRoute[p.getDestination()]+"), "+p.getTimeArrival()+"] ";
					}
					System.out.print(i+" ("+inRoute[i]+"): "+passengers+"\n");
					passengers="";
				}
				else if(i>=4 && numOutBusses>0){
					for(int j = 0; j<busStops[i].sizeOfList();j++) {
						Passenger p = busStops[i].get(j);
						passengers+= " ["+p.getGroupSize()+", "+(p.getDestination()+4)+" ("+outRoute[p.getDestination()]+"), "+p.getTimeArrival()+"] ";
					}
					System.out.print(i+" ("+outRoute[i-4]+"): "+passengers+"\n");
					passengers="";
				}
				else {
					if(i<4) {
						System.out.print(i+" ("+inRoute[i]+"): \n");
					}
					else 
						System.out.print(i+" ("+outRoute[i-4]+"): \n");
				}
			}
			durationDummy++;
		}
		double[]groupsAndTime = {groupsServed, totalTimeWaited};
		return groupsAndTime;
	}
	
	/**
	 * Method that randomly generates a number. If the number is less than class var arrivalProb, 
	 * 		then the event that a Passenger arrives at a bus stop is true.
	 * @return
	 * 		true if Passenger has arrived at a bus stop
	 * 		false if the generated number is greater than the indicated arrivalProbability.
	 */
	public boolean arriveAtStop() {
		return (Math.random() <= arrivalProb);
	}
	
	/**
	 * Method that generates a random number in between the indicated min and max number of people.
	 * @param min
	 * 		Minimum number of people in a Passenger object.
	 * @param max
	 * 		Maximum number of people in a Passenger object.
	 * @return
	 * 		Randomly generated number of people in a Passenger object.
	 */
	public static int randInt(int min, int max) {
		return (int)(Math.random()*(max-min)+1) + min;
	}
	
	/**
	 * Method that generates a random number to be used as an index for the inRoute and outRoute
	 * 		String arrays in simulate().
	 * @return
	 * 		Randomly generated number to be used as an index in String arrays.
	 */
	public static int randDestination() {
		return (int)(Math.random()*(4));
	}
	
}
