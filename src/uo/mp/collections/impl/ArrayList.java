package uo.mp.collections.impl;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import uo.mp.collections.List;
import uo.mp.collections.check.Arguments;

public class ArrayList<T> implements List<T> {
	private Object[] elements;
	private int numberOfElements;
	private final static int INITIAL_CAPACITY = 20;

	@SuppressWarnings("unchecked")
	public ArrayList(int capacity) {
		this.elements = (T[]) new Object[capacity];
		this.numberOfElements = 0;
	}

	@SuppressWarnings("unchecked")
	public ArrayList() {
		this.elements = (T[]) new Object[INITIAL_CAPACITY];
	}

	@Override
	public int size() {
		return numberOfElements;
	}

	@Override
	public boolean isEmpty() {
		return (numberOfElements == 0);
	}

	@Override
	public boolean contains(Object o) {
		for (int i = 0; i < size(); i++) {
			if (elements[i].equals(o)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean add(T element) {
		Arguments.checkIllegalArgumentException(element);
		if (size() >= this.elements.length) {
			this.moreMemory(size() + 1);
		}
		this.elements[size()] = element;
		this.numberOfElements++;
		return true;
	}

	private void moreMemory(int numElem) {
		if (numElem > elements.length) {
			Object[] aux = new Object[Math.max(numElem, 2 * elements.length)];
			System.arraycopy(elements, 0, aux, 0, elements.length);
			elements = aux;
		}
	}

	@Override
	public boolean remove(T o) {
		if (contains(o)) {
			int index_of_object = indexOf(o);
			remove(index_of_object);
			return true;
		}
		return false;
	}

	@Override
	public void clear() {
		if (size() != 0) {
			for (int i = size(); i > 0; i--) {
				remove(i);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(int index) {
		Arguments.checkIndexOutOfBoundsException(index >= 0 || index < size());
		return (T) elements[index];
	}

	@SuppressWarnings("unchecked")
	@Override
	public T set(int index, T element) {
		Arguments.checkIllegalArgumentException(element);
		Arguments.checkIndexOutOfBoundsException(index >= 0 || index < size());
		if (index < size()) {
			Arguments.checkIllegalArgumentException(element);
		}
		Object element_to_return = elements[index];
		elements[index] = element;
		return (T) element_to_return;
	}

	@Override
	public void add(int index, T element) {
		Arguments.checkIllegalArgumentException(element);
		Arguments.checkIndexOutOfBoundsException(index >= 0 || index < size());
		if (size() >= this.elements.length) {
			this.moreMemory(size() + 1);
		}
		for (int i = size(); i > index; i--) {
			this.elements[i] = this.elements[i - 1];
		}
		this.elements[index] = element;
		this.numberOfElements++;
	}

	@Override
	public T remove(int index) {
		Arguments.checkIndexOutOfBoundsException(index >= 0 || index < size());
		@SuppressWarnings("unchecked")
		T value = (T) elements[index];
		for (int j = index; j < size() - 1; j++)
			elements[j] = elements[j + 1];
		numberOfElements--;
		elements[numberOfElements] = null;
		return value;
	}

	@Override
	public int indexOf(Object o) {
		for (int i = 0; i < numberOfElements; i++) {
			if (elements[i].equals(o)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int hashCode() {
		int hashCode = 1;
		if (size() > 0) {
			for (Object e : elements) {
				hashCode = 31 * hashCode + (e == null ? 0 : e.hashCode());
			}
		}
		return hashCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("unchecked")
		ArrayList<T> other = (ArrayList<T>) obj;
		if (!Arrays.deepEquals(elements, other.elements))
			return false;
		if (numberOfElements != other.numberOfElements)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		for (int i = 0; i < size(); i++) {
			builder.append(elements[i].toString());
			if (i != (size() - 1)) {
				builder.append(", ");
			}
		}
		builder.append("]");
		return builder.toString();
	}

	@Override
	public Iterator<T> iterator() {
		ArrayListIterator<T> iteratorArrayList = new ArrayListIterator<T>();
		return iteratorArrayList;
	}

	@SuppressWarnings("hiding")
	private class ArrayListIterator<T> implements Iterator<T> {
		private int index = 0;
		private boolean available = false;

		@Override
		public boolean hasNext() {
			return (index < size());
		}

		@SuppressWarnings("unchecked")
		@Override
		public T next() {
			if (!hasNext()) {
		        throw new NoSuchElementException();
		      }
		      this.index++;
		      this.available = true;
		      return (T) get(index - 1);
		    }

		@Override
		public void remove() {
			if (available) {
				ArrayList.this.remove(index-1);
				available = false;
			} else {
				throw new IllegalStateException();
			}
		}
	}
}
