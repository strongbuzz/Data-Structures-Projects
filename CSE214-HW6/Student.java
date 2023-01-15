import java.io.Serializable;
import java.util.ArrayList;

/*
 * This class Represents a Student which has webID, List
 * @author Jinwoo Lee
 */
public class Student implements Comparable, Serializable {

	private String webID;
	private ArrayList<Course> List = new ArrayList<Course>(10);

	/**
	 * This is a constructor used to create a new Package object
	 * 
	 * @param initial webID
	 * 
	 */
	public Student(String webID) {

		this.webID = webID;
	}

	/**
	 * This method gets the student's webID
	 * 
	 * @return webID of student
	 */
	public String getWebID() {
		return webID;
	}

	/**
	 * This method gets the student's course list
	 * 
	 * @return course list of student
	 */
	public ArrayList<Course> getList() {
		return List;
	}

	/**
	 * This method set the course list of the student
	 * 
	 * @param List The List to be set
	 */
	public void setList(ArrayList<Course> list) {
		List = list;
	}

	/**
	 * This method set the webID of the student
	 * 
	 * @param webID The webID to be set
	 */
	public void setWebID(String webID) {
		this.webID = webID;
	}

	/**
	 * This method comparing the objects
	 * 
	 */
	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
