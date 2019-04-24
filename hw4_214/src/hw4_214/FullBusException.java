/**
 * Eddie Xu
 * ID: 112206686
 * Email: eddie.xu@stonybrook.edu
 * Homework 4
 * CSE214
 * Recitation 6: Juan Tarquino
 */

 /**
  * Special exception class designated to prompt user that the CardCollection is full. (MAX: 100).
  * @author Eddie Xu
  *
  */
package hw4_214;
public class FullBusException extends Exception {
	public FullBusException(String errorMsg) {
		super(errorMsg);
	}
}
