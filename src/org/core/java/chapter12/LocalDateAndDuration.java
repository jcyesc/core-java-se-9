package org.core.java.chapter12;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.stream.Stream;

/**
 * Local Dates
 * 
 * There are two kinds of human time in the Java API:
 * 
 * <ul>
 * <li>local date/time
 * <li>zoned time
 * </ul>
 * 
 * Local date/time has a date and/or time of day, but no associated time zone
 * information. Since LocalDate has neither a time or day nor time zone
 * information, it does not correspond to a precise instant of time. In
 * contrast, July 16, 1969, 09:32:00 EDT (the launch of Apollo 11) is a zoned
 * date//time, representing a precise instant on the time line.
 */
public class LocalDateAndDuration {

	public static void main(String[] args) {
		addOneYearUsingPeriod();

		addOneYearUsingPlusDays();

		differenceBetweenLocalDates();
		
		datesUntil();
	}

	/**
	 * Recall that the difference between two time instant is a Duration. The
	 * equivalent for local dates is a Period, which expresses a number of elapsed
	 * years, months, or days.
	 */
	private static void addOneYearUsingPeriod() {
		System.out.println("LocalDateAndDuration.addOneYear()");
		LocalDate birthday1 = LocalDate.of(1992, 7, 25);
		LocalDate birthday2 = birthday1.plus(Period.ofYears(1));
		System.out.println(birthday1);
		System.out.println(birthday2);
	}

	/**
	 * Recall that the difference between two time instant is a Duration. The
	 * equivalent for local dates is a Period, which expresses a number of elapsed
	 * years, months, or days.
	 */
	private static void addOneYearUsingPlusDays() {
		System.out.println("\nLocalDateAndDuration.addOneYearUsingPlusDays()");
		LocalDate birthday1 = LocalDate.of(1991, 7, 25);
		LocalDate birthday2 = birthday1.plusDays(365); // 1992 is a Leap Year
		System.out.println(birthday1);
		System.out.println(birthday2); // It doesn't calculate the next birthday appropriately.
	}

	private static void differenceBetweenLocalDates() {
		System.out.println("\nLocalDateAndDuration.differenceBetweenLocalDates()");
		LocalDate indenpenceDay = LocalDate.of(2018, Month.JULY, 4);
		LocalDate christmas = LocalDate.of(2018, Month.DECEMBER, 25);
		Period untilChristmas = indenpenceDay.until(christmas);
		long days = indenpenceDay.until(christmas, ChronoUnit.DAYS);

		// Using Period
		System.out.println(String.format("Months %d, days %d", untilChristmas.getMonths(), untilChristmas.getDays()));
		// Using the long
		System.out.println("Total number of days: " + days);
	}

	private static void datesUntil() {
		System.out.println("\nLocalDateAndDuration.datesUntil()");

		Stream<LocalDate> allDaysInSeptember2018 = LocalDate.of(2018, Month.SEPTEMBER, 1)
				.datesUntil(LocalDate.of(2018, Month.OCTOBER, 1));
		System.out.println(allDaysInSeptember2018.count());
	}
}
