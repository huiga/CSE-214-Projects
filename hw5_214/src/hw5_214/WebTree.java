/**
 * Eddie Xu
 * ID: 112206686
 * Email: eddie.xu@stonybrook.edu
 * Homework 5
 * CSE214
 * Recitation 6: Juan Tarquino
 */
package hw5_214;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * WebTree class. Methods to create a ternary tree (tree data structure where each node can contain up to 3 children
 * nodes) through recursive methods. Main use is to crawl HTML files to attain information. Creates a tree based
 * on the pathing per HTML file.
 * 
 * root : The root of the current WebTree object.
 * currentNode : static HTMLLinkNode to store the ancestor/parent of the current node. Made static in 
 * 		efforts to maintain reference to parent during next recursive call in creating a new WebTree.
 * crawledInfo : String concatenation of all the information crawled from the HTML file.
 * 
 * @author eddie
 *
 */
public class WebTree {
	private HTMLLinkNode root;
	public static HTMLLinkNode currentNode;
	private String crawledInfo;
	
	/**
	 * Default constructor for the WebTree class. Instantiates the root of the node to have no fileName,
	 * null link name and page titles, an active link type, and a null ancestor.
	 */
	public WebTree() {
		root = new HTMLLinkNode("",null,null,LinkType.ACTIVE,null);
		if (!(currentNode instanceof HTMLLinkNode))
			currentNode = null;
		crawledInfo = "";
	}
	
	/**
	 * Method that crawls and instantiates a ternary tree data structure recursively. First checks if the 
	 * parameter file exists or not. If it exists, the method looks for a title tag to set the current 
	 * root node's title to, then HTML style tags for a new fileName found and a link name. If a fileName 
	 * is found, the method recursively crawls the new fileName found. Returns the tree after each activation
	 * record. 
	 * 
	 * Postconditions: 
	 * 		The file specified is loaded and parsed for any title tag and any link tags. If there is no
	 * 		file as specified by filename, then the HTMLLinkNode object should be marked as a DEAD link.
	 * 		The HTMLLinkNode is inserted into the WebTree object at its proper position. Once the full
	 * 		structure of the WEbTree object has been constructed and all links have been followed, print
	 * 		out the information about the tree regarding the numbers and types of links encountered,
	 * 
	 * @param fileName
	 * 		the name of the file to parse and crawl.
	 * @return
	 * 		WebTree object of the current recursive iteration.
	 * @throws IllegalArgumentException
	 * 		if filename is null or an empty String.
	 * @throws FullNodeException 
	 * 		if the current HTMLLinkNode node has the maximum number of children. 
	 * @throws IOException
	 * 		if the file does not exist.
	 */
	public static WebTree crawlHTML(String fileName) throws IllegalArgumentException, IOException, FullNodeException{
		if(fileName == null || fileName == "")
			throw new IllegalArgumentException("\nCannot have a null/blank file name.\n");
		File f = new File(fileName);
		if(!f.exists())
			throw new FileNotFoundException("\nFile does not exist.\n");
		else {
			WebTree tree = new WebTree();
			tree.root.setFileName(fileName);
			Scanner reader = new Scanner(f);
			String pageTitle, linkName;
			while(reader.hasNext()) {
				String lineToRead = reader.nextLine();
				if((lineToRead.contains("<title>")) || lineToRead.contains("&lt;title&gt;")) {
					if(lineToRead.contains("<title>")) {
						pageTitle = lineToRead.substring(lineToRead.indexOf("<title>")+7, 
								lineToRead.indexOf("<", lineToRead.indexOf("<title>")+7));
						tree.root.setPageTitle(pageTitle);
						tree.root.setAncestor(currentNode);
					}
					else if(lineToRead.contains("&lt;title&gt;")) {
						pageTitle = lineToRead.substring(lineToRead.indexOf("&lt;title&gt;")+13, 
								lineToRead.indexOf("<", lineToRead.indexOf("&lt;title&gt;")+13));
						tree.root.setPageTitle(pageTitle);
						tree.root.setAncestor(currentNode);
					}
				}
				if((lineToRead.contains("<a")) || (lineToRead.contains("&lt;a"))) {
					if(lineToRead.contains("<a")) {
						String[] linesToRead = lineToRead.split("<a");
						for (int x = 1; x < linesToRead.length; x++) {
							linkName = linesToRead[x].substring(linesToRead[x].indexOf(">")+1, linesToRead[x].indexOf("<"));
							fileName = linesToRead[x].substring(linesToRead[x].indexOf("href=\"")+6, linesToRead[x].indexOf("\"", linesToRead[x].indexOf("href=\"")+7));
							File newFile = new File(fileName);
							if(!newFile.exists()) {
								HTMLLinkNode deadLink = new HTMLLinkNode(fileName,linkName,"",LinkType.DEAD,tree.root);
								if(tree.existsAsAncestor(tree.root)) {
									tree.root.setLinkType(LinkType.CIRCULAR);
									return tree;
								}
								tree.root.addLink(deadLink);
							}
							else {
								currentNode = tree.root;
								if(tree.existsAsAncestor(tree.root)) {
									tree.root.setLinkType(LinkType.CIRCULAR);
									return tree;
								}
								else {
									WebTree newNode = crawlHTML(fileName);
									currentNode = tree.root;
									newNode.getRoot().setLinkName(linkName);
									newNode.getRoot().setFileName(fileName);
									tree.root.addLink(newNode.getRoot());
								}
							}
						}
					}
				}
				else if(lineToRead.contains("&lt;a")) {
					if(lineToRead.contains("&lt;a")) {
						String[] linesToRead = lineToRead.split("&lt;a");
						for (int x = 1; x < linesToRead.length; x++) {
							linkName = linesToRead[x].substring(linesToRead[x].indexOf("&gt;")+4, linesToRead[x].indexOf("&lt;"));
							fileName = linesToRead[x].substring(linesToRead[x].indexOf("href=\"")+6, linesToRead[x].indexOf("\"", linesToRead[x].indexOf("href=\"")+7));
							File newFile = new File(fileName);
							if(!newFile.exists()) {
								HTMLLinkNode deadLink = new HTMLLinkNode(fileName,linkName,"",LinkType.DEAD,tree.root);
								if(tree.existsAsAncestor(tree.root)) {
									tree.root.setLinkType(LinkType.CIRCULAR);
									return tree;
								}
								tree.root.addLink(deadLink);
							}
							else {
								currentNode = tree.root;
								if(tree.existsAsAncestor(tree.root)) {
									tree.root.setLinkType(LinkType.CIRCULAR);
									return tree;
								}
								else {
									WebTree newNode = crawlHTML(fileName);
									currentNode = tree.root;
									newNode.getRoot().setLinkName(linkName);
									newNode.getRoot().setFileName(fileName);
									tree.root.addLink(newNode.getRoot());
								}
							}
						}
					}
				}
			}
		if(tree.getRoot().getAncestor() == null)
			currentNode = null;
		return tree;
		}
	}

