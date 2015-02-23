package HW2;

import java.util.Comparator;
/**
 * A testing class for the  arraylist class with sort methods
 * @author Jones,Kelly
 */
public class testing_old<E extends Comparable<E>> {
	public static void main(String args[]){
		System.out.println("Integer tests:");
		testing_old<Integer> testInteger = new testing_old<Integer>();
		//testInteger.go(1);
		//testInteger.go(0);
		testInteger.go(10);
		System.out.println("\nString tests:");
		testing_old<String> testString = new testing_old<String>();
		//testString.go(1);
		//testString.go(0);
		//testString.go(10);
	}
	
	/**
	 * a method to run a list of tests on the myarraylist class
	 * @param n is the length of list that the tests are run on
	 */
	public void go(int n){
		String failout = "";//string for printing error messages
		E to_add = (E)(Integer.valueOf(10));//is an easy value to check if something has changed since it will never normally exist otherwise
		E to_set = (E)(Integer.valueOf(20));//is an easy value to check if something has been set since it will never normally exist otherwise
		Comparator<E> compD = new Comparator<E>(){//generic comparator for comparisons
			@Override
			public int compare(E o1, E o2) 
			{
				return o1.compareTo(o2);
			}
		};
		System.out.println("BEGINNING " + n + " SIZE LIST TESTS");
		MyArrayList<E> run1 = new MyArrayList<E>();
		//FILLING FOR TESTING - the fill only gives values less than 10 or greater than -10,
		//this means that to_add=10 is an easy value to check if something has changed since it will never normally exist otherwise
		try{
			failout = "failed on filltest(n)";
			run1.fill_test(n);
			System.out.println("list filled to length " + n + ",values are: " + run1.printlist());
		} catch(IndexOutOfBoundsException e){//should be thrown in 0 length cases
			System.out.println("fill_test: " + failout + " expected exception to throw for 0 length, otherwise something is broken");
		}
		//OUT OF BOUNDS GET TEST
		try{
			failout = "failed on get(n/2)";
			System.out.println("checking get(" + n/2 + ") value is " + run1.get(n/2));//seeing if method works inside the size of the list
			failout = "failed on get(0)";
			System.out.println("checking get(" + 0 + ") value is " + run1.get(0));//seeing if it works at 0
			failout = "failed on get(n*2)";
			System.out.println("checking get(" + n*2 + ") should be out of bounds...");//now making sure it fails when given an index out of bounds
			run1.get(n*2);
		} catch(IndexOutOfBoundsException e){
			System.out.println("get_test: " + failout + " expected exception to throw for 0 length or intentional out of bounds test");
		}
		//INBOUNDS INDEX ADD TEST - checks to see what happens if a value is added in what is known to be an inbound index: n/2 or size/2
		//uses the value of to_add to check if the value has actually been added
		try{
			System.out.println("checking in bounds add(" + n/2 + "," + to_add + ")...");
			run1.add(n/2,to_add);//seeing if method works inside the size of the list
			if(compD.compare(run1.get(n/2),to_add)==0){//should be thrown in 0 length cases
				System.out.println("indexed add test: " + run1.get(n/2) + "=" + to_add + " add() successful! List is: " + run1.printlist());
			}
			else{
				System.out.println("indexed add test: " + run1.get(n/2) + "!=" + to_add + " add() failed!");
			}
		} catch(IndexOutOfBoundsException e){//should be thrown in 0 length cases
			System.out.println("index add_test: expected exception to throw for 0 length, otherwise something is broken");
		}
		//INBOUNDS REMOVE TEST - removes the previously added value and checks to make sure that it is gone
		try{
			System.out.println("checking in bounds remove(" + n/2 + ")...");//seeing if method works inside the size of the list
			run1.remove(n/2);
			if(compD.compare(run1.get(n/2),to_add)!=0){
				System.out.println("remove test: " + run1.get(n/2) + "!=" + to_add + " remove() successful! List is: " + run1.printlist());
			}
			else{
				System.out.println("remove test: " + run1.get(n/2) + "==" + to_add + " remove() failed!");
			}
		} catch(IndexOutOfBoundsException e){//should be thrown in 0 length cases
			System.out.println("remove_test: expected exception to throw for 0 length, otherwise something is broken");
		}
		//UNINDEXED ADD TEST - adds a value to the end of the list and checks to make sure it was added
		try{
			System.out.println("checking no index add(" + to_add + ")...");
			run1.add(to_add);
			if(compD.compare(run1.get(run1.size()-1),to_add)==0){//compares the last index of the list to the added value, checking if the value was added
				System.out.println("no index add test: " + run1.get(run1.size()-1) + "=" + to_add + " add() successful! List is: " + run1.printlist());
			}
			else{
				System.out.println("no index add test: " + run1.get(run1.size()-1) + "!=" + to_add + " add() failed!");
			}
		} catch(IndexOutOfBoundsException e){//should be thrown in 0 length cases
			System.out.println("unindexed add: expected exception to throw for 0 length, otherwise something is broken");
		}
		//SET TEST - tests the set method using a value of 20, not otherwise possible in the list using the filling methods
		try{
			System.out.println("checking set(n/2,20)...");
			run1.set(n/2,to_set);
			if(compD.compare(run1.get(n/2),to_set)==0){//compares the index of the set to the set value, checking if the value was set
				System.out.println("no index set test: " + run1.get(n/2) + "=" + to_set + " set() successful! List is: " + run1.printlist());
			}
			else{
				System.out.println("no index set test: " + run1.get(n/2) + "!=" + to_set + " set() failed!");
			}
		} catch(IndexOutOfBoundsException e){//should be thrown in 0 length cases
			System.out.println("unindexed add: expected exception to throw for 0 length, otherwise something is broken");
		}
		//SORT TEST - tests various sort methods, not much info given back since it was part of the last HW
		try{
			System.out.println("removing unindexed add...");
			run1.remove(run1.size()-1);
			failout = "fail on selection sort";
			System.out.println("running selection sort, list starts as: " + run1.printlist());
			run1.selectionSort(compD);
			System.out.println("Selection sort complete, list is: " + run1.printlist());
			failout = "fail on insertion sort";
			System.out.println("keeping ordered list for insertion test..." + run1.printlist());
			run1.insertionSort(compD);
			System.out.println("Insertion sort complete, list is: " + run1.printlist());
			run1.fill_random();//randomizes the values in the already sorted list
			System.out.println("randomizing list for sort: " + run1.printlist());		
			run1.insertionSort(compD);
			System.out.println("Insertion sort complete, list is: " + run1.printlist());
			failout = "fail on bubble sort";
			System.out.println("keeping ordered list for bubble sort, list is: " + run1.printlist());
			run1.bubbleSort(compD);
			System.out.println("Bubble sort complete, list is: " + run1.printlist());
			run1.fill_random();//randomizes the values in the already sorted list
			System.out.println("randomizing list for sort: " + run1.printlist());
			run1.bubbleSort(compD);
			System.out.println("Bubbe sort complete, list is: " + run1.printlist());
		} catch(NullPointerException e){//should be thrown in 0 length cases
			System.out.println("Sort: " + failout + " expected exception to throw for 0 length arraylist");
		}
	}
}
