package org.core.java.chapter8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ParallelStreams {
	public static void main(String[] args) {
		errorRaceCondition();
		
		parallelStream();
		
		interference();
	}

	public static void errorRaceCondition() {
		// Execute several times and you will see the race condition errors.
		System.out.println("ParallelStreams.errorRaceCondition()");
		int[] shortWords = new int[6];
		List<String> words = List.of("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
				"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
				"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
				"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
				"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
				"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
				"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten");
		words.parallelStream().forEach(s -> {
			if (s.length() < 6) {
				// Error - race condition!
				shortWords[s.length()]++;
			}
		});

		System.out.println(Arrays.toString(shortWords));
	}
	
	public static void parallelStream() {
		System.out.println("\nParallelStreams.parallelStream()");
		List<String> words = List.of("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
				"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
				"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
				"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
				"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
				"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
				"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten");
		Map<Integer, Long> shortWordCounts = words.parallelStream()
				.filter(s -> s.length() < 6)
				.collect(Collectors.groupingBy(String::length, Collectors.counting()));
		
		shortWordCounts.keySet().stream().sorted().forEach(k -> System.out.printf("\n%s -> %d", k, shortWordCounts.get(k)));
	}
	
	public static void interference() {
		System.out.println("\n\nParallelStreams.interference()");
		List<String> words = new ArrayList<>(List.of("one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"));
		words.forEach(s -> {
			if (s.length() < 6)  {
				// Error - interference
				words.remove(s); 
			}
			});
		
		System.out.println(words);
	}
}




