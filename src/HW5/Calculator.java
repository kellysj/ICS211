package HW5;

import java.util.Stack;

import javax.xml.datatype.DatatypeConfigurationException;

public class Calculator {

	String input;// the input provided to the method
	// the easiest way I could get doubles and integers to work was to make 2
	// seperate stacks, one for each data type
	// there may be an easier way to do this by making Calculator extend a class
	// that can use mathematical operators
	// but it was out of the scope of the homework
	MyStack<Integer> integerS;// a stack for integers
	MyStack<Double> doubleS;// a stack for doubles
	String[] inputArray;

	Calculator(String in) {
		input = in;
		doubleS = new MyStack<Double>();
		integerS = new MyStack<Integer>();
	}

	/**
	 * A method to return the calculated value
	 * 
	 * @return returns the calculated value as an integer or a double
	 */

	public Number solve() throws DatatypeConfigurationException {
		Number out = null;
		try {
			calc();

			if (doubleS.size() == 1) {
				out = doubleS.pop();
			} else if (integerS.size() == 1) {
				out = integerS.pop();
			}

		} catch (DatatypeConfigurationException e) {
			throw e;
		}
		return out;
	}

	/**
	 * A method that parses the input for the class and performs postfix
	 * calculations. Does not return a value *
	 * 
	 * @throws DatatypeConfigurationException
	 *             an exception for attempting to add different number types
	 */
	public void calc() throws DatatypeConfigurationException {// this probably
																// shouldn't
																// throw 2 kinds
																// of
																// exceptions...
																// but it does
		String[] inputArray = input.split("\\s+");// splits up the input based
													// on whitespace
		String printout = "";
		for (String n : inputArray) {
			printout = n + " ";

			if (n.matches("[+/*-]")) {// regex to find operators, will perform
										// postfix calculation on the stacks if
										// one is found
				printout = printout + "is an operator";
				if (n.equals("+")) {
					add();
					printout = printout + " that adds";
				}
				if (n.equals("-")) {
					subtract();
					printout = printout + " that subtracts";
				}
				if (n.equals("*")) {
					multiply();
					printout = printout + " that multiplies";
				}
				if (n.equals("/")) {
					divide();
					printout = printout + " that divides";
				}
			}

			else if (n.matches("\\d+(\\.\\d+)?")) {// regex to find numbers

				if (n.matches("\\d+\\.\\d+")) {// if the number is a double, the
												// double stack is used
					printout = printout + "is a double";
					doubleS.push(Double.valueOf(n));
				} else {// otherwise the integer stack is used, the string has
						// already been checked to make sure it only contains
						// numbers
					printout = printout + "is a integer";
					integerS.push(Integer.valueOf(n));
				}

			} else {
				throw new IllegalArgumentException();
			}
			if (!integerS.isEmpty() && !doubleS.isEmpty()) {// this can ensure
															// that different
															// data types cannot
															// be used
				throw new DatatypeConfigurationException();
			}
		}
	}

	/**
	 * A method to add things in a stack and add them back
	 * 
	 */
	public void add() throws NullPointerException {
		if (integerS.size() >= 2) {
			Integer a = integerS.pop();
			Integer b = integerS.pop();
			integerS.push(b + a);
		} else if (doubleS.size() >= 2) {
			Double a = doubleS.pop();
			Double b = doubleS.pop();
			doubleS.push(b + a);
		} else {
			throw new NullPointerException();
		}
	}

	/**
	 * A method to subtract things in a stack and add them back
	 * 
	 */
	public void subtract() {
		if (integerS.size() >= 2) {
			Integer a = integerS.pop();
			Integer b = integerS.pop();
			integerS.push(b - a);
		} else if (doubleS.size() >= 2) {
			Double a = doubleS.pop();
			Double b = doubleS.pop();
			doubleS.push(b - a);
		} else {
			throw new NullPointerException();
		}
	}

	/**
	 * A method to divide things in a stack and add them back
	 * 
	 */

	public void divide() {
		if (integerS.size() >= 2) {
			Integer a = integerS.pop();
			Integer b = integerS.pop();
			integerS.push(b / a);
			// System.out.println(integerS.peek());
		} else if (doubleS.size() >= 2) {
			Double a = doubleS.pop();
			Double b = doubleS.pop();
			doubleS.push(b / a);
			// System.out.println(doubleS.peek());
		} else {
			throw new NullPointerException();
		}
	}

	/**
	 * A method to multiply things in a stack and add them back
	 * 
	 */

	public void multiply() {
		if (integerS.size() >= 2) {
			Integer a = integerS.pop();
			Integer b = integerS.pop();
			integerS.push(b * a);
		} else if (doubleS.size() >= 2) {
			Double a = doubleS.pop();
			Double b = doubleS.pop();
			doubleS.push(b * a);
		} else {
			throw new NullPointerException();
		}
	}

	/**
	 * A method to print the stack contents
	 * 
	 */

	public void printStacks() {
		String out = "stack is:\n";


	}

}
