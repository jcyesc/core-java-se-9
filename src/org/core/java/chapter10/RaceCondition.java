package org.core.java.chapter10;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Race condition and Critical section
 * 
 * When two threads try to update a variable at the same time, the variable
 * might be updated incorrectly. This kind of error is called a "race condition"
 * because it depends on which thread wins the "race" for updating the share
 * variable.
 * 
 * Race conditions are a problem whenever shared variables are mutated. For
 * example, when adding a value to the head of a queue, the insertion code might
 * look like this:
 * 
 * <code>
 *   Node n = new Node();
 *   if (head == null) {
 *     head = n;
 *   } else {
 *     tail.next = n;
 *   }
 *   tail = n;
 *   tail.value = newValue;
 * </code>
 * 
 * Lots of things can go wrong if this sequence of instructions is paused at an
 * unfortunate time and another task gets control, accessing the queue while it
 * is in an inconsistent state.
 * 
 * We need to ensure that the entire sequence of operation is carried out
 * together. Such an instruction sequence is called a critical section. We can
 * use a lock to protect critical sections and make critical sequences of
 * operation atomic.
 * 
 * While it is straightforward to use locks for protecting critical sections,
 * locks are not a general solution for solving all concurrency problems. They
 * are difficult to use properly, and it is easy to make mistakes that severely
 * degrade performance or even cause "deadlock."
 * 
 * Strategies for Safe Concurrency
 * 
 * <p>
 * Confinement: Just say no when it comes to sharing data among tasks.
 * 
 * <p>
 * Immutability: It is safe to share immutable objects.
 * 
 * <p>
 * Locking: By granting only one task at a time access to a data structure, one
 * can keep it from being damaged.
 */
public class RaceCondition {

	private static volatile int count = 0;

	public static void main(String[] args) {
		System.out.println("RaceCondition.main()");

		ExecutorService executor = Executors.newFixedThreadPool(10);

		for (int i = 1; i <= 100; i++) {
			int taskId = i;
			Runnable task = () -> {
				for (int k = 1; k <= 100; k++) {
					count++; // Race condition happens here.
				}
				System.out.println(taskId + ": " + count);
			};

			executor.execute(task);
		}

		executor.shutdown();
	}
}
