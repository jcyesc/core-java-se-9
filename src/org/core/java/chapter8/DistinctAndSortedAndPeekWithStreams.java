package org.core.java.chapter8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

public class DistinctAndSortedAndPeekWithStreams {
	public static void main(String[] args) {
		distinct();
		sort();
		peek();
	}

	/**
	 * The distinct method returns a stream that yields elements from the original
	 * stream, in the same order, except that duplicates are suppressed.
	 */
	public static void distinct() {
		System.out.println("\nDistinctAndSortedAndPeekWithStreams.distinct()");

		Stream<String> uniqueWords = Stream.of("let", "the", "people", "know", "the", "truth").distinct();
		uniqueWords.forEach(System.out::println);
	}

	/**
	 * For sorting a stream, there are several variations of the sorted method. One
	 * works for streams of Comparable elements, and another accepts a Comparator.
	 */
	public static void sort() {
		System.out.println("\nDistinctAndSortedAndPeekWithStreams.sort()");
		Stream<String> sortedWords = Stream.of("let", "the", "people", "know", "the", "truth")
				.sorted(Comparator.comparing(String::length).reversed());
		sortedWords.forEach(System.out::println);
	}

	/**
	 * The peek method yields another stream with the same elements as the original,
	 * but a function is invoked every time an element is retrieved.
	 */
	public static void peek() {
		System.out.println("\nDistinctAndSortedAndPeekWithStreams.peek()");
		
		Stream<Integer> lazyStream = Stream.iterate(1, p -> p * 2).peek(e -> System.out.println("Fetching " + e)).limit(20);
		System.out.println("No elements printed yet :(");
		Object[] powers =  lazyStream.toArray();
		System.out.println(Arrays.toString(powers));
	}
}
