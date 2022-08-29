package org.core.java.chapter16;

public class JniHelloWorld {

    static {
	System.loadLibrary("native101");
    }

    public static void main(String[] args) {
	JniHelloWorld jni = new JniHelloWorld();
	jni.greet();

	System.out.println("sum = " + jni.sum(12, 11));
	System.out.println(jni.greetTo("Silver", true));
	System.out.println(jni.greetTo("Diana", false));
    }

    private native void greet();

    private native long sum(int first, int second);

    private native String greetTo(String name, boolean isMale);

}
