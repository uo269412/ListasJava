package uo.mp.collections.impl;

public class ArrayListTests extends ListTests {
	
	@Override
	public void setUp() {
		empty = new ArrayList<String>();
		some = new ArrayList<String>();
		some.add("A");
		some.add("B");
		some.add("C");
		several = new ArrayList<String>();
		several.add("X1");
		several.add("X2");
		several.add("Y1");
		several.add("Y2");
		several.add("Z1");
		several.add("Z2");
	}

}
