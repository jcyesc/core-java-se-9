package org.core.java.chapter2;

/**
 * The static variables could be initialized during declaration or in an static block.
 * 
 * The value that the static variable will have depends on the order of the declaration and static block.
 * If the static block appears first and then the declaration, the static variable will have the value
 * in the declaration.
 *
 */
public class ClassMembers {

	static String instanceVariable1 = "Declaration value 1";
	
	static {
		instanceVariable1 = "Block value = 1";
		instanceVariable2 = "Block value = 2";
	}

	static String instanceVariable2 = "Declaration value 2";

	public static void main(String[] args) {
		System.out.println(instanceVariable1);
		System.out.println(instanceVariable2);
	}
}
