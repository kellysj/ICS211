package HW5;

import javax.xml.datatype.DatatypeConfigurationException;
import java.util.Scanner;

public class UserInputCalculator {
	int failcount = 0;//an integer to keep track of mistakes
	public static void main(String args[]){
		UserInputCalculator go = new UserInputCalculator();
		go.start();
		
	}
	/**This method starts a console input interface
	 * 
	 */
	public void start(){		
		System.out.println("Input a valid postfix expression of a single datatype (type exit to quit):");
		String input = "";
		Scanner scanIn = new Scanner(System.in);
		input = scanIn.nextLine();
		if(input.matches("[ \\./*-+\\d]*")&&!input.isEmpty()){//basic input check to make sure nothing crazy was entered
			Calculator run = new Calculator(input);
			try{//trying to run the postfix calculation
				String out = "total is: " + run.solve();
				if(failcount>0){
					out = "Congradulations! You managed to enter a valid expresion after " + failcount + " tries\n" + out;
					if(failcount>=3){
						out = out + "\ntruly a testament to the determined tester or the complete failure of higher education...";
					}
				}
			System.out.println(out);
			return;
			}catch(DatatypeConfigurationException e){//exception for disparate datatypes entered
				System.out.println("please make sure number of operators matches numbers and only one data type is used...");
				failcount++;
				start();
			}catch(IllegalArgumentException e){//exception for bad postfix expressions
				System.out.println("not a vaild postfix expression...");
				failcount++;
				start();
			}
		}
		else if (input.equals("exit")){//exits the input dialogue so you can get back to all your important eclipse console work
			System.out.println("exiting...");
			return;
		}
		else if(input.isEmpty()){//the user didn't type anything in, why would they do such a thing?
			System.out.println("Why not type something in?");
			failcount++;
			start();
		}
		else{//the user didn't enter in anything valid
			System.out.println("not a valid input");
			failcount++;
			start();
		}
	}

}
