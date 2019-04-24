/**
 * Eddie Xu
 * ID: 112206686
 * Email: eddie.xu@stonybrook.edu
 * Homework 5
 * CSE214
 * Recitation 6: Juan Tarquino
 */
package hw5_214;

/**
 * Enumeration class to indicate what type of link the accessed '.html' file is.
 * ACTIVE: The accessed link 'works' (accessed .html file exists and has elements to read).
 * CIRCULAR: The accessed link accessed in the file exists as an ancestor to the current node's position.
 * DEAD: The accessed link does not exist (file not found).
 * @author eddie
 *
 */
public enum LinkType {
	ACTIVE,
	CIRCULAR,
	DEAD;
}
