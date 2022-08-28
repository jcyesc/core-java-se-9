package org.core.java.chapter4.proxys;

public class PrintServiceImpl implements PrintService {

	@Override
	public void print(String msg) {
		System.out.println("msg = " + msg);
	}

	@Override
	public void printInt(int value) {
		System.out.println("int = " + value);
	}
}
