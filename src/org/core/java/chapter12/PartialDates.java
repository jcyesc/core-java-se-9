package org.core.java.chapter12;

import java.time.Month;
import java.time.MonthDay;
import java.time.YearMonth;

public class PartialDates {
	public static void main(String[] args) {
		System.out.println("PartialDates.main()");
		
		MonthDay md = MonthDay.of(Month.APRIL, 1);
		System.out.println(md);
		
		YearMonth ym = YearMonth.of(2018, Month.JULY);	
		System.out.println(ym);
	}
}
