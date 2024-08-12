package thread.collection.simple.list;

import java.util.Arrays;

import static util.ThreadUtils.sleep;

public class BasicList implements SimpleList {

	private static final int DEFAULT_CAPACITY = 5;

	private Object[] elements;
	private int size = 0;

	public BasicList() {
		elements = new Object[DEFAULT_CAPACITY];
	}

	@Override public int size() {
		return size;
	}

	@Override public void add(Object o) {
		elements[size] = o;
		sleep(100); // Multi Thread를 디버깅 하기위해
		size++;
	}

	@Override public Object get(int index) {
		return elements[index];
	}

	@Override
	public String toString() {
		return Arrays.toString(Arrays.copyOf(elements, size)) +
				", size = " + size + ", " +
				"capacity = " + elements.length;
	}
}
