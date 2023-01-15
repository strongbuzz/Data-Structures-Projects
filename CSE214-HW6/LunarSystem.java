import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

import jdk.jshell.spi.ExecutionControl.ExecutionControlException;

/*
 * This class Represents a Course which quit, out, sudentout, database
 * @author Jinwoo Lee
 */
public class LunarSystem implements Serializable {

	static boolean quit = false;
	static boolean out = false;
	static boolean studentOut = false;
	private static HashMap<String, Student> database = new HashMap<String, Student>();

	/**
	 * This method runs the program
	 * 
	 * @throws ClassNotFoundException When class is at capacity.
	 * @throws IOException            When class is at error.
	 */
	public static void main(String[] args) throws ClassNotFoundException, IOException {

		System.out.println("Welcome to the Lunar System, a second place course"
				+ " registration system for second rate courses at a second class school.\n");

		Scanner scan = new Scanner(System.in);
		File databaseFile = new File("Lunar.ser");
		if (databaseFile.exists()) {
			System.out.println("Previous data loaded.");
			try {
				FileInputStream file = new FileInputStream("Lunar.ser");
				ObjectInputStream inStream = new ObjectInputStream(file);

				database = (HashMap<String, Student>) inStream.readObject();

				inStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("No previous data found.");
		}

		while (!quit) {
			out = false;
			studentOut = false;
			System.out.println(menu());
			System.out.println("\nPlease select an option:");
			String option = scan.nextLine();

			switch (option.toUpperCase()) {
				/**
				 * This method Login/Logout
				 */
				case "L": {
					System.out.println("Please enter webid:");
					String webID = scan.nextLine();

					if (webID.equalsIgnoreCase("REGISTRAR")) {
						System.out.println("\nWelcome " + webID + "." + "\n");
						System.out.println(option());
						while (!out) {
							System.out.println("\nPlease select an option:");
							String Loption = scan.nextLine();

							switch (Loption.toUpperCase()) {

								/**
								 * This method Register A Student.
								 */
								case "R": {
									System.out.println("Please enter a webid for the new student:");
									String studentWebId = scan.nextLine();

									if (database.containsKey(studentWebId.toUpperCase())) {
										System.out.println(studentWebId + " is already registered.\n");
										break;
									}

									else {
										Student student = new Student(studentWebId.toUpperCase());

										database.put(studentWebId.toUpperCase(), student);
										System.out.println(studentWebId + " registered.\n");
									}

									break;
								}
								/**
								 * This method De-Register A Student.
								 */
								case "D": {
									System.out.println("Please enter a webid for the student to be deregistered:");
									String dId = scan.nextLine();
									database.remove(dId.toUpperCase());
									System.out.println(dId + " deregistered.");

									break;
								}
								/**
								 * This method View students enrolled in a class.
								 */
								case "E": {
									System.out.println("Please enter course:");
									String checkCourse = scan.nextLine();
									String[] check = checkCourse.split(" ", 2);
									System.out.println("\nStudents Registered in " + checkCourse.toUpperCase() + " :");
									System.out.println("Student    Semester\r\n" + "\r\n" + "-------------------");
									for (String s : database.keySet()) {
										Student st = database.get(s);
										for (Course c : st.getList()) {
											if (c.getDepartment().equals(check[0].toUpperCase())
													&& c.getNumber() == Integer.parseInt(check[1])) {
												System.out.printf("%-12s", st.getWebID());
												System.out.printf("\t" + c.getSemester().toUpperCase());
												System.out.println();

											}
										}
									}
									break;
								}
								/**
								 * This method logout user.
								 */
								case "L": {
									System.out.println("Registrar logged out.\n");
									out = true;
									break;
								}
								default:
									System.out.println("Wrong input!\n");

							}
						}
					} else if (database.containsKey(webID.toUpperCase())) {
						System.out.println("\nWelcome " + webID + "." + "\n");

						while (!studentOut) {

							System.out.println(optionStudent());
							System.out.println("Please select an option:");
							String studentOption = scan.nextLine();

							switch (studentOption.toUpperCase()) {
								/**
								 * This method Add A Class.
								 */
								case "A": {

									System.out.println("Please enter course name:");
									String courseName = scan.nextLine();
									System.out.println("Please select a semester:");
									String semester = scan.nextLine();

									String[] courseAndNumber = courseName.split(" ", 2);
									try {
										int courseNumber = Integer.parseInt(courseAndNumber[1]);

										Course course = new Course(courseAndNumber[0].toUpperCase(), courseNumber,
												semester);

										database.get(webID.toUpperCase()).getList().add(course);
									} catch (ArrayIndexOutOfBoundsException e) {
										System.out.println("Wrong input! ex) CSE 214\n");
										break;
									} catch (NumberFormatException e) {
										System.out.println("Wrong input!");
										break;
									}
									String[] semesterAndYear = semester.split("", 2);
									String SorF = semesterAndYear[0];

									switch (SorF.toUpperCase()) {
										case "S": {

											System.out.println(
													courseName.toUpperCase() + " added in Spring " + semesterAndYear[1]
															+ ".\n");

											break;

										}
										case "F": {

											System.out.println(
													courseName.toUpperCase() + " added in Fall " + semesterAndYear[1]
															+ ".\n");

											break;
										}

										default:
											System.out.println("Wrong input!");
									}

									break;
								}
								/**
								 * This method Drop A Class.
								 */
								case "D": {

									System.out.println("Please enter course name:");
									String dropCourse = scan.nextLine();
									String[] sp = dropCourse.split(" ", 2);

									for (int i = 0; i < database.get(webID.toUpperCase()).getList().size(); i++) {
										String s = database.get(webID.toUpperCase()).getList().get(i).getDepartment();
										int n = database.get(webID.toUpperCase()).getList().get(i).getNumber();
										if (s.equals(sp[0].toUpperCase()) && n == Integer.parseInt(sp[1])) {
											String[] c = database.get(webID.toUpperCase()).getList().get(i)
													.getSemester()
													.split("", 2);
											if (c[0].equalsIgnoreCase("F")) {
												System.out.println(
														database.get(webID.toUpperCase()).getList().get(i)
																.getDepartment() + " "
																+ database.get(webID.toUpperCase()).getList().get(i)
																		.getNumber()
																+ " dropped from " + "Fall " + c[1] + ".");

											}
											if (c[0].equalsIgnoreCase("S")) {
												System.out.println(
														database.get(webID.toUpperCase()).getList().get(i)
																.getDepartment() + " "
																+ database.get(webID.toUpperCase()).getList().get(i)
																		.getNumber()
																+ " dropped from " + "Spring " + c[1] + ".");

											}

											database.get(webID.toUpperCase()).getList().remove(i);

										}
									}

									break;
								}
								/**
								 * This method List classes by Department.
								 */
								case "C": {

									Collections.sort(database.get(webID.toUpperCase()).getList(),
											new CourseNameComparator());

									System.out
											.println("Dept. Course Semester\r\n" + "\r\n"
													+ "-------------------------------");
									if (database.get(webID.toUpperCase()).getList().size() == 1) {
										System.out
												.println(database.get(webID.toUpperCase()).getList().get(0).toString());
									} else {
										for (int i = 0; i < database.get(webID.toUpperCase()).getList().size(); i++) {

											System.out.println(
													database.get(webID.toUpperCase()).getList().get(i).toString());
										}
									}
									break;
								}
								/**
								 * This method List Classes by semester.
								 */
								case "S": {

									Collections.sort(database.get(webID.toUpperCase()).getList(),
											new SemesterComparator());
									System.out.println(
											"\nDept. Course Semester\r\n" + "\r\n" + "-------------------------------");
									if (database.get(webID.toUpperCase()).getList().size() == 1) {
										System.out
												.println(database.get(webID.toUpperCase()).getList().get(0).toString());
									} else {
										for (int i = 0; i < database.get(webID.toUpperCase()).getList().size(); i++) {

											System.out.println(
													database.get(webID.toUpperCase()).getList().get(i).toString());
										}
									}
									break;
								}
								/**
								 * This method logout users.
								 */
								case "L": {

									System.out.println(webID + " logged out.\n");
									studentOut = true;
									break;

								}
								default:
									System.out.println("Wrong input!\n");
							}
						}
					} else {
						System.out.println("Wrong input!\n");
					}

					break;
				}
				/**
				 * This method save and quit.
				 */
				case "X": {
					System.out.println("System state saved, system shut down for maintenance.");
					try {
						FileOutputStream outFile = new FileOutputStream("Lunar.ser");

						ObjectOutputStream outStream = new ObjectOutputStream(outFile);

						outStream.writeObject(database);

						outStream.close();

					} catch (IOException e) {
						e.printStackTrace();
					}

					quit = true;

					break;

				}
				/**
				 * This method Quit without saving state (and delete any save-file).
				 */
				case "Q": {
					System.out.println("Good bye, please pick the right SUNY next time!");
					File myObj = new File("lunar.ser");
					if (myObj.delete()) {
						System.out.println();
					} else {
						System.out.println("Failed to delete the file.");
					}
					quit = true;
					break;
				}
				default:
					System.out.println("Wrong input!");
			}
		}
	}

	/**
	 * This method display the student's menu
	 * 
	 * @return The option of student
	 */
	public static String optionStudent() {
		String result = "";

		result = result + "Options:\n\n" + "A)Add a class\n" + "D)Drop a class\n"
				+ "C)View your classes sorted by course name/department\n" + "S)View your courses sorted by semester\n"
				+ "L)Logout\n";
		return result;
	}

	/**
	 * This method display the option
	 * 
	 * @return The option
	 */
	public static String option() {

		String result = "";

		result = result + "Options:\n\n" + "R) Register a student\n" + "D) De-register a student\n"
				+ "E) View course enrollment\n" + "L) Logout\n";
		return result;
	}

	/**
	 * This method display the menu
	 * 
	 * @return The menu
	 */
	public static String menu() {

		String result = "";

		result = result + "Menu:\n\n" + "L)Login\n" + "X)Save state and quit\n" + "Q)Quit without saving state.\n";

		return result;
	}
}
