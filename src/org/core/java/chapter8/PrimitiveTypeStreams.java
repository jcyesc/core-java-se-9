package org.core.java.chapter8;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Primitive Type Streams
 * 
 * It is inefficient to wrap each primitive types into a wrapper object. The
 * stream library has specialized types IntStream, LongStream, and DoubleStream
 * that store primitive values directly, without using wrappers.
 */
public class PrimitiveTypeStreams {
	
	public static void main(String[] args) {
		intStream();
	}

	public static void intStream() {
		System.out.println("PrimitiveTypeStreams.intStream()");
		Stream<Integer> integers = IntStream.range(0, 100).boxed();
		
		integers.forEach(System.out::println);
	}
}
