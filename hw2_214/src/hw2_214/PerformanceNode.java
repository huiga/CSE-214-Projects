/**
 * Eddie Xu
 * ID: 112206686
 * Email: eddie.xu@stonybrook.edu
 * Homework 2
 * CSE214
 * Recitation 6: Juan Tarquino
 */

package hw2_214;

/**
 * PerformanceNode class that holds datafields of the names, performers, participants, duration of performance,
 * and start time of each performance in a Node. Contains pointers to each node before and after,
 * indicating implementation to a doubly linked list.
 * 
 * @author Eddie Xu 112206686
 *
 */
public class PerformanceNode {
	private PerformanceNode nextPtr, prevPtr;
	private String performanceName;
	private String performerName;
	private int totalParticipants;
	private int duration;
	private int startTime;
	
	/**
	 * Constructor that instantiates a new PerformanceNode and the pointers to be null values only.
	 */
	public PerformanceNode() {
		nextPtr=null;
		prevPtr=null;
	}
	
	/**
	 * Constructor that instantiates a new PerformanceNode and all the datafields. 
	 * 
	 * @param performanceName
	 * 		String representation of the name of the performance.
	 * @param performerName
	 * 		String representation of the name of the performer.
	 * @param totalParticipants
	 * 		int representation of the amount of attendees. 
	 * @param duration
	 * 		int representation of the length of the performance in minutes. 
	 */
	public PerformanceNode(String performanceName, String performerName, int totalParticipants, int duration) {
		this.performanceName=performanceName;
		this.performerName = performerName;
		this.totalParticipants = totalParticipants;
		this.duration = duration;
		nextPtr = null;
		prevPtr = null;
	}
	
	/**
	 * Sets the name of the datafield performanceName.
	 * 
	 * @param performanceName
	 * 		String representation of the name of the performance.
	 */
	public void setPerformance(String performanceName) {
		this.performanceName = performanceName;
	}
	
	/**
	 * Gets the name of the performance.
	 * 
	 * @return
	 * 		Datafield performanceName: String representation of the performance name.
	 */
	public String getPerformance() {
		return performanceName;
	}
	
	/**
	 * Sets the name of the datafield performerName.
	 * 
	 * @param performerName
	 * 		String representation of the name of the performer.
	 */
	public void setPerformer(String performerName) {
		this.performerName = performerName;
	}
	
	/**
	 * Gets the name of the performer.
	 * 
	 * @return
	 * 		Datafield performerName: String representation of the performer name.
	 */	
	public String getPerformer() {
		return performerName;
	}
	
	/**
	 * Sets the value of the datafield participants.
	 * 
	 * @param participants
	 * 		int representation of the amount of attendees.
	 */
	public void setParticipants(int participants) {
		this.totalParticipants = participants;
	}
	
	/**
	 * Gets the amount of participants.
	 * 
	 * @return
	 * 		Datafield totalParticipants: int representation of the amount of attendees.
	 */
	public int getParticipants() {
		return totalParticipants;
	}
	
	/**
	 * Sets the value of the datafield duration.
	 * 
	 * @param duration
	 * 		int representation of the time of the performance in minutes.
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	/**
	 * Gets the time of the performance.
	 * 
	 * @return
	 * 		Datafield duration: int representation of the time of the performance in minutes.
	 */
	public int getDuration() {
		return duration;
	}
	
	/**
	 * Sets the next pointer datafield, nextPtr, to the desired node.
	 * 
	 * @param node
	 * 		PerformanceNode object for the pointer to link to.
	 */
	public void setNext(PerformanceNode node) {
		nextPtr = node;
	}
	
	/**
	 * Sets the previous pointer datafield, prevPtr, to the desired node.
	 * 
	 * @param node
	 * 		PerformanceNode object for the pointer to link to.
	 */
	public void setPrev(PerformanceNode node) {
		prevPtr = node;
	}
	
	/**
	 * Gets the referene of the nextPtr datafield.
	 * 
	 * @return
	 * 		Datafield nextPtr: the reference of the node that nextPtr is pointing to.
	 */
	public PerformanceNode getNext() {
		return nextPtr;
	}
	
	/**
	 * Gets the referene of the prevPtr datafield.
	 * 
	 * @return
	 * 		Datafield prevPtr: the reference of the node that prevPtr is pointing to.
	 */
	public PerformanceNode getPrev() {
		return prevPtr;
	}
	
	/**
	 * Gets the value of the start time of each performance.
	 * 
	 * @return
	 * 		Datafield startTime: the starting time of each performance in minutes.
	 */
	public int getStartTime() {
		return startTime;
	}
	
	/**
	 * Sets the value of datafield startTime to the desired starting time.
	 * 
	 * @param startTime
	 * 		Desired time for the performance to start at. 
	 */
	public void setStartTime(int startTime) {
		this.startTime=startTime;
	}
	
	/**
	 * Formats a string header for the data of a Node.
	 * 
	 * @return
	 * 		String representation of the headers.
	 */
	public static String formatter() {
		String head = "Current No.  Performance Name           Lead Performer Name       Participants  Duration  Start Time";
		String head2= "----------------------------------------------------------------------------------------------------";
		return(head+"\n"+head2);
	}

	/**
	 * toString method that formats the data of the a node.
	 * 
	 * @return
	 * 		String representing the formatted data the node the method was called on.
	 */
	public String toString() { 
		String print="";
			print+=String.format("%-27s%-26s%-14d%-10d%-10d\n",performanceName,performerName,totalParticipants,duration,startTime);
		return print;
	}
}
