package org.core.java.chapter16.userjni;

public class UserService {

    public native User create(String name, int age);

    public native String print(User user);

    public native void setFavoriteNumber(User user, int index, long number);
}
