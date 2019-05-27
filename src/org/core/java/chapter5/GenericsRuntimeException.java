package org.core.java.chapter5;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.Callable;

public class GenericsRuntimeException {

	/**
	 * Note: There is not IOException being caught or thrown.
	 */
	public static void main(String[] args) {
		
		Callable<String> callable = () -> {
			// Throws IOException.
			Path path = FileSystems.getDefault().getPath("logs", "access.log");
			return new String(Files.readAllBytes(path));
		};
		
		Exceptions.doWork(callable);
		// Exceptions.doWork(() -> new String(Files.readAllBytes(null)));
	}
}

/**
 * We can use generics to remove the distinction between checked
 * and unchecked exceptions. The key ingredient is this pair of methods:
 */
class Exceptions {
	@SuppressWarnings("unchecked")
	private static <T extends Throwable> void throwAs(Throwable e) throws T {
		throw (T) e;// The cast is erased to (Throwable) e
	}

	public static <V> V doWork(Callable<V> c) {
		try {
			return c.call();
		} catch (Throwable ex) {
			System.out.println("Exceptions.doWork() -> Rethrowing");
			Exceptions.<RuntimeException>throwAs(ex);
			return null;
		}

	}
}
