package HW11;

import java.security.KeyStore.Entry;
import java.util.LinkedList;

public class proto<K,V> {
	LinkedList<Entry>[] table;
	public static void main(String args[]){
		System.out.println("whatup".hashCode());
		proto test = new proto(10,false);
		test.get(10);
	}
	
	proto(int capacity, boolean printTimes){
		
		table = new LinkedList[capacity];
		// constructor
	}
	public V put(K key, V value){
		return null;		
	}           // add or replace a Key,Value
	public V get(K key){
		int index = Math.abs(key.hashCode())%table.length;
		LinkedList<Entry> temp = table[index];
		return null;
	}                 // return a value for the given key
}
