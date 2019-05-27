package org.core.java.chapter10;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

/**
 * Completable Futures
 * 
 * When you have a Future object, you need to call get to obtain the value,
 * blocking until the value is available. The {@link CompletableFuture} class
 * implements the {@link Future} interface, and it provides a second mechanism
 * for obtaining the result. You register a callback that will be invoked (in
 * some thread) with the result once it is available.
 * 
 */
public class AsynchronousComputations {

	public static void main(String[] args) throws Exception {
		supplyAsync_thenAccept();

		supplyAsync_whenComplete();

		sortUsingCompletableFuture();
	}

	/**
	 * To run a task asynchronously and obtain a {@link CompletableFuture}, you
	 * don't submit it directly to an executor service. Instead, you call the static
	 * method {@link CompletableFuture#supplyAsync(java.util.function.Supplier)}.
	 * 
	 * If we omit the executor, the task is run on a default executor (namely the
	 * executor returned by {@link ForkJoinPool#commonPool()}.
	 */
	public static void supplyAsync_thenAccept() throws InterruptedException {
		System.out.println("AsynchronousComputations.supplyAsync_thenAccept()");
		ExecutorService executor = Executors.newFixedThreadPool(10);
		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
			System.out.println(Thread.currentThread().getName() + " is about to sleep zzzzz!");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "DONE by " + Thread.currentThread().getName();
		}, executor);

		future.thenAccept((String r) -> System.out.println("The result is " + r));
		System.out.println("Continue executing programming even if the task has not been completed!");

		executor.shutdown();
		Thread.sleep(1000);
	}

	/**
	 * A {@link CompletableFuture} can complete in two ways: either with a result,
	 * or with an uncaught exception. In order to handle both cases, use the
	 * whenComplete method. The supplied function is called with the result (or null
	 * if none) and the exception (or null if none).
	 */
	public static void supplyAsync_whenComplete() throws InterruptedException {
		System.out.println("\nAsynchronousComputations.supplyAsync_whenComplete()");
		ExecutorService executor = Executors.newFixedThreadPool(10);
		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
			System.out.println(Thread.currentThread().getName() + " is about to sleep zzzzz!");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "DONE by " + Thread.currentThread().getName();
		}, executor);

		future.whenComplete((result, exception) -> {
			if (exception == null) {
				System.out.println("The result is " + result);
			} else {
				System.out.println("The exception is " + exception);
			}
		});

		System.out.println("Continue executing programming even if the task has not been completed!");

		executor.shutdown();
		Thread.sleep(1000);
	}

	/**
	 * The {@link CompletableFuture} is called completable because you can manually
	 * set a completion value. (In other concurrency libraries, such an object is
	 * called a promise). Of course, when you create a CompletableFuture with
	 * supplyAsync, the completion value is implicitly set when the task has
	 * finished. But setting the result explicitly gives you additional flexibility.
	 * For example two tasks can work simultaneously on computing an answer.
	 */
	public static void sortUsingCompletableFuture() {
		System.out.println("\nAsynchronousComputations.sortUsingCompletableFuture()");
		// A list of numbers will be created and then two threads will sort them, one
		// using an inefficient algorithm and the other with Quicksort.
		List<Integer> copy1 = createListOfRandomNumbers();
		List<Integer> copy2 = new ArrayList<>(copy1);
		copy1.add(-1); // To identify the list
		copy2.add(-2); // To identify the list
		CompletableFuture<List<Integer>> future = new CompletableFuture<>();
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(() -> {
			bubbleSort(copy1);
			System.out.println(Thread.currentThread().getName() + " completed sorting with Bubblesort "
					+ System.currentTimeMillis());
			future.complete(copy1);
		});
		executor.execute(() -> {
			copy2.sort((x, y) -> x - y);
			System.out.println(Thread.currentThread().getName() + " completed sorting with Quicksort "
					+ System.currentTimeMillis());
			future.complete(copy2);

		});

		future.thenAccept(sortedList -> {
			System.out.println("thenAccept() -> Sorting is DONE");
			System.out.println(sortedList);
		});

		executor.shutdown();
	}

	/**
	 * Sort the list using the bubble sort algorithm that runs in O (n ^ 2).
	 */
	private static void bubbleSort(List<Integer> list) {
		for (int i = 1; i < list.size(); i++) {
			for (int j = i; j > 0; j--) {
				int currentElem = list.get(j);
				int previousElem = list.get(j - 1);

				if (currentElem < previousElem) {
					list.set(j, previousElem);
					list.set(j - 1, currentElem);
				}
			}
		}
	}

	/**
	 * Creates a list of 9000 random numbers.
	 */
	private static List<Integer> createListOfRandomNumbers() {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < 9000; i++) {
			list.add((int) (Math.random() * 10000));
		}

		return list;
	}
}
