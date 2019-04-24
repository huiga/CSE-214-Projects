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
 * HTMLLinkNode class. Each HTMLLinkNode object represents a node object in the ternary tree. Every HTMLLinkNode
 * contains data representative of a .html file and it's elements.
 * 
 * fileName :  name of the html file (test.html, example.html, etc).
 * linkName : name of the html link. Contained in between html-style link tags (<a href="target_file.html">LINK NAME</a>, for example)
 * pageTitle : title of the accessed html file. Contained in ebtween html-style tags (<title>Test Page</title>, for example)
 * linkType : enum representation of what the accessed html file's link type is.Refer to LinkType.java
 * ancestor : HTMLLinkNode object representing the parent node to the current object ('this' object).
 * links : array of length 3 holding up to 3 children HTMLLinkNode objects. Implemented as a ternary tree.
 * 
 * @author eddie
 *
 */
public class HTMLLinkNode {
	private String fileName;
	private String linkName;
	private String pageTitle;
	private LinkType linkType;
	private HTMLLinkNode ancestor;
	private HTMLLinkNode[] links = new HTMLLinkNode[3];
	
	/**
	 * Default constructor of the HTMLLinkNode class. All class vars set to be null.
	 */
	public HTMLLinkNode() {
	}
	
	/**
	 * Default constructor of the HTMLLinkNode class. Sets class vars to desired parameters.
	 * 
	 * @param fileName
	 * 		fileName of the HTMLLinkNode object. Refer to class var description.
	 * @param linkName
	 * 		linkName of the HTMLLinkNode object. Refer to class var description.
	 * @param pageTitle
	 * 		pageTitle of the HTMLLinkNode object. Refer to class var description.
	 * @param linkType
	 * 		linkType of the HTMLLinkNode object. Refer to class var description.
	 * @param ancestor
	 * 		ancestor of the to-be-made HTMLLinkNode object. 
	 */
	public HTMLLinkNode(String fileName, String linkName, String pageTitle, LinkType linkType, HTMLLinkNode ancestor) {
		this.fileName = fileName;
		this.linkName = linkName;
		this.pageTitle = pageTitle;
		this.linkType = linkType;
		this.ancestor = ancestor;
	}
	
	/**
	 * Setter method to set the ancestor of the current HTMLLinkNode object.
	 * 
	 * @param ancestor
	 * 		HTMLLinkNode object to set to be ancestor of 'this' object.
	 */
	public void setAncestor(HTMLLinkNode ancestor) {
		this.ancestor = ancestor;
	}
	
	/**
	 * Getter method to access class var ancestor.
	 * 
	 * @return
	 * 		class var ancestor. Represents a parent node to current object.
	 */
	public HTMLLinkNode getAncestor() {
		return ancestor;
	}
	
	/**
	 * Setter method to set the file name of current HTMLLinkNode object.
	 * 
	 * @param fileName
	 * 		String to set the fileName to.
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	/**
	 * Getter method to access class var fileName
	 * 
	 * @return
	 * 		String representation of the file name of the current HTMLLinkNode object.
	 */
	public String getFileName() {
		return fileName;
	}
	
	/**
	 * Setter method to set the link name of the current HTMLLinkNode object.
	 * 
	 * @param linkName
	 * 		String to set the link name to.
	 */
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	/**
	 * Getter method to access class var linkName
	 * 
	 * @return
	 * 		String representation of the link name of the current HTMLLinkNode object.
	 */
	public String getLinkName() { 
		return linkName;
	}
	
