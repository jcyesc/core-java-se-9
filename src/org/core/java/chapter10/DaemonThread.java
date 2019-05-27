package org.core.java.chapter10;

/**
 * Daemon Thread
 * 
 * A daemon is a thread that has no other role in life than to server others.
 * This is useful for threads that send time ticks or clean up stale cache
 * entries. When only daemon threads remain, the virtual machine exits.
 * 
 * To make a daemon thread, call thread.setDaemon(true) before starting the
 * thread.
 */
public class DaemonThread {

	public static void main(String[] args) throws InterruptedException {
		runDaemon();
	}

	private static void runDaemon() throws InterruptedException {
		System.out.println("DaemonThread.runDaemon()");

		Thread daemon = new Thread(() -> {
			while (true) {
				System.out.println("Daemon running");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		daemon.setDaemon(true);
		daemon.start();

		Thread.sleep(5000);
		System.out.println("Daemon about to die :(");
	}
}
