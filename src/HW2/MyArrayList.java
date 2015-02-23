package HW2;
import java.util.*;

/**
 * A simple arraylist class with sort methods
 * @author Jones,Kelly
 */
public class MyArrayList<E> implements List211<E> {
	private E[] data;
	private int size;
	public static void main(String args[]){
		MyArrayList<Integer> run = new MyArrayList<Integer>();
		run.fill_test(10);
		run.printlist();
		System.out.println(run.get(5));
		run.add(5,2);
		run.printlist();
		System.out.println(run.get(5));
		run.remove(5);
		run.printlist();
		System.out.println(run.get(5));
		run.add(10);
		run.printlist();
		Comparator<Integer> compD = new Comparator<Integer>(){
			@Override
			public int compare(Integer o1, Integer o2) 
			{
				return o1.compareTo(o2);
			}
		};
		run.selectionSort(compD);
		run.printlist();
	}
	
	/**
	 * Constructor for the arraylist, requires a size to initialize
	 */
	MyArrayList(){
		this.size = 0;
		data = (E[]) new Object[10];//holds everything
	}
	
	/**This adds an element to the end of the list
	 *@param e is the element to add
	 *@return true if element is added
	 *@return false if element fails to add
	 */
	public boolean add(E e){
		add(size,e);
		return true;
	}
	
	/**
	 * this adds an element to the list at a specified index and preserves the values in the list by shifting all values after index to i+1
	 * @param index is the index the element is added at
	 * @param element is the element added to the list
	 */
	public void add(int index, E element){
		checkIndex(index);
		reallocate();
		if(index==0 && size==0){//special case for otherwise useless 0 length lists
			size++;
			data[0] = element;
		}
		else{			
			for(int i = size;i>index;i--){//moving all the values up one from the addition index
				data[i]=data[i-1];
			}
			data[index] = element;
			size++;
		}
	}
	
	/**
	 *this returns the element at a specified index
	 */
	public E get(int index){
		checkIndex(index);
		return data[index];
	}
	
	/**This removes an element at a specific index and shrinks the list
	 * @param index is the index to remove the value at
	 * @return the element at the removal index
	 */
	public E remove(int index){
		checkIndex(index);
		  E removed = data[index];
		  for(int i = index;i<size-1;i++){//moving the data into the space made by removal
			  data[i]=data[i+1];
		  }
		  size = size -1;
		  return removed;
	}
	
	/**Sets the element at a specific index to the provided element
	 * @param index is the index in the list to replace the value at
	 * @param element is the element to put in the index
	 * @return is the value that was originally at the index
	 */
	public E set(int index, E element){
		 checkIndex(index);
		  E removed = data[index];
		  data[index]=element;
		  return removed;
	}
	
	/**Gives the size of the list
	 * @return an int representing the size of the list
	 */
	public int size(){
		  return size;//returns the size variable
	}
	
	
	/**method to create a string of the contents of the list
	 * @return to_print is a string of the contents of the list
	 */
	public String printlist(){
		String to_print = "[";
		for(int i = 0;i<size;i++){
			to_print = to_print + data[i];
			if(i==size-1){
				to_print = to_print + "]";
			}
			else{
				to_print = to_print + ",";
			}
		}
		return to_print;
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
	
	/**
	 * makes the data[] array larger and copies over the values
	 */
	public void reallocate(){
		if(data.length<=size*2){
			E[] temp = (E[]) new Object[size*2];//doubling the size of the data array, lowers usage of reallocate 
			for(int i = 0;i<size;i++){//copying original values to new array
				temp[i] = data[i];
			}
			data = temp;//seting the array to the larger copy
		}
	}
	
	/**
	 * if an out of bounds index is given to another method, this method throws an exception before anything happens
	 * @param index is the index checked
	 */
	public void checkIndex(int index){
		if(index>size||index<0){
			System.out.println("index " + index + " not in size " + size);
			throw new IndexOutOfBoundsException();
		}
	}
	  
	/**insertion sort method for putting the values in ascending order
	 * @param data is the array of the list object to be sorted
	 * @param c is the comparator corresponding to the type of the list, created in a test method or provided in application of the method
	 */
	public void insertionSort(Comparator<? super E> compare){
		int cur_index;
		E shift_hold;
		int insert_index;
		//checking for conditions that the sort doesn't need to be run for
		if(size == 0 || size == 1){
			return;
		}
		//starting the sort...
		for(cur_index = 1;cur_index<size;cur_index++){
			insert_index = cur_index;//index where the current values being compared is moved to
			shift_hold = get(cur_index);//value to be moved
			//if any of the data are null, the array should throw an exception and print a short message
			if(shift_hold == null){
				throw new NullPointerException();
			}
			//if any of the values are lower than the cur_index value the insert_index is changed to that index
			for(int i = cur_index-1;i>=0;i--){
				if(compare.compare(get(cur_index),get(i))<0){
					insert_index = i;
				}
			}
			//if a insert_index value was found, all values inbetween it and the cur_index are moved up 1 
			if(cur_index != insert_index){
				for(int n = cur_index;n>insert_index;n--){
					set(n,get(n-1));
				}
				set(insert_index,shift_hold);//the value at cur_index is moved to the insert_index
			}
		}
	}
	
	/**bubble sort method for putting the values in ascending order
	 * @param data is the array of the list object to be sorted
	 * @param c is the comparator corresponding to the type of the list, created in a test method or provided in application of the method
	 */
	public void bubbleSort(Comparator<? super E> compare){
		//checking for conditions that the sort doesn't need to be run for
			if(size == 0 || size == 1){
				return;
			}
		//1st iterator moves from the last value to the 0th since the end fills with the largest values down
		for(int end = size-1;end>=0;end--){
			//2nd iterator moves up to the end
			for(int i = 0;i<end;i++){
				//if any of the data are null, the array should throw an exception and print a short message
				if(get(i) == null){
					System.out.println("null value in array, cannot sort");
					throw new NullPointerException();
				}
				//if 2 adjacent values are out of order, the larger value is moved to i+1
				if(compare.compare(get(i),get(i+1))>0){
					E hold = get(i);//temp hold variable
					set(i,get(i+1));
					set(i+1,hold);
				}
			}
		}
	}
	
	/**selection sort method for putting the values in ascending order
	 * this method is used AS IS, provided by the instructor and as such will have no inline comments
	 * @param data is the array of the list object to be sorted
	 * @param c is the comparator corresponding to the type of the list, created in a test method or provided in application of the method
	 */
	public void selectionSort(Comparator<? super E> compare){
        for (int i = 0; i < size - 1; i++)
        {
            int smallest = i;
            for (int j = i + 1; j < size; j++){
                if (compare.compare(get(smallest),get(j)) > 0){
                    smallest = j;
                }
            }
            E temp = get(smallest);
            set(smallest,get(i));
            set(i,temp);
        }
	}
}