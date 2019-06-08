/**
 * Eddie Xu
 * ID: 112206686
 * Email: eddie.xu@stonybrook.edu
 * Homework 7
 * CSE214
 * Recitation 6: Juan Tarquino
 */
package hw7_214;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import com.cse214.geocoder.GeocodeResponse;
import com.cse214.geocoder.Geocoder;

import latlng.LatLng;


/**
 * Model class of an Airline, SigmaAir. Holds connections between cities through an 
 * implementation of a 2D matrix directed graph. Each City is held as a vertex and the weights
 * are the distance between each City object, given a connection between the Cities
 * exist.
 * cities : ArrayList of City objects. The order that the Cities are held in the
 * 		arraylist is how they're ordered (row & col) in the 2D matrix.
 * MAX_CITIES : max amount of cities allowed. (100).
 * connections : 2D matrix used to implement the directed graph. Each connection distance
 * 		is in kilometers (km).  The rows and columns are implemented using the order of 
 * 		Cities in the class var, cities. (Ex: if the arraylist had cities New York, Beijing,
 * 		and Paris, the 2D matrix would be: 
 * 			
 * 			New York     Beijing      Paris
 * New York[		]	[		]	[		]
 * 
 * Beijing [		]	[		]	[		]
 *  
 * Paris   [		]	[		]	[		]
 * 
 * @author eddie
 *
 */
public class SigmaAir implements Serializable{
	private ArrayList<City> cities;
	public static final int MAX_CITIES = 100;
	private double[][] connections;
	
	/**
	 * Default constructor to create a new SigmaAir object. Instantiates the
	 * ArrayList and the 2D array connections. When the 2D array is initialized,
	 * every index is filled with Double.POSITIVE_INFINITY. If the row and col 
	 * index are equal, the spot is filled with a 0 (distance of a connection 
	 * between a city and itself would be 0).
	 */
	public SigmaAir() {
		cities = new ArrayList<City>();
		connections = new double[MAX_CITIES][MAX_CITIES];
		for(int i = 0; i<MAX_CITIES; i++) {
			for(int j = 0; j<MAX_CITIES; j++) {
				if(j==i) {
					connections[i][j] = 0;
				}
				else {
					connections[i][j] = Double.POSITIVE_INFINITY;
				}
			}
		}
	}
	
	/**
	 * Adds a new City to the class var cities.
	 * @param city
	 * 		City name to be added.
	 */
	public void addCity(String city) {
		try {
			City c = makeCity(city);
			if(cities.contains(c))
				System.out.print("City "+city+" already exists.");
			else {
				cities.add(c);
				System.out.println(city+" has been added: ("+c.getLocation().getLat()
						+","+c.getLocation().getLng()+")");
			}
		} catch(Exception e) {
			System.out.println(e.toString());
		}
	}
	
	/**
	 * Adds a connection between two cities in the class var connections.
	 * @param cityFrom
	 * 		Source city (city to depart at)
	 * @param cityTo
	 * 		Destination city (city to go to)
	 * @throws IOException
	 * 		If one of the cities (cityFrom/cityTo) are not currently recorded
	 * 		in the class var cities.
	 */
	public void addConnection(String cityFrom, String cityTo) throws IOException {
		int from = findCity(cityFrom);
		int to = findCity(cityTo);
		if(from != -1 && to != -1) {
			connections[from][to]=LatLng.calculateDistance(cities.get(from).getLocation(),
					cities.get(to).getLocation());
			System.out.print("\n"+cityFrom+" --> "+cityTo+" added: " 
					+LatLng.calculateDistance(cities.get(from).getLocation(),
							cities.get(to).getLocation()));
		}
		else {
			throw new IOException("\nNo information for one of the inputted cities. Cannot add connection.\n");
		}
	}
	
	/**
	 * Returns the index of a city in the class var citites.
	 * @param city
	 * 		City to find.
	 * @return
	 * 		index if the city is found, -1 otherwise.
	 */
	public int findCity(String city) {
		for(int i = 0; i<cities.size(); i++) {
			if(cities.get(i).getCity().equals(city))
				return i;
		}
		return -1;
	}
	
	/**
	 * Removes a connection between to cities in the 2D array. If both cities 
	 * are found, the respective elements in the array are set back to 
	 * Double.POSITIVE_INFINITY.
	 * @param cityFrom
	 * 		Source city (city to depart at).
	 * @param cityTo
	 * 		Destination city (city to go to).
	 * @throws IOException
	 * 		If one of the cities (cityFrom/cityTo) are not currently recorded
	 * 		in the class var cities.
	 */
	public void removeConnection(String cityFrom, String cityTo) throws IOException {
		int from = findCity(cityFrom);
		int to = findCity(cityTo);
		if(from != -1 && to != -1) 
			connections[from][to]=Double.POSITIVE_INFINITY;
		else 
			throw new IOException("\nNo information for one of the inputted cities. Cannot remove connection.\n");
	}
	
