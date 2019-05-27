package org.core.java.chapter3;

import java.util.Arrays;

/**
 * Lambda Expression
 * 
 * A Lambda Expression is a block of code together with the specification of any variables
 * that must be passed to the code.
 * 
 * Method References
 * 
 * The expression String::compareToIgnoreCase is a method reference that is equivalent to the
 * lambda expression (x, y) -> x.compareToIgnoreCase(y).
 * 
 * The :: operator separates the method name from the name of a class or object. There are
 * three variations:
 * 
 * 1. Class::instanceMethod
 * 2. Class::staticMethod
 * 3. object::instanceMethod
 * 
 * Functional Interfaces
 * 
 * You can supply a lamdba expression whenever an object of an interface with a single abstract
 * method is expected. Such an interface is called a functional interface.
 * 
 * In Java, there is only one thing you can do with a lambda expression: put it in a variable whose
 * type is a functional interface, so that it is converted to an instance of that interface.
 * 
 * You can capture the this parameter in a method reference. For example, this::equals is the same
 * as x -> this.equals(x).
 * 
 */
public class LambdaExpressions {

	public static void main(String[] args) {
		String[] words = {"one", "two", "three", "four", "five", "six"};

		System.out.println("Firs example");
		Arrays.sort(words, (String first, String second) -> first.length() - second.length());
		Arrays.stream(words).forEach(System.out::println);
		
		System.out.println("\nSecond example");
		Arrays.sort(words, (first, second) -> first.compareToIgnoreCase(second));
		Arrays.stream(words).forEach(word -> System.out.println(word));
		
		System.out.println("\nThird example");
		Runnable runnable = () -> Arrays.stream(words).forEach(System.out::println);
		runnable.run();
		
		System.out.println("\nFourth example");
		Arrays.sort(words, String::compareToIgnoreCase);
		Runnable runnable2 = () -> Arrays.stream(words).forEach(Printer::print);
		runnable2.run();
	}
}

class Printer {
	public static void print(String s) {
		System.out.println("Printing " + s);
	}
	
}




