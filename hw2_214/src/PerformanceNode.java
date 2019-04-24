/**
 * Eddie Xu
 * ID: 112206686
 * Email: eddie.xu@stonybrook.edu
 * Homework 2
 * CSE214
 * Recitation 6: Juan Tarquino
 */

package hw2_214;

public class PerformanceNode {
	private PerformanceNode nextPtr, prevPtr;
	private String performanceName;
	private String performerName;
	private int totalParticipants;
	private int duration;
	private int startTime;
	
	public PerformanceNode() {
		nextPtr=null;
		prevPtr=null;
	}
	
	public PerformanceNode(String performanceName, String performerName, int totalParticipants, int duration) {
		this.performanceName=performanceName;
		this.performerName = performerName;
		this.totalParticipants = totalParticipants;
		this.duration = duration;
		nextPtr = null;
		prevPtr = null;
	}
	
	public void setPerformance(String performanceName) {
		this.performanceName = performanceName;
	}
	
	public String getPerformance() {
		return performanceName;
	}
	public void setPerformer(String performerName) {
		this.performerName = performerName;
	}
	
	public String getPerformer() {
		return performerName;
	}
	
	public void setParticipants(int participants) {
		this.totalParticipants = participants;
	}
	
	public int getParticipants() {
		return totalParticipants;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public void setNext(PerformanceNode node) {
		nextPtr = node;
	}
	
	public void setPrev(PerformanceNode node) {
		prevPtr = node;
	}
	
	public PerformanceNode getNext() {
		return nextPtr;
	}
	
	public PerformanceNode getPrev() {
		return prevPtr;
	}
	
	public int getStartTime() {
		return startTime;
	}
	
	public void setStartTime(int startTime) {
		this.startTime=startTime;
	}
	
	public static String formatter() {
		String head = "Current No.  Performance Name           Lead Performer Name       Participants  Duration  Start Time";
		String head2= "----------------------------------------------------------------------------------------------------";
		return(head+"\n"+head2);
	}

	public String toString() { 
		String print="";
			print+=String.format("%-27s%-26s%-14d%-10d%-10d\n",performanceName,performerName,totalParticipants,duration,startTime);
		return print;
	}
}
