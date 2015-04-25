package HW10;

import java.util.Comparator;


public class proto{
public static void main(String args[]){
	int length = 100;
	proto run = new proto();
	Double testD1[] = run.arraygenD(length);
	Double testD2[] = run.arraygenD(length);
	Double testD3[] = run.arraygenD(length);
	Double testD4[] = run.arraygenD(4);
	String testS1[] = run.arraygenS(length);
	String testS2[] = run.arraygenS(length);
	String testS3[] = run.arraygenS(length);
	//String testS4[] = run.arraygenS(4);
	Double testz[] = new Double[0];
	Double testn[] = new Double[5];
	Comparator<String> compS = new Comparator<String>(){
		@Override
		public int compare(String o1, String o2) 
		{
			return o1.compareTo(o2);
		}
	};
	Comparator<Double> compD = new Comparator<Double>(){
		@Override
		public int compare(Double o1, Double o2) 
		{
			return o1.compareTo(o2);
		}
	};
	System.out.println("Double");
	run.mergeSort(testD4, compD);
	/**
	run.insertionSort(testD2, compD);
	run.selectionSort(testD1, compD);
	run.bubbleSort(testD3, compD);	
	System.out.println("String");
	run.insertionSort(testS2, compS);
	run.selectionSort(testS1, compS);
	run.bubbleSort(testS3, compS);
	System.out.println("Zero length");
	run.insertionSort(testz, compD);
	run.selectionSort(testz, compD);
	run.bubbleSort(testz, compD);
	**/
	//run.insertionSort(testn, compD);
	//run.selectionSort(testn, compD);
	//run.bubbleSort(testn, compD);
}

public <E> void heapSort(E[] data, Comparator<? super E> compare){
	
};
public <E>  void mergeSort(E[] data, Comparator<? super E> compare){
	E[] buffer = (E[]) new Object[data.length];
	copyA(data,0,data.length,buffer);
	//System.out.println(printg(data));
	int length = 2;
	While
	merge(data, 0,1,1, buffer, compare);
	copyA(data,0,1,buffer);
	merge(data, 2,3,3, buffer, compare);
	copyA(data,2,3,buffer);
	merge(data,0,2,3,buffer, compare);
	
};
/**
 * Merges left and right, sorted arrays into result. Result is large enough
 * to hold both left and right.
 * @param left a sorted array.
 * @param right a sorted array.
 * @param result the sorted combination of left and right.
 */
public <E> void merge(E[] preSort, int ileft, int iright, int iend, E[] buffer, Comparator<? super E> compare) {//I am going to assume that result is right.length+left.length
	System.out.println("pre: " + printg(preSort));
	int leftCount = ileft;
	  int rightCount = iright;
	  int resultCount = ileft;
	  Comparator<? super E> c = compare;
	  int i =0;
	  while(leftCount<iright&&rightCount<=iend){
		  i++;
		  //System.out.println("doin' it");
		  int check = c.compare(buffer[rightCount], buffer[leftCount]);
		  //System.out.println(buffer[rightCount] + ":" + buffer[leftCount]);
		  if(check<=0){
			  //E temp = preSort[resultCount];
			  preSort[resultCount]= buffer[rightCount];
			  rightCount++;
			  resultCount++;
		  }
		  else if(check>0){
			  preSort[resultCount]= buffer[leftCount];
			  leftCount++;
			  resultCount++;
		  }
		 //System.out.println("iteration: " + i + printg(preSort));
	  }
	  //System.out.println(printg(preSort) + "\n" + leftCount + ":" + iright + "\n" + rightCount + ":" + iend + "\n" + resultCount);
	  //at this point one of the arrays to be merged has been completely copied, 
	  //now the remainder of the incompletely added array is copied to the result
	  while(rightCount<=iend){
		  //System.out.println("left loop");
		  
		  preSort[resultCount]= buffer[rightCount];
		  //System.out.println("copying rest of right" + printg(preSort));
		  rightCount++;
		  resultCount++;
	  }
	  while(leftCount<iright){
		  //System.out.println("right loop");
		  
		  preSort[resultCount]= buffer[leftCount];
		  //System.out.println("copying rest of left" + printg(preSort));
		  leftCount++;
		  resultCount++;
	  }
	  System.out.println("final" + printg(preSort));
}

private <E> void copyA(E[] input,int start, int end, E[] output){
	for(int i=0;i<input.length;i++){
		output[i] = input[i];
	}
}

public <E> void quickSort(E[] data, Comparator<? super E> compare){
	
};

public <E> void selectionSort(E[] data, Comparator<? super E> compare){
	String print_out = "original: " + printg(data) + "\n";//creating console output
	long time_to_complete = System.nanoTime();//time start
	int main_index;
	Comparator<? super E> c = compare;
	E min;
	//checking for conditions that the sort doesn't need to be run for
	if(data.length == 0 || data.length == 1){
		System.out.println(data.length + " length array, nothing to sort\n");
		return;
	}
	//starting the sort...
	for(main_index = 0;main_index<data.length-1;main_index++){
		int min_index = main_index;
		min = data[main_index];
		//if any of the data are null, the array should throw an exception and print a short message
		if(min == null){
			System.out.println("null value in array, cannot sort");
			throw new NullPointerException();
		}
		//looking for values lower than the current index
		for(int i=main_index;i<data.length;i++){			
			if(c.compare(min,data[i])>0){
				min = data[i];
				min_index = i;
			}
		}
		//if a lower value is found, it is moved to the current position of the sort
		if(min_index!=main_index){
			data[min_index] = data[main_index];
			data[main_index] = min;
		}
	}
	time_to_complete = System.nanoTime()-time_to_complete;//time ends
	print_out = print_out + "final: " + printg(data) + "\nSelection sort complete in " + time_to_complete + "ns\n";//adding sort output and time to console output
	System.out.println(print_out);
}
public <E> void insertionSort(E[] data, Comparator<? super E> compare){
	String print_out = "original: " + printg(data) + "\n";//creating console output
	long time_to_complete = System.nanoTime();//time start
	int cur_index;
	E shift_hold;
	int insert_index;
	//checking for conditions that the sort doesn't need to be run for
	if(data.length == 0 || data.length == 1){
		System.out.println(data.length + " length array, nothing to sort\n");
		return;
	}
	//starting the sort...
	for(cur_index = 1;cur_index<data.length;cur_index++){
		insert_index = cur_index;//index where the current values being compared is moved to
		shift_hold = data[cur_index];//value to be moved
		//if any of the data are null, the array should throw an exception and print a short message
		if(shift_hold == null){
			throw new NullPointerException();
		}
		//if any of the values are lower than the cur_index value the insert_index is changed to that index
		for(int i = cur_index-1;i>=0;i--){
			if(compare.compare(data[cur_index],data[i])<0){
				insert_index = i;
			}
		}
		//if a insert_index value was found, all values inbetween it and the cur_index are moved up 1 
		if(cur_index != insert_index){
			for(int n = cur_index;n>insert_index;n--){
				data[n]=data[n-1];
			}
			data[insert_index]=shift_hold;//the value at cur_index is moved to the insert_index
		}
	}
	time_to_complete = System.nanoTime()-time_to_complete;//time ends
	print_out = print_out + "final: " + printg(data) + "\nInsertion sort complete in " + time_to_complete + "ns\n"; //adding sort output and time to console output
	System.out.println(print_out);
}
public <E> void bubbleSort(E[] data, Comparator<? super E> compare){
	String print_out = "original: " + printg(data) + "\n";//creating console output
	long time_to_complete = System.nanoTime();//time start
	//checking for conditions that the sort doesn't need to be run for
		if(data.length == 0 || data.length == 1){
			System.out.println(data.length + " length array, nothing to sort\n");
			return;
		}
	//1st iterator moves from the last value to the 0th since the end fills with the largest values down
	for(int end = data.length-1;end>=0;end--){
		//2nd iterator moves up to the end
		for(int i = 0;i<end;i++){
			//if any of the data are null, the array should throw an exception and print a short message
			if(data[i] == null){
				System.out.println("null value in array, cannot sort");
				throw new NullPointerException();
			}
			//if 2 adjacent values are out of order, the larger value is moved to i+1
			if(compare.compare(data[i],data[i+1])>0){
				E hold = data[i];//temp hold variable
				data[i] = data[i+1];
				data[i+1] = hold;
			}
			//System.out.println(printg(data));
		}
	}
	time_to_complete = System.nanoTime()-time_to_complete;//time ends
	print_out = print_out + "final: " + printg(data) + "\nBubble sort complete in " + time_to_complete + "ns\n"; //adding sort output and time to console output
	System.out.println(print_out);
};
//generates a random double array
public Double[] arraygenD(int len){
	Double[] out = new Double[len];
	for(int i=0;i<=len-1;i++){
		out[i]= Double.valueOf(Math.round(200*((Math.random()-0.5))));
	}
	return out;
}
//generates a random string array
public String[] arraygenS(int len){
	String[] out = new String[len];
	for(int i=0;i<=len-1;i++){
		out[i]= String.valueOf((Math.round(200*((Math.random()-0.5)))));
	}
	return out;
}
//generic array print method returns a string
public <E> String printg(E[] data){
	String to_print = "";
	

		to_print = "[";
		for(int i=0;i<data.length;i++){
			to_print = to_print + data[i];
			if(i<data.length-1){
				to_print = to_print + ",";
			}
		}
		to_print = to_print+ "]";
	
	return to_print;
}
}
