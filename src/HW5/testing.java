package HW5;

import javax.xml.datatype.DatatypeConfigurationException;
/**This class just provides a method for testing the Calculator class as per the assignment instructions
 * 
 * @author Kelly Jones
 *
 */

public class testing {
	
	public static void main(String args[]){
		Calculator run1 = new Calculator("1 2 + 3 * 6 + 2 3 + /");
		try{System.out.println(run1.solve());}catch(DatatypeConfigurationException e){
			System.out.println(e.getMessage());
		}
		Calculator run2 = new Calculator("2.2 7.0 + 3.0 * ");
		try{System.out.println(run2.solve());}catch(DatatypeConfigurationException e){
			System.out.println(e.getMessage());
		}
		Calculator run3 = new Calculator("1 3 5 + -");
		try{System.out.println(run3.solve());}catch(DatatypeConfigurationException e){
			System.out.println(e.getMessage());
		}
		Calculator run4 = new Calculator("k");
		try{System.out.println(run4.solve());}catch(DatatypeConfigurationException e){
			System.out.println(e.getMessage());
		}
	}

}
