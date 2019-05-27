package org.core.java.chapter4;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * Proxies
 * 
 * The Proxy class can create, at runtine, new classes that implement a given
 * interface or set of interfaces.
 * 
 * To use Proxies it is necessary to use an invocation handler, an object of a
 * class that implements the InvocationHandler interface. That interface has a
 * single method:
 * 
 * Object invoke(Object proxy, Method method, Object[]args)
 */
public class Proxies {

	public static void main(String[] args) {
		Object[] values = new Object[1000];
		proxyIntegers(values);

		Arrays.binarySearch(values, Integer.valueOf(500));
	}

	/**
	 * Prints all the methods and arguments of the {@link Integer} instances.
	 * 
	 * @param values
	 */
	private static void proxyIntegers(Object[] values) {
		for (int i = 0; i < values.length; i++) {
			Object value = Integer.valueOf(i);
			ClassLoader defaultClassLoader = Thread.currentThread().getContextClassLoader();
			values[i] = Proxy.newProxyInstance(defaultClassLoader, Integer.class.getInterfaces(),
					// Lambda expression for invocation handler
					(Object proxy, Method m, Object[] margs) -> {
						System.out.println(value + "." + m.getName() + Arrays.toString(margs));
						return m.invoke(value, margs);
					});
		}
	}

}
