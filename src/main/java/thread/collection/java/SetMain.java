package thread.collection.java;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;

public class SetMain {
	public static void main(String[] args) {
		Set<Integer> listSet = new CopyOnWriteArraySet<>();
		listSet.add(1);
		listSet.add(2);
		listSet.add(3);
		System.out.println("Copy Set : " + listSet);

		// Comparator(순서를 정의하기 위한 인터페이스)를 받기 떄문에 순서를 정리한다.
		ConcurrentSkipListSet<Object> skipSet = new ConcurrentSkipListSet<>();
		skipSet.add(3);
		skipSet.add(2);
		skipSet.add(1);
		System.out.println("Skip Set : " + skipSet);
	}
}
