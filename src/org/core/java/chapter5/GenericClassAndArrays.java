package org.core.java.chapter5;

/**
 * Generics
 * 
 * 1. A generic class is a class with one or more type parameters. 2. A generic
 * method is a method with type parameters. 3. You can require a type parameter
 * to be a subtype of one or more types. 4. Generic types are invariant: When S
 * is a subtype of T, there is no relationship between G<S> and G<T>. 5. By
 * using wildcards G<? extends T> or G<? super T>, you can specify that a method
 * can accept an instantiation of a generic type with a subclass or superclass
 * argument. 6. Type parameters are erased when generic classes and methods are
 * compiled. 7. Erasure puts many restrictions on generic types. In particular,
 * you can't instantiate generic classes or arrays, cast to a generic type, or
 * throw an object of a generic type. 8. The Class<T> class is generic, which is
 * useful because methods such as cast are declared to produce a value of type
 * T. 9. Even though generic classes and methods are erased in the virtual
 * machine, you can find out at runtime how they were declared.
 * 
 */
public class GenericClassAndArrays {

	public static void main(String[] args) {
		Entry<String, Integer> entry = new Entry<>("Hello", 23);
		entry.print();
		
		// The next construction is outlawed because, after erasure, the
		// array constructor would create a raw Entry array. It would then be
		// possible to add Entry objects of any type (such as Entry<Employee, Manager>)
		// without ArrayStoreException.
		// Entry<String, Integer>[] entries = new Entry<String, Integer>[100];
		@SuppressWarnings({ "unchecked", "unused" })
		Entry<String, Integer>[] entries1 = (Entry<String, Integer>[]) new Entry<?, ?>[100];
		
		@SuppressWarnings({ "unchecked", "unused" })
		Entry<String, Integer>[] entries2 = new Entry[100];
	} 
}

class Entry<K, V> {
	private K key;
	private V value;

	public Entry(K key, V value) {
		this.key = key;
		this.value = value;
	}

	public K getKey() {
		return key;
	}

	public V getValue() {
		return value;
	}
	
	public void print() {
		System.out.println(String.format("%s: %s", key, value));
	}
}
