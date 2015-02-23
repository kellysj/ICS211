package HW3;

public interface List211<E> {
	  boolean add(E e);
	  void add(int index, E element);
	  E get(int index);
	  E remove(int index);
	  E set(int index, E element);
	  int size();
}
