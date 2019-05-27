package org.core.java.chapter7;

import java.util.WeakHashMap;

/**
 * Weak Hash Maps
 * 
 * The WeakHashMap class was designed to solve an interesting problem. This data
 * structure cooperates with the garbage collector to remove key/values pairs when the
 * only reference to the key is the one from the has table entry.
 * 
 * Technically, the WeakHashMap uses weak references to hold keys. A WeakReference object
 * holds a reference to another object - in our case, a hash table key. Objects of this
 * type are treated in a special way by the garbage collector. If an object is reachable
 * only by a weak reference, the garbage collector reclaims the object and places the weak
 * reference into a queue associated with the WeakReference object. Whenever a method is
 * invoked on it, a WeakHashMap checks its queue of weak references for new arrivals and
 * removes the associated entries.
 */
public class WeakHashMapExample {
	public static void main(String[] args) {
		weakHashMap();
	}
	
	public static void weakHashMap() {
		System.out.println("WeakHashMapExample.weakHashMap()");
		
		WeakHashMap<Person, Integer> nbaPlayers = new WeakHashMap<>();
		
		Person michael = new Person("Michael Jordan");
		
		Person allen = new Person("Allen Iverson");
		nbaPlayers.put(michael, 23);
		nbaPlayers.put(allen, 3);
		
		allen = null;
		System.gc();
		
		while (nbaPlayers.size() == 2) {
			System.out.println(nbaPlayers.size());
		}
		
		// One player has been removed from the WeakHashMap.
		System.out.println(nbaPlayers.size());
		
	}
	
	public static class Person {
		public String name;
		
		public Person(String name) {
			this.name = name;
		}
	}
	
}

