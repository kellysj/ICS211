package HW8;

import java.util.Comparator;
/**a contact comparator
 * 
 * @author Kelly Jones
 *
 */

public class ContactComparator implements Comparator<Contact> {

	/**a method to compare the contact objects
	 * 
	 * @param c1 the first contact to compare
	 * @param c2 the second contact to compare
	 * @return the value of the comparison
	 */
	@Override
	public int compare(Contact c1, Contact c2) {
		int lastN = c1.lastName.compareToIgnoreCase(c2.lastName);
		if(lastN >0){
			return 1;
		}
		else if(lastN<0){
			return -1;
		}
		else if(lastN == 0){
			int firstN = c1.firstName.compareToIgnoreCase(c2.firstName);
			if(firstN > 0){
				return 1;
			}
			else if(firstN < 0){
				return -1;
			}
			else{
				return 0;
			}
		}
		throw new IllegalArgumentException("something wrong with the comparison!");
	}
}
