package org.core.java.chapter4;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Inspecting Objects
 * 
 * Through reflection you can access the private fields of an object.
 * 
 * Note: You must make private Field and Method objects "accessible" before you
 * can use them. Calling setAccessible(true) "unlocks" the field or method for
 * reflection. However, the module system or a security manager can block the
 * request and protect objects from being accessed in this way. In that case,
 * the setAccessible method throws an InnaccessibleObjectException or
 * SecurityException. Alternatively, you can call the trySetAccessible method
 * which simply returns false if the field or method is not accessible.
 */
public class InspectingObjects {

	public static void main(String[] args) throws Exception {
		Person person = new Person("Michael", 23);
		inspectObject(person);
		setValue(person, "name", "Michael Jordan");
		
		// Call print using reflection.
		Method method = Person.class.getMethod("print");
		method.invoke(person);
	}

	/**
	 * Inspect the object and prints all the fields in it, even private fields.
	 */
	public static void inspectObject(Object obj) throws IllegalAccessException {
		System.out.println("Inspecting ..... ");
		for (Field f : obj.getClass().getDeclaredFields()) {
			f.setAccessible(true);
			Object value = f.get(obj);
			System.out.println(f.getName() + ":" + value);
		}
	}

	/**
	 * Sets the value of a field in the given {@code obj}.
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	public static void setValue(Object obj, String fieldName, String value) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field f = obj.getClass().getDeclaredField(fieldName);
		f.setAccessible(true);
		f.set(obj, value);
	}
}

class Person {
	private String name;
	private int id;

	public Person(String name, int id) {
		this.name = name;
		this.id = id;
	}

	public void print() {
		System.out.println(id + " " + name);
	}
}
