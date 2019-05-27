package org.core.java.chapter1;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Nowadays, Unicode requires 21 bits. Each valid Unicode value is called a
 * code point. For example, the code point for the letter A is U+0041, and
 * the mathematical symbol 𝕆 for the set of octonions has code point U+1D546.
 * 
 * There is a variable length backwards compatible encoding, called UTF-15
 * that represents all "classic" Unicode characters with a single 16-bit value
 * and the ones beyond U+FFFF as pairs of 16-bit values taken from a special
 * region of the code space called "surrogated characters." In this encoding,
 * the letter A is \u0041 and 𝕆 is \ud835\udd46.
 * 
 * - Code Points represents the Symbol
 * - Code Units forms the Code Points and are 16-bit quantities of the UTF-16 encoding.
 */
public class CodeUnitsAndCodePoints {

	public static void main(String[] args) {		
		printCodePoints();
		
		equallityWithStrings();
	}
	
	/**
	 * Facts:
	 * 
	 * <p>UTF-16 is the default encoding of Java literal strings.
	 * 
	 * <p>JVM has its own default encoding {@link Charset#defaultCharset()}.
	 * 
	 *  <p>{@link String#getBytes()} encodes the string using the JVM default
	 *  encoding {@link Charset#defaultCharset()}.
	 *  
	 *  <p>It is better to specify the encoding using {#link {@link String#getBytes(Charset)}}.
	 */
	public static void printCodePoints() {
		System.out.println("Default JVM encoding: " + Charset.defaultCharset()); // EXPECTING UTF-8
		
		// The JVM encoding is used to encoded and decode the string, so not problem here.
		System.out.println("\nJVM encoding used for encoding and decoding strings");
		String octonions = new String("𝕆𝕆𝕆".getBytes());
		int length = octonions.length(); // # of Code units
		System.out.println(String.format("%s, length = %d", octonions, length));
		printCharacters(octonions);

		// Unexpected results because the default JVM encoding is UTF-8 and the encoding for Java strings is UTF-16.
		// This represents a problem when reading and writing from a file with a different format.
		System.out.println("\nJVM encoding is used to encode the string but the Charset is different");
		octonions = new String("𝕆𝕆𝕆".getBytes(), StandardCharsets.UTF_16); // getBytes use the default JVM encoding
		length = octonions.length(); // # of Code units
		System.out.println(String.format("%s, length = %d", octonions, length));
		printCharacters(octonions);
		
		System.out.println("\nUsing the same encoding to encode and decode");
		octonions = new String("𝕆𝕆𝕆".getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
		length = octonions.length(); // # of Code units
		System.out.println(String.format("%s, length = %d", octonions, length));
		printCharacters(octonions);
	}
	
	public static void equallityWithStrings() {
		System.out.println("CodeUnitsAndCodePoints.equallityWithStrings()");
		String octonions = "𝕆𝕆𝕆";
		
		String utf8 = new String(octonions.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
		String utf16 = new String(octonions.getBytes(StandardCharsets.UTF_16), StandardCharsets.UTF_16);
		String iso_8859_1 = new String(octonions.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.ISO_8859_1);
		
		// Check the String.equals() method and you will see that it checks that the strings have same encoding.
		System.out.println("utf8.equals(utf16) = " + utf8.equals(utf16)); // The utf8 string was set to the UTF-16 encoding in the equals method.
		System.out.println("utf16.equals(utf8) = " + utf16.equals(utf8)); // The utf8 string was set to the UTF-16 encoding in the equals method.
		System.out.println("utf8.equals(iso_8859_1) = " + utf8.equals(iso_8859_1)); 
		System.out.println("utf16.equals(iso_8859_1) = " + utf16.equals(iso_8859_1));
	}
	
	public static void printCharacters(String string) {
		System.out.print("Printing in one line : ");
		for (int i = 0; i < string.length(); i++) {
			System.out.print(string.charAt(i));
		}
		
		System.out.println("\nPrinting each character in one line");
		for (int i = 0; i < string.length(); i++) {
			System.out.println(string.charAt(i));
		}
	}
	
}
