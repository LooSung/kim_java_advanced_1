package thread.collection.simple.list;

import java.util.Arrays;

import static util.ThreadUtils.sleep;

public class SyncList implements SimpleList {

	private static final int DEFAULT_CAPACITY = 5;

	private Object[] elements;
	private int size = 0;

	public SyncList() {
		elements = new Object[DEFAULT_CAPACITY];
	}

	@Override public synchronized int size() {
		return size;
	}

	@Override public synchronized void add(Object o) {
		elements[size] = o;
		sleep(100); // Multi Thread를 디버깅 하기위해
		size++;
	}

	@Override public synchronized Object get(int index) {
		return elements[index];
	}

	@Override
	public synchronized String toString() {
		return Arrays.toString(Arrays.copyOf(elements, size)) +
				", size = " + size + ", " +
				"capacity = " + elements.length;
	}
}
