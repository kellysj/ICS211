package HW4;

import java.util.Comparator;
import java.util.ArrayList;
import java.util.ListIterator;
/**
 * A testing class for the  linkedlist class with sort methods
 * @author Jones,Kelly
 */
public class testingArrayList<E extends Comparable<E>>{
	public static void main(String args[]){
		System.out.println("INTEGER TESTS");
		testingArrayList<Integer> testInteger = new testingArrayList<Integer>();
		testInteger.go(0, "Integer");
		testInteger.go(1, "Integer");
		testInteger.go(10,"Integer");
		//System.out.println("STRING TESTS");
		//testing<String> testString = new testing<String>();
		//testString.go(1,"String");
		//testString.go(0,"String");
		//testString.go(10,"String");
	}
	
	MyArrayList<E> testObject = new MyArrayList<E>();
	ArrayList<E> checkObject = new ArrayList<E>();
	Comparator<E> compD = new Comparator<E>(){//generic comparator for comparisons
		@Override
		public int compare(E o1, E o2) 
		{
			return o1.compareTo(o2);
		}
	};
	public boolean checkorder(){
		for(int i=0;i<testObject.size()-1;i++){
			if(compD.compare((E)testObject.get(i),(E)testObject.get(i))<0){
				return false;
			}
		}
		return true;
	}
	public boolean compareLists(){
		boolean out = true;
		for(int i = 0;i<testObject.size();i++){
			if(compD.compare((E)testObject.get(i), checkObject.get(i))!=0){
				out = false;
				System.out.println("Values at " + i + " - " + (E)testObject.get(i) + ":" + checkObject.get(i) +  " not equal to check object!");
			}
		}
		return out;
	}
	
	/**
	 * a method to run a list of tests on the mylinkedlist class
	 * @param n is the length of list that the tests are run on
	 */

