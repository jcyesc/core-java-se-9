package org.core.java.chapter9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Shows an example of {@link Path} and {@link Files}.
 */
public class PathAndFiles {

	public static void main(String[] args) throws IOException {
		walkDirectory();
	}
	
	/**
	 * Traverse the directory recursively.
	 */
	public static void walkDirectory() throws IOException {
		System.out.println("PathAndFiles.visitDirectory()");
		// Try the root directory
		Path homeFile = Paths.get("/tmp");
		
		try (Stream<Path> entries = Files.walk(homeFile)) {
			// Contains all descendants, visited in depth-first order.
			entries.forEach((path) -> System.out.println(path.toString()));
		}
		
	}
}
