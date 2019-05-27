package org.core.java.chapter1;

public class DoubleToBinary {
	public static void main(String[] args) {
		System.out.println(Integer.toBinaryString(Float.floatToRawIntBits(-56.23f)));
		System.out.println(Integer.toBinaryString(Float.floatToRawIntBits(56.23f)).length());
		System.out.println(Long.toBinaryString(Double.doubleToRawLongBits(56.23)));
		// TODO Document the transformation of double.
		// 100 0010 0110 0000 1110 1011 1000 0101
		// 1000 0100
		// 110 0000 1110 1011 1000 0101

		// 11000010011000001110101110000101
		// 100000001001100000111010111000010100011110101110000101000111101
	}
}