	/**
	 * Helper method for getCrawledInfo(). Recursively gets all the information of the ternary WebTree nodes in a preorder traversal and
	 * appends it onto the class variable crawledInfo.
	 * 
	 * @param node
	 * 		Node to begin obtaining information from.
	 * @return
	 * 		String of the information of the current recursive iteration's node.
	 */
	public String getCrawledInfo(HTMLLinkNode node) {
		crawledInfo+=node.toString();
		HTMLLinkNode[] links = node.getLinks();
		for(int i = 0;i<links.length;i++) {
			if(links[i]!=null) {
				getCrawledInfo(links[i]);
			}
		}
		return crawledInfo;
	}
	
	/**
	 * Recursively gets all the information of the ternary WebTree nodes in a preorder traversal and appends it
	 * onto the class variable.
	 * 
	 * @return
	 * 		String of the information of the current recursive iteration's node.
	 */
	public String getCrawledInfo() {
		return getCrawledInfo(root);
	}
	
	/**
	 * Getter method to get the 'root' node of the WebTree object.
	 * 
	 * @return
	 * 		class var HTMLLinkNode root.
	 */
	public HTMLLinkNode getRoot() {
		return this.root;
	}
	
	/**
	 * Determines if there is an equivalent HTMLLinkNode object found as an ancestor to the parameter node.
	 * 
	 * @param node
	 * 		the HTMLLinkNode object to check the ancestry of.
	 * @return
	 * 		true if there is a HTMLLinkNode object along the path between the root of the entire 
	 * 		ternary WebTree object and the HTMLLinkNode object supplied.
	 * 		false otherwise.
	 */
	public boolean existsAsAncestor(HTMLLinkNode node) {
		HTMLLinkNode parent = node.getAncestor();
		while(parent != null) {
			if (parent.equals(node)) {
				return true;
			}
			parent = parent.getAncestor();
		}
		return false;
	}
	
	/**
	 * Prints the information of the entire WebTree object in a formatted fashion. 
	 */
	public void printWebTree() {
		System.out.println();
		printWebTree(root,"");
	}
	
	/**
	 * Helper method of printWebTree(). Prints the information of the entire WebTree object in a formatted
	 * fashion.
	 * 
	 * Postconditions:
	 * 		The hierarchy of the entire web site is output to the console. If the tree is empty or if root
	 * 		is null, print a message saying so and return to the calling method,
	 * 
	 * @param node
	 * 		Node of the WebTree object to get information of.
	 * @param tabs
	 * 		The "space" per level of the ternary tree in each subbranch. Each level gets an extra amount of 
	 * 		"tabs" appended to the current amount of tab spaces.
	 */
	public void printWebTree(HTMLLinkNode node, String tabs) {
		System.out.println(tabs+node.toString());
		tabs+="   ";
		HTMLLinkNode[] links = node.getLinks();
		for(int i = 0;i<links.length;i++) { 
			if(links[i]!=null)
				printWebTree(links[i],tabs);
		}
	}
	
