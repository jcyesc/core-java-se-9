package org.core.java.chapter7;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * Contains example about sets.
 */
public class SetExample {
	public static void main(String[] args) {
		treeSet();

		headAndTailSet();
	}

	public static void treeSet() {
		System.out.println("SetExample.treeSet()");
		Comparator<String> countryComparator = (u, v) -> u.equals(v) ? 0
				: u.equals("USA") ? -1 : v.equals("USA") ? 1 : u.compareTo(v);

		TreeSet<String> countries = new TreeSet<>(countryComparator);
		countries.add("Venezuela");
		countries.add("Panama");
		countries.add("Argentina");
		countries.add("Japan");
		countries.add("Mexico");
		countries.add("USA");

		System.out.println(countries);
	}

	public static void headAndTailSet() {
		System.out.println("\nSetExample.headSet()");

		TreeSet<String> countries = new TreeSet<>();
		countries.add("Venezuela");
		countries.add("Panama");
		countries.add("Argentina");
		countries.add("Japan");
		countries.add("Mexico");
		countries.add("USA");

		System.out.println("All countries -> " + countries);
		System.out.println("headSet() -> " + countries.headSet("Mexico"));
		System.out.println("tailSet() -> " + countries.tailSet("Mexico"));
	}
}
