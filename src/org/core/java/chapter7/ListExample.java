package org.core.java.chapter7;

import java.util.ArrayList;
import java.util.List;

public class ListExample {
	public static void main(String[] args) {
		set();
	}
	
	private static void set() {
		List<Integer> numbers = new ArrayList<>();
		numbers.add(0);
		numbers.add(1);
		numbers.add(3);
		numbers.add(4);
		System.out.println(numbers);
		
		// Setting a number in an specific index.
		int index = 2;
		numbers.set(index, 2);
		
		System.out.println(numbers);		
	}
}
