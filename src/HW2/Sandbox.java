package HW2;
import java.util.ArrayList;

public class Sandbox {
	
	private int[] main_array;
	private int size;
	
	public static void main(String args[]){
		Sandbox run = new Sandbox();
		ArrayList<Integer> al = new ArrayList<Integer>();
		al.add(0, 1);
		al.add(10);
		run.print(al);
	}
	
	Sandbox(){
		size = 5;
		main_array = new int[size];
	}
	
	public void fill_test(){
		for(int i = 0; i<size;i++){
			main_array[i]=(int)(Math.random()*10);
		}
	}
	public void print(ArrayList in){
		String to_print = "[";
		for(int i = 0;i<in.size();i++){
			to_print = to_print + in.get(i);
			if(i==in.size()-1){
				to_print = to_print + "]";
			}
			else{
				to_print = to_print + ",";
			}
		}
		System.out.println(to_print);
	}
	public void end_add(int value){
		int[] temp_array = new int[main_array.length+1];
		for(int i =0;i<size;i++){
			temp_array[i]=main_array[i];
		}
		temp_array[size] = value;
		size = temp_array.length;
		main_array = temp_array;
	}
	public void index_add(int value,int index){
		
	}

}
