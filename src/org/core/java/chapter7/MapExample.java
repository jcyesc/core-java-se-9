package org.core.java.chapter7;

import java.util.HashMap;
import java.util.Map;

/**
 * Contains examples of Map methods
 */
public class MapExample {
	public static void main(String[] args) {
		putIfAbsent();

		compute();

		forEach();
	}

	public static void putIfAbsent() {
		System.out.println("MapExample.putIfAbsent()");
		Map<String, Integer> counter = new HashMap<>(Map.of("one", 1, "two", 2, "three", 3, "four", 4, "five", 5));
		System.out.println("Map content -> " + counter);

		Integer previousValue = counter.putIfAbsent("four", 5);
		System.out.println("MapExample.putIfAbsent() -> " + counter);
		System.out.println("Previous value -> " + previousValue);

		previousValue = counter.putIfAbsent("ten", 10);
		System.out.println("MapExample.putIfAbsent() -> " + counter);
		System.out.println("Previous value -> " + previousValue);
	}

	public static void compute() {
		System.out.println("\nMapExample.compute()");
		Map<String, Integer> counter = new HashMap<>(Map.of("one", 1, "two", 2, "three", 3, "four", 4, "five", 5));

		Integer value = counter.compute("two", (k, v) -> {
			return 23;
		});
		System.out.println("MapExample.compute() -> " + counter);
		System.out.println("MapExample.compute() -> " + value);
	}

	public static void forEach() {
		System.out.println("\nMapExample.forEach()");
		Map<String, Integer> counter = new HashMap<>(Map.of("one", 1, "two", 2, "three", 3, "four", 4, "five", 5));
		counter.forEach((k, v) -> {
			System.out.println(String.format("Entry<key, value> = <%s, %s>", k, v));
		});
	}
}
