package org.core.java.chapter10;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Locks
 * 
 * To avoid the corruption of shared variables, one needs to ensure that only
 * one thread at a time can compute and set the new values. Code that must be
 * executed in its entirety, without interruption, is called a critical section.
 * One can use lock to implement a critical section.
 */
public class LocksAndConditions {
	private static int count = 0; // Assume is shared among multiple threads.
	
	public static void main(String[] args) {
		usingLock();
	}
	
	private static void usingLock() {
		Lock countLock = new ReentrantLock(); // Assume is shared among multiple threads.

		countLock.lock();
		try {
			count++; // Critical section
			System.out.println("Incremented");
		} finally {
			countLock.unlock(); // Make sure that the lock is unlock.
		}
		
		System.out.println("Value: " + count);
	}
}



