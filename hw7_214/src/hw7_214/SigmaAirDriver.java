/**
 * Eddie Xu
 * ID: 112206686
 * Email: eddie.xu@stonybrook.edu
 * Homework 7
 * CSE214
 * Recitation 6: Juan Tarquino
 */
package hw7_214;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 * Main class to simulate an airport with display of Cities and connections between 
 * cities and their respective distances. Implemented using a graph to show connections.
 * 
 * @author eddie
 *
 */
public class SigmaAirDriver {
	public static void main(String[]args) throws IOException {
		File sigmaAir = new File("sigma_air.obj");
		SigmaAir airport = new SigmaAir();
		if(sigmaAir.exists()) {
			FileInputStream file = new FileInputStream("sigma_air.obj");
			ObjectInputStream inStream = new ObjectInputStream(file);
			try {
				airport=(SigmaAir)inStream.readObject();
				System.out.println("Successfully loaded contents if sigma_air.obj.\n");
			}catch(EOFException eof) {
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		else {
			System.out.println("sigma_air.obj is not found. New SigmaAir object will be created.\n");
			FileOutputStream file = new FileOutputStream("sigma_air.obj");
			ObjectOutputStream outStream = new ObjectOutputStream(file);
		}
		String y = "y";
		Scanner scan = new Scanner(System.in);
		while(y.equalsIgnoreCase("y")) {
			System.out.print("(A) Add City\n(B) Add Connection\n(C) Load all Cities\n(D) Load all Connections"
					+ "\n(E) Print all Cities\n(F) Print all Connections\n(G) Remove Connection"
					+ "\n(H) Find Shortest Path\n(Q) Quit\n");
			System.out.print("\nEnter a selection: ");
			try {
				switch(scan.next().toLowerCase()) {
				case "a":
					System.out.print("\nEnter the name of the city: ");
					scan.nextLine();
					String city = scan.nextLine();
					airport.addCity(city);
					System.out.print("\n");
					break;
				case "b":
					System.out.print("\nEnter source city: ");
					scan.nextLine();
					String srcCity = scan.nextLine();
					System.out.print("Enter destination city: ");
					String destCity = scan.nextLine();
					airport.addConnection(srcCity, destCity);
					System.out.print("\n\n");
					break;
				case "c":
					System.out.print("\nEnter the file name: ");
					String fileName = scan.next();
					airport.loadAllCities(fileName);
					System.out.println("");
					break;
				case "d":
					System.out.print("\nEnter the file name: ");
					fileName = scan.next();
					airport.loadAllConnections(fileName);
					break;
				case "e":
					boolean inE = true;
					while(inE) {
						System.out.println("\n(EA) Sort Cities by Name\n(EB) Sort Cities by Latitude"
								+ "\n(EC) Sort Cities by Longitude\n(Q) Quit\n");
						System.out.print("Enter a selection: ");
						String input = scan.next();
						try {
							switch(input.toLowerCase()) {
							case "ea":
								System.out.print("\nCities:\n");
								NameComparator nc = new NameComparator();
								airport.printAllCities(nc);
								System.out.println("");
								break;
							case "eb":
								System.out.print("\nCities:\n");
								LatComparator latComp = new LatComparator();
								airport.printAllCities(latComp);
								System.out.println("");
								break;
							case "ec":
								System.out.print("\nCities:\n");
								LngComparator lngComp = new LngComparator();
								airport.printAllCities(lngComp);
								System.out.println("");
								break;
							case "q":
								System.out.println("");
								inE = false;
								break;
							default: 
								System.out.println("\nPlease enter a choice on the menu.");
							}
						}catch(Exception e) {
							e.printStackTrace();
						}
					}
					break;
				case "f":
					airport.printAllConnections();
					break;
				case "g":
					System.out.print("\nEnter source city: ");
					scan.nextLine();
					srcCity = scan.nextLine();
					System.out.print("Enter destination city: ");
					destCity = scan.nextLine();
					try {
						airport.removeConnection(srcCity, destCity);
						System.out.print("\nConnection from "+srcCity+" to "+destCity+" has been removed!\n\n");
					}catch(IOException ioe) {
						System.out.print(ioe.toString());
					}
					break;
				case "h":
					System.out.print("\nEnter source city: ");
					scan.nextLine();
					srcCity = scan.nextLine();
					System.out.print("Enter destination city: ");
					destCity = scan.nextLine();
					System.out.print("\n"+airport.shortestPath(srcCity, destCity)+"\n\n");
					break;
				case "q":
					FileOutputStream file = new FileOutputStream("sigma_air.obj");
					ObjectOutputStream fout = new ObjectOutputStream(file);
					fout.writeObject(airport);
					fout.close();
					y = "q";
					System.out.println("\nSigmaAir object saved into file sigma_air.obj.");
					System.out.println("\nProgram terminating normally...");
					break;
				default: 
					System.out.println("\nPlease enter a choice on the menu.\n");
				}
			} catch (NullPointerException npe) {
				System.out.println("");
				npe.printStackTrace();
				System.out.println("");
			} catch (FileNotFoundException fnf) {
				System.out.print("\n"+fnf.toString()+"\n");
			} catch (IOException ioe) {
				System.out.print("\n"+ioe.toString()+"\n");
			} catch (Exception e) {
				System.out.print("\n"+e.toString()+"\n");
			}
		}
	}
}
