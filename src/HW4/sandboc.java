package HW4;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

public class sandboc {
	public static void main(String args[]){
		ArrayList<Integer> testing = new ArrayList<Integer>();
		LinkedList<Integer> linkedtest = new LinkedList<Integer>();
		for(int i = 0;i<=10;i++){
			Integer valueAdded = Integer.valueOf((int)(10*Math.random()));
			testing.add(valueAdded);
			linkedtest.add(valueAdded);
		}
		ListIterator<Integer> itchecking = testing.listIterator();
		ListIterator<Integer> linkedcheck = linkedtest.listIterator(linkedtest.size());
		ListIterator<Integer> linkedcheckstart = linkedtest.listIterator(0);
		String lisS= "[";
		while(itchecking.hasNext()){
			System.out.println(itchecking.nextIndex());
			lisS = lisS + itchecking.next();
			System.out.println(itchecking.nextIndex());
			if(itchecking.hasNext()==false){
				lisS = lisS + "]";
			}
			else{
				lisS = lisS + ",";
			}
		}
		String linS = "[";
		while(linkedcheck.hasPrevious()){
			linS = linS + linkedcheck.previous();
			if(linkedcheck.previousIndex()==-1){
				linS = linS + "]";
			}
			else{
				linS = linS + ",";
			}
		}
		System.out.println("Linked List backwards:" + linS);
		System.out.println("Array List forwards:" + lisS);
		lisS = "[";
		while(linkedcheckstart.hasNext()){
			lisS = lisS + linkedcheckstart.next();
			if(linkedcheckstart.hasNext()==false){
				lisS = lisS + "]";
			}
			else{
				lisS = lisS + ",";
			}
		}
		System.out.println("Linked List forwards:" + lisS);
	}

}