	/**
	 * Setter method to set the page title of the current HTMLLinkNode object.
	 * 
	 * @param pageTitle
	 * 		String to set the page title to.
	 */
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}
	
	/**
	 * Getter method to access class var pageTitle
	 * 
	 * @return
	 * 		String representation of the page title of the current HTMLLinkNode object.
	 */
	public String getPageTitle() {
		return pageTitle;
	}
	
	/**
	 * Setter method to set the LinkType of the current HTMLLinkNode object.
	 * 
	 * @param linkType
	 * 		LinkType enum to set the current HTMLLinkNode object's link type to be.
	 */
	public void setLinkType(LinkType linkType) {
		this.linkType = linkType;
	}
	
	/**
	 * Getter method to access the LinkType of the current HTMLLinkNode object.
	 * 
	 * @return
	 * 		LinkType enum representative of the current HTMLLinkNode object's link type.
	 */
	public LinkType getLinkType() {
		return linkType;
	}
	
	/**
	 * Getter method to access the children nodes of current HTMLLinkNode object (class var links)
	 * 
	 * @return
	 * 		Array of length 3 of HTMLLinkNode objects representing the children of the current node. 
	 */
	public HTMLLinkNode[] getLinks() {
		return links;
	}
	/**
	 * Checks if the current HTMLLinkNode object contains a reference to a dead link.
	 * 
	 * @return
	 * 		true if the html file found does not exist,
	 * 		false if the html file exists.
	 */
	public boolean isDeadLink() {
		return this.getLinkType()==LinkType.DEAD;
	}
	
	/**
	 * Checks if the current HTMLLinkNode object contains a reference to a circular link.
	 * 
	 * @return
	 * 		true if the html file found exists as an ancestor to the current node (LinkType is circular)
	 * 		false is the html file found does not exist as an ancestor to the current node (LinkType is NOT circular)
	 */
	public boolean isCircularLink() {
		return this.getLinkType()==LinkType.CIRCULAR;
	}
	
	/**
	 * Adds a child HTMLLinkNode object to the class var links[].
	 * 
	 * Preconditions: 
	 * 		There is at least one open position in the links array.
	 * 		newLink is not null
	 * 
	 * Postconditions: 
	 * 		newLink has been added as a child of this node. 
	 * 
	 * @param newLink
	 * 		HTMLLinkNode object to add to the children of the current HTMLLinkNode object.
	 * 
	 * @throws FullNodeException
	 * 		If the class var links[] is full (aka the current node has 3 children nodes already, 
	 * 		throws the FullNodeException to indicate the node already has max amount of children.
	 * @throws IllegalArgumentException
	 * 		If newLink is not a valid reference to an HTMLLinkNode object.
	 * 		
	 */
	public void addLink(HTMLLinkNode newLink) throws FullNodeException, IllegalArgumentException{
		if(newLink==null) 
			throw new IllegalArgumentException("Cannot add a null link.");
		else if(!(newLink instanceof HTMLLinkNode))
			throw new IllegalArgumentException("Cannot add a link that is not of a HTMLLinkNode.");
		else {
			boolean wasAdded = false;
			for(int i=0;i<links.length;i++) {
				if(links[i]==null) {
					links[i] = newLink;
					wasAdded = true;
					break;
				}
			}
			if(!wasAdded)
				throw new FullNodeException("The node link array is full. Cannot add new link.");
		}
	}

	/**
	 * Returns child node at the given parameter index.
	 * 
	 * Preconditions:
	 * 		index if within the bounds of the links array.
	 * 
	 * @param index
	 * 		Index to access class var links
	 * @return
	 * 		child HTMLLinkNode object at links[index];
	 * @throws IllegalArgumentException
	 * 		if index is out of bounds of the array.
	 */
	public HTMLLinkNode getLinkAt(int index) throws IllegalArgumentException{
		if(index<0 || index>2) 
			throw new IllegalArgumentException("Cannot get link at the specified index (index must be 0-2).");
		else
			return links[index];
	}
	
	
	/**
	 * Compares the current HTMLLinkNode object with given object in parameter.
	 * 
	 * @param o
	 * 		the object to compare this HTMLLinkNode to
	 * @return
	 * 		true if the two objects are equal (same file name and page title)
	 * 		false if the two objects are not equal.
	 */
	public boolean equals(Object o) {
		if(o instanceof HTMLLinkNode)
			return (fileName.equals(((HTMLLinkNode) o).getFileName()) && pageTitle.equals(((HTMLLinkNode) o).getPageTitle()));
		else 
			return false;
	}
	
	/**
	 * toString format to format the infomration of the HTMLLinkNode object. If the HTMLLinkNode object to format 
	 *		is a dead link, the page title should be replaced by "***". If the HTMLLinkNode object to format is 
	 *		a circular link, the file name should have a "*" next to it.
	 * 
	 * @return
	 * 		neatly formatted string with class variables.
	 * 
	 */
	public String toString() {
		String circularFileName = fileName;
		String deadLink ="[ "+pageTitle+" ]";
		if(this.isCircularLink())
			circularFileName += "*";
		if(this.isDeadLink())
			deadLink = "***";
		if(fileName=="" && linkName==null && pageTitle==null)
			return "No file parsed/No elements found in file.";
		else if(linkName == null)
			return "|- "+circularFileName+" "+deadLink; 
		else
			return "|- "+circularFileName+" ("+linkName+") "+deadLink; 
	}
	
}
