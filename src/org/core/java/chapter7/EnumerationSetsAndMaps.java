package org.core.java.chapter7;

import java.util.EnumSet;
import java.util.Set;

/**
 * Enumberation Sets and Maps
 */
public class EnumerationSetsAndMaps {
	enum WeekDay {MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY};
	private static Set<WeekDay> always = EnumSet.allOf(WeekDay.class);
	private static Set<WeekDay> workday = EnumSet.range(WeekDay.MONDAY, WeekDay.FRIDAY);
	private static Set<WeekDay> mwf = EnumSet.of(WeekDay.MONDAY, WeekDay.WEDNESDAY, WeekDay.FRIDAY);
	
	public static void main(String[] args) {
		System.out.println(always);
		System.out.println(workday);
		System.out.println(mwf);
	}
}
