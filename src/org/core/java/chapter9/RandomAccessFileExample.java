package org.core.java.chapter9;

import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class RandomAccessFileExample {

	public static void main(String[] args) throws IOException {
		readFileRandomly();
	}

	/**
	 * Reads a file in reverse order using {@link RandomAccessFile}.
	 */
	public static void readFileRandomly() throws IOException {
		System.out.println("RandomAccessFileExample.readFileRandomly()");
		int[] primeNumbers = { 2, 3, 5, 7, 11, 13, 17 };

		Path tmpFile = createTempFileWithContent(primeNumbers);
		try (RandomAccessFile raf = new RandomAccessFile(tmpFile.toFile(), "rw")) {
			long lenghtOfFileInBytes = raf.length();
			long sizeOfIntInBytes = 4;

			// Read the files from the end of the file till the beginning of the file.
			for (long pointer = lenghtOfFileInBytes - sizeOfIntInBytes; pointer >= 0; pointer = pointer
					- sizeOfIntInBytes) {
				raf.seek(pointer);
				int primeNumber = raf.readInt();
				System.out.print(primeNumber + " ");
			}
		}

	}

	/**
	 * Creates a temporary file with the given {@code toWriteContent} and returns
	 * the {@link Path} to the new file.
	 */
	public static Path createTempFileWithContent(int[] toWriteContent) throws IOException {
		System.out.println("RandomAccessFiles.createTempFile()");
		Path tmpDirectory = Paths.get("/tmp");
		Path tmpFile = Files.createTempFile(tmpDirectory, "randomFile", ".bin");

		try (OutputStream outputStream = Files.newOutputStream(tmpFile, StandardOpenOption.WRITE)) {
			for (int b : toWriteContent) {
				// In order to storethe content of an integer number we need to store
				// each byte separately.
				byte msb1 = (byte) (b >> 24);
				byte msb2 = (byte) (b >> 16);
				byte msb3 = (byte) (b >> 8);
				byte msb4 = (byte) b;
				// By default, the methods for reading and writing numbers use big-endian byte
				// order.
				outputStream.write(msb1); // Write the most significant byte
				outputStream.write(msb2); // Write the second most significant byte
				outputStream.write(msb3); // Write the third most significant byte
				outputStream.write(msb4); // Write the least significant byte
			}
		}

		return tmpFile;
	}
}
