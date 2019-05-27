package org.core.java.chapter12;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * LocalTime
 * 
 * A LocalTime represents a time of day, such as 15:30:00.
 * 
 * LocalDateTime
 * 
 * LocalDateTime class represents a date and time. That class is suitable for
 * storing points in time in a fixed time zone - for example, for a schedule of
 * classes or events. However, if you need to make calculations that span the
 * daylight saving time, or if you need to deal with users in different time
 * zones, you should use the ZonedDateTime class.
 */
public class LocalTimeExample {

	public static void main(String[] args) {
		localTime();
		
		localDateTime();
	}

	private static void localTime() {
		System.out.println("LocalTimeExample.localTime()");
		LocalTime bedTime = LocalTime.of(23, 0);
		LocalTime wakeup = bedTime.plusHours(8);

		System.out.println(wakeup);
	}

	private static void localDateTime() {
		System.out.println("\nLocalTimeExample.localDateTime()");
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime tomorrow = now.plusDays(1);
		
		System.out.println("Now " + now);
		System.out.println("Tomorrow " + tomorrow); // YYYY-MM-ddThh:mm:ssnnnnnn -> 2018-09-16T21:31:13.976985
		System.out.println("Nanoseconds " + tomorrow.getNano());
		
	}
}
