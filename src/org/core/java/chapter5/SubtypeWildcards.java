package org.core.java.chapter5;

import java.util.ArrayList;

public class SubtypeWildcards {

	public static void main(String[] args) {
		ArrayList<Employee> employees = new ArrayList<>();
		employees.add(new Manager("Cat"));
		employees.add(new Employee("Kitty"));
		
		printNames(employees);
	}

	public static void printNames(ArrayList<? extends Employee> staff) {
		for (int i = 0; i < staff.size(); i++) {
			Employee e = staff.get(i);
			e.printTitle();
		}
		
		/* What happens if you try to store an element into an ArrayList<? extends Employee>?
		 * That would not work.
		 * 
		 */
		//staff.add(new Employee("Luis"));
		/* The add method has parameter type ? extends Employee, and there is not object
		 * that you can pass to this method. If you pass, say, a Manager object, the compiler
		 * will refuse. After all, ? could refer to any subclass, perhaps Janitor, and you
		 * can't add a Manager to an ArrayList<Janitor?.
		 * 
		 * In summary, you can convert from ? extends Employee to Employee, but you can never
		 * convert anything to ? extends Employee. This explains why you can read from an
		 * ArrayList<? extends Employee> but cannot write to it.
		 */
	}

	private static class Employee {
		protected String name;

		public Employee(String name) {
			this.name = name;
		}

		public void printTitle() {
			System.out.println("Simple Employee: " + name);
		}
	}

	private static class Manager extends Employee {

		public Manager(String name) {
			super(name);
		}

		public void printTitle() {
			System.out.println("Manager: " + name);
		}

	}

}
