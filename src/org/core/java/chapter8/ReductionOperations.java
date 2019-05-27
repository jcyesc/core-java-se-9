package org.core.java.chapter8;

import java.util.List;
import java.util.Optional;

/**
 * The reduce method is a general mechanism for computing a value from a stream.
 * The simplest form takes a binary function and keeps applying it, starting
 * with the first two elements.
 */
public class ReductionOperations {

	public static void main(String[] args) {
		reduceByAdding();

		reduceWithAccumulator();
	}

	public static void reduceByAdding() {
		System.out.println("ReductionOperations.sum()");
		List<Integer> values = List.of(1, 2, 3, 4, 5);
		Optional<Integer> sum = values.stream().reduce((x, y) -> x + y);

		System.out.println(sum);
	}

	public static void reduceWithAccumulator() {
		System.out.println("\nReductionOperations.reduceWithAccumulator()");
		List<String> values = List.of("one", "two", "three", "four", "five", "six");
		int totalLength = values.stream().reduce(0, (total, word) -> total + word.length(),
				(total1, total2) -> total1 + total2);

		System.out.println(totalLength);

	}
}
