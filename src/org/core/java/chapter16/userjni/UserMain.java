package org.core.java.chapter16.userjni;

public class UserMain {

    static {
	System.loadLibrary("userservice101");
    }

    public static void main(String[] args) {
	System.out.println("Running Native interface");

	UserService us = new UserService();
	User user = us.create("Luna", 21);
	System.out.println(us.print(user));

	us.setFavoriteNumber(user, 2, 23);

	System.out.println(us.print(user));
    }
}
