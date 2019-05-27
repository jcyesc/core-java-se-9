package org.core.java.chapter10;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Processes
 * 
 * When we need to execute another program, we can use the
 * {@link ProcessBuilder} and {@link Process} classes. The Process class
 * executes a command in a separate operating system process and lets you
 * interact with its standard input, output, and error streams. The
 * ProcessBuilder class lets you configure a Process object.
 */
public class Processes {

	public static void main(String[] args) throws IOException {
		executingProcess();
	}

	private static void executingProcess() throws IOException {
		System.out.println("Processes.executingProcess()");
		File tmpDir = Paths.get("/tmp").toFile();
		Process process = new ProcessBuilder("/bin/ls", "-la").directory(tmpDir).start();

		try (Scanner in = new Scanner(process.getInputStream())) {
			while (in.hasNextLine()) {
				System.out.println(in.nextLine());
			}
		}

		process.onExit().thenAccept(p -> System.out.println("Exit value: " + p.exitValue()));
	}
}
