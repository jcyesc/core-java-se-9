package org.core.java.chapter8;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Reductions
 * 
 * Reductions are terminal operations. They reduce the stream to a nonstream
 * value that can be used in your program.
 */
public class SimpleReductionsWithFindAndAny {

	public static void main(String[] args) {
		max();
		findFirst();
		findAny();
		anyMatch();
		allOrNoneMatch();
	}

	public static void max() {
		System.out.println("\nSimpleReductionsWithFindAndAny.max()");

		Optional<String> largest = Stream.of("let", "the", "people", "know", "the", "truth")
				.max(String::compareToIgnoreCase);
		largest.ifPresent(System.out::println);
	}

	public static void findFirst() {
		System.out.println("\nSimpleReductionsWithFindAndAny.findFirst()");

		Optional<String> first = Stream.of("let", "the", "people", "know", "the", "truth").filter(w -> w.length() > 3)
				.findFirst();
		first.ifPresent(System.out::println);
	}

	/**
	 * If you are OK with any match, not just the first one, use the findAny method.
	 * This is effective when you parallelize the stream, since the stream can
	 * report any match that it finds instead of being constrained to the first one.
	 */
	public static void findAny() {
		System.out.println("\nSimpleReductionsWithFindAndAny.findAny()");

		Optional<String> any = Stream.of("let", "the", "people", "know", "the", "truth").parallel()
				.filter(w -> w.length() > 3).findAny();
		any.ifPresent(System.out::println);
	}
	
	/**
	 * If you just want to know if there is a match, use anyMatch. That method takes a predicate
	 * argument, so you won't need to use filter.
	 */
	public static void anyMatch() {
		System.out.println("\nSimpleReductionsWithFindAndAny.anyMatch()");
		
		boolean result = Stream.of("let", "the", "people", "know", "the", "truth").parallel()
				.anyMatch(w -> w.contains("z"));
		System.out.println("Is there anyMatch (word that contains 'z'? " + result);
	}
	
	public static void allOrNoneMatch() {
		System.out.println("\nSimpleReductionsWithFindAndAny.allOrNoneMatch()");
		
		boolean result = Stream.of("let", "the", "people", "know more", "than the", "truthest true").parallel()
				.allMatch(w -> w.contains("e"));
		System.out.println("Do all words contain 'e'? " + result);
		
		result = Stream.of("let", "the", "people", "know", "the", "truth").parallel()
				.noneMatch(w -> w.contains("z"));
		System.out.println("Does none of the words contain 'z'? " + result);
		
	}
}
