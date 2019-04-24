/**
 * Eddie Xu
 * ID: 112206686
 * Email: eddie.xu@stonybrook.edu
 * Homework 2
 * CSE214
 * Recitation 6: Juan Tarquino
 */

package hw2_214;

import java.io.IOException;

/**
 * PerformanceList class. Contains data fields head, tail, and cursor which reference the respective
 * position in the doubly linked list.
 * @author Eddie Xu
 *
 */
public class PerformanceList {
	protected PerformanceNode head;
	protected PerformanceNode tail;
	protected PerformanceNode cursor;
	private String cursorIndicator = "->";

	/**
	 * Constructor that creates a new PerformanceList. Sets data fields to null (instantiates a 
	 * new list with no elements).
	 */
	public PerformanceList() {
		head=null;
		tail=null;
		cursor=null;
	}
	
	/**
	 * Sets the head of the list to a the passed PerformanceNode.
	 * 
	 * @param node
	 * 		PerformanceNode object to set head of the list to.
	 */
	public void setHead(PerformanceNode node) {
		head=node;
	}
	
	/**
	 * Appends a PerformanceNode object to the end of the current list. If list is empty,
	 * instantiates a new list using the class constructor and adds node as new head.
	 * 
	 * @param newPerformance
	 * 		PerformanceNode to add to the end of the current list.
	 */
	public void addToEnd(PerformanceNode newPerformance) { 
		if(head==null&&tail==null&&cursor==null) {
			head=newPerformance;
			tail=newPerformance;
			cursor=newPerformance; 
			calculateStart();
			head.setPrev(null);
			tail.setNext(null);
			
		}
		else {
			PerformanceNode nodePtr = head;
			while(nodePtr.getNext()!=null) {
				nodePtr = nodePtr.getNext();
			}
			cursor=nodePtr;
			newPerformance.setPrev(cursor);
			cursor.setNext(newPerformance);
			tail=cursor.getNext();
			calculateStart();
			tail.setNext(null);
			cursor = cursor.getNext();
		}
	}
	
	/**
	 * Adds PerformanceNode after where the cursor is currently pointing. 
	 * 
	 * @param newPerformance
	 * 		PerformanceNode to add after the cursor.
	 */
	public void addAfterCurrent(PerformanceNode newPerformance) {
		if((head==null && tail==null && cursor==null)||(cursor.getNext()==null)) {
			addToEnd(newPerformance);
		}
		else {
			newPerformance.setNext(cursor.getNext());
			newPerformance.setPrev(cursor);
			cursor.setNext(newPerformance);
			cursor.getNext().getNext().setPrev(newPerformance);
			calculateStart();
		}
	}
	public boolean removeCurrentNode() {
		boolean onlyNode=false;
		if(head.getNext()==null&&head.getPrev()==null) {
			onlyNode=true;
		}
		if(onlyNode) {
			calculateStart();
			head=tail=cursor=null;
			return true;
		}
		else if(cursor==head) {
			cursor = cursor.getNext();
			head=cursor;
			cursor.getPrev().setNext(null);
			cursor.setPrev(null);
			calculateStart();
			return true;
		}
		else if(cursor==tail) {
			cursor = cursor.getPrev();
			tail=null;
			cursor.setNext(tail);
			tail = cursor;
			calculateStart();
			return true;
		}
		else if(head==null&&tail==null&&cursor==null) {
			return false;
		}
		else {
			PerformanceNode tempCursor = cursor;
			cursor=cursor.getNext();
			tempCursor.getPrev().setNext(cursor);
			cursor.setPrev(tempCursor.getPrev());
			tempCursor.setPrev(null);
			tempCursor.setNext(null);
			calculateStart();
			return true;
		}
	}
	
	/**
	 * Gets the data of the current node that the cursor is pointing at. Prints out a neatly
	 * formatted interface of current node data.
	 * 
	 */
	public void displayCurrentPerformance() {
		PerformanceNode nodePtr = head;
		int position = cursorPosition();
		int nodePtrPos=1;
		while(nodePtrPos!=position) {
			nodePtr=nodePtr.getNext();
			nodePtrPos++;
		}
		System.out.print("\n"+PerformanceNode.formatter()+"\n");
		System.out.print(String.format("%7s%4d",cursorIndicator,position));
		System.out.print("  "+nodePtr.toString());
	}
	
	/**
	 * Method to find the current position of the cursor in the linked list.
	 * 
	 * @return
	 * @throws NullPointerException
	 * 		If the current list is empty, throws an exception indicating so.
	 */
	public int cursorPosition() throws NullPointerException {
		PerformanceNode nodePtr = head;
		if(nodePtr==null&&tail==null&&cursor==null) 
			throw new NullPointerException("The list is empty.");
		else {
			int position = 1;
			while(nodePtr!=cursor) {
				position++;
				nodePtr = nodePtr.getNext();
			}
			return position;
		}
	}
	
