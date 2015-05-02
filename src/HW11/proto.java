package HW11;

import java.security.KeyStore.Entry;
import java.util.LinkedList;
import java.util.Map;

public class proto<K,V>{
	LinkedList<hashEntry>[] table;
	public static void main(String args[]){
		System.out.println("whatup".hashCode());
		System.out.println("lol".hashCode());
		proto test = new proto(10,false);
		
		test.put("lol", 10);
		System.out.println("getting lol:" + test.get("lol"));
		System.out.println("getting lolin(should fail): " + test.get("lolin"));
	}
	
	proto(int capacity, boolean printTimes){
		
		table = new LinkedList[capacity];
		// constructor
	}
	public V put(K key, V value){
		int index = Math.abs(key.hashCode())%table.length;
		hashEntry temp = new hashEntry(key,value);
		if(table[index]==null){
			table[index] = new LinkedList<hashEntry>();
			table[index].add(temp);
			return (V)table[index].peekLast().getValue();
		}
		else{
			int i = 0;
			for(hashEntry tempEntry : table[index]){
				if(tempEntry.getKey()==key){
					if(tempEntry.getValue()!=value){
						
					}
					else{
						throw new IllegalArgumentException("this key,value already exists in this hash");
					}					
				}
			}
			table[index].add(temp);
			return (V)table[index].peekLast().getValue();
		}
	}           // add or replace a Key,Value
	public V get(K key){
		int index = Math.abs(key.hashCode())%table.length;
		LinkedList<hashEntry> tempChain = table[index];
		if(tempChain==null){
			throw new IllegalArgumentException("this key doesn't exist this hash");
		}
		else{
			int i = 0;
			for(hashEntry tempEntry : table[index]){
				if(tempEntry.getKey()==key){
					return (V)tempEntry.getValue(); 
				}
				else{
					throw new IllegalArgumentException("this key doesn't exist this hash");
				}	
			}
		}
		throw new IllegalArgumentException("something wrong in get method");
	}                 // return a value for the given key

	final class hashEntry<K,V> implements Map.Entry<K,V>{
		private final K key;
		private V value;
		hashEntry(K key, V value){
			this.key = key;
			this.value = value;
		}

		@Override
		public K getKey() {
			// TODO Auto-generated method stub
			return key;
		}

		@Override
		public V getValue() {
			// TODO Auto-generated method stub
			return value;
		}

		@Override
		public V setValue(V value) {
			// TODO Auto-generated method stub
			this.value = value;
			return value;
		}
		
	}
}
