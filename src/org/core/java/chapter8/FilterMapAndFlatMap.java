package org.core.java.chapter8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FilterMapAndFlatMap {

	public static void main(String[] args) {
		usingFilterMapAndFlapMapp();
	}

	public static void usingFilterMapAndFlapMapp() {
		System.out.println("FilterMapAndFlatMap.usingFilterMapAndFlapMapp()");
		List<String> words = List.of("Life", "is", "beautiful");

		// Using filer()
		System.out.println("\nUsing filter()");
		words.stream().filter(w -> w.length() > 2).forEach(System.out::println);

		// Using map()
		System.out.println("\nUsing map()");
		words.stream().map(String::toUpperCase).forEach(System.out::printf);

		// Using flatMap();
		System.out.println("\n\nBefore using flatMap");
		List<char[]> result = words.stream().map(String::toCharArray).collect(Collectors.toList());
		result.stream().forEach(e -> System.out.println(Arrays.toString(e)));
		
		// Arrays.stream() doesn't have method to receive an array of char[].
		System.out.println("\n After using flatMap");
		words.stream().flatMap(s -> {
			char c[] = s.toCharArray();
			Character[] ch = new Character[c.length];
			for (int i = 0; i < c.length; i++) {
				ch[i] = c[i];
			}
			return Arrays.stream(ch);
		}).forEach(System.out::println);
	}
}
