import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/*
 * This class Represents a FXComponentTree which has root, cursor.
 * @author Jinwoo Lee
 */
public class FXComponentTree {

	FXTreeNode root;
	FXTreeNode cursor;

	/**
	 * This is a constructor used to create a new Package object
	 * 
	 * @param initial root, cursor
	 * 
	 */
	public FXComponentTree() {
		// initialize root node
		this.root = new FXTreeNode("", ComponentType.AnchorPane, null);
		this.cursor = root;
	}

	/**
	 * This method gets the cursor of node
	 * 
	 * @return cursor of node
	 */
	public FXTreeNode getCursor() {
		return cursor;
	}

	/**
	 * This method gets the root of node
	 * 
	 * @return root of node
	 */
	public FXTreeNode getroot() {
		return root;
	}

	/**
	 * This method cursor to root
	 * 
	 */
	public void cursorToRoot() {
		cursor = root;
	}

	/**
	 * This method deleting its child
	 * 
	 */
	public void deleteChild(int index) {

		int target = index;
		for (int i = target; i < cursor.getChildren().length; i++) {
			cursor.getChildren()[i - 1] = cursor.getChildren()[i];
		}
	}

	/**
	 * This method add a childeren
	 * 
	 */
	public void addChildren(int index, FXTreeNode node) {
		/**
		 * Scanner scan = new Scanner(System.in); FXTreeNode newNode = new FXTreeNode();
		 * 
		 * 
		 * 
		 * // FXTreeNode n = new FXTreeNode(); // index = node;
		 **/
		cursor.addChild(index, node);
	}

	/**
	 * This method edit text of the node
	 * 
	 * @param text The text to be set
	 */
	public void setTextAtCursor(String text) {

		cursor.setText(text);
	}

	/**
	 * This method cursor to child
	 * 
	 */
	public void cursorToChild(int index) {

		FXTreeNode c = cursor.getChildren()[index - 1];
		cursor = c;
	}

	/**
	 * This method cursor to parent
	 * 
	 */
	public void cursorToParent() {
		cursor = cursor.getParent();
	}

	/**
	 * This method reading from file
	 * 
	 * @return tree
	 */
	public static FXComponentTree readFromFile(String filename) throws FileNotFoundException {
		// String fileName1 = filename;
		File file = new File(filename);
		// System.out.println(file.getAbsolutePath().toString());
		Scanner scan = new Scanner(file);
		FXComponentTree tree = new FXComponentTree();
		// skip first line
		scan.nextLine();

		while (scan.hasNextLine()) {

			String[] inputs = scan.nextLine().split(" ", 3);
			String index = inputs[0];
			ComponentType type = ComponentType.valueOf(inputs[1]);
			String text;

			if (inputs.length == 3) {
				text = inputs[2];
			} else {
				text = "";
			}

			String[] indices = index.split("-");

			int j = 1;
			// find parent
			FXTreeNode parent = tree.root;
			while (j < indices.length - 1) {
				int childIndex = Integer.parseInt(indices[j]);

				parent = parent.getChildren()[childIndex];

				j++;
			}

			int i = Integer.parseInt(indices[indices.length - 1]);

			FXTreeNode newNode = new FXTreeNode(text, type, parent);
			parent.children[i] = newNode;

		}
		return tree;

	}

	/**
	 * This method printing tree
	 * 
	 */
	public void printTree(FXTreeNode cur) {

		String s = "";

		if (root == cur)
			System.out.println(s + "=>" + root.getType() + " " + root.getText());
		else
			System.out.println(s + root.getType() + " " + root.getText());
		int i = 0;
		FXTreeNode[] childs = root.getChildren();
		while (childs[i] != null) {
			childs[i].print(s + "", cur);
			i += 1;

		}

	}

	/**
	 * This method wrting tree to file
	 * 
	 */
	public void writeToFile(FXComponentTree tree, String filename) throws IOException {
		File file = new File("/Users/Jinwoo Lee/Desktop/" + filename);
		FileWriter rw = new FileWriter(file);
		PrintWriter pw = new PrintWriter(rw);

		pw.print(helper(cursor));
		pw.close();

	}

	/**
	 * This method help to write to file
	 * 
	 */
	public String helper(FXTreeNode cur) {
		String s = "";

		s = "0 " + root.getType() + " " + root.getText() + "\n";
		int i = 0;
		FXTreeNode[] childs = root.getChildren();
		while (childs[i] != null) {
			s += childs[i].helper_node(s + "", cur);
			i += 1;
		}
		return s;
	}

	public void exportToFXML(FXComponentTree tree, String filename) {

	}
}
