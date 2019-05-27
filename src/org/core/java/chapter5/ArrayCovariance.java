package org.core.java.chapter5;

public class ArrayCovariance {

	public static void main(String[] args) {
		Employee[] employees = {new Employee("Matha"), new Employee("Juan")};
		Manager[] managers = {new Manager("Michael"), new Manager("Naomi")};
		
		printTitle(employees);
		printTitle(managers);
	}
	
	/**
	 * If a Manager is a subclass of Employee, you can pass a Manager[] array to
	 * the method since Manager[] is a subtype of Employee[]. This behavior is
	 * call covariance. Arrays vary in the same way as the element types.
	 * 
	 * Generics are invariant.
	 */
	public static void printTitle(Employee[] employees) {
		for (Employee employee : employees) {
			employee.printTitle();
		}
	}
}

class Employee {
	protected String name;
	
	public Employee(String name) {
		this.name = name;
	}
	
	public void printTitle() {
		System.out.println("Simple Employee: " + name);
	}
}

class Manager extends Employee {
	
	public Manager(String name) {
		super(name);
	}
	
	public void printTitle() {
		System.out.println("Manager: " + name);
	}
	
}
