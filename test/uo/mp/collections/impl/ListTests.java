package uo.mp.collections.impl;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import uo.mp.collections.List;

public abstract class ListTests {

	List<String> empty;
	List<String> some;
	List<String> several;
	
	@Before
	public abstract void setUp();

	@Test
	public void hasNextEmpty() {
		Iterator<String> i1 = empty.iterator();
		assertFalse(i1.hasNext());
	}

	@Test
	public void hasNextSome() {
		Iterator<String> i2 = some.iterator();
		assertTrue(i2.hasNext());
	}

	@Test
	public void hasNextSeveral() {
		Iterator<String> i3 = several.iterator();
		assertTrue(i3.hasNext());
	}

	@Test
	public void nextEmpty() {
		Iterator<String> i1 = empty.iterator();
		try {
			i1.next();
		} catch (NoSuchElementException ex) {
		}
	}

	@Test
	public void nextSome() {
		Iterator<String> i2 = some.iterator();
		assertEquals(i2.next(), "A");
		assertEquals(i2.next(), "B");
		assertEquals(i2.next(), "C");
		try {
			i2.next();
		} catch (NoSuchElementException ex) {
		}
	}

	@Test
	public void nextSeveral() {
		Iterator<String> i3 = several.iterator();
		assertEquals(i3.next(), "X1");
		assertEquals(i3.next(), "X2");
		assertEquals(i3.next(), "Y1");
		assertEquals(i3.next(), "Y2");
		assertEquals(i3.next(), "Z1");
		assertEquals(i3.next(), "Z2");
		try {
			i3.next();
		} catch (NoSuchElementException ex) {
		}
	}

	@Test
	public void removeEmpty() {
		Iterator<String> i1 = empty.iterator();
		try {
			i1.remove();
		} catch (IllegalStateException ex) {
		}
	}

	@Test
	public void removeSome() {
		Iterator<String> i2 = some.iterator();
		try {
			i2.remove();
		} catch (IllegalStateException ex) {
		}
		assertEquals(i2.next(), "A");
		i2.remove();
		assertEquals(some.get(0), "B");
		assertEquals(some.get(1), "C");
		try {
			i2.remove();
		} catch (IllegalStateException ex) {
		}
		assertEquals(i2.next(), "C");
	}

	@Test
	public void removeSeveral() {
		Iterator<String> i3 = several.iterator();
		assertEquals(i3.next(), "X1");
		i3.remove();
		assertEquals(several.get(0), "X2");
		assertEquals(i3.next(), "Y1");
		try {
			i3.remove();
		} catch (IllegalStateException ex) {
		}
		try {
			i3.remove();
		} catch (IllegalStateException ex) {
		}
		assertEquals(several.get(0), "X2");
	}
}
