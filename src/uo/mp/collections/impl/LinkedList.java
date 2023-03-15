package uo.mp.collections.impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

import uo.mp.collections.List;
import uo.mp.collections.check.Arguments;

public class LinkedList<T> implements List<T> {
	private Node head;
	private int numberOfElements;

	class Node {
		T value;
		Node next;

		Node(T value, Node next) {
			this.value = value;
			this.next = next;
		}
	}

	@Override
	public int size() {
		return this.numberOfElements;

	}

	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

	@Override
	public boolean contains(Object o) {
		for (int i = 0; i < numberOfElements; i++) {
			if (get(i).equals(o)) {
				return true;
			}
		}
		return false;
	}

	public void addFirst(T value) {
		this.head = new Node(value, this.head);
		this.numberOfElements++;
	}

	@Override
	public boolean add(T element) {
		Arguments.checkIllegalArgumentException(element);
		if (isEmpty())
			addFirst(element);
		else {
			Node last = getNode(size() - 1);
			last.next = new Node(element, null);
			this.numberOfElements++;
		}
		return true;
	}

	@Override
	public boolean remove(T o) {
		if (contains(o)) {
			int index = indexOf(o);
			remove(index);
			return true;
		}
		return false;
	}

	@Override
	public void clear() {
		for (int i = size() - 1; i >= 0; i--) {
			remove(i);
		}
	}

	@Override
	public T get(int index) {
		Arguments.checkIndexOutOfBoundsException(index >= 0 || index < size());
		return getNode(index).value;
	}

	private Node getNode(int index) {
		Arguments.checkIndexOutOfBoundsException(index >= 0 || index < size());
		int position = 0;
		Node node = this.head;
		while (position < index && node.next != null) {
			node = node.next;
			position++;
		}
		return node;
	}

	@Override
	public T set(int index, T element) {
		Arguments.checkIndexOutOfBoundsException(index >= 0 || index < size());
		Arguments.checkIllegalArgumentException(element);
		T previous = getNode(index).value;
		getNode(index).value = element;
		return previous;

	}

	@Override
	public void add(int index, T element) {
		Arguments.checkIllegalArgumentException(element);
		Arguments.checkIndexOutOfBoundsException(index >= 0 || index < size());
		if (index == 0)
			addFirst(element);
		else {
			Node previous = getNode(index - 1);
			previous.next = new Node(element, previous.next);
			this.numberOfElements++;
		}
	}

	@Override
	public T remove(int index) {
		Arguments.checkIndexOutOfBoundsException(index >= 0 || index < size());
		if (this.isEmpty())
			return null;
		T value;
		if (index == 0) {
			value = this.head.value;
			this.head = this.head.next;
		} else {
			Node previous = getNode(index - 1);
			value = previous.next.value;
			previous.next = previous.next.next;
		}
		this.numberOfElements--;
		return value;
	}

	@Override
	public int indexOf(Object o) {
		for (int i = 0; i < numberOfElements; i++) {
			if (get(i).equals(o)) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int hashCode() {
		int result = 1;
		for (int i = 0; i < size(); i++) {
			result = 31 * result + (getNode(i).value == null ? 0 : getNode(i).value.hashCode());
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LinkedList<T> other = (LinkedList<T>) obj;
		if (size() == other.size()) {
			for (int i = 0; i < size(); i++) {
				if (!(getNode(i).value.equals(other.getNode(i).value))) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		for (int i = 0; i < size(); i++) {
			builder.append(getNode(i).value.toString());
			if (i != (size() - 1)) {
				builder.append(", ");
			}
		}
		builder.append("]");
		return builder.toString();
	}

	@Override
	public Iterator<T> iterator() {
		LinkedListIterator<T> iteratorLinkedList = new LinkedListIterator<T>();
		return iteratorLinkedList;
	}

	@SuppressWarnings("hiding")
	private class LinkedListIterator<T> implements Iterator<T> {
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
				LinkedList.this.remove(index-1);
				available = false;
			} else {
				throw new IllegalStateException();
			}
		}
	}
}
