package hello.world;

import javax.swing.JOptionPane;

/**
 * 
 * Modules
 * 
 * A module is a collection of packages. The package names in the module need
 * not be related.
 * 
 * When creating a module for use by others, it is important that its name is
 * globally unique. It is expected that most module names will follow the
 * "reverse domain name" convention, just like package names.
 * 
 * The module-info.java file doesn't look like a Java source file, and of course
 * there can't be a class with the name module-info, since class names cannot
 * contains hyphens. The module keyword, as well as keywords requires, exports,
 * and so on, hat you will see in the following sections, are "restricted
 * keywords" that have a special meaning only in module declarations. The file
 * is compiled into a class file module-info.class that contains the module
 * definition in binary form.
 * 
 * Requiring Modules
 * 
 * The JDK has been modularized, and the javax.swing package is now contained in
 * the java.desktop module. It is a design goal of the module are explicit about
 * their requirements, so that the vertual machine can ensure that all
 * requirements are fulfilled before starting a program.
 * 
 * You cannot have cycles in the module graph. That is, a module cannot directly
 * or indirectly require itself.
 * 
 * A module does not automatically pass on access rights to other modules. In
 * our example, the java.desktop module declares that it requires java.prefs,
 * and the java.prefs module declares that it requires java.xml. That does not
 * give java.desktop the right to use packages from the java.xml module. It
 * needs to explicitly declare that requirement. In mathematical terms, the
 * requires relationship is not "transitive." Generally, this behavior is
 * desirable because it makes requirements explicit.
 * 
 * 
 * In the folder, example1, run the following commands:
 * 
 * <code>
 * javac module-info.java hello/world/HelloWorld.java
 * java --module-path . --module hello.world.name/hello.world.HelloWorld
 * </code>
 * 
 * The JDK has been modularized, and the javax.s
 * 
 */
public class HelloWorld {
	public static void main(String[] args) {
		System.out.println("Hello, Modular World!");

		/*
		 * If module-info.java doesn't add "requires java.desktop;" the following error
		 * is found:
		 * 
		 * import javax.swing.JOptionPane; ^ (package javax.swing is declared in module
		 * java.desktop, but module hello.world.name does not read it) 1 error
		 */
		JOptionPane.showMessageDialog(null, "Hello, Modular World");

	}
}
