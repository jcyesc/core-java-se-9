package org.core.java.chapter5;

import java.util.ArrayList;
import java.util.List;

public class TheClassT {

	public static void main(String[] args) throws ReflectiveOperationException {
		List<String> result = repeat(10, String.class);

		System.out.println(result);
	}

	/**
	 * The method compiles since cl.getConstructor().newInstance() returns a result
	 * of type T.
	 * 
	 * Suppose you call this method as repeat(10, Employee.class). Then T is
	 * inferred to be the type String since String.class has the type
	 * Class<Employee>. Therefore the return type is ArrayList<Employee>.
	 */
	public static <T> ArrayList<T> repeat(int n, Class<T> cl) throws ReflectiveOperationException {
		ArrayList<T> result = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			result.add(cl.getConstructor().newInstance());
		}
		return result;
	}
}
