package org.core.java.chapter5;

/**
 * Type Bounds
 * 
 * Sometimes, the type parameters of a generic class or method need to fulfill
 * certain requirements. You can specify a type bound to require that the type
 * extends certain classes or implements certain interfaces.
 * 
 * You can have as many interface bounds as you like, but at most one of the
 * bounds can be a class. If you have a class as abound, it must be the first
 * one in the bounds list.
 * 
 */
public class TypeBounds {

	public static void main(String[] args) {
		Duck duck = new Duck("Donald");
		doThings(duck);
	}

	public static <T extends Duck & Flyable & Walkable> void doThings(T t) {
		t.quack();
		t.fly();
		t.walk();
	}
}

class Duck implements Flyable, Walkable {
	private String name;

	public Duck(String name) {
		this.name = name;
	}

	public void quack() {
		System.out.println(name + " is quacking!!");
	}

	@Override
	public void fly() {
		System.out.println(name + " is flying!!");
	}

	@Override
	public void walk() {
		System.out.println(name + " is walking!!");
	}
}

interface Flyable {
	public void fly();
}

interface Walkable {
	public void walk();
}