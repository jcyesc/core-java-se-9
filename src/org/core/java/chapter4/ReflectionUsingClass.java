package org.core.java.chapter4;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * Reflection
 * 
 * Reflection allows a program to inspect the contents of objects at runtime and to invoke
 * arbitrary methods on them.
 * 
 * Caution: By default, only classes in the same module can be analyzed though reflection. If
 * you don't declare modules, all your classes belong to a single module, and they can all be
 * access through reflection. However, the Java library classes belong to different modules, and
 * reflective access to their non-public members is restricted.
 */
public class ReflectionUsingClass {
	
	/**
	 * Declares a member class or {@link ReflectionUsingClass}.
	 */
	public class InnerClass {	
	}
	
	public static void main(String[] args) throws ClassNotFoundException {
		basicExample();
		
		printAllMethods("java.lang.String");
	}
	
	private static void basicExample() throws ClassNotFoundException {
		String className = "org.core.java.chapter4.ReflectionUsingClass$InnerClass";
		Class<?> cl = Class.forName(className);
		
		System.out.println(cl.getCanonicalName());
		System.out.println(cl.getSimpleName());
		System.out.println(cl.isMemberClass());
	}
	
	public static void printAllMethods(String className) throws ClassNotFoundException {
		System.out.println("\nReflectionUsingClass.printAllMethods()");
		Class<?> cl = Class.forName(className);
		if(cl != null) {
			for (Method m : cl.getDeclaredMethods()) {
				System.out.println(String.format("%s %s %s %s",
						Modifier.toString(m.getModifiers()),
						m.getReturnType().getCanonicalName(),
						m.getName(),
						Arrays.toString(m.getParameters())));
			}
		}
	}
	
}
