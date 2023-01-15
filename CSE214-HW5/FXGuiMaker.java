import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/*
 * This class Represents a FXGuiMaker which has tree.
 * @author Jinwoo Lee
 */
public class FXGuiMaker {
	FXComponentTree tree;
	static boolean quit = false;
	static boolean flag = false;

	/**
	 * This method runs the program
	 * 
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		System.out.println("Welcome to counterfeit SceneBuilder.\n");

		FXComponentTree o = null;

		Scanner scan = new Scanner(System.in);

		while (!quit) {

			System.out.println(Menu());
			System.out.println("Please select an option:");
			String option = scan.nextLine();

			switch (option.toUpperCase()) {
				/**
				 * This method Load from file
				 */
				case "L": {
					System.out.println("Enter absoulute path of filename ");

					String fileName = scan.nextLine();
					try {
						o = FXComponentTree.readFromFile("/Users/Jinwoo Lee/Desktop/" + fileName);
						System.out.println(fileName + " loaded");

					} catch (FileNotFoundException e) {
						System.out.println("File is not found.");
					}
					break;
				}
				/**
				 * This method Print the tree
				 */
				case "P": {
					try {
						o.printTree(o.getCursor());
					} catch (NullPointerException e) {
						System.out.println("File is not found\n");
					}
					break;

				}
				/**
				 * This method Cursor to child (index number)
				 */
				case "C": {
					System.out.println("Please enter number of child (starting with 1):");
					int numChild = Integer.parseInt(scan.nextLine());
					try {
						o.cursorToChild(numChild);
					} catch (NullPointerException e) {
						System.out.println("Wrong input!");
					}
					try {
						System.out.println(
								"Cursor Moved to " + o.getCursor().getType() + " " + o.getCursor().getText() + ".");
					} catch (NullPointerException e) {
						System.out.println("Wrong input!");
					}
					break;

				}
				/**
				 * This method Cursor to root
				 */
				case "R": {

					o.cursorToRoot();
					System.out.println("Cursor is at root.");
					break;

				}
				/**
				 * This method Add child (index, type, prompt for text)
				 */
				case "A": {
					// �ؾߵ�
					System.out.println(
							"Select component type (H - HBox, V - VBox, T - TextArea, B - Button, L - Label):");
					String type = scan.nextLine();
					System.out.println("Please enter text:");
					String text = scan.nextLine();

					System.out.println("Please enter an index:");
					int index = Integer.parseInt(scan.nextLine());

					ComponentType c = null;
					if (type.toUpperCase().equals("H")) {
						c = ComponentType.HBox;
					} else if (type.toUpperCase().equals("V")) {
						c = ComponentType.VBox;
					} else if (type.toUpperCase().equals("T")) {
						c = ComponentType.TextArea;
					} else if (type.toUpperCase().equals("B")) {
						c = ComponentType.Button;
					} else if (type.toUpperCase().equals("L")) {
						c = ComponentType.Label;
					}
					FXTreeNode newNode = new FXTreeNode(text, c, o.root.getParent());
					try {
						o.addChildren(index, newNode);
					} catch (Exception e) {
						System.out.println("Wrong input!");
					}

					break;

				}

				/**
				 * This method Cursor up (to parent)
				 */
				case "U": {
					try {
						o.cursorToParent();
					} catch (NullPointerException e) {
						System.out.println("No parent.");
					}
					try {
						System.out.println("Cursor Moved to " + o.getCursor().getType() + ".");
					} catch (NullPointerException e) {
						System.out.println("No parent.\n");
					}
					break;

				}
				/**
				 * This method Edit Text
				 */
				case "E": {
					System.out.println("Please enter new text:");
					String editText = scan.nextLine();
					o.setTextAtCursor(editText);
					System.out.println("Text Edited.");
					break;

				}
				/**
				 * This method Delete child (index number)
				 */
				case "D": {
					System.out.println("Please enter number of child (starting with 1):");
					int index = Integer.parseInt(scan.nextLine());
					if (o.getCursor().getChildren()[index] == null) {
						System.out.println("Wrong input!");
						break;
					} else {
						System.out.println(o.getCursor().getType() + " removed");
					}
					o.deleteChild(index);
					break;

				}
				/**
				 * This method Save to Text File
				 */
				case "S": {
					System.out.println("Please enter a file name:");
					String fileName = scan.nextLine();
					o.writeToFile(o, fileName);
					System.out.println("File saved.");

					break;

				}
				/**
				 * This method Quit the program
				 */
				case "Q": {
					quit = true;
					System.out.println("Make like a tree and leave!");
					break;

				}

				/**
				 * This method when wrong input
				 */
				default:
					System.out.println("Wrong input!");

			}

		}
	}

	/**
	 * This method display the menu
	 * 
	 * @return The menu
	 */
	public static String Menu() {
		String menu = "";
		menu = menu + "\nMenu:\n" + "L) Load from file\n" + "P) Print tree\n" + "C) Move cursor to a child node\n"
				+ "R) Move cursor to root\n" + "A) Add a child\n" + "U) Cursor up (to parent)\n"
				+ "E) Edit text of cursor\n" + "D) Delete child\n" + "S) Save to file\n" + "X) Export FXML\n"
				+ "Q) Quit)\n";

		return menu;

	}
}
