package HW6;

import java.util.NoSuchElementException;

public class CircularArrayQueue<E> implements Queue211<E> {
	
	/**
	 * @param args
	 */
	public static void main(String args[]){
		CircularArrayQueue<Integer> test = new CircularArrayQueue(5);
		String listIs = "";
		System.out.println(test.size());
		test.offer(10);
		
		System.out.println(test.peek() + ":" + test.size() + ":" + test.queuesize);
		System.out.println("head:" + test.head.data);
		System.out.println("tail:" + test.tail.data);
		listIs = listIs + test.tail.data + ",";
		test.offer(12);
		System.out.println(test.peek() + ":" + test.size() + ":" + test.queuesize);
		System.out.println("head:" + test.head.data);
		System.out.println("tail:" + test.tail.data);
		listIs = listIs + test.tail.data + ",";
		test.offer(15);
		System.out.println(test.peek() + ":" + test.size() + ":" + test.queuesize);
		System.out.println("head:" + test.head.data);
		System.out.println("tail:" + test.tail.data);
		listIs = listIs + test.tail.data + ",";
		test.offer(16);
		System.out.println(test.peek() + ":" + test.size() + ":" + test.queuesize);
		System.out.println("head:" + test.head.data);
		System.out.println("tail:" + test.tail.data);
		listIs = listIs + test.tail.data + ",";
		test.offer(17);
		System.out.println(test.peek() + ":" + test.size() + ":" + test.queuesize);
		System.out.println("head:" + test.head.data);
		System.out.println("tail:" + test.tail.data);
		listIs = listIs + test.tail.data + ":";
		test.offer(18);
		System.out.println(test.peek() + ":" + test.size() + ":" + test.queuesize);
		System.out.println("head:" + test.head.data);
		System.out.println("tail:" + test.tail.data);
		listIs = listIs + test.peek() + ",";
		System.out.println(test.remove() + ":" + test.size() + ":" + test.queuesize);
		System.out.println("head:" + test.head.data);
		System.out.println("tail:" + test.tail.data);
		listIs = listIs + test.peek() + ",";
		System.out.println(test.remove() + ":" + test.size() + ":" + test.queuesize);
		System.out.println("head:" + test.head.data);
		System.out.println("tail:" + test.tail.data);
		listIs = listIs + test.peek() + ",";
		System.out.println(test.remove() + ":" + test.size() + ":" + test.queuesize);
		System.out.println("head:" + test.head.data);
		System.out.println("tail:" + test.tail.data);
		listIs = listIs + test.peek() + ",";
		System.out.println(test.remove() + ":" + test.size() + ":" + test.queuesize);
		System.out.println("head:" + test.head.data);
		System.out.println("tail:" + test.tail.data);
		listIs = listIs + test.peek();
		System.out.println(test.remove() + ":" + test.size() + ":" + test.queuesize);
		System.out.println(listIs);
		System.out.println(test.poll() + ":" + test.size() + ":" + test.queuesize);
		System.out.println("head:" + test.head.data);
		System.out.println("tail:" + test.tail.data);
	}


	private DLinkedNode<E> head;// node at the start of the list
	private DLinkedNode<E> tail;// node at the end of the list
	private int size;// size of the list
	private int queuesize;
	
	CircularArrayQueue(int fixedSize){
		queuesize = fixedSize;
		head = null;
		tail = null;
		size = 0;
	}

	/**
	 * adds e to the end of the queue. May throw an IllegalStateException if the
	 * queue is full.
	 * 
	 * 
	 */
	public boolean add(E e) {
		if (size == 0) {
			addFirst(e);
			//System.out.println("add: size was zero, first add");
			return true;
		} 
		else if(size<queuesize) {
			addAfter(tail, e);
			//System.out.println("add: size was less that queue size but larger than zero, afteradd");
			return true;
		}
		else if(size==queuesize){
			throw new IllegalStateException("size cannot exceed fixed queue size, cannot add!");
		}
		else{
			//System.out.println("something is wrong");
			return false;
			
		}
	}

	/**Retrieves, but doesn't remove the head of the queue. Throws NoSuchElementException if queue is empty.
	 * 
	 * 
	 */
	public E element() {
		if(head!=null){
			return head.data;
		}
		else{
			throw new NoSuchElementException("nothing at the head of the queue!");
		}
	}

	/**adds e to the end of the queue. Returns false if the queue is full.
	 * 
	 */
	public boolean offer(E e) {
		if (size == 0) {
			addFirst(e);
			return true;
		} 
		else if(size<queuesize) {
			addAfter(tail, e);
			return true;
		}
		else if(size==queuesize){
			return false;
		}
		else{
			return false;
		}
	}

