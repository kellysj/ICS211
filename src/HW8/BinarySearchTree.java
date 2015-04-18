package HW8;

import java.io.Serializable;
import java.util.Comparator;
/**A binary search tree
 * 
 * @author Kelly Jones
 *
 * @param <E> the type of the tree
 */
public class BinarySearchTree<E> implements SearchTree<E> {
	  private Comparator<E> comp;
	  boolean addReturn;
	  E deleteReturn;
	  protected Node root;
	  
	  public static void main(String args[]){
		  ContactComparator compy = new ContactComparator();

		  BinarySearchTree<Contact> test = new BinarySearchTree(compy);
		  test.add(new Contact("Mackintire", "Jason", "8082268439"));
		  test.add(new Contact("Jolly", "Mincey", "8085555555"));
		  test.add(new Contact("Costanza", "Seven", "5552268439"));
		  test.add(new Contact("McKellan", "Alpha", "8088476309"));
		  
	  }
	  /**a node class for the tree to use
	   * 
	   * @author Kelly Jones
	   *
	   * @param <E>
	   */
	  protected static class Node<E> implements Serializable{
		  
		  Node(E datain){
			  data = datain;
		  }
		  protected E data;
		  protected Node<E> left;
		  protected Node<E> right;
	  }

	  /**
	   * Creates a new BinarySearchTree.
	   * @param c the comparator to use for determining order.
	   */
	  public BinarySearchTree(Comparator<E> c) {
	    comp = c;
	  }

	  /**a method to output an ordered string
	   * @return the contacts in order as a string.
	   */
	  public String inorderString() {
		  return inorderString(root);
	  }
	  /**A helper method for the in order string
	   * 
	   * @param localRoot
	   * @return
	   */
	  public String inorderString(Node localRoot){
		  String out = "";
		  if(localRoot == null){
			  return "";
		  }
		  else{
			  out = inorderString(localRoot.left) +"," + localRoot.data + "," + inorderString(localRoot.right);
		  }
		  
		  throw new IllegalArgumentException("something wrong with writing the tree in order!");
	  }
	  
	  
	 /**Adds an item to the BST
	  * @param the item to add
	  * @return the status of the add working
	  */
	public boolean add(E item){
		 
		 root = add(root, item);
		 return addReturn;
	 }

	/** inserts item where it belongs in a tree
	 * @param item to add
	 */
	public Node<E> add(Node<E> localRoot,E item) {
		
		if(localRoot == null){
			addReturn = true;
			return new Node<E>(item);
		}
		int compN = comp.compare(item, localRoot.data);
		if(compN==0){
			addReturn = false;
			return localRoot;
		}
		else if(compN<0){
			localRoot.left = add(localRoot.left,item);
			return localRoot;
		}
		else{
			localRoot.right = add(localRoot.right, item);
			return localRoot;
		}
	}

	/**method to show if the tree contains an item
	 * @param the item to check for
	 * @return whether or not the item is in the tree
	 */
	public boolean contains(E item) {
		if(find(item)!=null){
			return true;
		}
		return false;
	}
	
	/**recursive helper method for find
	 * 
	 * @param localRoot
	 * @param target
	 * @return
	 */
	private E find(Node<E> localRoot, E target){
		if(localRoot==null){
			return null;
		}
		int compN = comp.compare(target, localRoot.data);
		if(compN==0){
			return localRoot.data;
		}
		else if(compN<0){
			return find(localRoot.right, target);
		}
		else{
			return find(localRoot.right, target);
		}
	}

	/**finds an element in the tree
	 * @param target to look for
	 * @param the found element equal to the target
	 */
	@Override
	public E find(E target) {
		return find(root,target);
	}

	@Override
	public E delete(E target) {
		return delete(root,target).data;
	}
	/**a method to delete a node and 
	 * 
	 * @param localRoot
	 * @param item
	 * @return
	 */
	private Node<E> delete(Node<E> localRoot, E item){
		if(localRoot ==null){
			deleteReturn = null;
			return localRoot;
		}
		int compN = comp.compare(item, localRoot.data);
		if(compN<0){
			localRoot.left = delete(localRoot.left, item);
			return localRoot;
		}
		else if (compN>0){
			localRoot.right = delete(localRoot.right, item);
			return localRoot;
		}
		else{
			return replaceNode(localRoot);
		}
	}
	/**a recursive helper method for delete
	 * @param localRoot the node to replace the data on
	 * @return the new node after replacement
	 */
	private Node<E> replaceNode(Node<E> localRoot){
		deleteReturn = localRoot.data;
		if(localRoot.left==null){
			return localRoot.right;
		}
		else if(localRoot.right==null){
			return localRoot.left;
		}
		else{
			if(localRoot.left.right==null){
				localRoot.data = localRoot.left.data;
				localRoot.left = localRoot.left.left;
				return localRoot;
			}
			else{
				localRoot.data = findLargestChild(localRoot.left);
				return localRoot;
			}
		}
	}
	/**a recursive helper for the replace node method
	 * 
	 * @param parent to find the largest child of
	 * @return the largest child
	 */
	private E findLargestChild(Node<E> parent){
		if(parent.right == null){
			E returnValue = parent.right.data;
			parent.right = parent.right.left;
			return returnValue;
		}
		else{
			return findLargestChild(parent.right);
		}
	}

	/**a method to remove an item from the tree and return true if it is removed
	 * @param the item to remove
	 * @return whether or not the item was removed
	 */
	public boolean remove(E target) {
		if(delete(target)!=null){
			return true;
		}
		return false;
	}
	}