	/**
	 * Finds the shortest path in the directed graph between the two cities using the
	 * Floyd-Warshall algorithm.
	 * @param cityFrom
	 * 		Source city (city to depart at).
	 * @param cityTo
	 * 		Destination city (city to go to).
	 * @return
	 * 		String representing the shortest path taken to get to the destination city.
	 */
	public String shortestPath(String cityFrom, String cityTo) {
		int from = findCity(cityFrom);
		int to = findCity(cityTo);
		double [][]dist = new double[MAX_CITIES][MAX_CITIES];
		for (int i = 0; i < dist.length; i++) {
			for (int o = 0; o < dist.length; o++) {
				dist[i][o] = connections[i][o];
			}
		}
		City [][]next = new City[MAX_CITIES][MAX_CITIES];
		for(int u = 0;u<cities.size();u++) {
			for(int v = 0; v<cities.size();v++) {
				next[u][v] = cities.get(v);
			}
		}
		if(( from != -1 && to != -1)) {
			for(int k = 0; k<cities.size();k++) {
				for(int i = 0; i<cities.size();i++) {
					for(int j = 0; j<cities.size();j++) {
						if(dist[i][k] + dist[k][j] < dist[i][j]) {
							dist[i][j] = dist[i][k] + dist[k][j];
							next[i][j] = next[i][k];
						}
					}
				}
			}
		int path_distance = 0;
		if(dist[from][to] == Double.POSITIVE_INFINITY) {
			return "\nShortest path from "+cityFrom+" to "+cityTo+" does not exist!\n";
		}
		String path = cityFrom;
		while (from != to) {
			path_distance += LatLng.calculateDistance(cities.get(from).getLocation(), 
				cities.get(to).getLocation());
			from = findCity(next[from][to].getCity());
			path+=" --> "+cities.get(from).getCity();
		}
		return path+" : "+ path_distance;
		}
		else
			return "\nShortest path from "+cityFrom+" to "+cityTo+" does not exist!\n";
	}
	
	
	/**
	 * Neatly formats and prints all cities based on the comparator given. Can either 
	 * have cities sorted by name, latitude, or longitude. 
	 * @param comp
	 * 		Comparator object with respective desired sorting/comparison.
	 */
	public void printAllCities(Comparator<City> comp) {
		ArrayList<City> dummy = cities;
		Collections.sort(dummy, comp);
		System.out.println(String.format("%-28s%-16s%-17s", "City Name","Latitude","Longitude")
				+"\n-------------------------------------------------------------");
		for(City c : dummy)
			System.out.println(c);
	}

	/**
	 * Neatly formats and prints all connections in the class var connections.
	 */
	public void printAllConnections() {
		 System.out.print(String.format("\n%-36s%-22s","Route","Distance")
				 +"\n----------------------------------------------------------\n");
		 for(int i = 0; i<cities.size();i++) {
			 for(int j = 0; j<cities.size();j++) {
				 if(connections[i][j] != 0 && connections[i][j] != Double.POSITIVE_INFINITY) {
					 String toPrint = cities.get(i).getCity()+" --> "+cities.get(j).getCity();
					 System.out.println(String.format("%-36s%-22f", toPrint, connections[i][j]));
				 }
			 }
		 }
		 System.out.print("\n");
	}
	
	/**
	 * Creates a city object based on the city name given.
	 * @param cityName
	 * 		Name of the city to create.
	 * @return
	 * 		City object of the given city name
	 * @throws Exception
	 * 		IF the given city does not exist/is not found in the imported package of the 
	 * 		Geocoder.
	 */
	public City makeCity(String cityName) throws Exception {
	    try {
			Geocoder geocoder = new Geocoder();
		    GeocodeResponse geocodeResponse = geocoder.geocode(cityName);
		    String addr = geocodeResponse.getFormattedAddress();
		    double lat = geocodeResponse.getLat();
		    double lng = geocodeResponse.getLng();
		    LatLng ltln = new LatLng(lat,lng);
		    City c = new City(cityName, ltln);
		    c.setIndexPos(c.getCityCount());
		    return c;
	    } catch(Exception e) {
	    	throw new Exception("\nError in locating city "+cityName+" in Geocoder.\n");
	    }
	}
	
	/**
	 * Parses a .txt file of City names (with each name on a new line) and creates and adds 
	 * a new City object from the parsed name. 
	 * @param filename
	 * 		Name of .txt file to parse.
	 * @throws FileNotFoundException
	 * 		If the file does not exist.
	 */
	public void loadAllCities(String filename) throws FileNotFoundException {
		try {
			File f = new File(filename);
			Scanner scan = new Scanner(f);
			System.out.println("");
			while(scan.hasNextLine()) {
				addCity(scan.nextLine());
			}
		}catch(FileNotFoundException fnf) {
			throw new FileNotFoundException("\nFile "+filename+" not found.\n ");
		}
	}
	
	/**
	 * Parses a .txt file of connections between two cities separated by a comma (Ex: New York,Beijing)
	 * (with each connection on a new line) and creates and adds tbe connection to the class var.
	 * @param filename
	 * 		Name of .txt file to parse.
	 * @throws FileNotFoundException
	 * 		If the file does not exist.
	 */
	public void loadAllConnections(String filename) throws FileNotFoundException {
		File f = new File(filename);
		if(f.exists()) {
			Scanner scan = new Scanner(f);
			while(scan.hasNextLine()) {
				boolean srcExists = false, destExists = false;
				String[] connection = scan.nextLine().split(",");
				for(City c : cities) {
					if(!srcExists)
						if(c.getCity().equals(connection[0])){
							srcExists = true;
						}
					if(!destExists)
						if(c.getCity().equals(connection[1])) {
							destExists = true;
						}
					if(srcExists && destExists)
						break;
				}
				if(srcExists && destExists) {
					try {
						addConnection(connection[0],connection[1]);

					} catch (IOException ioe) {
						System.out.println(ioe.toString());
					}
				}
				else
					System.out.print("\nError in adding connection: "+connection[0]+" --> "+connection[1]);
			}
			System.out.println("\n");
		}
		else
			throw new FileNotFoundException("\nFile "+filename+" not found.\n ");
	}
}