package org.core.java.chapter7;

import java.util.Collections;
import java.util.List;

/**
 * Check the some of the static methods in Collections.
 */
public class CollectionsExample {

	public static void main(String[] args) {
		disjoint();

		nCopies();

		frequency();

		binarySearch();
	}

	public static void disjoint() {
		System.out.println("CollectionsExample.disjoint()");
		List<String> list1 = List.of("one", "two", "three");
		List<String> list2 = List.of("four", "five");

		System.out.println("Collections.disjoint(list1, list2) : " + Collections.disjoint(list1, list2));
	}

	public static void nCopies() {
		System.out.println("\nCollectionsExample.nCopies()");
		List<String> copies = Collections.nCopies(10, "test");
		System.out.println("CollectionsExample.nCopies() -> " + copies);
	}

	public static void frequency() {
		System.out.println("\nCollectionsExample.frequency()");
		List<String> list1 = List.of("one", "two", "three", "one");
		System.out.println("CollectionsExample.frequency() -> " + Collections.frequency(list1, "one"));
	}

	public static void binarySearch() {
		System.out.println("\nCollectionsExample.binarySearch()");

		List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9);

		int index = Collections.binarySearch(numbers, 6, (a, b) -> a - b);

		System.out.println("CollectionsExample.binarySearch(numbers, 6, ...) -> " + index);

	}

}
