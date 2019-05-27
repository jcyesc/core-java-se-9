package org.core.java.chapter8;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GroupingAndPartitioning {
	public static void main(String[] args) {
		groupingBy();

		partitioningBy();
	}

	public static void groupingBy() {
		System.out.println("GroupingAndPartitioning.groupingBy()");
		Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());
		// Key -> Country, Value -> List of Locales.
		Map<String, List<Locale>> countryToLocales = locales.collect(Collectors.groupingBy(Locale::getDisplayCountry));

		countryToLocales.keySet().stream().sorted((countryName1, countryName2) -> countryName1.compareTo(countryName2))
				.limit(10)
				.forEach(countryName -> System.out.println(countryName + " -> " + countryToLocales.get(countryName)));

	}

	/**
	 * When the classifier function is a predicate function (that is, a function
	 * returning a boolean value), the stream elements are partitioned into two
	 * lists: those where the function returns true and the complement.
	 */
	public static void partitioningBy() {
		System.out.println("\nGroupingAndPartitioning.partitioningBy()");
		Stream<Locale> locales = Stream.of(Locale.getAvailableLocales());

		// Key -> True means Country speaks English, otherwise false, Value -> List of locales
		Map<Boolean, List<Locale>> englishAndOtherLocales = locales
				.collect(Collectors.partitioningBy(l -> l.getLanguage().equals("en")));

		List<Locale> englishLocales = englishAndOtherLocales.get(true);
		englishLocales.stream().limit(10)
				.forEach((locale) -> System.out.println(locale.getDisplayCountry() + " -> " + locale));
	}
}
