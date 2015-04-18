package HW7;

import java.util.ArrayList;

/**
 * Class for recursively finding a solution to a Sudoku problem.
 * 
 * @author Biagioni, Edoardo, Cam Moore
 * @date October 23, 2013
 * @missing solveSudoku, to be implemented by the students in ICS 211
 */
public class sudoku {
	static int checkcount = 0;
	static int solvecount =0;
	public static void main(String args[]) {
		int[][] example = {
                {7, 8, 3, 4, 9, 0, 0, 0, 5},
                {1, 2, 5, 0, 3, 0, 0, 0, 4},
                {4, 0, 9, 0, 5, 0, 0, 0, 8},
                {2, 3, 0, 0, 8, 0, 0, 0, 9},
                {5, 0, 4, 0, 7, 0, 3, 8, 2},
                {9, 7, 8, 0, 0, 0, 0, 1, 6},
                {8, 0, 7, 0, 0, 0, 0, 4, 1},
                {6, 4, 1, 5, 2, 7, 8, 9, 3},
                {3, 9, 2, 8, 0, 4, 0, 5, 7}};
		sudoku test = new sudoku();
		// test.print2dArr(solution2);
		// test.printList(test.legalValues(solution2, 0, 0));
		test.printList(test.legalValues(example, 0, 3));
		System.out.println(test.psolveSudoku(example));
		test.solveSudoku(example);

	}

	public static String printList(ArrayList<Integer> in) {
		String outString = "";
		for (Integer out : in) {
			outString = outString + " " + out;
		}
		return outString;
	}

	public void print2dArr(int[][] in) {
		for (int i = 0; i < in[0].length; i++) {
			System.out.println(" " + in[0][i]);
		}
	}

	/**
	 * Find an assignment of values to sudoku cells that makes the sudoku valid.
	 * 
	 * @param the
	 *            sudoku to be solved
	 * @return whether a solution was found if a solution was found, the sudoku
	 *         is filled in with the solution if no solution was found, restores
	 *         the sudoku to its original value
	 */
	public static boolean solveSudoku(int[][] sudoku) {
		long timer = System.nanoTime();
		 solveSudoku(sudoku, 0, 0);
		 timer = System.nanoTime()-timer;
		 System.out.println("Time to solve was: " + timer + " ns");
		 return true;
	}

	// sudoku[row][column]
	public static boolean solveSudoku(int[][] gridin, int rowin, int colin) {
		solvecount++;
		int[][] in = gridin;
		int row = rowin;
		int col = colin;
		//System.out.println(row + ":" + col);
		int newCol = colin;
		int newRow = rowin;
		if(row == in.length - 1 && col == in[row].length){
			//System.out.println("last cell:");
			System.out.println(psolveSudoku(in));
			boolean cond = checkSudoku(gridin, true);
			System.out.println("Solvecount is: " + solvecount + "\nCheckcount is: " + checkcount);
			return cond;
		}
		else if (row < in.length - 1 && col == in[row].length - 1) {
			//System.out.println("end of row triggered");
			newRow++; 
			newCol = 0;
		} else if (col < in[row].length && row <= in.length -1) {
			newCol++;
		}
		else{
			throw new NullPointerException("exceeds some ending parameter in solve1 sodoku " + row + ":" + col + ":" + row + ":" + newCol);
		}
		
		if (in[row][col] == 0) {
			ArrayList<Integer> validList = legalValues(in, row, col);
			
			if(validList.size() != 0) {
				//System.out.println(row + ":" + col + " Valid values:" + printList(validList));
				//System.out.println(psolveSudoku(in));
				for (Integer checkValue : validList) {												
					//System.out.println(row + ":" + col + " new branch for: " + checkValue);					
					in[row][col] = checkValue;
					//System.out.println(psolveSudoku(in));
					if(checkSudoku(gridin, false)&&solveSudoku(in, newRow, newCol)){
						//System.out.println(psolveSudoku(in));
						return true;
					}
					// creating the new sudoku value
					
				}
			}
			//System.out.println("bad solution");
			//System.out.println(psolveSudoku(in));			
			//System.out.println("returning from " + row + ":" + col);
			in[row][col] = 0;
			return false;
		} else {
			//System.out.println(row + ":" + col + " skip");
			//System.out.println(psolveSudoku(in));
			return solveSudoku(in, newRow, newCol);
		}
	}

	/**
	 * A recursive printing method
	 * 
	 * @param sudokuin
	 * @return
	 */
	public static String psolveSudoku(int[][] sudokuin) {
		return psolveSudoku(sudokuin, 0, 0);
	}

	/**
	 * a helper method for the recursive printer method
	 * 
	 * @param in
	 * @param row
	 * @param col
	 * @return
	 */
	private static String psolveSudoku(int[][] in, int row, int col) {
		String out = "";
		if (col > in.length - 1 && row < in[row].length - 1) {

			out = "\n" + psolveSudoku(in, ++row, 0);

		} else if (row <= in[row].length - 1 && col <= in.length - 1) {

			out = in[row][col] + psolveSudoku(in, row, ++col);

		} else {
			out = out + "\n";
		}
		return out;

	}

