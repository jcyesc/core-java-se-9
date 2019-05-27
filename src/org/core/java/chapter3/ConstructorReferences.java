package org.core.java.chapter3;

import java.util.ArrayList;
import java.util.List;

/**
 * Constructor References
 * 
 * Constructor references are just like method references, except that the name
 * of the method is new. For example, Employee:new is a reference to an Employee
 * constructor. If the class has more than one constructor, then it depends on
 * the context which constructor is chosen.
 */
public class ConstructorReferences {

	public static void main(String[] args) {
		List<String> names = new ArrayList<>();
		names.add("Luis");
		names.add("Phillip");
		names.add("Ricardo");

		names.stream().map(Employee::new).forEach(Employee::print);
	}

	static class Employee {
		private String name;

		public Employee() {
		}

		public Employee(String name) {
			this.name = name;
		}

		public void print() {
			System.out.println("Print: employee -> " + name);
		}
	}
}
