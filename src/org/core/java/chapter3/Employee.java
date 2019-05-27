package org.core.java.chapter3;

/**
 * This example shows how to use default interface methods
 * and how to override them or select one using "super1".
 */

/**
 * Interface and default methods.
 */
interface Person {
	default String getName() {
		return "Noname";
	}

	default int getId() {
		return 999;
	}

	default String version() {
		return "person-344";
	}

	// void hello();
}

interface Identified {
	default int getId() {
		return 123;
	}

	String version();

	// String hello();
}

public class Employee implements Person, Identified {
	public static void main(String[] args) {
		Employee e = new Employee();
		System.out.println(e.getName());
		System.out.println(e.getId());
		System.out.println(e.version());
	}

	@Override
	public int getId() {
		// Selecting an specific default interface method.
		return Identified.super.getId();
	}

	@Override
	public String version() {
		return "employee-44";
	}
}
