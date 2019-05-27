package org.core.java.chapter9;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Input/Output Streams
 * 
 * In the Java API, a source from which one can read bytes is called an input
 * stream. The bytes can come from a file, a network connection, or an array in
 * memory. Similarly, a destination for bytes is an output stream. In contrast,
 * readers and writers consume and produce sequence of characters.
 * 
 * For the examples provided, we don't use real files. We use streams that are
 * represented in memory.
 * 
 * Stream and Charset
 * 
 * UTF-16 Java Strings are encoded by default in UTF-16, which encodes each
 * Unicode code point into one or two 16-bit values.
 * 
 * There is no reliable way to automatically detect the character encoding
 * from a stream of bytes. Some API methods let you use the "default charset"
 * - the character encoding that is preferred by the operating system of the
 * computer. Is that the same encoding that is used by your source of bytes?
 * These bytes may well originate from a different part of the world. Therefore,
 * you should always explicitly specify the encoding. For example, when reading
 * a web page, check the Content-Type header.
 */
public class InputAndOutputStreams {

	public static void main(String[] args) throws IOException {
		defaultCharset();

		inputStream();
		
		outputStream();
	}

	public static void defaultCharset() {
		System.out.println("WorkingWithStreams.defaultCharset()");
		System.out.println("The default Charset is: " + Charset.defaultCharset());
	}

	/**
	 * Reads a {@link String} using an {@link InputStream} and then the string is
	 * transformed into an array of {@like byte}s using the default Charset.
	 * 
	 * @throws IOException
	 */
	public static void inputStream() throws IOException {
		System.out.println("\nWorkingWithStreams.inputStream()");
		String msg = new String("This is a secret message");
		// Encodes this {@code String} into a sequence of bytes using the
		// platform's default charset, storing the result into a new byte array.
		// The default encoding is given by Charset.defaultCharset();
		byte[] msgInput = msg.getBytes();
		// byte[] msgInput = msg.getBytes(StandardCharsets.UTF_16);
		ByteArrayInputStream byteInputStream = new ByteArrayInputStream(msgInput);
		byte[] output = new byte[100];
		int len = 5;
		int offset = 0;
		try (InputStream in = byteInputStream) {
			int copied = 0;
			while (copied != -1) {
				offset += copied;
				copied = in.read(output, offset, len);
			}
		}

		System.out.printf("array bytes length %d, offset %d", msg.getBytes().length, offset);

		// UTF_8 is used because it is the default charset in this machine.
		String readString = new String(output, 0, offset, StandardCharsets.UTF_8);
		System.out.println("\nOutput is: " + readString);
	}

	/**
	 * Creates a {@link ByteArrayOutputStream} and outputs data to it and then
	 * creates a new {@link String} with the appropriate {@link Charset}.
	 * @throws IOException
	 */
	public static void outputStream() throws IOException {
		System.out.println("\nWorkingWithStreams.outputStream()");
		String msgToWrite = "Secret message to write";
		byte[] msgBytes = msgToWrite.getBytes(StandardCharsets.UTF_16);

		ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
		OutputStream outputStream = byteArray;
		for (byte b : msgBytes) {
			outputStream.write(b);
		}
		outputStream.close();
		
		// Verifying that the message was written.
		byte[] result = byteArray.toByteArray();
		String msgOutput = new String(result, StandardCharsets.UTF_16);
		System.out.println("Result: " + msgOutput);
	}
}
