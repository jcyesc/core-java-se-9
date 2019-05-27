package org.core.java.chapter8;

import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Downstream Collectors
 * 
 * The groupingBy method yields a map whose values are lists. If you want to
 * process those list in some way, supply a downstream collector.
 */
public class DownstreamCollectors {

	public static void main(String[] args) {
		groupingByIntoSet();
		
		groupingByAndCounting();
		
		groupingByAndMapping();
		
		groupingByAndFiltering();
	}

	public static void groupingByIntoSet() {
		System.out.println("DownstreamCollectors.groupingByIntoSet()");
		Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
		// Key -> Country, Value -> Set of Locales.
		Map<String, Set<Locale>> countryToLocales = locales
				.collect(Collectors.groupingBy(Locale::getDisplayCountry, Collectors.toSet()));

		countryToLocales.keySet().stream().sorted((countryName1, countryName2) -> countryName1.compareTo(countryName2))
				.limit(10)
				.forEach(countryName -> System.out.println(countryName + " -> " + countryToLocales.get(countryName)));

	}
	
	public static void groupingByAndCounting() {
		System.out.println("\nDownstreamCollectors.groupingByAndCount()");
		Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
		// Key -> Country, Value -> # of languages in a country.
		Map<String, Long> countryGroupBy = locales
				.collect(Collectors.groupingBy(Locale::getDisplayCountry, Collectors.counting()));

		countryGroupBy.keySet().stream().sorted((countryName1, countryName2) -> countryName1.compareTo(countryName2))
				.limit(10)
				.forEach(countryName -> System.out.println(countryName + " -> " + countryGroupBy.get(countryName)));

	}
	
	public static void groupingByAndMapping() {
		System.out.println("\nDownstreamCollectors.groupingByAndMapping()");
		Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
		// Key -> Country, Value -> Set of languages.
		Map<String, Set<String>> languagesByCountry = locales
				.collect(Collectors.groupingBy(Locale::getDisplayCountry, 
						Collectors.mapping(Locale::getDisplayLanguage, Collectors.toSet())));

		languagesByCountry.keySet().stream().sorted((countryName1, countryName2) -> countryName1.compareTo(countryName2))
				.limit(10)
				.forEach(countryName -> System.out.println(countryName + " -> " + languagesByCountry.get(countryName)));
	}
	
	public static void groupingByAndFiltering() {
		System.out.println("\nDownstreamCollectors.groupingByAndFiltering()");
		Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
		// Key -> Country, Value -> Set of languages.
		Map<String, Set<Locale>> languagesByCountry = locales
				.collect(Collectors.groupingBy(Locale::getDisplayCountry, 
						// It filters just the locales where the language is English or French.
						Collectors.filtering(l -> {
							return l.getDisplayLanguage().equals("English")  ||
									l.getDisplayLanguage().equals("French"); }, Collectors.toSet())));

		// Shows all the countries where English is spoken.
		languagesByCountry.keySet().stream()
				.filter(n -> languagesByCountry.get(n).size() > 0)
				.sorted((countryName1, countryName2) -> countryName1.compareTo(countryName2))
				.limit(10)
				.forEach(countryName -> System.out.println(countryName + " -> " + languagesByCountry.get(countryName).stream().map(l -> l.getDisplayLanguage()).collect(Collectors.toList())));
	}
	
}
