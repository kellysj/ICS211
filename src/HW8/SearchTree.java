package HW8;

public interface SearchTree<E> {
	  /**
	   * Inserts item into where it belongs in the tree.
	   * @return true if item is inserted, false if item is already in tree.
	   */
	  boolean add(E item);
	  /**
	   * @return true if item is in the tree, false otherwise.
	   */
	  boolean contains(E item);
	  /**
	   * @return a reference to the target if found, null if target isn't in the tree.
	   */
	  E find(E target);
	  /**
	   * Removes target from the tree.
	   * @return a reference to the target if found, null if target isn't in the tree.
	   */
	  E delete(E target);
	  /**
	   * Removes target from the tree.
	   * @return true if target was in the tree, false otherwise.
	   */
	  boolean remove(E target);
	}