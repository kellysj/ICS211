package HW4;

public interface ListIterator<E> {
	  boolean hasNext(); // Returns true if this list iterator has more elements while traversing in the forward direction.
	  boolean hasPrevious(); // Returns true if this list iterator has more elements while traversing in the reverse direction.
	  E next(); // Returns the next Element.
	  int nextIndex(); // Returns the index of the next element.
	  E previous(); // Returns the previous Element
	  int previousIndex(); // Returns the index of the previous element.
	}