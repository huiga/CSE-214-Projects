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
 * Comparator class. Compares two City objects based on Latitude location.
 * @author eddie
 *
 */
public class LatComparator implements Comparator<City>{

	/**
	 * compare method. Compares two City objects based on Latitude location.
	 * @return
	 * 		1 if the first city is greater in Latitude location,
	 * 		0 if their latitude's are the same
	 * 		-1 if the second city has a larger Latitude location.
	 */
	public int compare(City o1, City o2) {
		double c1 = o1.getLocation().getLat();
		double c2 = o2.getLocation().getLat();
		if(c1>c2)
			return 1;
		else if(c1<c2)
			return -1;
		else
			return 0;
	}
}
