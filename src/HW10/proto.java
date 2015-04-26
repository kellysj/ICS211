package HW10;

import java.util.Comparator;

public class proto {
	int swapCount = 0;
	int compareCount = 0;
	int arraySize = 0;

	public static void main(String args[]) {
		int length = 100;
		proto run = new proto();
		Double testD1[] = run.arraygenD(length);
		Double testD2[] = run.arraygenD(length);
		Double testD3[] = run.arraygenD(10);
		Double testD4[] = run.arraygenD(10);
		/**
		 * Double testD4[] = new Double[10];
		 * //-84.0,-41.0,-38.0,-29.0,22.0,-22.0,-9.0,47.0,27.0,54.0 testD4[0] =
		 * 70.0; testD4[1] = -62.0; testD4[2] = -52.0; testD4[3] = 89.0;
		 * testD4[4] = 35.0; testD4[5] = -23.0; testD4[6] = 58.0; testD4[7] =
		 * 66.0; testD4[8] = 81.0; testD4[9] = 75.0;
		 **/

		String testS1[] = run.arraygenS(length);
		String testS2[] = run.arraygenS(length);
		String testS3[] = run.arraygenS(length);
		// String testS4[] = run.arraygenS(4);
		Double testz[] = new Double[0];
		Double testn[] = new Double[5];
		Comparator<String> compS = new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		};
		Comparator<Double> compD = new Comparator<Double>() {
			@Override
			public int compare(Double o1, Double o2) {
				return o1.compareTo(o2);
			}
		};
		System.out.println("Double");
		System.out.println("before: " + run.printg(testD4));
		long timer = System.nanoTime();
		//run.quickSort(testD4, compD);
		// run.heapSort(testD4, compD);
		 run.mergeSort(testD4, compD);
		timer = System.nanoTime() - timer;
		System.out.println("after: " + run.printg(testD4) + "\nTime to Sort: "
				+ timer + "ns" + "\nNumber of Swaps: " + run.swapCount
				+ "\nNumber of Compares: " + run.compareCount);
		/**
		 * System.out.println("before: " + run.printg(testD4)); long timer =
		 * System.nanoTime(); run.mergeSort(testD4, compD); timer =
		 * System.nanoTime()-timer; System.out.println("after: " +
		 * run.printg(testD4) + "\nTime to Sort: " + timer + "ns"); /**
		 * run.insertionSort(testD2, compD); run.selectionSort(testD1, compD);
		 * run.bubbleSort(testD3, compD); System.out.println("String");
		 * run.insertionSort(testS2, compS); run.selectionSort(testS1, compS);
		 * run.bubbleSort(testS3, compS); System.out.println("Zero length");
		 * run.insertionSort(testz, compD); run.selectionSort(testz, compD);
		 * run.bubbleSort(testz, compD);
		 **/
		// run.insertionSort(testn, compD);
		// run.selectionSort(testn, compD);
		// run.bubbleSort(testn, compD);
	}

	/**
	 * 
	 * @param data
	 * @param compare
	 */
	public <E> void quickSort(E[] data, Comparator<? super E> compare) {
		quickHelp(data, 0, data.length - 1, compare);
	}
	/**A helper method for quicksort, rearranges a wall and pivot
	 * 
	 * @param data the array to sort
	 * @param start the start index to sort through
	 * @param end the end index to sort through
	 * @param compare a comparator to determine sorting
	 */
	private <E> void quickHelp(E[] data, int start, int end,
			Comparator<? super E> compare) {
		if (end > start) {// if end becomes less than start the recursion is
							// complete
			int pivot = end;
			int wall = start;
			int currentElement = wall;
			// iterating through the array from the required indicies
			while (currentElement < pivot) {
				int check = compare.compare(data[currentElement], data[pivot]);
				compareCount++;
				if (check >= 0) {
					currentElement++;
				} else if (currentElement != wall && check < 0) {
					swap(currentElement, wall, data);
					wall++;
					currentElement++;
				} else {
					currentElement++;
					wall++;
				}

			}
			// checking to see if you need to bother to swap anything
			if (pivot != wall) {
				swap(pivot, wall, data);
			}
			quickHelp(data, start, wall - 1, compare);// left recursion
			quickHelp(data, wall + 1, end, compare);// right recursion
		} else {
			return;
		}
	}
	/**A simple swaping function to make code more concise and keep track of swaps
	 * 
	 * @param a index to exchange
	 * @param b index to exchange to
	 * @param in the array to perform the exchange on
	 */
	private <E> void swap(int a, int b, E[] in) {
		// System.out.println("SWAPPING");
		swapCount++;
		E temp = in[a];
		in[a] = in[b];
		in[b] = temp;

	}

	/**
	 * 
	 * @param data
	 * @param compare
	 */
	public <E> void heapSort(E[] data, Comparator<? super E> compare) {
		Comparator<? super E> c = compare;

		// putting everything in a heap
		heapify(data, data.length - 1, c);
		System.out.println("end of heapify");
		// System.out.println("update1:" + printg(data));
		// sorting that heap

		for (int i = data.length - 1; i > 1; i--) {
			heapHelp(i, 0, data, c);
			System.out.println("end of a heap help");
			// System.out.println("update" + i + ": " + printg(data));
			E temp = data[i];
			data[i] = data[0];
			data[0] = temp;
			swapCount++;
			// System.out.println("update" + i + ": " + printg(data));
		}

	};
	
	private <E> void heapify(E[] data, int end, Comparator<? super E> compare) {
		Comparator<? super E> c = compare;
		int frame = (end);
		int startI = (frame - 1) / 2;
		// putting everything in a heap
		while (startI >= 0) {
			heapHelp(frame, startI, data, c);
			// System.out.println("\tAfter swap:" + printg(data));
			startI--;

		}

	}

	public <E> void heapHelp(int frame, int index, E[] data,
			Comparator<? super E> compare) {
		int swap = index;
		// System.out.println("original:" + printg(data));
		int n = 0;
		while (((swap * 2 + 1) <= frame) && (n < 5)) {
			n++;
			int left = 2 * swap + 1;
			int right = left + 1;
			// System.out.println("left:" + left + "\nright" + right + "\nframe"
			// + frame);
			compareCount++;
			if (compare.compare(data[swap], data[left]) < 0) {
				System.out.println("left switch:" + data[swap] + "<"
						+ data[left] + ": index:" + index);
				swap = left;

			}
			compareCount++;
			if (right <= frame) {
				if (compare.compare(data[swap], data[right]) < 0) {
					System.out.println("right switch:" + data[swap] + "<"
							+ data[right]);
					swap = right;

				}
			}
			compareCount++;
			if (index == swap) {
				// System.out.println("no change" + printg(data) +
				// "returning...");
				return;
			} else {
				swapCount++;
				E temp = data[swap];
				data[swap] = data[index];
				data[index] = temp;

				heapHelp(frame, swap, data, compare);
				System.out.println("" + data[index] + "swaped with"
						+ data[swap] + "\n" + "\nleft:" + left + "\nright"
						+ right + "\nframe" + frame);
			}

			// System.out.println("after switches:" + printg(data));
		}
	}

	/**
	 * A merge sort method
	 * 
	 * @param data
	 *            the array to sort
	 * @param compare
	 *            a comparator to sort by
	 */
	public <E> void mergeSort(E[] data, Comparator<? super E> compare) {
		E[] buffer = (E[]) new Object[data.length];
		copyA(data, 0, data.length, buffer);
		// System.out.println("before:" + printg(data));

		int frame = 2;
		while (frame <= data.length) {

			for (int i = 0; i <= data.length - frame; i = i + frame) {
				merge(data, i, i + frame / 2, i + frame - 1, buffer, compare);
				copyA(data, i, i + frame / 2, buffer);

			}
			frame = frame * 2;
		}
		// System.out.println("after:" + printg(data));
	};

	/**
	 * Takes an input array, a bufferarray copy of that and uses input indicies
	 * to represent the sub arrays for merging. These sub arrays can only be
	 * merged and sorted if they are sorted individually!
	 * 
	 * @param preSort
	 *            the presorted array, this is the array that is modified
	 * @param ileft
	 *            the start of the left sub array being merged
	 * @param iright
	 *            the start of the right sub array being merged
	 * @param iend
	 *            the end of the right sub array being merged
	 * @param buffer
	 *            a copy of the presorted array
	 * @param compare
	 *            a comparator to sort the items in the sub arrays
	 */
	private <E> void merge(E[] preSort, int ileft, int iright, int iend,
			E[] buffer, Comparator<? super E> compare) {// I am going to assume
														// that result is
														// right.length+left.length
		System.out.println("pre: " + printg(preSort));
		int leftCount = ileft;
		int rightCount = iright;
		int resultCount = ileft;
		Comparator<? super E> c = compare;
		int i = 0;
		while (leftCount < iright && rightCount <= iend) {
			i++;
			// System.out.println("doin' it");
			int check = c.compare(buffer[rightCount], buffer[leftCount]);
			compareCount++;
			System.out.println(buffer[rightCount] + ":" + buffer[leftCount]);
			if (check <= 0) {
				// E temp = preSort[resultCount];
				preSort[resultCount] = buffer[rightCount];
				rightCount++;
				resultCount++;
			} else if (check > 0) {
				preSort[resultCount] = buffer[leftCount];
				leftCount++;
				resultCount++;
			}
			System.out.println("iteration: " + i + printg(preSort));
		}
		System.out.println(printg(preSort) + "\n" + leftCount + ":" + iright + "\n" + rightCount + ":" + iend + "\n" + resultCount); 
		//at this point one of the arrays to be merged has been completelycopied, now the remainder of the incompletely added array is copied to theresult
		while (rightCount <= iend) {
			// System.out.println("left loop");

			preSort[resultCount] = buffer[rightCount];
			swapCount++;
			// System.out.println("copying rest of right" + printg(preSort));
			rightCount++;
			resultCount++;
		}
		while (leftCount < iright) {
			// System.out.println("right loop");

			preSort[resultCount] = buffer[leftCount];
			swapCount++;
			// System.out.println("copying rest of left" + printg(preSort));
			leftCount++;
			resultCount++;
		}
		System.out.println("final" + printg(preSort));
	}

	/**
	 * A helper method originally made for merge sort. Only copies a certain
	 * range of elements
	 * 
	 * @param input
	 * @param start
	 * @param end
	 * @param output
	 */
	private <E> void copyA(E[] input, int start, int end, E[] output) {
		for (int i = 0; i < input.length; i++) {
			output[i] = input[i];
		}
	}

	public <E> void selectionSort(E[] data, Comparator<? super E> compare) {
		String print_out = "original: " + printg(data) + "\n";// creating
																// console
																// output
		long time_to_complete = System.nanoTime();// time start
		int main_index;
		Comparator<? super E> c = compare;
		E min;
		// checking for conditions that the sort doesn't need to be run for
		if (data.length == 0 || data.length == 1) {
			System.out
					.println(data.length + " length array, nothing to sort\n");
			return;
		}
		// starting the sort...
		for (main_index = 0; main_index < data.length - 1; main_index++) {
			int min_index = main_index;
			min = data[main_index];
			// if any of the data are null, the array should throw an exception
			// and print a short message
			if (min == null) {
				System.out.println("null value in array, cannot sort");
				throw new NullPointerException();
			}
			// looking for values lower than the current index
			for (int i = main_index; i < data.length; i++) {
				if (c.compare(min, data[i]) > 0) {
					min = data[i];
					min_index = i;
				}
			}
			// if a lower value is found, it is moved to the current position of
			// the sort
			if (min_index != main_index) {
				data[min_index] = data[main_index];
				data[main_index] = min;
			}
		}
		time_to_complete = System.nanoTime() - time_to_complete;// time ends
		print_out = print_out + "final: " + printg(data)
				+ "\nSelection sort complete in " + time_to_complete + "ns\n";// adding
																				// sort
																				// output
																				// and
																				// time
																				// to
																				// console
																				// output
		System.out.println(print_out);
	}

	public <E> void insertionSort(E[] data, Comparator<? super E> compare) {
		String print_out = "original: " + printg(data) + "\n";// creating
																// console
																// output
		long time_to_complete = System.nanoTime();// time start
		int cur_index;
		E shift_hold;
		int insert_index;
		// checking for conditions that the sort doesn't need to be run for
		if (data.length == 0 || data.length == 1) {
			System.out
					.println(data.length + " length array, nothing to sort\n");
			return;
		}
		// starting the sort...
		for (cur_index = 1; cur_index < data.length; cur_index++) {
			insert_index = cur_index;// index where the current values being
										// compared is moved to
			shift_hold = data[cur_index];// value to be moved
			// if any of the data are null, the array should throw an exception
			// and print a short message
			if (shift_hold == null) {
				throw new NullPointerException();
			}
			// if any of the values are lower than the cur_index value the
			// insert_index is changed to that index
			for (int i = cur_index - 1; i >= 0; i--) {
				if (compare.compare(data[cur_index], data[i]) < 0) {
					insert_index = i;
				}
			}
			// if a insert_index value was found, all values inbetween it and
			// the cur_index are moved up 1
			if (cur_index != insert_index) {
				for (int n = cur_index; n > insert_index; n--) {
					data[n] = data[n - 1];
				}
				data[insert_index] = shift_hold;// the value at cur_index is
												// moved to the insert_index
			}
		}
		time_to_complete = System.nanoTime() - time_to_complete;// time ends
		print_out = print_out + "final: " + printg(data)
				+ "\nInsertion sort complete in " + time_to_complete + "ns\n"; // adding
																				// sort
																				// output
																				// and
																				// time
																				// to
																				// console
																				// output
		System.out.println(print_out);
	}

	public <E> void bubbleSort(E[] data, Comparator<? super E> compare) {
		String print_out = "original: " + printg(data) + "\n";// creating
																// console
																// output
		long time_to_complete = System.nanoTime();// time start
		// checking for conditions that the sort doesn't need to be run for
		if (data.length == 0 || data.length == 1) {
			System.out
					.println(data.length + " length array, nothing to sort\n");
			return;
		}
		// 1st iterator moves from the last value to the 0th since the end fills
		// with the largest values down
		for (int end = data.length - 1; end >= 0; end--) {
			// 2nd iterator moves up to the end
			for (int i = 0; i < end; i++) {
				// if any of the data are null, the array should throw an
				// exception and print a short message
				if (data[i] == null) {
					System.out.println("null value in array, cannot sort");
					throw new NullPointerException();
				}
				// if 2 adjacent values are out of order, the larger value is
				// moved to i+1
				if (compare.compare(data[i], data[i + 1]) > 0) {
					E hold = data[i];// temp hold variable
					data[i] = data[i + 1];
					data[i + 1] = hold;
				}
				// System.out.println(printg(data));
			}
		}
		time_to_complete = System.nanoTime() - time_to_complete;// time ends
		print_out = print_out + "final: " + printg(data)
				+ "\nBubble sort complete in " + time_to_complete + "ns\n"; // adding
																			// sort
																			// output
																			// and
																			// time
																			// to
																			// console
																			// output
		System.out.println(print_out);
	};

	// generates a random double array
	public Double[] arraygenD(int len) {
		Double[] out = new Double[len];
		for (int i = 0; i <= len - 1; i++) {
			out[i] = Double.valueOf(Math.round(200 * ((Math.random() - 0.5))));
		}
		return out;
	}

	// generates a random string array
	public String[] arraygenS(int len) {
		String[] out = new String[len];
		for (int i = 0; i <= len - 1; i++) {
			out[i] = String
					.valueOf((Math.round(200 * ((Math.random() - 0.5)))));
		}
		return out;
	}

	// generic array print method returns a string
	public <E> String printg(E[] data) {
		String to_print = "";

		to_print = "[";
		for (int i = 0; i < data.length; i++) {
			to_print = to_print + data[i];
			if (i < data.length - 1) {
				to_print = to_print + ",";
			}
		}
		to_print = to_print + "]";

		return to_print;
	}
}
