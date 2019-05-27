package org.core.java.chapter10;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Future
 * 
 * {@link Future} is an object that represents a computation whose result will
 * be available at some future time.
 * 
 * Callable
 * 
 * {@link Callable} represents a task that computes a result.
 */
public class FutureExample {
	public static void main(String[] args) throws Exception {
		future();

		invokeAll();
		
		invokeAny();
	}

	/**
	 * Gets the input from the user using a thread and a Future. Once that the input
	 * is received, prints the name.
	 */
	public static void future() throws InterruptedException, ExecutionException {
		System.out.println("FutureExample.future()");
		ExecutorService executor = Executors.newFixedThreadPool(10);
		Callable<String> task = () -> {
			// Gets the user's name from System.in.
			Scanner scanner = new Scanner(System.in);
			System.out.println("Enter your name: ");
			String name = scanner.next();
			scanner.close();
			return name;
		};
		Future<String> result = executor.submit(task);

		// Note that if the "shutdown()" method is not called, the program continues
		// running.
		executor.shutdown();

		String name = result.get();
		System.out.println("The name is: " + name);
	}

	/**
	 * Counts the number of code points in the words using several threads and
	 * Futures.
	 */
	public static void invokeAll() throws InterruptedException, ExecutionException {
		System.out.println("\nFutureExample.invokeAll()");
		List<String> words = List.of("one", "two", "three", "four");
		// Tasks that count the number of code points
		List<Callable<Integer>> tasks = new ArrayList<>();
		for (String word : words) {
			tasks.add(() -> {
				int count = word.length();
				return count;
			});
		}

		ExecutorService executor = Executors.newFixedThreadPool(10);
		// This call blocks until all tasks have completed.
		List<Future<Integer>> results = executor.invokeAll(tasks);
		int totalCodePoints = 0;
		for (Future<Integer> result : results) {
			totalCodePoints += result.get();
		}

		System.out.println("Total of code points " + totalCodePoints);
		
		// Note that if the "shutdown()" method is not called, the program continues
		// running.
		executor.shutdown();
	}
	
	/**
	 * The invokeAny method is like invokeAll, but it returns as soon as any one
	 * of the submitted tasks has completed normally, without throwing an exception.
	 * it then returns the value of its Future. The other tasks are cancelled. This
	 * is useful for a search that can conclude as as soon as a match has been found.
	 */
	public static void invokeAny() throws InterruptedException, Exception {
		System.out.println("\nFutureExample.invokeAny()");
		List<String> words = List.of("one", "two", "three", "four", "five", "six", "seven");
		// Tasks that try to find a letter that contains a "e".
		List<Callable<String>> tasks = new ArrayList<>();
		for (String word : words) {
			tasks.add(() -> {
				System.out.println("Executing: " + Thread.currentThread().getName());
				if (word.contains("i") ) {
					return word;
				}
				System.out.println(Thread.currentThread().getName() + " is throwing exception");
				throw new RuntimeException("Not found in " + word);
			});
		}

		ExecutorService executor = Executors.newFixedThreadPool(10);
		String foundWord = executor.invokeAny(tasks);
		
		System.out.println("The word that contains 'i' is " + foundWord);
		
		// Note that if the "shutdown()" method is not called, the program continues
		// running.
		executor.shutdown();
	}
}
