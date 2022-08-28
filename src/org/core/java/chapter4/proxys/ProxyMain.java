package org.core.java.chapter4.proxys;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Example of how to create Proxy objects.
 */
public class ProxyMain {
    public static void main(String[] args) {
	PrintService ps = createProxy();
	ps.print("Hellow World");
	ps.printInt(123);
    }

    private static PrintService createProxy() {
	ClassLoader defaultClassLoader = Thread.currentThread().getContextClassLoader();
	InvocationHandler handler = new PrintServiceInvocationHandler(new PrintServiceImpl());
	PrintService ps = (PrintService) Proxy.newProxyInstance(defaultClassLoader,
		PrintServiceImpl.class.getInterfaces(), handler);

	return ps;
    }
}

/**
 * This {@code InvocationHandler} overrides the functionality of the methods in
 * {@code PrintService}.
 */
class PrintServiceInvocationHandler implements InvocationHandler {
    private PrintService printService;

    public PrintServiceInvocationHandler(PrintService printService) {
	this.printService = printService;
    }

    @Override
    public Object invoke(Object proxy, Method m, Object[] margs) throws Throwable {
	System.out.println("Proxy: Method name = " + m.getName());
	for (int i = 0; i < margs.length; i++) {
	    System.out.printf("Proxy: margs[%d] = %s\n", i, margs[i]);
	}

	if (m.getName().equals("printInt")) {
	    System.out.println(m.getName() + " is NOT going to be executed");
	    return null;
	}

	Object[] args = new Object[] { margs[0] + " Something else" };
	return m.invoke(printService, args);
    }
}
