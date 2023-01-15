import java.util.Comparator;

/*
 * This class Represents a SemesterComparator which has comparator
 * @author Jinwoo Lee
 */
public class SemesterComparator implements Comparator {

	/**
	 * This method get the number with comparing two object
	 * 
	 * @return The number from comparator
	 */
	public int compare(Object o1, Object o2) {
		Course e1 = (Course) o1;
		Course e2 = (Course) o2;
		int i = Integer.parseInt(e1.getSemester().substring(4));
		int ii = Integer.parseInt(e2.getSemester().substring(4));

		int iii = Integer.parseInt(e1.getSemester().substring(3));
		int iiii = Integer.parseInt(e2.getSemester().substring(3));

		if (i == ii) {
			if (iii > iiii) {
				return 1;
			} else if (iii < iiii) {
				return -1;
			} else {
				return 0;
			}
		}

		else if (i > ii && iii == iiii) {
			return 1;
		}

		else if (i < ii) {
			return -1;
		}

		return 0;

	}
}
// if (e1.getSemester() == e2.getSemester())
// return 0;