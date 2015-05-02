package HW11;

import java.util.LinkedList;
import java.util.Map;

/**
 * This class creates a simple chained hash
 * 
 * @author Kelly Jones
 *
 * @param <K>
 *            keys for entries in the hash
 * @param <V>
 *            values in entries in the hash
 */
public class Hash211<K, V> {

	LinkedList<hashEntry>[] table;// the table to hold the linked list chains of
									// hashEntry
	long timer;// a timer variable
	boolean printT;// a boolen to print or not print the time for various
					// methods

	/**
	 * This was the testing method I used for writing the hash, once this code
	 * worked I moved to testing with HashTableStressTest.java
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		Hash211 test = new Hash211(10, true);

		test.put("lol", 10);
		System.out.println("getting lol:" + test.get("lol"));
		// System.out.println("getting lolin(should fail): " +
		// test.get("lolin"));
	}

	/**
	 * The constructor for the hash object
	 * 
	 * @param capacity
	 *            the capacity of table, specifies the number of chains the hash
	 *            will use
	 * @param printTimes
	 *            a boolean to print times using system.out. Adds significant
	 *            time to a run!
	 */
	Hash211(int capacity, boolean printTimes) {
		printT = printTimes;
		timer = 0;
		table = new LinkedList[capacity];
	}

	/**
	 * A method to add an entry to the hash
	 * 
	 * @param key
	 *            to identify the entry
	 * @param value
	 *            to associate with the entry
	 * @return the value added to the hash
	 */
	public V put(K key, V value) {
		if (printT == true) {
			timer = System.nanoTime();
		}
		int index = Math.abs(key.hashCode()) % table.length;// location of the
															// chain in the
															// table is given by
															// this index
															// calculation. It
															// is self
															// explanatory just
															// read it.
		hashEntry temp = new hashEntry(key, value);
		if (table[index] == null) {
			table[index] = new LinkedList<hashEntry>();
			table[index].add(temp);
			if (printT) {
				System.out
						.println("time to create a new chain and add a key,value: "
								+ (System.nanoTime() - timer));
			}
			return (V) table[index].peekLast().getValue();// the chain for this
															// hash didn't exist
															// but it was
															// created and the
															// new entry was
															// added
		} else {
			table[index].add(temp);
			if (printT) {
				System.out.println("time to add to existing chain: "
						+ (System.nanoTime() - timer));
			}
			return (V) table[index].peekLast().getValue();// the chain for this
															// hash value exists
															// and a new entry
															// is added
		}
	}

	/**
	 * A method to get a value associated with the key from the hash if the key
	 * isn't in the hash then the method will throw an error
	 * 
	 * @param key
	 *            is the key to retrieve
	 * @return values is the value for the key
	 */
	public V get(K key) {
		int index = Math.abs(key.hashCode()) % table.length;
		LinkedList<hashEntry> tempChain = table[index];
		if (tempChain == null) {
			if (printT == true) {
				System.out
						.println("time to find that a key's hash has no chain in the table: "
								+ (System.nanoTime() - timer));
			}
			throw new IllegalArgumentException(
					"this key doesn't exist this hash");
		} else {
			int i = 0;
			for (hashEntry tempEntry : table[index]) {// checking all the
														// entries' keys in the
														// chain
				if (tempEntry.getKey().equals(key)) {
					if (printT == true) {
						System.out
								.println("time to get a value associated with a key: "
										+ (System.nanoTime() - timer));
					}
					return (V) tempEntry.getValue();// the key was found and the
													// value associated with
													// this entry is returned
				}
			}
			if (printT == true) {
				System.out
						.println("time to find that a key doesn't exist in a chain: "
								+ (System.nanoTime() - timer));
			}
			return null;// the key was not found, null is returned: THIS IS WHAT
						// THE ORACLE DOCUMENTATION SPECIFIES THAT GET DOES! The
						// homework says "no value can be null
		}
	}

	/**
	 * A class that implements the entry interface as an object to store data
	 * associated with the hash
	 * 
	 * @author Kelly Jones
	 *
	 * @param <K>
	 *            the key for the entry
	 * @param <V>
	 *            the value for the entry
	 */
	final class hashEntry<K, V> implements Map.Entry<K, V> {
		private final K key;
		private V value;

		hashEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		/**
		 * A method that returns a key associated with an entry
		 * 
		 * @return the key for the entry
		 */
		@Override
		public K getKey() {
			return key;
		}

		/**
		 * A method that returns a value associated with an entry
		 * 
		 * @return the value associated with the entry
		 * 
		 */
		@Override
		public V getValue() {
			return value;
		}

		/**
		 * A method that sets the value associated with the entry
		 * 
		 * @return the value stored in the entry before it was set
		 */
		@Override
		public V setValue(V value) {
			V temp = this.value;
			this.value = value;
			return temp;
		}

	}

}
