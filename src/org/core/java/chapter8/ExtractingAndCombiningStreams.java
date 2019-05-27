package org.core.java.chapter8;

import java.util.List;
import java.util.stream.Collectors;

public class ExtractingAndCombiningStreams {

	public static void main(String[] args) {
		skip();
		takeWhile();
		dropWhile();
	}

	public static void skip() {
		System.out.println("ExtractingAndCombiningStreams.skip()");
		List<String> words = List.of("we", "like", "Jazz");

		words.stream().skip(1).forEach(System.out::println);
	}
	
	public static void takeWhile() {
		System.out.println("\nExtractingAndCombiningStreams.takeWhile()");
		
		List<String> words = List.of("we", "like", "Jazz");

		String result = words.stream().takeWhile(s -> s.contains("e")).collect(Collectors.joining(" "));
		System.out.println(result);
	}

	public static void dropWhile() {
		System.out.println("\nExtractingAndCombiningStreams.dropWhile()");
		
		List<String> words = List.of("we", "like", "Jazz");

		String result = words.stream().dropWhile(s -> s.contains("e")).collect(Collectors.joining(" "));
		System.out.println(result);
	}
}
