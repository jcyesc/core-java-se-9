package org.core.java.chapter16.userjni;

import java.util.Arrays;

public class User {
    public static final int NUM_FAVORITE_NUMBERS = 5;

    private String name;
    private int age;
    private long favoriteNumbers[];

    public User() {
	favoriteNumbers = new long[NUM_FAVORITE_NUMBERS];
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public int getAge() {
	return age;
    }

    public void setAge(int age) {
	this.age = age;
    }

    public long[] getFavoriteNumbers() {
	return favoriteNumbers;
    }

    public long getFavoriteNumber(int index) {
	return favoriteNumbers[index % NUM_FAVORITE_NUMBERS];
    }

    @Override
    public String toString() {
	return "[name]=" + name + ", [age]=" + age + ", [favoriteNumbers]="
		+ (favoriteNumbers != null ? Arrays.toString(favoriteNumbers) : null);
    }
}