	/**
	 * Find the legal values for the given sudoku and cell.
	 * 
	 * @param sudoku
	 *            , the sudoku being solved.
	 * @param row
	 *            the row of the cell to get values for.
	 * @param col
	 *            the column of the cell.
	 * @return an ArrayList of the valid values.
	 * 
	 *         sudoku[row][column]
	 */
	private static ArrayList<Integer> legalValues(int[][] sudoku, int row,
			int column) {
		ArrayList<Integer> validValues = new ArrayList();// generating an
															// initial list of
															// valid values
		for (int i = 1; i <= sudoku.length; i++) {
			validValues.add(i);
		}
		for (int i = 0; i < sudoku[0].length; i++) {
			int index = validValues.indexOf(sudoku[row][i]);
			// System.out.println("row:" + sudoku[row][i] + " at index " +
			// index);
			validValues.remove(Integer.valueOf(sudoku[row][i]));
		}
		for (int i = 0; i < sudoku[0].length; i++) {
			int index = validValues.indexOf(sudoku[i][column]);
			// System.out.println("col:" + sudoku[i][column] + " at index " +
			// index);
			validValues.remove(Integer.valueOf(sudoku[i][column]));
		}

		return validValues;
		// TODO: Implement this method. You may want to look at the checkSudoku
		// method
		// to see how it finds conflicts.
	}

	/**
	 * checks that the sudoku rules hold in this sudoku puzzle. cells that
	 * contain 0 are not checked.
	 * 
	 * @param the
	 *            sudoku to be checked
	 * @param whether
	 *            to print the error found, if any
	 * @return true if this sudoku obeys all of the sudoku rules, otherwise
	 *         false
	 */
	public static boolean checkSudoku(int[][] sudoku, boolean printErrors) {
		checkcount++;
		if (sudoku.length != 9) {
			if (printErrors) {
				System.out.println("sudoku has " + sudoku.length
						+ " rows, should have 9");
			}
			return false;
		}
		for (int i = 0; i < sudoku.length; i++) {
			if (sudoku[i].length != 9) {
				if (printErrors) {
					System.out.println("sudoku row " + i + " has "
							+ sudoku[i].length + " cells, should have 9");
				}
				return false;
			}
		}
		/* check each cell for conflicts */
		for (int i = 0; i < sudoku.length; i++) {
			for (int j = 0; j < sudoku.length; j++) {
				int cell = sudoku[i][j];
				if (cell == 0) {
					continue; /* blanks are always OK */
				}
				if ((cell < 1) || (cell > 9)) {
					if (printErrors) {
						System.out.println("sudoku row " + i + " column " + j
								+ " has illegal value " + cell);
					}
					return false;
				}
				/* does it match any other value in the same row? */
				for (int m = 0; m < sudoku.length; m++) {
					if ((j != m) && (cell == sudoku[i][m])) {
						if (printErrors) {
							System.out.println("sudoku row " + i + " has "
									+ cell + " at both positions " + j
									+ " and " + m);
						}
						return false;
					}
				}
				/* does it match any other value it in the same column? */
				for (int k = 0; k < sudoku.length; k++) {
					if ((i != k) && (cell == sudoku[k][j])) {
						if (printErrors) {
							System.out.println("sudoku column " + j + " has "
									+ cell + " at both positions " + i
									+ " and " + k);
						}
						return false;
					}
				}
				/* does it match any other value in the 3x3? */
				for (int k = 0; k < 3; k++) {
					for (int m = 0; m < 3; m++) {
						int testRow = (i / 3 * 3) + k; /* test this row */
						int testCol = (j / 3 * 3) + m; /* test this col */
						if ((i != testRow) && (j != testCol)
								&& (cell == sudoku[testRow][testCol])) {
							if (printErrors) {
								System.out.println("sudoku character " + cell
										+ " at row " + i + ", column " + j
										+ " matches character at row "
										+ testRow + ", column " + testCol);
							}
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	/**
	 * Converts the sudoku to a printable string
	 * 
	 * @param the
	 *            sudoku to be converted
	 * @param whether
	 *            to check for errors
	 * @return the printable version of the sudoku
	 */
	public static String toString(int[][] sudoku, boolean debug) {
		if ((!debug) || (checkSudoku(sudoku, true))) {
			String result = "";
			for (int i = 0; i < sudoku.length; i++) {
				if (i % 3 == 0) {
					result = result + "+-------+-------+-------+\n";
				}
				for (int j = 0; j < sudoku.length; j++) {
					if (j % 3 == 0) {
						result = result + "| ";
					}
					if (sudoku[i][j] == 0) {
						result = result + "  ";
					} else {
						result = result + sudoku[i][j] + " ";
					}
				}
				result = result + "|\n";
			}
			result = result + "+-------+-------+-------+\n";
			return result;
		}
		return "illegal sudoku";
	}
}
