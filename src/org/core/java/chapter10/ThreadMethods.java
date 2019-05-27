package org.core.java.chapter10;

/**
 * Thread
 * 
 * A thread is a mechanism for executing a sequence of instructions, usually
 * provided by the operating system. Multiple threads run concurrently, by using
 * separate processors or different time slices on the same processor.
 * 
 * stop(), suspend() and resume() methods
 * 
 * The initial release of Java defined a stop() method that immediately
 * terminates a thread, and a suspend() method that blocks a thread until
 * another thread calls resume(). Both methods have since been deprecated.
 * 
 * The stop() method is inherently unsafe. Suppose a thread is stopped in the
 * middle of a critical section -for example, inserting an element into a queue.
 * Then the queue is left in a partially updated state. However, the lock
 * protecting the critical section is unlocked, and other threads can use the
 * corrupted data structure. You should interrupt a thread when you want it to
 * stop. The interrupted thread can then stop when it is safe to do so.
 * 
 * The suspend() method is not as risky but still problematic. If a thread is
 * suspended while it holds a lock, any other thread trying to acquire that lock
 * blocks. If the resuming thread is among them, the program deadlocks.
 * 
 */
public class ThreadMethods {

	public static void main(String[] args) throws Exception {
		joinExample();
	}

	/**
	 * The main thread waits for {@code slowThread} to finish and then continues.
	 * 
	 * @throws Exception
	 */
	private static void joinExample() throws Exception {
		System.out.println("ThreadMethods.joinExample()");

		Thread slowThread = new Thread(() -> {
			int limit = 4;
			for (int i = 1; i <= limit; i++) {
				System.out.println(Thread.currentThread().getName() + ", " + i + " out of " + limit);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}, "Slow-Thread");

		slowThread.start();

		System.out.println(Thread.currentThread().getName() + " before join!");
		slowThread.join();
		System.out.println(Thread.currentThread().getName() + " after join!");
	}
}
