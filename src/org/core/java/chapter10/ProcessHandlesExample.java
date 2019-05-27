package org.core.java.chapter10;

import java.lang.ProcessHandle.Info;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Process Handles
 * 
 * To get more information about a process that your program started, on any
 * other process that is currently running on your machine, use the
 * ProcessHandle interface.
 */
public class ProcessHandlesExample {

	public static void main(String[] args) {
		printAllProcesses();
	}

	private static void printAllProcesses() {
		System.out.println("ProcessHandlesExample.printAllProcesses()");

		Stream<ProcessHandle> handles = ProcessHandle.allProcesses();
		handles.forEach(p -> {
			System.out.println("\nProcess");
			Info info = p.info();
			info.command().ifPresent(c -> System.out.println("\tCommand: " + c));
			info.arguments().ifPresent(a -> System.out.println("\tArguments: " + Arrays.toString(a)));
			info.commandLine().ifPresent(cl -> System.out.println("\tCommandLine: " + cl));
			info.startInstant().ifPresent(si -> System.out.println("\tStartInstant: " + si));
			info.totalCpuDuration().ifPresent(cpu -> System.out.println("\tTotalCpuDuration: " + cpu));
			info.user().ifPresent(u -> System.out.println("\tUser: " + u));
		});
	}
}
