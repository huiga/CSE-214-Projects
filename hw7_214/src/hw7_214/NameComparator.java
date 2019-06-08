/**
 * Eddie Xu
 * ID: 112206686
 * Email: eddie.xu@stonybrook.edu
 * Homework 7
 * CSE214
 * Recitation 6: Juan Tarquino
 */
package hw7_214;

import java.util.Comparator;

/**
 * Comparator class. Compares two City objects alphabetically based on name.
 * @author eddie
 *
 */
public class NameComparator implements Comparator<City> {

	/**
	 * compare method. Compares two City objects based on name.
	 * @return
	 * 		1 if the first city is greater alphabetically,
	 * 		0 if their latitude's are the same
	 * 		-1 if the second city is greater alphabetically.
	 */
	public int compare(City o1, City o2) {
		City c1 = o1;
		City c2 = o2;
		return (c1.getCity().compareTo(c2.getCity()));
	}

}
