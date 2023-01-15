import java.io.Serializable;

/*
 * This class Represents a Course which has department,number,semester
 * @author Jinwoo Lee
 */
public class Course implements Comparable, Serializable {

	private String department;
	private int number;
	private String semester;

	/**
	 * This is a constructor used to create a new Package object
	 * 
	 * @param initial department,number,semester
	 * 
	 */
	public Course(String department, int number, String semester) {

		this.department = department;
		this.number = number;
		this.semester = semester;
	}

	/**
	 * This method gets the depart ment of course
	 * 
	 * @return department of course
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * This method gets the number of course
	 * 
	 * @return number of course
	 */
	public int getNumber() {
		return number;
	}

	/**
	 * This method gets the semeseter of course
	 * 
	 * @return semester of course
	 */
	public String getSemester() {
		return semester;
	}

	/**
	 * This method set the department of the course
	 * 
	 * @param department The department to be set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * This method set the number of the course
	 * 
	 * @param number The number to be set
	 */
	public void setNumber(int number) {
		this.number = number;
	}

	/**
	 * This method set the semester of the course
	 * 
	 * @param semester The semester to be set
	 */
	public void setSemester(String semester) {
		this.semester = semester;
	}

	/**
	 * This method print the course information
	 * 
	 */
	public String toString() {

		String result = "";
		result = result + String.format("%-3s", department.toUpperCase()) + String.format("%6s", number)
				+ String.format("%8s", semester.toUpperCase());

		return result;

	}

	/**
	 * This method comparing the objects
	 * 
	 */

	public int compareTo(Object o) {
		return 0;
	}

}