	/**Retrieves, but doesn't remove the head of the queue. Return null if queue is empty.
	 * 
	 */
	public E peek() {
		if(head!=null){
			return head.data;
		}
		else{
			return null;
		}
	}

	/**Retrieves and removes the head of the queue. Returns null if the queue is empty.
	 * 
	 */
	public E poll() {
		if(size==1){
			return (E)removeFirst();
		}
		else if(size>0){
			return removeAfter(head);
		}
		else{
			return null;
		}
	}

	/**Retrieves and removes the head of the queue. Throws NoSuchElementException if queue is empty.
	 * 
	 */
	public E remove() {
		if(size==1){
			return (E)removeFirst();
		}
		else if(size>0){
			return removeAfter(head);
		}
		else{
			throw new NoSuchElementException("No head of queue to remove!");
		}
	}

	/**Returns the size of the queue.
	 * 
	 */
	public int size() {		
		return size;
	}

	

	/**
	 * DLinked Node class to build the Dlinked list
	 */
	private static class DLinkedNode<E> {
		private E data;// data at the node
		private DLinkedNode next;// next node for this node
		private DLinkedNode prev;// previous node for this node

		private DLinkedNode(E dataItem, DLinkedNode nextRef, DLinkedNode prevRef) {
			this.data = dataItem;
			this.next = nextRef;
			this.prev = prevRef;
		}

	}

	/**
	 * Special case method for the first add, has ways of dealing with setting
	 * the tail and head on one node
	 * 
	 * @param entry
	 *            the element to be added to the first node
	 */
	private void addFirst(E entry) {
		DLinkedNode newnode = new DLinkedNode(entry, tail, head);
		tail = newnode;
		head = newnode;
		size++;
		//System.out.println("firstadded");
	}

	/**
	 * Method for adding elements after a given element
	 * 
	 * @param node
	 *            the node to add the element after
	 * @param entry
	 *            the element to add
	 */
	public void addAfter(DLinkedNode<E> node, E entry) {
		if (node == tail) {// checks to see if not at the end of the list
			DLinkedNode temp = new DLinkedNode(entry, head, node);
			tail = temp;
			node.next = temp;
			//System.out.println("add after next is head");
		} else {// only for end of list adds, sets tail
			DLinkedNode temp = new DLinkedNode(entry, node.next, node);
			node.next.prev = temp;
			node.next = temp;
			//System.out.println("add after next is normal");
		}
		size++;
	}

	/**
	 * method for adding an element before something, useful on 1 length arrays
	 * 
	 * @param node
	 *            to add before
	 * @param for the ned added node
	 */
	public void addBefore(DLinkedNode<E> node, E entry) {
		if (node.prev == tail) {
			DLinkedNode temp = new DLinkedNode(entry, node, tail);
			head = temp;
			node.prev = temp;
		} else {
			DLinkedNode temp = new DLinkedNode(entry, node, node.prev);
			node.prev.next = temp;
			node.prev = temp;
		}
		size++;
	}

	/**
	 * Method for removing a node after a given node
	 * 
	 * @param node
	 *            to be removed
	 * @return the element in the removed node
	 */
	private E removeAfter(DLinkedNode<E> node) {
		if (node != null) {
			if (node == head) {// checking for the start of the list
				head = node.next;
				head.prev = tail;
			} else {// otherwise, moving
				node.prev.next = node.next;
			}
			if (node == tail) {// checking for end of list
				tail = node.prev;
				tail.next = head;
			} else {// otherwise, move references
				node.next.prev = node.prev;
			}
			size--;
			return node.data;
		} else {
			return null;
		}
	}

	/**
	 * method for removing the very first and only element
	 * 
	 * @return returns the element at that node
	 */
	private E removeFirst() {
		DLinkedNode<E> temp = head;
		if (head != null) {
			head = null;
		}
		if (temp != null) {
			size--;
			return temp.data;
		} else {
			return null;
		}
	}

	/**
	 * Method to return the node at an index, uses prev to move from tail or
	 * head when either is faster than the other
	 * 
	 * @param index
	 *            index to get node from
	 * @return the node returned
	 */
	private DLinkedNode<E> getNode(int index) {
		if (index <= (size - 1) / 2) {
			DLinkedNode<E> node = head;
			for (int i = 0; i < index && node != null; i++) {
				node = node.next;
			}
			return node;
		} else {
			DLinkedNode<E> node = tail;
			for (int i = size - 1; i > index && node != null; i--) {
				node = node.prev;
			}

			return node;
		}
	}

}
