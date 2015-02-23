package HW3;

import java.util.Comparator;

public class MyLinkedList<E> implements List211 {
	/**
	 * 
	 * @author Kelly Jones
	 *
	 */
	public static void main(String args[]){
		Integer check = null;
		if(check == null){
		}
		MyLinkedList<Integer> test = new MyLinkedList();
		test.add(Integer.valueOf(1));
		test.add(Integer.valueOf(20));
		test.add(Integer.valueOf(3));
	}
	private static class DLinkedNode<E> {
		private E data;//data at the node
		private DLinkedNode next;//next node for this node
		private DLinkedNode prev;//previous node for this node

		private DLinkedNode(E dataItem, DLinkedNode nextRef, DLinkedNode prevRef) {
			this.data = dataItem;
			this.next = nextRef;
			this.prev = prevRef;
		}

	}
	
	/**
	 * Special case method for the first add, has ways of dealing with setting the tail and head on one node
	 * @param entry the element to be added to the first node
	 */
	private void addFirst(E entry) {
		DLinkedNode newnode = new DLinkedNode(entry,tail,head);
		newnode.next = null;
		newnode.prev = null;
		tail = newnode;
		head = newnode;
		size++;
		//System.out.println("First add works");
	}
	
	/**
	 * Method for adding elements after a given element
	 * @param node the node to add the element after
	 * @param entry the element to add
	 */
	public void addAfter(DLinkedNode<E> node, E entry) {
		if( null != node.next){//checks to see if not at the end of the list
			DLinkedNode temp = new DLinkedNode(entry,node.next,node);
			node.next.prev = temp;
			node.next = temp;
		}
		else{//only for end of list adds, sets tail
			DLinkedNode temp = new DLinkedNode(entry,null,node);
			tail = temp;
			node.next = temp;
		}
		size++;
	}
	
	/**
	 * Method for removing a node after a given node
	 * @param node to be removed
	 * @return the element in the removed node
	 */
	private E removeAfter(DLinkedNode<E> node) {
		if(node != null){
			if(node.prev == null){//checking for the start of the list
				head = node.next;
			}
			else{//otherwise, moving
				node.prev.next = node.next;
			}
			if(node.next == null){//checking for end of list
				tail = node.prev;
			}
			else{//otherwise, move references
				node.next.prev = node.prev;
			}
			size--;
			return node.data;
		}
		else{
			return null;
		}
	}
	
	/**method for removing the very first and only element
	 * 
	 * @return returns the element at that node
	 */
	private E removeFirst() {
		DLinkedNode<E> temp = head;
		if (head != null) {
			head = head.next;
		}
		if (temp != null) {
			size--;
			return temp.data;
		} else {
			return null;
		}
	}
	
	/**Method to return the node at an index, uses prev to move from tail or head when either is faster than the other
	 * 
	 * @param index index to get node from
	 * @return the node returned
	 */
	private DLinkedNode<E> getNode(int index) {
		if(index<=(size-1)/2){
			DLinkedNode<E> node = head;
			for (int i = 0; i < index && node != null; i++) {
				node = node.next;
			}
			return node;
		}
		else{
			DLinkedNode<E> node = tail;
			for (int i = size-1;i>index && node != null; i--){
				node = node.prev;
			}
			
			return node;
		}
	}
	
	private DLinkedNode<E> head;//node at the start of the list
	private DLinkedNode<E> tail;//node at the end of the list
	private int size;//size of the list

	public MyLinkedList() {
		this.head = null;
		this.size = 0;
	}
	
	/**Adds an element to the end of the list
	 * @param e the element to be added
	 */
	public boolean add(Object e) {
		add(size, (E) e);
		return true;
	}

	/**Adds an element to a specified index in the list, changes references so all nodes after are shifted
	 * @param index the index the element is added at
	 * @param element the element to be added to the list
	 */
	public void add(int index, Object element) {
		if (index < 0 || index > size) {
			// throw new IndexOutOfBoundsException(index);
		}
		if (index == 0) {
			addFirst((E) element);
		} else {
			DLinkedNode<E> node = getNode(index - 1);
			//System.out.println("data in gotten node:" + node.data);
			addAfter(node, (E) element);
		}
	}
	
