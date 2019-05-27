package org.core.java.chapter10;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The synchronized keyword
 * 
 * In Java, every object has an intrinsic lock. To understand intrinsic locks,
 * however, it helps to have seen explicit locks first.
 * 
 * The synchronized keyword is used to lock the intrinsic lock. It can occur in
 * two forms:
 * <ol>
 * <li>Lock a block using synchronized(object).
 * <li>Declare a method as synchronized
 * </ol>
 * 
 * We can lock a block in this way: <code>
 *     synchronized(obj) {
 *       Critical section
 *     }
 * </code>
 * 
 * This essentially means:
 * 
 * <code>
 *     obj.intrinsicLock.lock();
 *     try {
 *       Critical section
 *     } finally {
 *       obj.intrinsicLock.unlock();
 *     }
 * </code>
 * 
 * We can also declare a method as synchronized. Then its body is locked on the
 * receiver parameter this. That is:
 * 
 * <code>
 *     public synchronized void method() {
 *       Body
 *     }
 * </code>
 * 
 * is the equivalent of
 * 
 * <code>
 *     public void method() {
 *       this.intrinsicLock.lock();
 *       try {
 *       	this.intrinsicLock.lock();
 *       } finally {
 *       	this.intrinsicLock.unlock();
 *       }
 *     }
 * </code>
 * 
 * Synchronized methods were inspired by the monitor concept that was pioneered
 * by Per Brinch Hansen and Tony Hoare in the 1970s. A monitor is essentially a
 * class in which all instance variables are private and all methods are
 * protected by a private lock.
 * 
 * In Java, it is possible to have public instance variables and to mix
 * synchronized and unsynchronized methods. More problematically, the intrinsic
 * lock is publicly accessible.
 * 
 */
public class IntrinsicLockAndSynchronized {

	public static void main(String[] args) {
		usingSynchronizedToLockTheIntrinsicLock();
	}

	/**
	 * Creates a {@link Counter} object and increases it by two in every thread. The
	 * share variable {@link Counter} uses synchronized to lock the intrinsic lock
	 * and avoids race conditions.
	 */
	private static void usingSynchronizedToLockTheIntrinsicLock() {
		int numberOfTimesToIncreaseByTwo = 10000;
		ExecutorService executor = Executors.newFixedThreadPool(3000);
		Counter sharedCounter = new Counter();

		for (int i = 0; i < numberOfTimesToIncreaseByTwo; i++) {
			executor.execute(() -> {
				sharedCounter.increment();
				int value = sharedCounter.increment();
				System.out.println(Thread.currentThread().getName() + " , value " + value);
			});
		}

		executor.shutdown();

		// Waiting till all the threads have completed.
		while (!executor.isTerminated())
			;

		System.out.println(
				"Final value should be " + (numberOfTimesToIncreaseByTwo * 2) + ": " + sharedCounter.getValue());
	}

	private static class Counter {
		private int value;

		public synchronized int increment() {
			value++;
			return value;
		}

		public int getValue() {
			return value;
		}
	}
}
