package org.core.java.chapter13;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.prefs.BackingStoreException;
import java.util.prefs.InvalidPreferencesFormatException;
import java.util.prefs.Preferences;

/**
 * Preferences
 * 
 * Some operating systems have a central repository for configuration
 * information. The best-known example is the registry in Microsoft Windows. The
 * {@link Preferences} class, which is the standard mechanism in Java for
 * storing user preferences, uses the registry on Windows. On Linux, the
 * information is stored in the local file system instead. The specific
 * repository implementation is transparent to the programmer using the
 * {@link Preferences} class.
 * 
 * The Preferences repository holds a tree of nodes. Each node in the repository
 * has a table of key/value pairs. Values can be numbers, boolean values,
 * strings or byte arrays.
 */
public class PreferencesExample {
	public static void main(String[] args)
			throws BackingStoreException, IOException, InvalidPreferencesFormatException {
		/*
		 * Tries to print the preferences, then set the preferences and print them.
		 * After this, the preferences are exported and then a new preferences are set
		 * up. Then we import the preferences from the file that was exported and print
		 * them. Finally, we remove the node.
		 */
		printUserName();
		setupUserName();
		printUserName();
		exportPreferences();
		setupUserName();
		printUserName();
		importPreferences();
		printUserName();
		removePreferences();
	}

	private static final String NODE_NAME = "/org/core/java";
	private static final String EXPORT_FILE = "myprefences.xml";
	private static final String USERNAME_NODE = "username";

	/**
	 * Prints the userName that is stored in the Preferences if found.
	 */
	private static void printUserName() {
		System.out.println("\nPreferencesExample.printName()");
		Preferences root = Preferences.userRoot();
		Preferences node = root.node(NODE_NAME);
		String userName = node.get(USERNAME_NODE, "");

		if (userName.equals("")) {
			System.out.println("Name hasn't been set up");
		} else {
			System.out.println("Hello, " + userName);
		}
	}

	/**
	 * Sets up the userName in the Preferences.
	 */
	private static void setupUserName() {
		System.out.println("\nPreferencesExample.setupUserName()");
		Preferences root = Preferences.userRoot();
		Preferences node = root.node(NODE_NAME);
		String userName = java.time.LocalDateTime.now().toString();
		node.put(USERNAME_NODE, userName);
		System.out.println("Set userName to " + userName);
	}

	/**
	 * Exports the preferences to a file.
	 */
	private static void exportPreferences() throws IOException, BackingStoreException {
		System.out.println("\nPreferencesExample.exportPreferences()");
		Preferences root = Preferences.userRoot();
		Preferences node = root.node(NODE_NAME);
		Path path = Paths.get(EXPORT_FILE);

		try (OutputStream out = Files.newOutputStream(path)) {
			node.exportSubtree(out);
		}

		System.out.println("Preferences imported from " + path.toAbsolutePath());
	}

	private static void importPreferences() throws IOException, InvalidPreferencesFormatException {
		System.out.println("\nPreferencesExample.importPreferences()");
		Path path = Paths.get(EXPORT_FILE);

		try (InputStream in = Files.newInputStream(path)) {
			Preferences.importPreferences(in);
		}
		System.out.println("Preferences imported from " + path.toAbsolutePath());
	}

	private static void removePreferences() throws BackingStoreException {
		System.out.println("\nPreferencesExample.removePreferences()");
		Preferences root = Preferences.userRoot();
		Preferences node = root.node(NODE_NAME);
		node.removeNode();
	}
}
