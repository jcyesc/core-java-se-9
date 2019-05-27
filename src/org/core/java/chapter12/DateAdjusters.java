package org.core.java.chapter12;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

/**
 * Date Adjusters
 * 
 * For scheduling applications, you often need to compute dates such as "the
 * first Tuesday of every month." The TemporalAdjusters class provides a number
 * of static methods for common adjustments. You pass the result of an
 * adjustment method to the with method.
 */
public class DateAdjusters {
	public static void main(String[] args) {
		firstSaturdayOfSeptember2018();
		
		customTemporalAdjuster1();
		
		customTemporalAdjuster2();
	}

	private static void firstSaturdayOfSeptember2018() {
		System.out.println("DateAdjusters.firstSaturdayOfSeptember2018()");

		LocalDate firstSaturday = LocalDate.of(2018, Month.SEPTEMBER, 1)
				.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY));
		System.out.println("First Saturday :" + firstSaturday);
		
		LocalDate nextSaturday = LocalDate.of(2018, Month.SEPTEMBER, 1)
				.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
		System.out.println("Next Saturday : " + nextSaturday);
	}
	
	private static void customTemporalAdjuster1() {
		System.out.println("\nDateAdjusters.customTemporalAdjuster1()");
		
		TemporalAdjuster NEXT_WORKDAY = w -> {
			LocalDate result = (LocalDate) w;
			do {
				result = result.plusDays(1);
			} while(result.getDayOfWeek().getValue() >= 6);
			
			return result;
		};
		
		LocalDate today = LocalDate.now();
		LocalDate backToWork = today.with(NEXT_WORKDAY);
		System.out.println(backToWork);
	}
	
	private static void customTemporalAdjuster2() {
		System.out.println("\nDateAdjusters.customTemporalAdjuster2()");
		
		TemporalAdjuster NEXT_SATURDAY = TemporalAdjusters.ofDateAdjuster(w -> {
			LocalDate result = w; // No cast
			do {
				result = result.plusDays(1);
			} while (result.getDayOfWeek().getValue() == 6);
			
			return result;
		});
		
		LocalDate today = LocalDate.now();
		LocalDate backToParty = today.with(NEXT_SATURDAY);
		System.out.println(backToParty);
	}
}
