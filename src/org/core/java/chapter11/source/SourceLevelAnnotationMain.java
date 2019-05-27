package org.core.java.chapter11.source;

/*
Run these commands:

javac org/core/java/chapter11/source/ToStringAnnotationProcessor.java
javac -processor org.core.java.chapter11.source.ToStringAnnotationProcessor org/core/java/chapter11/source/*.java
java org.core.java.chapter11.source.SourceLevelAnnotationMain
    
*/

public class SourceLevelAnnotationMain {
	public static void main(String[] args) {
		Rectangle rect = new Rectangle(new Point(10, 10), 20, 30);
		System.out.println(ToStrings.toString(rect));
	}
}
