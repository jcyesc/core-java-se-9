package org.core.java.chapter10;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Threadsafe Data Structures
 * 
 * If multiple thread concurrently modify a data structure, such as a queue or
 * hash table, it is easy to damage the internals of the data structure. For
 * example, one thread may begin to insert a new element. Supposed it is
 * preempted in the middle of rerouting links, and another thread starts
 * traversing the same location. The second thread may follow invalid links and
 * create havoc, perhaps throwing exceptions or even getting trapped in an
 * infinite loop.
 * 
 * We can use locks to ensure that only one thread can access the data structure
 * at a given point in time, blocking any others. But we can do better than
 * that. The collections in the {@link java.util.concurrent} package have been
 * cleverly implemented so that multiple threads can access them without
 * blocking each other, provided they access different parts.
 * 
 * Iterators and the concurrent APIs
 *
 * These collections yield weakly consistent iterators. That means that the
 * iterators present elements appearing at onset of iteration, but may or may
 * not reflect some or all of the modifications that were made after they were
 * constructed. However, such an iterator will not throw a
 * ConcurrentModificationException.
 * 
 * In contrast, an iterator of a collection in the {@link java.util} package throws
 * a {@link ConcurrentModificationException} when the collection has been modified after 
 * construction of the iterator.
 * 
 * Other threadsafe data structures
 * 
 * <ul>
 * <li>{@link java.util.concurrent.ConcurrentSkipListMap}
 * <li>{@link java.util.concurrent.ConcurrentSkipListSet}
 * <li>{@link java.util.concurrent.CopyOnWriteArrayList}
 * <li>{@link java.util.concurrent.CopyOnWriteArraySet}
 * </ul>
 * 
 */
public class ConcurrentHashMapExample {
	public static void main(String[] args) {
		concurrentHashMap();
	}

	private static void concurrentHashMap() {
		System.out.println("ConcurrentHashMapExample.concurrentHashMap()");
		ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

		map.put("first", 2);
		map.put("second", 5);
		System.out.println(map);

		map.computeIfPresent("first", (key, value) -> {
			return value * 2;
		});

		map.computeIfAbsent("third", (key) -> 1);
		System.out.println(map);
	}
}
