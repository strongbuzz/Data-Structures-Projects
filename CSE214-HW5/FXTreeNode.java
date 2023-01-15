/*
 * This class Represents a FXTreeNode which has text, type, parent, childeren.
 * @author Jinwoo Lee
 */
public class FXTreeNode {

	String text;
	ComponentType type;
	FXTreeNode parent;
	FXTreeNode[] children;
	int maxChildren = 10;

	/**
	 * This is a constructor used to create a new Package object
	 * 
	 * @param initial text, type, parent of node
	 * 
	 */
	public FXTreeNode(String text, ComponentType type, FXTreeNode parent) {

		this.text = text;
		this.type = type;
		this.parent = parent;
		this.children = new FXTreeNode[this.maxChildren];

	}

	/**
	 * This method adding a child nodes
	 * 
	 */
	public void addChild(int index, FXTreeNode child) {

		if (this.children[index - 1] == null)
			// check precondition
			// should not be null
			this.children[index - 1] = child;
	}

	/**
	 * This method gets the text of node
	 * 
	 * @return text of node
	 */
	public String getText() {
		return text;
	}

	/**
	 * This method gets the type of node
	 * 
	 * @return tpye of node
	 */
	public ComponentType getType() {
		return type;
	}

	/**
	 * This method gets the parent of node
	 * 
	 * @return parent of node
	 */
	public FXTreeNode getParent() {
		return parent;
	}

	/**
	 * This method gets the childeren array of node
	 * 
	 * @return childeren of node
	 */
	public FXTreeNode[] getChildren() {
		return children;
	}

	/**
	 * This method set the text of the node
	 * 
	 * @param text The text to be set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * This method set the type of the node
	 * 
	 * @param type The type to be set
	 */

	public void setType(ComponentType type) {
		this.type = type;
	}

	/**
	 * This method set the parent of the node
	 * 
	 * @param parent The parent to be set
	 */

	public void setParent(FXTreeNode parent) {
		this.parent = parent;
	}

	/**
	 * This method set the childeren of the node
	 * 
	 * @param childeren The childeren to be set
	 */

	public void setChildren(FXTreeNode[] children) {
		this.children = children;
	}

	/**
	 * This method print the type and text
	 * 
	 */
	public void print(String s, FXTreeNode cur) {

		if (this == cur)
			System.out.println(s + "=>" + this.type + " " + this.text);
		else
			System.out.println(s + "+--" + this.type + " " + this.text);
		int i = 0;
		while (this.children[i] != null) {
			this.children[i].print(s + "\t", cur);
			i += 1;

		}
	}

	/**
	 * This method helper function of helper method
	 * 
	 */
	public String helper_node(String s, FXTreeNode cur) {

		int o = 0;
		s += 0 + "-" + o + this.type + " " + this.text + "\n";
		int i = 0;
		while (this.children[i] != null) {
			s = this.children[i].helper_node(s, cur);
			i += 1;
			o++;
		}
		return s;
	}

}
