package org.core.java.chapter4;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Working with Arrays.
 * 
 * The isArray method checks whether a given Class object represents an array.
 * If so, the getComponentType method yields the Class describing the type of the
 * array elements.
 */
public class WorkingWithArrays {

	/**
	 * It presents several ways to copy an array.
	 * @param args
	 */
	public static void main(String[] args) {
		String []countries = {"Brazil", "Mexico", "USA"};
		String []copy = Arrays.copyOf(countries, 10);	
		System.out.println(Arrays.toString(copy));
		
		Object []copy2 = badCopyOf(countries, 10);
		System.out.println(Arrays.toString(copy2));
		
		String []copy3 = (String[]) goodCopyOf(countries, 10);
		System.out.println(Arrays.toString(copy3));

		String []copy4 = betterCopyOf(countries, 10);
		System.out.println(Arrays.toString(copy4));
		
		Object[] arrayObj = {"x", "y", "z"};
		Object []copy5 = betterCopyOf(arrayObj, 10);
		System.out.println(Arrays.toString(copy5));
	}	
	
	public static Object[] badCopyOf(Object[] array, int newLength) {
		Object[] newArray = new Object[newLength];
		for (int i = 0; i < Math.min(array.length, newLength); i++) {
			newArray[i] = array[i];
		}
		
		return newArray;
	}
	
	public static Object goodCopyOf(Object array, int newLength) {
		Class<?> cl = array.getClass();
		if (!cl.isArray()) {
			return null;
		}
		
		Class<?> componentType = cl.getComponentType();
		int length = Array.getLength(array);
		Object newArray = Array.newInstance(componentType, newLength);
		for (int i = 0; i < Math.min(length, newLength); i++) {
			Array.set(newArray, i, Array.get(array, i));
		}
		
		return newArray;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T[] betterCopyOf(T[] array, int newLength) {
		Class<T[]> cl = (Class<T[]>) array.getClass();
		System.out.println(cl);
		
		Class<T> componentType = (Class<T>) cl.getComponentType();
		int length = Array.getLength(array);
		T[] newArray = (T[]) Array.newInstance(componentType, newLength);
		for (int i = 0; i < Math.min(length, newLength); i++) {
			newArray[i] = array[i];
		}

		return newArray;
	}
}


