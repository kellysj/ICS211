package HW6;

/**
 * @author Kelly Jones
 *
 */
public class CheckoutLanes {
	CircularArrayQueue<Shopper>[] eLane;
	CircularArrayQueue<Shopper>[] rLane;
	int laneMaxSize;

	public static void main(String args[]) {
		CheckoutLanes checkout = new CheckoutLanes(1, 2);
		checkout.enterLane(0, new Shopper(15));
		checkout.enterLane(0, new Shopper(3));
		checkout.enterLane(1, new Shopper(20));
		checkout.enterLane(2, new Shopper(17));
		checkout.enterLane(2, new Shopper(11));
		checkout.enterLane(2, new Shopper(12));
		checkout.enterLane(2, new Shopper(13));
		checkout.simulateCheckout();
	}
	/**a class for a checkout lane simulation
	 * 
	 * @param numExpress
	 * @param numRegular
	 */
	public CheckoutLanes(int numExpress, int numRegular) {
		laneMaxSize = 10;
		eLane = new CircularArrayQueue[numExpress];
		rLane = new CircularArrayQueue[numRegular];
		for (int i = 0; i < numExpress; i++) {
			eLane[i] = new CircularArrayQueue<Shopper>(laneMaxSize);
		}
		for (int i = 0; i < numRegular; i++) {
			rLane[i] = new CircularArrayQueue<Shopper>(laneMaxSize);
		}
	}
	/**A method to add a shopper to a lane
	 * 
	 * @param laneNumber the lant to add them to
	 * @param shopper
	 */
	public void enterLane(int laneNumber, Shopper shopper) {
		int laneNum = laneNumber;
		//System.out.println("Lane number is:" + laneNum + " eLane length is:" + eLane.length + "   rLane length is:" + rLane.length);
		if(laneNum<eLane.length){
			//System.out.println("express add");
			if(shopper.numItems>=10){
				laneNum = (eLane.length)+ (int)((rLane.length-1) * Math.random());			
			}
			else{
				int eLaneFill = 0;
				while(eLane[laneNum].size()==laneMaxSize){
					//System.out.println("lane was full");
					laneNum = (int)(((eLane.length)+rLane.length-1) * Math.random());
					eLaneFill++;
				}	
				eLane[laneNumber].add(shopper);
			}
		}
		
		else if(laneNum<(eLane.length+rLane.length)){
			rLane[laneNum-eLane.length].add(shopper);
		}
		else{
			throw new IllegalArgumentException("there is no such lane!");
		}
	}
	/**A method to simulate foodland
	 * 
	 */
	public void simulateCheckout() {
		boolean endflag = true;
		while(endflag){
			int eEmpty = 0;
			int rEmpty = 0;
			for(int i = 0;i<eLane.length;i++){
				String out = "Express Lane " + i;
				if(eLane[i].peek()!=null){
				out =  out + ",shopper had " + eLane[i].poll().numItems + " items";
				}
				else{
					out = out + " is empty";
					eEmpty++;
				}
				System.out.println(out);
			}
			boolean rflag = true;
			for(int n = 0;n<rLane.length;n++){
				String out = "Regular Lane " + (eLane.length+n);
				if(rLane[n].peek()!=null){
				out =  out + ",shopper had " + rLane[n].poll().numItems + " items";
				}
				else{
					out = out + " is empty";
					rEmpty++;
				}
				System.out.println(out);
			}
			if((rEmpty+eEmpty)>=(eLane.length+rLane.length)){
				endflag = false;
			}			
		}
	}
	public void betterSimulateCheckout() {
		boolean endflag = true;
		while(endflag){
			int eEmpty = 0;
			int rEmpty = 0;
			for(int i = 0;i<eLane.length;i++){
				String out = "Express Lane " + i;
				if(eLane[i].peek()!=null){
				out =  out + ",shopper had " + eLane[i].poll().numItems + " items";
				}
				else{
					out = out + " is empty";
					eEmpty++;
				}
				System.out.println(out);
			}
			boolean rflag = true;
			for(int n = 0;n<rLane.length;n++){
				String out = "Regular Lane " + (eLane.length+n);
				if(rLane[n].peek()!=null){
				out =  out + ",shopper had " + rLane[n].poll().numItems + " items";
				}
				else{
					out = out + " is empty";
					rEmpty++;
				}
				System.out.println(out);
			}
			if((rEmpty+eEmpty)>=(eLane.length+rLane.length)){
				endflag = false;
			}			
		}
	}

}