	/**
	 * Moves cursor forward one position.
	 * 
	 * @return
	 * 		boolean value representing whether the cursor was able to be moved forward or not. 
	 * 		True if move, false if unable.
	 * @throws IOException
	 * 		If the list is empty (cursor references a null), prompts user that there is no node.
	 * 		and that cursor cannot be moved forward anymore.
	 */
	public boolean moveCursorForward() throws IOException {
		if(cursor==null)
			throw new IOException("There is no current node, cannot move cursor forward.");
		else if(cursor.getNext()!=null) {
			cursor = cursor.getNext();
			return true;
		}
		if(cursor.getNext()==null) {
			tail=cursor;
		}
		return false;
	}
	
	/**
	 * Moves cursor backwards one position.
	 * 
	 * @return
	 * 		boolean value representing whether the cursor was able to be moved backwards or not. 
	 * 		True if move, false if unable.
	 * @throws IOException
	 * 		If the list is empty (cursor references a null), prompts user that there is no node.
	 * 		and that cursor cannot be moved backwards anymore.
	 */
	public boolean moveCursorBackwards() throws IOException{
		if(cursor==null)
			throw new IOException("There is no current node, cannot move cursor backwards.");
		else if(cursor.getPrev()!=null) {
			cursor = cursor.getPrev();
			return true;
		}
		if(cursor.getPrev()==null) {
			head=cursor;
		}
		return false;
	}
	
	/**
	 * Moves cursor to a given position.	
	 * 
	 * @param position
	 * 		Position in the list to have cursor be moved to.
	 * @return
	 * 		boolean value representing whether the cursor was able to be moved to given position
	 * 		or not.
	 * 		True if move, false if unable.
	 */
	public boolean jumpToPosition(int position) {
		if((head==null&&tail==null&&cursor==null) || position<0 || position > getSize())
			return false;
		else{
			int currentCursorPos = cursorPosition();
			PerformanceNode nodePtr = cursor;
			if(position<currentCursorPos) {
				while(nodePtr.getPrev()!=null) { 
						currentCursorPos--;
						nodePtr = nodePtr.getPrev();
						if(currentCursorPos==position){
							cursor = nodePtr;
							return true;
						}
					}
				}
			if(position > currentCursorPos) {
				while(nodePtr.getNext()!=null) {
					currentCursorPos++;
					nodePtr = nodePtr.getNext();
					if(currentCursorPos==position) {
						cursor=nodePtr;
						return true;
					}
				}
			}
			return true;
		}
	}
	
	/**
	 * Gets the size of the entire linked list.
	 * 
	 * @return
	 * 		int value representing how large the list is.
	 */
	public int getSize() {
		PerformanceNode nodePtr = head;
		int size=0;
		if(head==null && tail == null && cursor == null)
			return size;
		else {
			do {
				size++;
				nodePtr = nodePtr.getNext();
			} while(nodePtr!=null);
			return size;
		}
	}
	
	/**
	 * Calculates AND sets the start times for every performance node in the linked list.
	 * 
	 * @throws NullPointerException
	 * 		If the list is currently empty, will indicate that it is unable to set a time
	 * 		for a null node.
	 */
	public void calculateStart() throws NullPointerException {
		if(head==null&&cursor==null&tail==null) {
			throw new NullPointerException("Cannot set a time for a null node.");
		}
		PerformanceNode nodePtr = head;
		int startTime = 0;
		do {
			nodePtr.setStartTime(startTime);
			startTime+=nodePtr.getDuration();
			nodePtr=nodePtr.getNext();
		} while(nodePtr!=tail.getNext());

	}
	
	/**
	 * toString method that formats the data of all the nodes in the PerformanceList (doubly linked list)
	 * 
	 * @return
	 * 		String representing the formatted data of all nodes.
	 */
	public String toString() {
		try {
			String print = "";
			int size=0;
			PerformanceNode nodePtr = head;
			do {
				if(nodePtr==cursor) {
					size++;
					print+=(String.format("%7s%4d  %-27s%-26s%-14d%-10d%-10d\n",cursorIndicator,size,nodePtr.getPerformance(),nodePtr.getPerformer(),nodePtr.getParticipants(),nodePtr.getDuration(),nodePtr.getStartTime()));
					nodePtr = nodePtr.getNext();
				}
				else {
					size++;
					print+=String.format("%11d  %-27s%-26s%-14d%-10d%-10d\n",size,nodePtr.getPerformance(),nodePtr.getPerformer(),nodePtr.getParticipants(),nodePtr.getDuration(),nodePtr.getStartTime());
					nodePtr = nodePtr.getNext();
				}
			} while(nodePtr!=null);
			return "\nSchedule:\n\n"+PerformanceNode.formatter()+"\n"+print;
		}catch(NullPointerException nee) {
			return ("\nCannot print from a null reference.\n");
		}
	}
}
