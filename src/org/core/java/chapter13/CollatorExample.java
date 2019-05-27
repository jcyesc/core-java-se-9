package org.core.java.chapter13;

import java.text.Collator;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.TreeSet;

/**
 * Collation and Normalization
 * 
 * Most programmers know how to compare strings with the compareTo() method of
 * the String class. Unfortunately, when interacting with human users, this
 * method is not very useful. The compareTo() method uses the values of the
 * UTF-16 encoding of the string, which leads to absurd results, even in
 * English.
 * 
 * For dictionary ordering, you would want to consider upper case and lower case
 * equivalent, and accents should not be significant. In order to sort the words
 * in a way that makes sense for humans, we use {@link Collator} objects. There
 * are a couple of advanced settings for collators. You can set a collator's
 * strength to adjust how selective it should be. Character differences are
 * classified as primary, secondary or tertiary. For example, in English, the
 * difference between e and f is considered primary, the difference between e
 * and é is secondary, and between e and E is tertiary.
 * 
 * Decomposition mode
 * 
 * A more technical setting is the decomposition mode which deals with the fact
 * that a character or sequence of characters can sometimes be described in more
 * than one way in Unicode. For example, an é (U+00E9) can also be expressed as
 * a plain e (U+0065) followed by a ´ (combining acute accent U+0301). You
 * probably don't care about that difference, and by default, it is not
 * significant. If you do care, you need to configure the collator as follows:
 * 
 * <code>
 *   coll.setStrength(Collator.IDENTICAL);
 *   coll.setDecomposition(Collator.NO_DECOMPOSITION);
 * </code>
 * 
 * Conversely, if you want to be very lenient and consider the trademark symbol
 * ™ (U+2122) the same as the character combination TM, then set the
 * decomposition mode to {@link Collator#FULL_DECOMPOSITION}.
 * 
 * You might want to convert strings into normalized forms even when you don't
 * do collation-for example, for persistent storage or communication with
 * another program. The Unicode standard defines four normalization forms (C, D,
 * KC, and KD). In the normalization form C, accented characters are always
 * composed. In form D, accented characters are always decomposed into their
 * base letters and combining accents. Forms KC and KD also decompose characters
 * such as the trademark symbol ™. The W3C recommends that you use normalization
 * form C for transferring data over Internet.
 * 
 * The static normalize() method of the {@link Normalizer} class carries out the
 * normalization process. For example:
 * 
 * <code>
 * 	String city = "San Jose\u0301"
 * 	String normalized = Normalizer.normalize(city, Normalizer.Form.NFC);
 * </code>
 * 
 */
public class CollatorExample {
	public static void main(String[] args) {
		Locale locale = Locale.forLanguageTag("en");
		Collator coll = Collator.getInstance(locale);
		ArrayList<String> words = new ArrayList<>(List.of("Athens", "Ångström", "Zulu", "able", "zebra"));
		words.sort(coll);
		System.out.println(words);

		coll = Collator.getInstance(locale);
		coll.setStrength(Collator.PRIMARY);
		TreeSet<String> set = new TreeSet<>(coll);
		set.addAll(List.of("San José", "San Jose", "SAN JOSE", "San Jose\u0301"));
		System.out.println(set.size() + " " + set);

		coll = Collator.getInstance(locale);
		coll.setStrength(Collator.SECONDARY);
		set = new TreeSet<>(coll);
		set.addAll(List.of("San José", "San Jose", "SAN JOSE", "San Jose\u0301"));
		System.out.println(set.size() + " " + set);

		coll = Collator.getInstance(locale);
		coll.setStrength(Collator.TERTIARY);
		set = new TreeSet<>(coll);
		set.addAll(List.of("San José", "San Jose", "SAN JOSE", "San Jose\u0301"));
		System.out.println(set.size() + " " + set);

		// Decompsoition mode examples
		coll = Collator.getInstance(locale);
		coll.setStrength(Collator.IDENTICAL);
		coll.setDecomposition(Collator.NO_DECOMPOSITION);
		set = new TreeSet<>(coll);
		set.addAll(List.of("San José", "San Jose", "SAN JOSE", "San Jose\u0301"));
		System.out.println(set.size() + " " + set);

		coll = Collator.getInstance(locale);
		set = new TreeSet<>(coll);
		set.addAll(List.of("JavaTM", "Java\u2122"));
		System.out.println(set.size() + " " + set);

		coll = Collator.getInstance(locale);
		coll.setDecomposition(Collator.FULL_DECOMPOSITION);
		set = new TreeSet<>(coll);
		set.addAll(List.of("JavaTM", "Java\u2122"));
		System.out.println(set.size() + " " + set);

		System.out.println(Arrays.toString(Normalizer.normalize("ée\u0301\u2122", Normalizer.Form.NFC).codePoints()
				.mapToObj(n -> String.format("%04X", n)).toArray()));
		System.out.println(Arrays.toString(Normalizer.normalize("ée\u0301\u2122", Normalizer.Form.NFD).codePoints()
				.mapToObj(n -> String.format("%04X", n)).toArray()));
		System.out.println(Arrays.toString(Normalizer.normalize("ée\u0301\u2122", Normalizer.Form.NFKC).codePoints()
				.mapToObj(n -> String.format("%04X", n)).toArray()));
		System.out.println(Arrays.toString(Normalizer.normalize("ée\u0301\u2122", Normalizer.Form.NFKD).codePoints()
				.mapToObj(n -> String.format("%04X", n)).toArray()));
	}
}
