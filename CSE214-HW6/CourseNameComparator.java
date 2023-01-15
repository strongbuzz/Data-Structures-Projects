import java.util.Comparator;

/*
 * This class Represents a CourseNameComparator which has comparator
 * @author Jinwoo Lee
 */
public class CourseNameComparator implements Comparator {

	/**
	 * This method get the number with comparing two object
	 * 
	 * @return The number from comparator
	 */
	public int compare(Object o1, Object o2) {

		Course e1 = (Course) o1;
		Course e2 = (Course) o2;
		Integer int1 = e1.getNumber();
		Integer int2 = e2.getNumber();
		char a = e1.getDepartment().charAt(0);
		char b = e2.getDepartment().charAt(0);
		if (a > b) {
			return 1;
		} else if (a < b) {
			return -1;
		}

		else if (e1.getDepartment().equals(e2.getDepartment())) {

			if (e1.getNumber() > e2.getNumber()) {
				return 1;
				// return int1.compareTo(int2);}

			} else if (e1.getNumber() < e2.getNumber()) {
				return -1;
			} else {
				return 0;
			}
		}

		else {
			return 0;
		}

	}

}
