package implementations;

import utilities.Iterator;
import java.util.NoSuchElementException;
import java.util.List;

public class TreeIterator<E> implements Iterator<E> {
    private List<E> elements;
    private int index;

    // Constructor to accept an ArrayList of elements
    public TreeIterator(List<E> elements) {
        this.elements = elements;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < elements.size();
    }

    @Override
    public E next() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements in the list!");
        }
        return elements.get(index++);
    }
}
