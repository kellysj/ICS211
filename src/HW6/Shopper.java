package HW6;


/**
 * @author Kelly Jones
 *
 */
public class Shopper {
	int numItems;//the number of items the shopper holds
	
	Shopper(int itemnum){
		numItems = itemnum;
	}
	/**A method to remove an item from the shoppers posession
	 * 
	 * @return when the shopper has no items this returns false
	 */
	public boolean removeItem(){
		if(numItems>0){
			numItems--;
			return true;
		}
		else{return false;}
	}

}
