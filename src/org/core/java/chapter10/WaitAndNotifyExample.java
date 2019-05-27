package org.core.java.chapter10;

import java.util.ArrayList;
import java.util.List;

/**
 * This example shows:
 *
 * <ol>
 * <li>How to add numbers using multiple threads</li>
 * <li>How to wait till all the results have been calculated</li>
 * <li>Then add the results and display the total</li>
 * <li>Use wait() and notifyAll() methods</li>
 * </ol>
 */
public class WaitAndNotifyExample {
	public static void main(String[] args) {
		List<Integer> numbers = new ArrayList<>();
		int total = 0;
		int size = 50;
		for (int i = 0; i < size; i++) {
			numbers.add(i);
			total += i;
		}
		System.out.println("Expected result " + total);

		List<Integer> results = new ArrayList<>();
		int expectedResults = size / 10;
		PrintWorker printWorker = new PrintWorker(results, expectedResults);
		printWorker.start();
		AddWorker addWorker1 = new AddWorker(numbers, 0, 9, results);
		addWorker1.start();
		AddWorker addWorker2 = new AddWorker(numbers, 10, 19, results);
		addWorker2.start();
		AddWorker addWorker3 = new AddWorker(numbers, 20, 29, results);
		addWorker3.start();
		AddWorker addWorker4 = new AddWorker(numbers, 30, 39, results);
		addWorker4.start();
		AddWorker addWorker5 = new AddWorker(numbers, 40, 49, results);
		addWorker5.start();
	}
}

class PrintWorker extends Thread {
	private List<Integer> results;
	private int expectedResults;

	public PrintWorker(List<Integer> results, int expectedResults) {
		this.results = results;
		this.expectedResults = expectedResults;
	}

	@Override
	public void run() {
		synchronized (results) {
			while (results.size() != expectedResults) {
				try {
					System.out.println(Thread.currentThread().getName() + " is waiting");
					results.wait();
					System.out.println(Thread.currentThread().getName() + " awoke");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			int total = 0;
			for (int result : results) {
				total += result;
			}
			System.out.println("The result is " + total);
		}
	}
}

class AddWorker extends Thread {
	private List<Integer> numbers;
	private int startIndex = 0;
	private int endIndex = 0;
	private List<Integer> results;

	/**
	 * @param numbers
	 *            The numbers to be added.
	 * @param startIndex
	 *            The starting index of the first number to be added.
	 * @param endIndex
	 *            The last index of the number to be added.
	 * @param results
	 */
	public AddWorker(List<Integer> numbers, int startIndex, int endIndex, List<Integer> results) {
		this.numbers = numbers;
		this.startIndex = startIndex;
		this.endIndex = endIndex;
		this.results = results;
	}

	public void run() {
		System.out.println(Thread.currentThread().getName() + " is executing");
		int sum = 0;
		for (int i = startIndex; i <= endIndex; i++) {
			sum += numbers.get(i);
		}

		synchronized (results) {
			results.add(sum);
			results.notifyAll();
		}
	}
}
