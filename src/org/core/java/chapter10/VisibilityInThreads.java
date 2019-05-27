package org.core.java.chapter10;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Visibility of variables
 * 
 * Due to compiler optimizations, some changes in variables might not visible to
 * other threads. In order to warranty the visibility, it is necessary to use
 * "volatile".
 * 
 * Why wouldn't it be visible?
 * 
 * Modern compilers, virtual machines, and processors perform many
 * optimizations. These optimizations assume that the code is sequential unless
 * explicitly told otherwise.
 * 
 * One optimization is caching of memory locations. We think of a memory
 * location such "done" as bits somewhere in the transistors of a RAM chip. But
 * RAM chips are slow - many times slower than modern processors. Therefore, a
 * processor tries to hold the data that it needs in registers or an onboard
 * memory cache, and eventually writes changes back to memory. This caching is
 * simply indispensable for processor performance. There are operations for
 * synchronizing cached copies, but they have a significant performance cost and
 * are only issued when requested.
 * 
 * Another optimization is instruction reordering. The compiler, the virtual
 * machine, and the processor are allowed to change the order of instructions to
 * speed up operations, provided it does not change the sequential semantics of
 * the program.
 * 
 * By default, optimizations assume that there are no concurrent memory
 * accesses. If there are, the virtual machine needs to know, so that it can
 * then emit processor instructions that inhibit improper reorderings.
 * 
 * There are several ways of ensuring that an update to a variable is visible.
 * Here is a summary:
 * 
 * <ol>
 * <li>The value of a final variable is visible after initialization.
 * <li>The initial value of a static variable is visible after static
 * initialization.
 * <li>Changes to a volatile variable are visible.
 * <li>Changes that happen before releasing a lock are visible to anyone
 * acquiring the same lock.
 * </ol>
 * 
 * 
 */
public class VisibilityInThreads {
	// REMOVE "volatile" to see the problem.
	private static volatile boolean done = false;

	public static void main(String[] args) {
		Runnable hellos = () -> {
			for (int i = 1; i <= 1000; i++) {
				System.out.println("Hellol " + i);
			}
			done = true;
		};

		Runnable goodbye = () -> {
			int i = 1;
			while (!done) { // Could be reordered to: if (!done) while (true) i++;
				i++;
			}
			// This line might never execute because "done" is not visible
			// to this thread.
			System.out.println("Goodbye " + i);
		};

		Executor executor = Executors.newCachedThreadPool();
		executor.execute(hellos);
		executor.execute(goodbye);
	}
}