	public void go(int n, String testprefix){
		testprefix = n + " " + testprefix;

		String failOut = "";//string for printing error messages
		E to_add = (E)(Integer.valueOf(10));//is an easy value to check if something has changed since it will never normally exist otherwise
		E to_set = (E)(Integer.valueOf(20));//is an easy value to check if something has been set since it will never normally exist otherwise

		System.out.println(testprefix + ":\t" +  "BEGINNING " + n + " SIZE LIST TESTS");

		//FILLING FOR TESTING - the fill only gives values less than 10 or greater than -10,
		//this means that to_add=10 is an easy value to check if something has changed since it will never normally exist otherwise
		try{
			failOut = "failed on filling with random stuff";
			for(int i = 0; i<n;i++){
				E valueToAdd = (E)(Integer.valueOf(((int)(Math.random()*10))));
				testObject.add(valueToAdd);
				checkObject.add(valueToAdd);
			}
			System.out.println(testprefix + ":\t" +  "list filled to length " + n + ",values are: " + testObject.printlist());
		} catch(IndexOutOfBoundsException e){//should be thrown in 0 length cases
			System.out.println(testprefix + ":\t" +  "fill_test: " + failOut + " expected exception to throw for 0 length, otherwise something is broken");
		}
		compareLists();
		//Iterator Testing
		HW4.ListIterator<E> linkedcheck = testObject.listIterator();
		String linS = "[";
		while(linkedcheck.hasNext()){//building a regular ordered string using the iterator
			linS = linS + linkedcheck.next();
			if(linkedcheck.hasNext()){
				linS = linS + ",";
			}
		}
		linS = linS + "]";
		System.out.println(testprefix + ":\t"+ "iterator tests" +  "forwards using Iterator: " + linS);
		linS = "";
		for(E temp: testObject){//building a string using for each
			if(linS != ""){
				linS = linS + ",";
			}
			linS = linS + temp;
		}
		System.out.println(testprefix + ":\t"+ "iterator tests" +  "forwards using for each: " + linS);
		linkedcheck = testObject.listIterator(testObject.size());
		linS = "[";
		while(linkedcheck.hasPrevious()){//building a backwards string
			linS = linS + linkedcheck.previous();
			if(linkedcheck.hasPrevious()){
				linS = linS + ",";
			}
		}
		linS = linS + "]";
		System.out.println(testprefix + ":\t"+ "iterator tests" +  "backwards from size: " + linS);
		linkedcheck = testObject.listIterator(testObject.size()/2);
		linS = "[";
		while(linkedcheck.hasPrevious()){//building a backwards string starting from the middle
			linS = linS + linkedcheck.previous();
			if(linkedcheck.hasPrevious()){
				linS = linS + ",";
			}
		}
		linS = linS + "]";
		System.out.println(testprefix  +":\t"+ "iterator tests" +  "backwards from size/2: " + linS);
		//OUT OF BOUNDS GET TEST
		try{
			failOut = "failed on get(n/2)";
			System.out.println(testprefix + ":\t" +  "checking get(" + n/2 + ") value is " + testObject.get(n/2) + ", check is " + checkObject.get(n/2));//seeing if method works inside the size of the list
			failOut = "failed on get(0)";
			System.out.println(testprefix + ":\t" +  "checking get(" + 0 + ") value is " + testObject.get(0) + ", check is " + checkObject.get(0));//seeing if it works at 0
			failOut = "failed on get(n*2)";
			System.out.println(testprefix + ":\t" +  "checking get(" + n*2 + ") should be out of bounds...");//now making sure it fails when given an index out of bounds
			testObject.get(n*2);
		} catch(IndexOutOfBoundsException e){
			System.out.println(testprefix + ":\t" +  "get_test: " + failOut + " expected exception to throw for 0 length or intentional out of bounds test");
		}
		compareLists();
		//INBOUNDS INDEX ADD TEST - checks to see what happens if a value is added in what is known to be an inbound index: n/2 or size/2
		//uses the value of to_add to check if the value has actually been added
		try{
			System.out.println(testprefix + ":\t" +  "checking in bounds add(" + n/2 + "," + to_add + ")...");
			testObject.add(n/2,to_add);//seeing if method works inside the size of the list
			checkObject.add(n/2,to_add);
			if(compD.compare((E)testObject.get(n/2),to_add)==0){//should be thrown in 0 length cases
				System.out.println(testprefix + ":\t" +  "indexed add test: " + testObject.get(n/2) + "=" + to_add + " add() successful! List is: " + testObject.printlist());
			}
			else{
				System.out.println(testprefix + ":\t" +  "indexed add test: " + testObject.get(n/2) + "!=" + to_add + " add() failed!");
			}
		} catch(IndexOutOfBoundsException e){//should be thrown in 0 length cases
			System.out.println(testprefix + ":\t" +  "index add_test: expected exception to throw for 0 length, otherwise something is broken");
		}
		compareLists();
		//INBOUNDS REMOVE TEST - removes the previously added value and checks to make sure that it is gone
		try{
			System.out.println(testprefix + ":\t" +  "checking in bounds remove(" + n/2 + ")...");//seeing if method works inside the size of the list
			testObject.remove(n/2);
			checkObject.remove(n/2);
			if(compD.compare((E)testObject.get(n/2),to_add)!=0){
				System.out.println(testprefix + ":\t" +  "remove test: " + testObject.get(n/2) + "!=" + to_add + " remove() successful! List is: " + testObject.printlist());
			}
			else{
				System.out.println(testprefix + ":\t" +  "remove test: " + testObject.get(n/2) + "==" + to_add + " remove() failed!");
			}
			compareLists();
		} catch(NullPointerException e){//should be thrown in 0 length cases
			System.out.println(testprefix + ":\t" +  "remove_test: expected exception to throw for 0 length, otherwise something is broken");
		}
		//UNINDEXED ADD TEST - adds a value to the end of the list and checks to make sure it was added
		try{
			System.out.println(testprefix + ":\t" +  "checking no index add(" + to_add + ")...");
			testObject.add(to_add);
			checkObject.add(to_add);
			if(compD.compare((E)testObject.get(testObject.size()-1),to_add)==0){//compares the last index of the list to the added value, checking if the value was added
				System.out.println(testprefix + ":\t" +  "no index add test: " + testObject.get(testObject.size()-1) + "=" + to_add + " add() successful! List is: " + testObject.printlist());
			}
			else{
				System.out.println(testprefix + ":\t" +  "no index add test: " + testObject.get(testObject.size()-1) + "!=" + to_add + " add() failed!");
			}
		} catch(IndexOutOfBoundsException e){//should be thrown in 0 length cases
			System.out.println(testprefix + ":\t" +  "unindexed add: expected exception to throw for 0 length, otherwise something is broken");
		}
		compareLists();
		//SET TEST - tests the set method using a value of 20, not otherwise possible in the list using the filling methods
		try{
			System.out.println(testprefix + ":\t" +  "checking set(n/2,20)...");
			testObject.set(n/2,to_set);
			checkObject.set(n/2,to_set);
			if(compD.compare((E)testObject.get(n/2),to_set)==0){//compares the index of the set to the set value, checking if the value was set
				System.out.println(testprefix + ":\t" +  "set test: " + testObject.get(n/2) + "=" + to_set + " set() successful! List is: " + testObject.printlist());
			}
			else{
				System.out.println(testprefix + ":\t" +  "set test: " + testObject.get(n/2) + "!=" + to_set + " set() failed!");
			}
		} catch(IndexOutOfBoundsException e){//should be thrown in 0 length cases
			System.out.println(testprefix + ":\t" +  "set test: expected exception to throw for 0 length, otherwise something is broken");
		}
		compareLists();
		//SORT TEST - tests various sort methods, not much info given back since it was part of the last HW
		try{
			System.out.println(testprefix + ":\t" +  "removing unindexed add...");
			testObject.remove(testObject.size()-1);
			checkObject.remove(checkObject.size()-1);
			failOut = "fail on selection sort";
			System.out.println(testprefix + ":\t" +  "running selection sort, list starts as: " + testObject.printlist());
			testObject.selectionSort(compD);			
			System.out.println(testprefix + ":\t" +  "Selection sort complete, list is: " + testObject.printlist());
			System.out.println(testprefix + ":\t" +  "List is in order:" + checkorder());
			failOut = "fail on insertion sort";
			System.out.println(testprefix + ":\t" +  "keeping ordered list for insertion test..." + testObject.printlist());
			testObject.insertionSort(compD);
			System.out.println(testprefix + ":\t" +  "Insertion sort complete, list is: " + testObject.printlist());
			System.out.println(testprefix + ":\t" +  "List is in order:" + checkorder());
			testObject.fill_random();//randomizes the values in the already sorted list
			System.out.println(testprefix + ":\t" +  "randomizing list for sort: " + testObject.printlist());		
			testObject.insertionSort(compD);
			System.out.println(testprefix + ":\t" +  "Insertion sort complete, list is: " + testObject.printlist());
			failOut = "fail on bubble sort";
			System.out.println(testprefix + ":\t" +  "keeping ordered list for bubble sort, list is: " + testObject.printlist());
			testObject.bubbleSort(compD);
			System.out.println(testprefix + ":\t" +  "Bubble sort complete, list is: " + testObject.printlist());
			System.out.println(testprefix + ":\t" +  "List is in order:" + checkorder());
			testObject.fill_random();//randomizes the values in the already sorted list
			System.out.println(testprefix + ":\t" +  "randomizing list for sort: " + testObject.printlist());
			testObject.bubbleSort(compD);
			System.out.println(testprefix + ":\t" +  "Bubbe sort complete, list is: " + testObject.printlist());
			System.out.println(testprefix + ":\t" +  "List is in order:" + checkorder());
		} catch(NullPointerException e){//should be thrown in 0 length cases
			System.out.println(testprefix + ":\t" +  "Sort: " + failOut + " expected exception to throw for 0 length linkedlist");
		}
		while(testObject.size()!=0){
			testObject.remove(0);
		}
		while(checkObject.size()!=0){
			checkObject.remove(0);
		}
	}
}
