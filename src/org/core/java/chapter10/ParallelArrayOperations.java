package org.core.java.chapter10;

import java.util.Arrays;

/**
 * Parallel Array Operations
 * 
 * The {@link Arrays} class has a number of parallelized operations. The
 * operations break the array into sections, work on them concurrently, and
 * combine the results.
 * 
 */
public class ParallelArrayOperations {

	public static void main(String[] args) {
		parallelSetAll();

		parallelSort();
	}

	public static void parallelSetAll() {
		System.out.println("ParallelArrayOperations.fillArray()");
		int array[] = new int[100];
		Arrays.parallelSetAll(array, index -> index % 10);
		System.out.println(Arrays.toString(array));
	}

	public static void parallelSort() {
		System.out.println("\nParallelArrayOperations.parallelSort()");
		String array[] = { "one", "two", "three", "four", "five", "six" };
		Arrays.parallelSort(array, String::compareTo);
		System.out.println(Arrays.toString(array));
	}
}
