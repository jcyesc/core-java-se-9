package org.core.java.chapter3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Higher-Order Functions
 * 
 * In a functional programming language, functions are first-class citizens. 
 * Functions that process or return functions are called higher-order functions.
 */
public class HighOrderFunctions {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		list.add(34);
		list.add(8);
		list.add(23);
		list.add(12);
		
		list.sort(reverse());
	}

	/**
	 * Returns a function, or technically, instances of classes that implement a functional interface.
	 * @return
	 */
	public static Comparator<Integer> reverse() {
		return (x, y) -> y - x;
	}
	
}
