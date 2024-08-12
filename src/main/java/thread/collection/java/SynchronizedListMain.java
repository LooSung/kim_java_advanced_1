package thread.collection.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SynchronizedListMain {
	public static void main(String[] args) {
		List<String> list = Collections.synchronizedList(new ArrayList<>());
		list.add("DATA_1");
		list.add("DATA_2");
		list.add("DATA_3");

		System.out.println(list.getClass());
		System.out.println("List : " + list);
	}
}
