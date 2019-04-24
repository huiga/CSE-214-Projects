/**
 * Eddie Xu
 * ID: 112206686
 * Email: eddie.xu@stonybrook.edu
 * Homework 1
 * CSE214
 * Recitation 6: Juan Tarquino
 */

 /**
  * Special exception class designated to prompt user that the CardCollection is full. (MAX: 100).
  * @author Eddie Xu
  *
  */
public class FullCollectionException extends Exception {
	public FullCollectionException(String errorMsg) {
		super(errorMsg);
	}
}
