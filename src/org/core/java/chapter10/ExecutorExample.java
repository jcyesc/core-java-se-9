package org.core.java.chapter10;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Executor service
 * 
 * In the Java concurrency library, an executor service schedules and executes
 * tasks, choosing the threads on which to run them.
 */
public class ExecutorExample {

	public static void main(String[] args) {
		availableProcessors();

		executors();
		
		newFixedThreadPool();
	}

	/**
	 * Prints the number of available processors.
	 */
	public static void availableProcessors() {
		System.out.println("ExecutorExample.availableProcessors()");
		int processors = Runtime.getRuntime().availableProcessors();
		System.out.println("# processors: " + processors);
	}

	/**
	 * Executes two tasks using {@link ExecutorService}.
	 */
	public static void executors() {
		System.out.println("\nExecutorExample.executors()");
		Runnable task1 = () -> {
			System.out.println(Thread.currentThread().getName() + " executing task 1");
		};
		Runnable task2 = () -> {
			System.out.println(Thread.currentThread().getName() + " executing task 2");
		};
		ExecutorService executor = Executors.newCachedThreadPool();
		executor.execute(task1);
		executor.execute(task2);
	}

	public static void newFixedThreadPool() {
		System.out.println("\nExecutorExample.newFixedThreadPool()");
		Runnable task1 = () -> {
			for (int i = 0; i <= 100; i++) {
				System.out.println(Thread.currentThread().getName() + " executing task 1");
			}
		};
		Runnable task2 = () -> {
			for (int i = 0; i <= 100; i++) {
				System.out.println(Thread.currentThread().getName() + " EXECUTING TASK 2");
			}
		};
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.execute(task1);
		executor.execute(task2);
	}

}