	/**Returns the element located at a node at a specific index
	 * @param index the index of the returned element
	 * @return the element at the given index
	 */
	public Object get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		DLinkedNode<E> node = getNode(index);
		return node.data;
	}
	
	/**Removes the node a a specified index, returns the element at that node
	 * @param the index of the node to be removed
	 * @return returns the element in the removed node
	 */
	public Object remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		if (index == 0) {
			return removeFirst();
		} else {
			DLinkedNode<E> node = getNode(index);
			return removeAfter(node);
		}
	}
	
	/**Sets a node in the list to a given element, overwrites existing values
	 * @return the element originally in the node
	 */
	public Object set(int index, Object element) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		DLinkedNode<E> node = getNode(index);
		E old = node.data;
		node.data = (E) element;
		return old;
	}
	
	/**Gives the size of the list
	 * @return the size of the list
	 */
	public int size() {
		return this.size;
	}

	/**
	 * insertion sort method for putting the values in ascending order
	 * 
	 * @param data
	 *            is the array of the list object to be sorted
	 * @param c
	 *            is the comparator corresponding to the type of the list,
	 *            created in a test method or provided in application of the
	 *            method
	 */
	public void insertionSort(Comparator<? super E> compare) {
		int cur_index;
		E shift_hold;
		int insert_index;
		// checking for conditions that the sort doesn't need to be run for
		if (size == 0 || size == 1) {
			return;
		}
		// starting the sort...
		for (cur_index = 1; cur_index < size; cur_index++) {
			insert_index = cur_index;// index where the current values being
										// compared is moved to
			shift_hold = (E)get(cur_index);// value to be moved
			// if any of the data are null, the array should throw an exception
			// and print a short message
			if (shift_hold == null) {
				throw new NullPointerException();
			}
			// if any of the values are lower than the cur_index value the
			// insert_index is changed to that index
			for (int i = cur_index - 1; i >= 0; i--) {
				if (compare.compare((E)get(cur_index), (E)get(i)) < 0) {
					insert_index = i;
				}
			}
			// if a insert_index value was found, all values inbetween it and
			// the cur_index are moved up 1
			if (cur_index != insert_index) {
				for (int n = cur_index; n > insert_index; n--) {
					set(n, get(n - 1));
				}
				set(insert_index, shift_hold);// the value at cur_index is moved
												// to the insert_index
			}
		}
	}

	/**
	 * bubble sort method for putting the values in ascending order
	 * 
	 * @param data
	 *            is the array of the list object to be sorted
	 * @param c
	 *            is the comparator corresponding to the type of the list,
	 *            created in a test method or provided in application of the
	 *            method
	 */
	public void bubbleSort(Comparator<? super E> compare) {
		// checking for conditions that the sort doesn't need to be run for
		if (size == 0 || size == 1) {
			return;
		}
		// 1st iterator moves from the last value to the 0th since the end fills
		// with the largest values down
		for (int end = size - 1; end >= 0; end--) {
			// 2nd iterator moves up to the end
			for (int i = 0; i < end; i++) {
				// if any of the data are null, the array should throw an
				// exception and print a short message
				if (get(i) == null) {
					//System.out.println("null value in array, cannot sort");
					throw new NullPointerException();
				}
				// if 2 adjacent values are out of order, the larger value is
				// moved to i+1
				if (compare.compare((E)get(i), (E)get(i + 1)) > 0) {
					E hold = (E)get(i);// temp hold variable
					set(i, get(i + 1));
					set(i + 1, hold);
				}
			}
		}
	}

	/**
	 * selection sort method for putting the values in ascending order this
	 * method is used AS IS, provided by the instructor and as such will have no
	 * inline comments
	 * 
	 * @param data
	 *            is the array of the list object to be sorted
	 * @param c
	 *            is the comparator corresponding to the type of the list,
	 *            created in a test method or provided in application of the
	 *            method
	 */
	public void selectionSort(Comparator<? super E> compare) {
		for (int i = 0; i < size - 1; i++) {
			int smallest = i;
			for (int j = i + 1; j < size; j++) {
				if (compare.compare((E)get(smallest), (E)get(j)) > 0) {
					smallest = j;
				}
			}
			E temp = (E)get(smallest);
			set(smallest, get(i));
			set(i, temp);
		}
	}
	/**testing method that will fill the array with random values to a certain point
	 * @param n is the number of values to put in the list
	 */
	public void fill_test(int n){
		for(int i = 0; i<n;i++){
			add(i,(E)(Integer.valueOf(((int)(Math.random()*10)))));
		}
	}
	
	/**testing method that will set the array with random values to a certain point
	 * @param n is the number of values to put in the list
	 */
	public void fill_random(){
		for(int i = 0; i<size;i++){
			set(i,(E)(Integer.valueOf(((int)(Math.random()*10)))));
		}
	}
	/**method to create a string of the contents of the list
	 * @return to_print is a string of the contents of the list
	 */
	public String printlist(){
		String to_print = "[";
		for(int i = 0;i<size;i++){
			to_print = to_print + get(i);
			if(i==size-1){
				to_print = to_print + "]";
			}
			else{
				to_print = to_print + ",";
			}
		}
		return to_print;
	}
	

}
