package org.core.java.chapter13.bundle;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Resource Bundles
 * 
 * When localizing an application, it is best to separate the program from the
 * message strings, button labels, and other texts that need to be translated.
 * In Java, you can place them into resource bundles. Then, you can give these
 * bundles to a translator who can edit them without having to touch the source
 * code of the program.
 * 
 * Organizing Resource Bundles
 * 
 * You need to use a specific naming convention for the files that make up these
 * bundles. For example, resource specific to Germany go into a file
 * bundleName_de_DE, whereas those shared by all German-speaking countries go
 * into bundleName_de. For a given combination of language, script, and country,
 * the following candidates are considered:
 * 
 * <ol>
 * <li>bundleName_language_script_country
 * <li>bundleName_language_script
 * <li>bundleName_language_country
 * <li>bundleName_language
 * </ol>
 * 
 */
public class ResourceBundleExample {
	public static void main(String[] args) {
		Locale[] locales = { Locale.forLanguageTag("fr-FR"), Locale.forLanguageTag("en-US") };

		for (Locale locale : locales) {
			System.out.println("\n" + locale.toLanguageTag());
			Locale.setDefault(Locale.Category.DISPLAY, Locale.forLanguageTag(locale.toLanguageTag()));
			// Locale.setDefault(Locale.Category.FORMAT, Locale.forLanguageTag("fr-FR"));
			Locale.setDefault(Locale.Category.FORMAT, Locale.forLanguageTag(locale.toLanguageTag()));

			ResourceBundle res = ResourceBundle.getBundle("org.core.java.chapter13.bundle.messages", locale);
			String priceTemplate = res.getString("price");
			System.out.println(MessageFormat.format(priceTemplate, 19.95));
			System.out.println(res.getString("greeting"));
			System.out.println(res.getString("farewell"));
		}
	}
}