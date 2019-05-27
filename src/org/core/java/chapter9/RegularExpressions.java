package org.core.java.chapter9;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Regular Expressions
 * 
 * <p>Regular expressions specify string patterns. Use them whenever you need to
 * locale strings that match a particular pattern.
 */
public class RegularExpressions {
	public static void main(String[] args) {	
		findOneMatch();
		
		findAllMatches();
		
		flags();
	}
	
	public static void findOneMatch() {
		System.out.println("RegularExpressions.findOneMatch()");
		String input = "650-219-4758";
		String regex = "[0-9]{3}-[\\d]{3}-[\\d]{4}";
		
		if (Pattern.matches(regex, input)) {
			System.out.println(String.format("The input %s matches the regex %s", input, regex));
		}
	}
	
	public static void findAllMatches() {
		System.out.println("\nRegularExpressions.findAllMatches()");
		String input = "650-219-4758, 458-778-5785, 471-987-6633";
		String regex = "[0-9]{3}-[\\d]{3}-[\\d]{4}";
		
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		while (matcher.find()) {
			String match = matcher.group();
			int matchStart = matcher.start();
			int matchEnd = matcher.end();
			System.out.printf("%s starts in [%d] and ends in [%d].\n", match, matchStart, matchEnd);
		}
	}
	
	/**
	 * Flags
	 * 
	 * <p>Several flags change the behavior of regular expressions. You can specify them
	 * when you compile the pattern.
	 */
	public static void flags() {
		System.out.println("\nRegularExpressions.flags()");
		// The strings "a\u030A" and "\u00E5" are exactly the same.
		// Pattern.CANON_EQ takes canonical equivalence of Unicode characters into account.
		Pattern pattern = Pattern.compile("\u00E5", Pattern.CANON_EQ);
		String input = "a\u030A is the same \u00E5";
		Matcher matcher = pattern.matcher(input);
		
		System.out.println("Finding matches:");
		while(matcher.find()) {
			String result = matcher.group();
			System.out.println(result);
		}
	}
}
