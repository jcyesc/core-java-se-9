package org.core.java.chapter10;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CompletableFutures
 * 
 * The {@link CompletableFuture} class provides a mechanism for composing
 * asynchronous tasks into a processing pipeline.
 */
public class ComposingCompletableFutures {
	public static void main(String[] args) {
		generateSortAndPrint();
	}

	/**
	 * This methods generate a list of numbers, sort the list and print the list
	 * using several threads.
	 */
	public static void generateSortAndPrint() {
		System.out.println("ComposingCompletableFutures.generateSortAndPrint()");
		ExecutorService executor = Executors.newFixedThreadPool(10);

		CompletableFuture<List<Integer>> generatedList = generateList(executor);
		CompletableFuture<List<Integer>> sortedList = generatedList.thenApply((list) -> {
			System.out.println("Sorting List in Thread: " + Thread.currentThread().getName());
			list.sort((a, b) -> a - b);
			return list;
		});

		sortedList.thenAccept((list) -> {
			System.out.println("Printing List in Thread: " + Thread.currentThread().getName());
			System.out.println(list);
		});

		executor.shutdown();
	}

	private static CompletableFuture<List<Integer>> generateList(ExecutorService executor) {
		CompletableFuture<List<Integer>> future = CompletableFuture.supplyAsync(() -> {
			System.out.println("Generating List in Thread: " + Thread.currentThread().getName());
			List<Integer> list = new ArrayList<>();

			// Increase or Decrease the number of elements in the list to see which thread
			// execute the sorting (try 50 and 10,000).
			for (int i = 0; i < 100; i++) {
				list.add((int) (Math.random() * 1000));
			}

			return list;
		}, executor);

		return future;
	}
}
