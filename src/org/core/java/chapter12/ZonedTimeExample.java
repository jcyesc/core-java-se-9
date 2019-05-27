package org.core.java.chapter12;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Zoned Time
 * 
 * <p>
 * Time zones, perhaps because they are an entirely human creation, are even
 * messier than the complications caused by the Earth's irregular rotation.
 * 
 * <p>
 * The Internet Assigned Numbers Authority (IANA) keeps a database of all known
 * time zones around the world (https: //www.iana.org/time-zones), which is
 * updated several times per year. The bulk of the updates deals with the
 * changing rules for daylight saving time. Java uses the IANA database.
 * 
 * <p>
 * Each time zone has an ID, such as America/New_York or Europe/Berlin. To find
 * out all available time zones, call ZoneId.getAvailableIds. At the time of
 * this writing , there were almost 6000 IDs.
 * 
 * <p>
 * Given a time zone ID, the static method ZoneId.of(id) yields a ZoneId object.
 * You can use that object to turn a LocalDateTime object into a ZoneDateTime
 * object by calling local.atZone(zoneId), or you can construct a ZonedDateTime
 * by calling the static method ZoneDateTime.of(year, month, day, hour, minute,
 * second, nano, zoneId).
 * 
 * <code>
 * 	ZonedDateTime apollo11launch = ZonedDateTime.of(1969, 7, 16, 9, 32, 0, 0, ZoneId.of("America/New_York"));
 *  // 1969-07-16T09:32-00:00[America/New_York]
 * </code>
 * 
 * <p>
 * This is a specific instant in time. Call apollo11launch.toInstant() to get
 * the Instant. Conversely, if you have an instant in time, call
 * instant.atZone(ZoneId.of("UTC")) to get the ZonedDateTime at the Greenwich
 * Royal Observatory, or use another ZoneId to get it elsewhere on the planet.
 */
public class ZonedTimeExample {
	public static void main(String[] args) {
		getAvailableZoneIds();
		
		zonedDateTime();
		
		daylightSavingsStarts();
		
		daylightSavingsEnds();
		
		adjustingDateAcrossDaylightSavings();
	}
	
	private static void getAvailableZoneIds() {
		System.out.println("ZonedTimeExample.getAvailableZoneIds()\n");
		
		System.out.println("# Zone Ids " + ZoneId.getAvailableZoneIds().size() + "\n");
		ZoneId.getAvailableZoneIds().stream().sorted().skip(160).limit(6).forEach(System.out::println);
	}
	
	private static void zonedDateTime() {
		System.out.println("\nZonedTimeExample.zonedDateTime()");
		ZonedDateTime apollo11launch = ZonedDateTime.of(1969, 7, 16, 9, 32, 0, 0, ZoneId.of("America/New_York"));
		
		System.out.println(apollo11launch);
	}
	/**
	 * Daylight savings starts
	 * 
	 * When daylight savings time starts, clocks advance by an hour. What happends when you construct
	 * a time that falls into the skipped hour? For example, in 2013, Central Europe switched to daylight
	 * savings time on March 31 at 2:00. If you try to construct nonexistent time March 31 2:30, you
	 * actually get 3:30.
	 */
	private static void daylightSavingsStarts() {
		System.out.println("\nZonedTimeExample.daylightSavingsStarts()");
		ZonedDateTime skipped = ZonedDateTime.of(
				LocalDate.of(2013, 3, 31), // Start of daylight savings time.
				LocalTime.of(2, 30),
				ZoneId.of("Europe/Berlin"));
		
		System.out.println(skipped);
		
	}
	
	/**
	 * Daylight savings ends
	 * 
	 * Conversely, when daylight time ends, clocks are set back by an hour, and there
	 * are two instants with the same local time! When you construct a time within that
	 * span, you get the earlier of the two.
	 */
	private static void daylightSavingsEnds() {
		System.out.println("\nZonedTimeExample.daylightSavingsEnds()");
		ZonedDateTime daylightSavingsEnd = ZonedDateTime.of(
				LocalDate.of(2013, 10, 27), // End of daylight savings time
				LocalTime.of(2, 30),
				ZoneId.of("Europe/Berlin"));
		
		ZonedDateTime anHourLater = daylightSavingsEnd.plusHours(1);
		
		System.out.println("Daylight savings end: " + daylightSavingsEnd);
		System.out.println("After one hour:       " + anHourLater);
		
		/*
		 * An hour later, the time has the same hours and minutes, but the zone offset
		 * has changed.
		 */
	}
	
	/**
	 * You also need to pay attention when adjusting a date across daylight savings time
	 * boundaries. For example, if you set a meeting for next week, don't add a duration
	 * of seven days.
	 */
	private static void adjustingDateAcrossDaylightSavings() {
		System.out.println("\nZonedTimeExample.adjustingDateAcrossDaylightSavings()");
		ZonedDateTime meetingDaylightSavingsEnd = ZonedDateTime.of(
				LocalDate.of(2013, 10, 27), // End of daylight savings time
				LocalTime.of(2, 30),
				ZoneId.of("Europe/Berlin"));
		
		// Using Duration won't work.
		ZonedDateTime nextMeetingUsingDuration = meetingDaylightSavingsEnd.plus(Duration.ofDays(7));
		System.out.println("(Duration) First meeting:  " + meetingDaylightSavingsEnd);
		System.out.println("(Duration) Second meeting: " + nextMeetingUsingDuration);
		
		// Use Period instead
		nextMeetingUsingDuration = meetingDaylightSavingsEnd.plus(Period.ofDays(7));
		System.out.println("(Period) First meeting:  " + meetingDaylightSavingsEnd);
		System.out.println("(Period) Second meeting: " + nextMeetingUsingDuration);
	}
}

