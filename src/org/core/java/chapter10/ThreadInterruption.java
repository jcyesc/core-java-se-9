package org.core.java.chapter10;

/**
 * Thread Interruption
 * 
 * Suppose that, for a given query, you are always satisfied with the first
 * result. When the search for an answer is distributed over multiple tasks, you
 * want to cancel all others as soon as the answer is obtained. In Java, task
 * cancellation is cooperative.
 * 
 * Each thread has an interrupted status that indicates that someone would like
 * to "interrupt" the thread. There is no precise definition of what
 * interruption means, but most programmers use it to indicate a cancellation
 * request.
 * 
 * A Runnable can check for this status, which is typically done in a loop:
 * 
 * <code>
 * 	Runnable task = () -> {
 * 	  	while (more work to do) {
 * 			if (Thread.currentThread().isInterrupted())
 * 				return;
 * 
 * 			Do more work
 *      }
 *  };
 * </code>
 * 
 * When this thread is interrupted, the run() method simply ends.
 * 
 * Sometimes, a thread becomes temporaly inactive. That can happen if a thread
 * waits for a value to be computed by another thread or for input/output, or if
 * it goes to sleep to give other threads a chance.
 * 
 * If the thread is interrupted while it waits or sleeps, it is immediately
 * reactivated - but in this case, the interrupted status is not set. Instead,
 * an InterruptedException is thrown. This is a check exception, and you must
 * catch it inside the run method of a Runnable. The usual reaction to the
 * exception is to end the run method:
 * 
 * <code>
 * 	Runnable task = () -> {
 *  		try {
 *  			while (more work to do) {
 *  				Do more work
 *  				Thread.sleep(millis);
 *  			}
 *  		} catch (InterruptedException ex) {
 *  			// Do nothing
 *  		}
 *  };
 * </code>
 * 
 * When you catch the InterrupteException in this way, there is no need to check
 * for the interrupted status. If the thread was interrupted outside the call to
 * Thread.sleeep(), the status is set and the Thread.sleep() method throws an
 * InterruptedException as soon as it is called.
 * 
 * The InterruptedException may seem pesky, but you should not just catch and
 * hide it when you call a method such as sleep(). If you can't do anything
 * else, at least set the interrupted status:
 * 
 * <code>
 * 	try {
 * 		Thread.sleep(millis);
 *  } catch (InterruptedException ex) {
 *  		Thread.currentThread().interrupt();
 *  }
 * </code>
 * 
 * Or better, simple propagate the exception to a competent handler:
 * 
 * <code>
 * 	public void mySubTask() throws InterruptedException {
 * 		.....
 * 		Thread.sleep(millis);
 * 		.....
 *  }
 * </code>
 */
public class ThreadInterruption {
	public static void main(String[] args) throws InterruptedException {
		interrupedThread();

		interrupedThreadThrowsAnException();
	}

	/**
	 * Creates a thread that starts working and then is interrupted by the main
	 * thread.
	 */
	private static void interrupedThread() throws InterruptedException {
		System.out.println("ThreadInterruption.interrupedThread()");
		Thread threadToBeInterrupted = new Thread(() -> {
			int i = 0;
			while (true) {
				i++;
				System.out.println(Thread.currentThread().getName() + " continues working ...." + i);
				if (Thread.currentThread().isInterrupted()) {
					System.out.println(Thread.currentThread().getName() + " was interrupted.");
					return;
				}
			}
		});

		threadToBeInterrupted.start();

		Thread.sleep(1); // 1/1000 second
		System.out.println("Interrupting the thread " + threadToBeInterrupted.getName());
		threadToBeInterrupted.interrupt();
		Thread.sleep(1000); // 1 seconds.
	}

	/**
	 * Creates a thread that starts working and then is interrupted by the main
	 * thread while it is sleeping, so an exception is thrown.
	 */
	private static void interrupedThreadThrowsAnException() {
		System.out.println("\nThreadInterruption.interrupedThreadThrowsAnException()");
		Thread threadToBeInterrupted = new Thread(() -> {
			int i = 0;
			while (true) {
				i++;
				System.out.println(Thread.currentThread().getName() + " continues working ...." + i);
				try {
					Thread.sleep(10000); // 10 seconds
				} catch (InterruptedException e) {
					System.out.println(Thread.currentThread().getName() + " was interrupted while sleeping.");
					e.printStackTrace();
					return;
				}
			}
		});

		threadToBeInterrupted.start();

		System.out.println("Interrupting the thread " + threadToBeInterrupted.getName());
		threadToBeInterrupted.interrupt();
	}
}
