package thread.collection.java;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class MapMain {
	public static void main(String[] args) {
		Map<Integer, String> map = new ConcurrentHashMap<>();
		map.put(3, "data3");
		map.put(2, "data2");
		map.put(1, "data1");
		System.out.println("Map : " + map);

		Map<Integer, String> map2 = new ConcurrentSkipListMap<>();
		map2.put(3, "data3");
		map2.put(2, "data2");
		map2.put(1, "data1");
		System.out.println("Map : " + map2);
	}
}
