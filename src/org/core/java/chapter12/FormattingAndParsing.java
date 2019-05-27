package org.core.java.chapter12;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * Formatting and Parsing
 * 
 * The DateTimeFormatter class provides three kinds of formatters to print a
 * date/time value:
 * 
 * <ul>
 * <li>Predefined standard formatters
 * <li>Locale-specific formatters
 * <li>Formatters with custom patterns
 * </ul>
 */
public class FormattingAndParsing {
	public static void main(String[] args) {
		predefinedStandardFormatters();

		localeSpecificFormatters();

		localeSpecificDayOfWeek();

		customFormatter();

		parsing();
	}

	private static void predefinedStandardFormatters() {
		System.out.println("FormattingAndParsing.predefinedStandardFormatters()");
		ZonedDateTime apollo11launch = ZonedDateTime.of(1969, 7, 16, 9, 32, 0, 0, ZoneId.of("America/New_York"));
		String formatted = DateTimeFormatter.ISO_DATE_TIME.format(apollo11launch);
		System.out.println(formatted);
	}

	private static void localeSpecificFormatters() {
		System.out.println("\nFormattingAndParsing.localeSpecificFormatters()");
		ZonedDateTime apollo11launch = ZonedDateTime.of(1969, 7, 16, 9, 32, 0, 0, ZoneId.of("America/New_York"));
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
		String formatted = formatter.format(apollo11launch);
		System.out.println("Local: " + formatted);

		formatted = formatter.withLocale(Locale.FRENCH).format(apollo11launch);
		System.out.println("\nIn French: " + formatted);
	}

	private static void localeSpecificDayOfWeek() {
		System.out.println("\nFormattingAndParsing.localeSpecificDayOfWeek()");
		for (DayOfWeek dow : DayOfWeek.values()) {
			System.out.print(dow.getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + " ");
		}

		System.out.println();
		for (DayOfWeek dow : DayOfWeek.values()) {
			System.out.print(dow.getDisplayName(TextStyle.SHORT, Locale.FRENCH) + " ");
		}
	}

	private static void customFormatter() {
		System.out.println("\n\nFormattingAndParsing.customFormatter()");
		ZonedDateTime apollo11launch = ZonedDateTime.of(1969, 7, 16, 9, 32, 0, 0, ZoneId.of("America/New_York"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E yyyy-MM-dd HH:mm");
		String formatted = formatter.format(apollo11launch);
		System.out.println(formatted);
	}

	private static void parsing() {
		System.out.println("\nFormattingAndParsing.parsing()");
		LocalDate churchsBirthday = LocalDate.parse("1903-06-14");
		System.out.println("After parsing: " + churchsBirthday);
		ZonedDateTime apollo11launch = ZonedDateTime.parse("1969-07-06 03:32:00-0400",
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssxx"));
		System.out.println("After parsing: " + apollo11launch);
	}
}
