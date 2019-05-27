package org.core.java.chapter8;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * When you are don with a stream, it is common to desire to collect the result
 * in a data structure. This class shows some examples.
 */
public class CollectorsExample {
	public static void main(String[] args) {
		collect();
	}

	public static void collect() {
		System.out.println("CollectorsExample.collect()");
		List<String> names = List.of("Graph Theory", "Calculus", "Arithmetic", "Geometric", "Graph Theory");

		// Converting an Stream to an Array.
		System.out.println("\nStream to an Array");
		Stream<String> upperCaseNames = names.stream().map(String::toUpperCase);
		String[] result = upperCaseNames.toArray(String[]::new);
		System.out.println(Arrays.toString(result));

		// Using Collectors.toList();
		System.out.println("\nCollectors.toList()");
		List<String> sortedList = names.stream().sorted((a, b) -> b.compareTo(a)).collect(Collectors.toList());
		System.out.println(sortedList);

		// Using Collectors.toSet();
		System.out.println("\nCollectors.toSet()");
		Set<String> sortedSet = names.stream().map(String::toLowerCase).collect(Collectors.toSet());
		System.out.println(sortedSet);

		// Using Collectors.joining();
		System.out.println("\nCollectors.joining()");
		String subjects = names.stream().collect(Collectors.joining(", "));
		System.out.println(subjects);

		// Using Collectors.summarizingInt();
		System.out.println("\nCollectors.summarizingInt()");
		IntSummaryStatistics summary = names.stream().sorted((a, b) -> b.compareTo(a))
				.collect(Collectors.summarizingInt(String::length));
		double averageWordLength = summary.getAverage();
		double maxWordLength = summary.getMax();
		double count = summary.getCount();
		System.out.println("averageWordLength: " + averageWordLength);
		System.out.println("maxWordLength: " + maxWordLength);
		System.out.println("# strings: " + count);
	}

}
