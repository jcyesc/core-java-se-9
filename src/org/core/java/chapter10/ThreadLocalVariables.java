package org.core.java.chapter10;

/**
 * Thread-Local Variables
 * 
 * Sometimes, you can avoid sharing by giving each thread its own instance,
 * using the {@code ThreadLocal} helper class.
 * 
 * To construct one instance per thread, use the following code:
 * 
 * <code>
 * 	public static final ThreadLocal<NumberFormat> currencyFormat =
 * 			ThreadLocal.withInitial(() -> NumberFormat.getCurrencyInstance());
 * </code>
 * 
 * To access the actual formatter, call
 * 
 * <code>
 * 	String amountDue = currencyFormat.get().format(total);
 * </code>
 * 
 * The first time you call get() in a given thread, the lambda expression in the
 * constructor is called to create the instance for the thread. From then on,
 * the get() method returns the instance belonging to the current thread.
 */
public class ThreadLocalVariables {
	public static void main(String[] args) {
		execute();
	}

	/**
	 * Creates two threads that share the same request object. However, for every
	 * thread a new Session object is created.
	 */
	private static void execute() {
		Request sharedRequest = new Request();

		Thread t1 = new Thread(() -> {
			double magicNumber = 3.14166;
			sharedRequest.process(magicNumber);
			for (int i = 0; i < 5; i++) {
				sharedRequest.print();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t1.start();

		Thread t2 = new Thread(() -> {
			double magicNumber = 2.1666;
			sharedRequest.process(magicNumber);
			for (int i = 0; i < 5; i++) {
				sharedRequest.print();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t2.start();

	}

	/**
	 * Request that stores a {@link Session} per thread.
	 */
	public static class Request {
		private static final ThreadLocal<Session> session = ThreadLocal
				.withInitial(() -> new Session("Session-" + Thread.currentThread().getName()));

		public void process(double magicNumber) {
			System.out.println(Thread.currentThread().getName() + " processing ....");
			session.get().setMagicNumber(magicNumber);
		}

		public void print() {
			session.get().print();
		}
	}

	public static class Session {
		private String name;
		private double magicNumber;

		public Session(String name) {
			this.name = name;
		}

		public void setMagicNumber(double magicNumber) {
			this.magicNumber = magicNumber;
		}

		public void print() {
			System.out.println(name + ": Magic number is " + magicNumber);
		}
	}
}
