package org.core.java.chapter10;

/**
 * Waiting on a condition
 * 
 * Waiting on a condition happens when a thread is waiting that something
 * happens in order to start executing. In this case we say that the thread is
 * blocked.
 * 
 * There is an essential difference between a thread that is blocking to acquire
 * a lock and a thread that has called wait(). Once a thread calls the wait
 * method, it enters a wait set for the object. The thread is not made runnable
 * when the lock is available. Instead, it says deactivated until another thread
 * has called the notifyAll method on the same object.
 * 
 * The call to notifyAll reactivates all threads in the wait set. When the
 * threads are removed from the wait set, they are again runnable and the
 * scheduler will eventually activate them again. At that time, they will
 * attempt to reacquire the lock. As one of them succeeds, it continues where it
 * left off, returning from the call to wait.
 * 
 * A thread can only call wait, notifyAll, or notify on an object if it holds
 * the lock on that object.
 * 
 * Another method, notify, unblocks only a single thread from the wait set. That
 * is more efficient than unblocking all threads, but there is a danger. If the
 * chosen thread finds that it still cannot proceed, it becomes blocked again.
 * If no other thread calls notify again, the program deadlocks.
 * 
 */

public class WaitOnConditions {

	public static void main(String[] args) {
		waitTillThereElements();
	}

	/**
	 * Creates a {@link Queue} that is shared with two threads: {@code consumer} and
	 * {@code producer}.
	 * <p>
	 * The consumer thread blocks if there are no elements in the Queue.
	 * <p>
	 * The producer thread notifies all the blocked threads when something was added
	 * in the {@link Queue}. Every 2 seconds an element is produced.
	 */
	private static void waitTillThereElements() {
		System.out.println("WaitOnConditions.waitTillThereElements()");
		Queue sharedQueue = new Queue();

		// Thread that consumes the elements in the Queue.
		Thread consumer = new Thread(() -> {
			int elementsToTake = 4;
			for (int i = 0; i < elementsToTake; i++) {
				Object value = sharedQueue.take();
				System.out.println(Thread.currentThread().getName() + " consumed " + value);
			}
		}, "Consumer-Thread");

		consumer.start();

		// Thread that produces the elements in the Queue.
		Thread producer = new Thread(() -> {
			int elementsToProduce = 4;

			for (int i = 0; i < elementsToProduce; i++) {
				try {
					Thread.sleep(2000);
					String newValue = "Element_" + i;
					sharedQueue.add(newValue);
					System.out.println(Thread.currentThread().getName() + " produce " + newValue);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "Producer-Thread");

		producer.start();
	}

	private static class Queue {
		class Node {
			Object value;
			Node next;
		}

		private Node head;
		private Node tail;

		/**
		 * Adds {@code newValue} to the queue and notify all the threads blocked that
		 * there is something in the queue.
		 */
		public synchronized void add(Object newValue) {
			Node n = new Node();
			if (head == null) {
				head = n;
			} else {
				tail.next = n;
			}
			tail = n;
			tail.value = newValue;

			notifyAll(); // Notify all the threads waiting on a condition.
		}

		/**
		 * Takes one element from the queue. If there are no elements, it blocks till
		 * one is available.
		 */
		public synchronized Object take() {
			while (head == null) {
				try {
					System.out.println(Thread.currentThread().getName() + " is BLOCKED because there are no elements");
					wait(); // Wait till there are elements in the queue.
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			Node n = head;
			head = n.next;
			return n.value;

		}
	}
}
