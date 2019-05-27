package org.core.java.chapter13;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

/**
 * Locales
 * 
 * A locale specifies the language and location of a user, which allows
 * formatters to take user preferences into account.
 */
public class Locales {
	public static void main(String[] args) {
		System.out.println("NumberFormat");
		System.out.println("English " + NumberFormat.getNumberInstance(Locale.ENGLISH).format(123456.78));
		System.out.println("German  " + NumberFormat.getNumberInstance(Locale.GERMAN).format(123456.78));

		System.out.println("\nDateTimeFormatter SHORT");
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
		LocalDate date = LocalDate.of(1961, 3, 22);
		System.out.println("English " + formatter.withLocale(Locale.ENGLISH).format(date));
		System.out.println("German  " + formatter.withLocale(Locale.GERMAN).format(date));
		System.out.println("Chinese " + formatter.withLocale(Locale.CHINESE).format(date));

		System.out.println("\nDateTimeFormatter LONG");
		formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
		System.out.println("English " + formatter.withLocale(Locale.ENGLISH).format(date));
		System.out.println("German  " + formatter.withLocale(Locale.GERMAN).format(date));
		System.out.println("Chinese " + formatter.withLocale(Locale.CHINESE).format(date));

		Locale defaultLocale = Locale.getDefault();
		Locale displayLocale = Locale.getDefault(Locale.Category.DISPLAY);
		Locale formatLocale = Locale.getDefault(Locale.Category.FORMAT);
		System.out.println("\nDefault locales");
		System.out.println("General: " + defaultLocale);
		System.out.println("Display: " + displayLocale);
		System.out.println("Format: " + formatLocale);

		System.out.println("\nAvailable locales:");
		for (Locale loc : Locale.getAvailableLocales()) {
			String languageTag = loc.toLanguageTag();
			System.out.println(languageTag + ": " + loc.getDisplayName());
		}

		System.out.println("\nLocale.forLanguageTag()");
		Locale loc = Locale.forLanguageTag("de-CH");
		System.out.println(loc.getDisplayName(Locale.GERMAN));
		System.out.println(loc.getDisplayName(Locale.ENGLISH));
		System.out.println(loc.getDisplayName());

		System.out.println("\nSetting the default Locale");
		Locale.setDefault(Locale.Category.FORMAT, Locale.GERMAN);
		System.out.println(LocalDate.now());
		System.out.println(Locale.getDefault().getDisplayName());
		Locale.setDefault(Locale.Category.DISPLAY, Locale.GERMAN);
		System.out.println(LocalDate.now());
		System.out.println(Locale.getDefault().getDisplayName());
	}
}
