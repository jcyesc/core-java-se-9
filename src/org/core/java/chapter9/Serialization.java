package org.core.java.chapter9;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Serialization
 * 
 * <p>Serialization is a mechanism for turning an object into a bunch of bytes that
 * can be shipped somewhere else or stored on disk, and for reconstituting the
 * object from those bytes.
 * 
 * <p>Serialization is an essential tool for distributed processing, where objects
 * are shipped from one virtual machine to another. It is also used for
 * fail-over and load balancing, when serialized objects can be moved to another
 * server. If you work with server-side software, you will often need to enable
 * serialization for classes.
 */
public class Serialization {

	/**
	 * Does the following:
	 * 
	 * <li>Creates two employees objects.
	 * <li>Creates a temporary file.
	 * <li>Serializes the two employees
	 * <li>Deserialzes the two employees.
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		System.out.println("Serialization.main()");
		Employee employee1 = new Employee("Johny", 145);
		Employee employee2 = new Employee("Sam", 96);
		System.out.printf("\nBefore serializations: \n%s\n%s\n", employee1, employee2);

		Path tmpFile = createTempFileWithContent();
		// Serialization.
		serialize(tmpFile, employee1, employee2);

		// Deserialization
		List<Employee> employees = deserialize(tmpFile);
		System.out.println("\nAfter deserialization (Note that code is null):");
		employees.forEach(System.out::println);
	}

	public static void serialize(Path tmpFile, Employee... employees) throws IOException {
		System.out.println("\nSerialization.serialize()");
		try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(tmpFile))) {
			for (Employee employee : employees) {
				out.writeObject(employee);
			}
		}
	}

	/**
	 * Deserialization
	 * 
	 * <p>We take it for granted that objects can only be constructed with the constructor. However,
	 * a deserialized object is not constructed. Its instance variables are simply restored from
	 * an object stream.
	 * 
	 * <p>This is a problem if the constructor enforces some condition. For example, a singleton object
	 * main be implemented so that the constructor can only be called once.
	 */
	public static List<Employee> deserialize(Path tmpFile) throws IOException, ClassNotFoundException {
		System.out.println("\nSerialization.deserialize()");
		List<Employee> employees = new ArrayList<>();
		try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(tmpFile))) {
			Employee employee = (Employee) in.readObject();	
			employees.add(employee);
			employee = (Employee) in.readObject();
			employees.add(employee);
		}

		return employees;

	}

	/**
	 * Creates a temporary file in /tmp and return the {@link Path} to the new file.
	 */
	public static Path createTempFileWithContent() throws IOException {
		System.out.println("\nSerialization.createTempFileWithContent()");
		Path tmpDirectory = Paths.get("/tmp");
		Path tmpFile = Files.createTempFile(tmpDirectory, "person", ".serialize");

		return tmpFile;
	}

	public static class Employee implements Serializable {

		/**
		 * Versioning
		 * 
		 * The serialization mechanism supports a simple versioning schema. When an object
		 * is serialized, both the name of the class and its serialVersionUID are written to
		 * the object stream. That unique identifier is assigned by the implementor,
		 * by defining an static variable like:
		 */
		private static final long serialVersionUID = 1L;

		private String name;
		private double salary;
		private transient String code;

		public Employee(String name, double salary) {
			this.name = name;
			this.salary = salary;
			this.code = String.format("CODE[%s:%f:s]", name, salary, new Date().toString());
		}

		public String getName() {
			return name;
		}

		public double getSalary() {
			return salary;
		}

		public String getCode() {
			return code;
		}
		
		public String toString() {
			return name + ":" + salary + ":" + code;
		}
	}
}
