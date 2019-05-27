package org.core.java.chapter7;

import java.util.Properties;

public class PropertiesExample {

	public static void main(String[] args) {
		Properties systemProperties = System.getProperties();

		systemProperties.forEach((k, v) -> {
			System.out.println(String.format("%s -> %s", k, v));
		});
	}
}
