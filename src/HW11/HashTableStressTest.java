package HW11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Performs stress testing of the Hash211 HashTable by inserting and getting Strings from a dictionary file into the
 * HashTable.
 *
 * @author Anthony Christe
 */
public class HashTableStressTest {
  private Hash211<String, String> hashTable;
  private List<String> words;

  /**
   * Instantiate a new Hash211 table.
   * @param initialCapacity Number of LinkedLists the initial hashtable should hold.
   * @param printTimes Should the hashtable display times for insertions and gets.
   */
  public HashTableStressTest(int initialCapacity, boolean printTimes) {
    hashTable = new Hash211<String, String>(initialCapacity, printTimes);
  }

  /**
   * Insert all dictionary words into hashtable.
   * @return The number of inserted words.
   */
  public long stressPuts() {
    long cnt = 0;
    for (String word : words) {
      hashTable.put(word, word);
      cnt++;
    }
    return cnt;
  }

  /**
   * Get all dictionary words from hashtable.
   * @return Number of words retrieved from hashtable.
   */
  public long stressGets() {
    long cnt = 0;
    for (String word : words) {
      hashTable.get(word);
      cnt++;
    }
    return cnt;
  }

  /**
   * Read in list of dictionary words.
   * File format should be a single word on each line.
   * @param fileLocation The location of the dictionary file.
   * @throws IOException Thrown if the file can not be found or if there is a problem reading the file.
   */
  public void readWordsFromFile(String fileLocation) throws IOException {
    BufferedReader in = null;
    String line;

    words = new LinkedList<String>();

    in = new BufferedReader(new FileReader(fileLocation));
    line = in.readLine();
    while (line != null) {
      words.add(line);
      line = in.readLine();
    }
    in.close();
  }

  /**
   * Perform stress testing as mandated by the command line arguments.
   * @param args The first argument should be the location of the dictionary text file.
   *             The second argument should be an integer representing the initial size to use for the hashtable.
   *             The third argument is optional. -pt or --print-times is used to specify the program to print times or not.
   */
  public static void main(String[] args) {
	/**
    if (args.length < 2) {
      usage();
      return;
    }
	**/
    HashTableStressTest stressTest;
    String dictLocation = "dict.txt";
    int initialCapacity = 2349370/2;
    long startTime;
    long numItems;
    boolean printTimes = false;
    /**
    // Check to see if the printTimes option was specified
    if(args.length == 3) {
      if(args[2].equals("-pt") || args[2].equals("--print-times")) {
        printTimes = true;
      }
    }
	**/
    try {
      // Try to convert second argument to integer
      //initialCapacity = Integer.parseInt(args[1]);
      stressTest = new HashTableStressTest(initialCapacity, printTimes);

      // Try to read in dictionary file
      System.out.println("Loading dictionary");
      stressTest.readWordsFromFile(dictLocation);
    }
    catch (NumberFormatException e) {
      usage();
      return;
    }
    catch (IOException e) {
      System.err.format("Problem reading file %s\n", dictLocation);
      e.printStackTrace();
      return;
    }


    // Stress them puts
    System.out.println("Stressing puts");
    startTime = System.nanoTime();
    numItems = stressTest.stressPuts();
    System.out.format("stressPuts %d items %d ns capacity %d\n", numItems, System.nanoTime() - startTime, initialCapacity);

    // Stress them gets
    System.out.println("Stressing gets");
    startTime = System.nanoTime();
    numItems = stressTest.stressGets();
    System.out.format("stressGets %d items %d ns capacity %d\n", numItems, System.nanoTime() - startTime, initialCapacity);
  }

  /**
   * Displays the usages for this program.
   */
  private static void usage() {
    System.err.println("Usage: java HashTableStressTest dictionary_location initial_capacity [-pt | --print-times]");
  }
}