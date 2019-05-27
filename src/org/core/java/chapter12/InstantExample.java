package org.core.java.chapter12;

import java.time.Duration;
import java.time.Instant;

/**
 * The Time Line
 * 
 * In Java, an Instant represents a point on the time line. An origin, called the epoch,
 * is arbitrarily set at midnight of January 1, 1970 at the prime meridian that passes
 * through the Greenwich Royal Observatory in London.
 */
public class InstantExample {
	
	public static void main(String[] args) {
		long millis = findDurationInMillis(() -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Awake!!");
		});
		
		System.out.println("Duration in millis: " + millis);
	}
	
	
	public static long findDurationInMillis(Runnable action) {
		System.out.println("InstantExample.findDurationInMillis()");
		Instant start = Instant.now();
		action.run();
		Instant end = Instant.now();
		Duration timeElapsed = Duration.between(start, end);
		long millis = timeElapsed.toMillis();
		
		return millis;
	}
	

}
