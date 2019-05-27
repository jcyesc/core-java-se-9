package org.core.java.chapter9;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Memory-Mapped Files
 * 
 * Memory-mapped files provide another, very efficient approach for random
 * access that works well for very large files. However, the API for data access
 * is completely different from that of input/output stream. First, get a
 * channel to the file:
 * 
 * <code>
 *     FileChannel channel = FileChannel.open(path, StandardOpenOption.READ,
 * 	       StandardOpenOption.WRITE);;
 * </code> Then, map an area of the file (or, if it is not too large, the entire
 * file) into memory:
 * 
 * <code>
 *     ByteBuffer buffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, channel.size());
 * </code>
 * 
 * Use methods get, getInt, getDouble, and so on to read values, and the
 * equivalent put methods to write values.
 * 
 * <code>
 *     int offset = ...;
 *     int value = buffer.getInt(offset);
 *     buffer.put(offset, value + 1);
 * </code> At some point, and certainly when the channel is closed, these
 * changes are written back to the file.
 * 
 * Note: By default, the methods for reading and writing numbers use big-endian
 * byte order. You can change the byte order with the command
 * 
 * <code>
 *     buffer.order(ByteOrder.LITTLE_ENDIAN);
 * </code>
 * 
 * Example:
 * 
 * In this example we use memory map files to sort the numbers store in a file
 * using quick sort to sort parts of the memory map file and then merge
 * everything.
 */
public class MemoryMapFilesAndPathAndFileLock {

	public static void main(String[] args) throws IOException {
		readUsingMemoryMappedFile();
	}

	/**
	 * Read a file using a memory-mapped file and locks the file before reading it.
	 * 
	 * @throws IOException
	 */
	public static void readUsingMemoryMappedFile() throws IOException {
		System.out.println("MemoryMapFiles.readUsingMemoryMappedFile()");

		String content = "Secret message to write";
		Path tmpFile = createTempFileWithContent(content);
		try (FileChannel channel = FileChannel.open(tmpFile, StandardOpenOption.READ, StandardOpenOption.WRITE)) {
			// Getting the lock of the file.
			FileLock lock = channel.lock();

			int sizeFirstBlock = 5;
			// Buffer for the first 5 bytes in the file.
			ByteBuffer buffer1 = channel.map(FileChannel.MapMode.READ_WRITE, 0, sizeFirstBlock);
			// Buffer for the remaining bytes in the file.
			ByteBuffer buffer2 = channel.map(FileChannel.MapMode.READ_WRITE, sizeFirstBlock,
					channel.size() - sizeFirstBlock);

			// Get the first 5 bytes in the file.
			byte[] bytes1 = new byte[sizeFirstBlock];
			buffer1.get(bytes1);
			// Get the remaining bytes in the file.
			byte[] bytes2 = new byte[(int) channel.size() - sizeFirstBlock];
			buffer2.get(bytes2);

			// Releasing the lock of the file.
			lock.release();

			// Merging the two array of bytes.
			byte[] result = new byte[bytes1.length + bytes2.length];
			for (int i = 0; i < bytes1.length; i++) {
				result[i] = bytes1[i];
			}
			for (int i = 0; i < bytes2.length; i++) {
				result[bytes1.length + i] = bytes2[i];
			}

			// This is the result.
			String readContent = new String(result, StandardCharsets.UTF_16);
			System.out.println(readContent);
		}
	}

	/**
	 * Creates a temporary file with the given {@code content} in UTF-16 and returns
	 * the {@link Path} to the new file.
	 */
	public static Path createTempFileWithContent(String content) throws IOException {
		System.out.println("MemoryMapFiles.createTempFile()");
		Path tmpDirectory = Paths.get("/tmp");
		Path tmpFile = Files.createTempFile(tmpDirectory, "bigFile", ".bin");

		byte[] msgBytes = content.getBytes(StandardCharsets.UTF_16);
		System.out.println("Size of the file created: " + msgBytes.length);
		try (OutputStream outputStream = Files.newOutputStream(tmpFile, StandardOpenOption.WRITE)) {
			for (byte b : msgBytes) {
				outputStream.write(b);
			}
		}

		return tmpFile;
	}

}
