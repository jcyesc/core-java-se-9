package org.core.java.chapter8;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class OptionalStream {
	public static void main(String[] args) {
		orElse();

		orElseGet();

		or();

		ifPresent();

		ifPresentOrElse();

		map();

		flatMap();
		
		turnOptionalIntoStream();
	}

	public static void orElse() {
		System.out.println("\nOptionalStream.orElse()");
		Optional<String> opt = Optional.of("Hello");

		String result = opt.map(String::toUpperCase).orElse("Nothing");
		System.out.println(result);

		opt = Optional.empty();
		result = opt.map(String::toUpperCase).orElse("Nothing");
		System.out.println(result);
	}

	public static void orElseGet() {
		System.out.println("\nOptionalStream.orElseGet()");
		Optional<String> opt = Optional.empty();

		String result = opt.orElseGet(() -> "NOT FOUND");
		System.out.println(result);
	}

	public static void or() {
		System.out.println("\nOptionalStream.or()");
		Optional<String> opt = Optional.empty();

		Optional<String> newOpt = opt.or(() -> Optional.of("OR ....."));
		System.out.println(newOpt.get());
	}

	public static void ifPresent() {
		System.out.println("\nOptionalStream.ifPresent()");
		Optional<String> opt = Optional.of("Here we go!!");

		opt.ifPresent(System.out::println);
	}

	public static void ifPresentOrElse() {
		System.out.println("\nOptionalStream.ifPresentOrElse()");
		Optional<String> opt = Optional.empty();

		opt.ifPresentOrElse(System.out::println, () -> System.out.println("The else part!"));
	}

	public static void map() {
		System.out.println("\nOptionalStream.map()");
		Optional<String> optName = Optional.of("Scottie");

		Optional<Integer> length = optName.map(String::length);
		System.out.println("The length is: " + length);
	}

	public static void flatMap() {
		System.out.println("\nOptionalStream.flatmap()");
		Double x = 20d;

		Optional<Double> result = inverse(x).flatMap(OptionalStream::squareRoot);

		result.ifPresent(r -> System.out.println("The result is: " + r));
	}

	public static void turnOptionalIntoStream() {
		System.out.println("\nOptionalStream.turnOptionalIntoStream()");
		Map<String, Integer> nameToId = Map.of("abc", 23, "ghi", 3);
		
		System.out.println("Without flatMap");
		Stream<String> names = Stream.of("abc", "def", "ghi");
		Stream<Integer> ids = names.map(n -> lookup(nameToId, n)).filter(Optional::isPresent).map(Optional::get);
		ids.forEach(System.out::println);
		
		System.out.println("\nUsing flatMap and Optional::stream");
		names = Stream.of("abc", "def", "ghi");
		ids = names.map(n -> lookup(nameToId, n)).flatMap(Optional::stream);
		ids.forEach(System.out::println);
	}

	public static Optional<Integer> lookup(Map<String, Integer> nameToId, String name) {
		return Optional.ofNullable(nameToId.get(name));
	}

	public static Optional<Double> inverse(Double x) {
		return x == 0 ? Optional.empty() : Optional.of(1 / x);
	}

	public static Optional<Double> squareRoot(Double x) {
		return x < 0 ? Optional.empty() : Optional.of(Math.sqrt(x));
	}

}
