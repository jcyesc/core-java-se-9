package org.core.java.chapter13;

import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Locale;

/**
 * Character Encodings
 * 
 * When you display a string, the virtual machine encodes it for the local
 * platform. There are two potential problems. It could happen that a display
 * font does not have a glyph for a particular Unicode character. In a Java GUI,
 * such characters are displayed as hollow boxes. For console output, if the
 * console uses a character encoding that cannot represent all output
 * characters, missing characters are displayed as ?. Users can correct these
 * issues by installing appropriate fonts or by switching the console to UTF-8.
 * 
 * Java source files are also text files. Assuming you are not the only
 * programmer on a project, don't store source files in the platform encoding.
 * You could represent any non-ASCII characters in code or comments with
 * \u0E0AAA escapes, but that is tedious. Instead, set your text editor to use
 * UTF-8. Either set your console preference to UTF-8, or compile with:
 * 
 * <code>
 *   javac -encoding UTF-8 *.java
 * </code>
 */
public class CharacterEncodings {
	public static void main(String[] args) {
		Charset platformEncoding = Charset.defaultCharset();
		System.out.println("Your platform encoding: " + platformEncoding);

		Collection<Charset> availableCharset = Charset.availableCharsets().values();
		Locale locale = Locale.getDefault();
		for (Charset charset : availableCharset) {
			String displayName = charset.displayName(locale);
			System.out.println(displayName);
		}
	}
}
