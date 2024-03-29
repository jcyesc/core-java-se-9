package org.core.java.chapter9;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

/**
 * Readers and Writers
 * 
 * Readers and writers consume and produce sequence of characters.
 */
public class ReadersAndWriters {

	public static void main(String[] args) throws IOException {
		reader();

		bufferedReader();

		writer();
	}

	public static void reader() throws IOException {
		System.out.println("ReadersAndWriters.reader()");
		String msg = "ɖ  This is the input message \u0256";
		byte[] input = msg.getBytes(StandardCharsets.UTF_8);
		// We could have replaced ByteArrayInputStream for a Stream that uses a file.
		InputStream inputStream = new ByteArrayInputStream(input);
		Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

		char[] characters = new char[input.length];
		int character = reader.read();
		for (int i = 0; character > -1; i++) {
			characters[i] = (char) character;
			character = reader.read();
		}

		String result = new String(characters);
		System.out.printf("READ: '%s'", result);
	}

	public static void bufferedReader() throws IOException {
		System.out.println("\n\nReadersAndWriters.bufferedReader()");
		String msg = "ɖ  This is the input message \u0256 \nAnother line just here.";
		byte[] input = msg.getBytes(StandardCharsets.UTF_8);

		// We could have replaced ByteArrayInputStream for a Stream that uses a file.
		InputStream inputStream = new ByteArrayInputStream(input);
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
			reader.lines().forEach(System.out::println);
		}
	}

	public static void writer() throws IOException {
		System.out.println("\nReadersAndWriters.writer()");
		String msgToWrite = "ɖ  This is the input message \u0256";

		ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
		// The variables in the try are closed in the opposite order that they were
		// declared.
		try (OutputStream outputStream = byteArray;
				Writer out = new OutputStreamWriter(outputStream, StandardCharsets.UTF_16)) {

			for (int i = 0; i < msgToWrite.length(); i++) {
				out.append(msgToWrite.charAt(i));
			}
		}

		// Verifying that the message was written.
		byte[] result = byteArray.toByteArray();
		String msgOutput = new String(result, StandardCharsets.UTF_16);
		System.out.println("Result: " + msgOutput);
	}
}
