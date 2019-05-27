package org.core.java.chapter8;

import java.util.List;

public class IterateWithStreams {

	public static void main(String[] args) {
		iterate();
	}

	public static void iterate() {
		System.out.println("IterateWithStreams.iterate()\n");

		String sentence = "This is a very simple sentence that will be splitted";
		List<String> words = List.of(sentence.split("\\PL+"));

		// Print every word
		words.stream().forEach(System.out::println);

		// Count the words longer than 3 characters.
		long countWords = words.stream().filter(w -> w.length() > 3).count();
		System.out.println("\n# of words longer than 3 characters: " + countWords);
	}
}
