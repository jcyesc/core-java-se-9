package org.core.java.chapter8;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Shows how to create an Stream from an array of using the method
 * {@link Stream::of}.
 */
public class StreamCreation {
	public static void main(String[] args) {
		creation();

		generateInfiniteStreams();
	}

	public static void creation() {
		System.out.println("\nStreamCreation.of()");

		Stream<String> wordsOfSong = Stream.of("Stop", "me", "uhhh", "if", "you");
		String song = wordsOfSong.map(String::toUpperCase).collect(Collectors.joining(","));
		System.out.println("Song: " + song);

		int[] primeNumbers = { 1, 2, 3, 5 };
		long count = Arrays.stream(primeNumbers).count();
		System.out.println("\n# of prime numbers in the array: " + count);
	}

	/**
	 * Generate The {@link Stream} interface has two static methods for making
	 * infinite streams. The {@link Stream#generate(java.util.function.Supplier)}
	 * method takes a function with not arguments. Whenever a stream value is
	 * needed, that function is called to produce a value.
	 * 
	 * Iterate To produce sequences such as 0 1 2 3 ..., use the
	 * {@link Stream#iterate(Object, java.util.function.UnaryOperator)} method
	 * instead. It takes a "seed" value and a function and repeatedly applies the
	 * function to the previous result.
	 * 
	 * To produce a finite stream instead, add a predicate that specifies when the
	 * iteration should finish.
	 */
	public static void generateInfiniteStreams() {
		System.out.println("\nStreamCreation.generateInfiniteStreams()");

		// Stream.generate() example.
		System.out.println("\nStream.generate()");
		int maxSize = 10;
		Stream<String> echo = Stream.generate(() -> "Love");
		String echoes = echo.limit(maxSize).collect(Collectors.joining(", "));
		System.out.println("echoes: " + echoes);

		// Stream.iterate() example.
		System.out.println("\nStream.iterate()");
		Stream<BigInteger> integers = Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.ONE));
		integers.limit(5).forEach(System.out::print);

		// Finite stream
		System.out.println("\n\nFinite Stream");
		BigInteger limit = new BigInteger("10");
		Stream<BigInteger> integers2 = Stream.iterate(BigInteger.ZERO,
				n -> n.compareTo(limit) < 0,
				n -> n.add(BigInteger.ONE));
		integers2.forEach(System.out::print);

	}

}
