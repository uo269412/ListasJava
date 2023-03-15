package uo.mp.collections.collections;

import java.util.Comparator;

import uo.mp.collections.List;
import uo.mp.collections.impl.LinkedList;

public class Collections {

	public static <T> void sort(List<T> list) {
		List<T> aux = new LinkedList<T>();
		int pos;
		for (T t : list) {
			pos = searchPosInList(aux, t);
			aux.add(pos, t);
		}
		copy(aux, list);
	}

	public static <T> void sort(List<T> list, Comparator<T> comp) {
		List<T> aux = new LinkedList<T>();
		int pos;
		for (T t : list) {
			pos = searchPosInList(aux, t, comp);
			aux.add(pos, t);
		}
		copy(aux, list);
	}

	@SuppressWarnings("unchecked")
	private static <T> int searchPosInList(List<T> list, T t) {
		if (list.isEmpty()) {
			return 0;
		}
		for (int i = 0; i < list.size(); i++) {
			if (((Comparable<T>) t).compareTo(list.get(i)) <= 0) {
				return i;
			}
		}
		return list.size();
	}

	private static <T> void copy(List<T> aux, List<T> list) {
		list.clear();
		for (T t : aux) {
			list.add(t);
		}
	}

	private static <T >int searchPosInList(List<T> list, T t, Comparator<T> comp) {
		if (list.isEmpty()) {
			return 0;
		}
		for (int i = 0; i < list.size(); i++) {
			if (comp.compare(t, list.get(i))<= 0) {
				return i;
			}
		}
		return list.size();
	}

}
