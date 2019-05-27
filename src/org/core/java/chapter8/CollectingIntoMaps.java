package org.core.java.chapter8;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Shows examples about how to collect results into maps. The
 * {@link Collectors#toMap()} method has two function arguments that produce the
 * map's keys and values.
 */
public class CollectingIntoMaps {
	public static void main(String[] args) {
		collectors();

		collectorsUsingLocales();
		
		collectorsUsingSets();
	}

	public static void collectors() {
		System.out.println("CollectingIntoMaps.collectors()");
		List<Person> persons = List.of(new Person(23, "Michael"), new Person(3, "Iverson"), new Person(45, "Lebron"));

		Map<Integer, Person> peopleById = persons.stream()
				.collect(Collectors.toMap(Person::getId, Function.identity()));
		System.out.println(peopleById);
	}

	/**
	 * Here, we construct a map that contains, for each language in the available
	 * locales, as key its name in your default locale (such as "German"), and as
	 * value its localized name (such as "Deutsch").
	 * 
	 * We don't care that the same language might occur twice (for example, German
	 * in Germany and in Switzerland), so we just keep the first entry.
	 */
	public static void collectorsUsingLocales() {
		System.out.println("\nCollectingIntoMaps.collectorsUsingLocales()");
		Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
		Map<String, String> languageNames = locales.collect(
				Collectors.toMap(
						Locale::getDisplayLanguage,
						loc -> loc.getDisplayLanguage(loc), 
						(existingValue, newValue) -> existingValue));

		languageNames.keySet().stream().sorted()
				.forEach(key -> System.out.println(key + " -> " + languageNames.get(key)));
	}

	/**
	 * Collects all the languages in a given country. We need a Map<String,
	 * Set<String>>. For example, the value for "Switzerland" is the set [French,
	 * German, Italian].
	 */
	public static void collectorsUsingSets() {
		System.out.println("\nCollectingIntoMaps.collectorsUsingSets()");
		Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
		Map<String, Set<String>> countryLanguageSets = locales.collect(
				Collectors.toMap(
						Locale::getDisplayCountry,
						l -> Collections.singleton(l.getDisplayLanguage()), (oldSet, newSet) -> {
							// Union of a and b
							Set<String> union = new HashSet<>();
							union.addAll(oldSet);
							union.addAll(newSet);
							return union;
						},
						TreeMap::new));

		countryLanguageSets.forEach((key, value) -> System.out.println(key + " -> " + countryLanguageSets.get(key)));
	}
}

class Person {
	private Integer id;
	private String name;

	public Person(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String toString() {
		return String.format("%s", name);
	}
}