package uo.mp.collections.check;

public class Arguments {

	public static void checkIllegalArgumentException(Object o) {
		if (o == null) {
			throw new IllegalArgumentException();
		}
	}

	public static void checkIndexOutOfBoundsException(boolean expr) {
		if (!expr) {
			throw new IndexOutOfBoundsException();
		}
	}
}
