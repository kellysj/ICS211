package HW5;

import java.util.LinkedList;

/**A stack object created specifically for this project using the linkedlist object from java.
 * in this specific stack, the "head" of the list is being used as the "top" of the stack so all additions or removals
 * stay n^0. Since head is private on the list, it is referred to as index 0.
 * 
 * @author Kelly Jones
 *
 * @param <E> the generic type of the stack, probably Integer or Double for this project
 */
public class MyStack<E>{
	LinkedList stackList = new LinkedList();//instantiating a MyLinkedList to act as the stack
	/**checks the stack to see if it is empty
	 * 
	 * @return a boolean that tells whether or not the stack has any content
	 */
	public boolean isEmpty(){
		return stackList.size() == 0;
	}
	/**a method to check on the top of the stack without altering the contents of the stack
	 * 
	 * @return the element at the top of the stack
	 */
	public E peek(){
		return (E)stackList.get(0);
	}
	/**a method to add something to the top of the stack
	 * 
	 * @param in the element to be added as the top of the stack
	 */
	public void push(E in){
		stackList.add(0, in);
	}
	/**a method to remove the current top of the stack and return it
	 * 
	 * @return the element in the top of the stack
	 */
	public E pop(){
		return (E)stackList.remove(0);
	}
	/**returns the size of the stack
	 * 
	 * @return the size of the stack
	 */
	public int size(){
		return stackList.size();
	}
}
