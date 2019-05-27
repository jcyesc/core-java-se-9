package org.core.java.chapter7;

import java.util.BitSet;

public class BitSetExample {
	public static void main(String[] args) {
		BitSet bitSet = new BitSet();
		
		bitSet.set(0);
		bitSet.set(1);
		bitSet.set(2);
		bitSet.set(3);
		
		bitSet.flip(1);
		
		System.out.println(bitSet);
		System.out.println("BitSet.cardinality: " + bitSet.cardinality());
		
	}
}