	/**
	 * Helper method for printDeadLinks(). Outputs the path to any HTMLLinkNode objects which contain
	 * links that have a DEAD link type (file does not exist).
	 * 
	 * Postconditions:
	 * 		THe hierarchy of the entire website is output to the console. If the tree is empty or if
	 * 		root is null, print a message saying so and return to the calling method. 
	 * 
	 * @param path
	 * 		The file name of the current node. Represents the total concatenation of the file names before
	 * 		reaching a dead link, hence var name 'path'.
	 * @param parent
	 * 		The ancestor/parent of the current node.
	 * @return
	 * 		boolean indicating whether dead links were found or not.
	 */
	public boolean printDeadLinks(String path, HTMLLinkNode parent) {
		String linkPath = path;
		boolean entered = false;
		HTMLLinkNode[] links = parent.getLinks();
		for(int i = 0;i<links.length;i++) {
			if(links[i] != null) {
				if(links[i].isDeadLink()) {
					entered = true;
					System.out.println(linkPath + " contains dead link '" + links[i].getLinkName() + "' with target '" +  links[i].getFileName() + "'");
				}
				else if (!links[i].isCircularLink()){
					entered = printDeadLinks(linkPath + "->" + links[i].getFileName(), links[i]);
				}
			}
		}
		return entered;
	}
	
	/**
	 * Helper method for printDeadLinks(). Outputs the path to any HTMLLinkNode objects which contain
	 * links that have a DEAD link type (file does not exist).
	 * 
	 * Postconditions:
	 * 		THe hierarchy of the entire website is output to the console. If the tree is empty or if
	 * 		root is null, print a message saying so and return to the calling method. 
	 * 
	 */
	public void printDeadLinks() {
		System.out.println();
		if(!printDeadLinks(root.getFileName(), root))
			System.out.println("There are no dead links to print.");
	}
	
	/**
	 * Searches the WebTree object for a specific keyword in each node's title.
	 * 
	 * Postconditions:
	 * 		Any valid path for reaching a HTMLLInkNode object which contains a title attribute which 
	 * 		contains keyword as a substring is output to the console.
	 * 
	 * @param keyword
	 * 		String to search in each node's page title.
	 * @throws IllegalArgumentException
	 * 		if the keyword is null or empty.
	 */
	public void search(String keyword) throws IllegalArgumentException {
		if(keyword==null || keyword =="")
			throw new IllegalArgumentException("There is no keyword to search.");
		if(!search(root, root.getFileName(), keyword,false))
			System.out.print("\nNo page found with the keyword '"+keyword+"'\n");
	}
	
	/**
	 * Helper method for search(String keyword). Searches the WebTree object for a specific keyword 
	 * in each node's title.
	 * 
	 * Postconditions:
	 * 		Any valid path for reaching a HTMLLInkNode object which contains a title attribute which 
	 * 		contains keyword as a substring is output to the console.
	 * @param parent
	 * 		Parent to the current node.
	 * @param info
	 * 		toString() return of the current node's information.
	 * @param keyword
	 * 		String to search in each node's page title.
	 * @param hasEntered
	 * 		boolean to indicate if you entered the if statement that shows you found a node containing keyword.
	 * @return
	 * 		boolean to indicate if you entered the if statement that shows you found a node containing keyword.
	 * @throws IllegalArgumentException
	 * 		if the keyword is null or empty.
	 */
	public boolean search(HTMLLinkNode parent, String info, String keyword,boolean hasEntered) throws IllegalArgumentException{
		if(keyword==null || keyword == "")
			throw new IllegalArgumentException("There is no keyword to search.");
		HTMLLinkNode[]links = parent.getLinks();
		String linkInfo = info;
		boolean contains=hasEntered;
		for(int i = 0;i<links.length;i++) {
			if(links[i]!=null) {
				if(!links[i].isCircularLink() && links[i].getPageTitle().toLowerCase().contains(keyword.toLowerCase())) {
					contains = true;
					System.out.println(linkInfo + "->" + links[i].getFileName());
				}
				if (!links[i].isCircularLink()){
					search(links[i],linkInfo + "->" + links[i].getFileName(),keyword,contains);
				}
			}
		}
		return contains;
	}
	
	/**
	 * Resets the tree structure to empty.
	 * 
	 * Postconditions:
	 * 		root is set to null.
	 */
	public void resetTreeStructure() {
		root = new HTMLLinkNode("",null,null,LinkType.ACTIVE,null);
		if (!(currentNode instanceof HTMLLinkNode))
			currentNode = null;
		crawledInfo = "";
	}
}
