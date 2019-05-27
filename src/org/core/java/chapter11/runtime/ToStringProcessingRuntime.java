package org.core.java.chapter11.runtime;

import java.lang.reflect.Field;

public class ToStringProcessingRuntime {

	/**
	 * Generates a {@link String} that contains the values of the fields of the object
	 * and it includes the name of the variables too, if required.
	 */
	public static String toString(Object obj) {
		if (obj == null) {
			return "null";
		}
		
		Class<?> cl = obj.getClass();
		ToString ts = cl.getAnnotation(ToString.class);
		if (ts == null) {
			return obj.toString();
		}
		
		String result = buildToString(obj, ts);
		return result;
	}
	
	private static String buildToString(Object obj, ToString ts) {
		StringBuilder result = new StringBuilder();
		Class<?> cl = obj.getClass();
		if (ts.includeName()) {
			result.append(cl.getSimpleName());
		}
		result.append("[");
		boolean first = true;
		for (Field field : cl.getDeclaredFields()) {
			ts = field.getAnnotation(ToString.class);
			if (ts != null) {
				if (first) {
					first = false;
				} else {
					result.append(",");
				}
				field.setAccessible(true);
				
				if (ts.includeName()) {
					result.append(field.getName());
					result.append("=");
				}
				
				try {
					result.append(ToStringProcessingRuntime.toString(field.get(obj)));
				} catch (ReflectiveOperationException ex) {
					ex.printStackTrace();
				}
			}
		}
		
		result.append("]");
		return result.toString();
	}
}
