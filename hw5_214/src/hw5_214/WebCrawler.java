/**
 * Eddie Xu
 * ID: 112206686
 * Email: eddie.xu@stonybrook.edu
 * Homework 5
 * CSE214
 * Recitation 6: Juan Tarquino
 */
package hw5_214;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Main class to run and test a ternary WebTree. Loads up user interface with options and their functions.
 * 
 * webTree : WebTree object to crawl, store and perform methods on. 
 * 
 * @author eddie
 *
 */
public class WebCrawler {
	private WebTree webTree;
	
	/**
	 * Default constructor of a WebCrawler object. Instantiates class var to a new WebTree.
	 */
	public WebCrawler() {
		webTree = new WebTree();
	}
	
	public static void main(String[]args) throws IllegalArgumentException, FullNodeException, IOException {
		Scanner scan = new Scanner(System.in);
		System.out.print("[L] : Load HTML file\n[P] : Print tree\n[D] : Print dead links"
				+ "\n[S] : Search for a page with a title within the tree\n[R] : Reset tree structure"
				+ "\n[Q] : Quit\n");
		String y = "y";
		String[]crawledInfo = null;
		WebCrawler crawler = new WebCrawler();
		while(!y.equalsIgnoreCase("q")) {
			System.out.print("\nEnter selection: ");
			try {
				switch(scan.next().toLowerCase()) {
				case "l":
					System.out.print("\nEnter HTML file: ");
					String htmlFile = scan.next().toLowerCase();
					try {
						crawler.webTree = WebTree.crawlHTML(htmlFile);
						crawledInfo = crawler.webTree.getCrawledInfo().split("\\|- ");
						int active,circular,dead;
						active = circular = dead = 0;
						for(int i = 2; i < crawledInfo.length; i++) {
							String str = crawledInfo[i];
							if(str.contains("***"))
								dead++;
							else if(str.contains("*"))
								circular++;
							else 
								active++;
						}
							System.out.print("\n'"+htmlFile+"'successfully crawled.\n"+(active)+" active links followed\n"+circular+
									" circular links found\n"+dead+" dead links found\n"+(active+circular+dead)+" total links found.\n");
					}
					catch(FileNotFoundException e) {
						System.out.println("\nNo such file exists.");
					}
					break;
				case "p":
					crawler.webTree.printWebTree();
					break;
				case "d":
					crawler.webTree.printDeadLinks();
					break;
				case "s":
					System.out.print("\nEnter keyword of title to search for: ");
					String keyword = scan.next();
					System.out.println();
					crawler.webTree.search(keyword);
					break;
				case "r":
					crawler.webTree.resetTreeStructure();
					System.out.print("\nInformation reset.\n");
					break;
				case "q":
					System.out.println("\nProgram terminating successfully...");
					y = "q";
					break;
				}
			}catch(FullNodeException fne) {
				System.out.print("\n"+fne.toString()+"\n");
			}catch(IllegalArgumentException iae) {
				System.out.print("\n"+iae.toString()+"\n");
			}catch(IOException ioe) {
				ioe.printStackTrace();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
