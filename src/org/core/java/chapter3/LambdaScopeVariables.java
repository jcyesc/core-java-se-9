package org.core.java.chapter3;

/**
 * Lambda expression
 * 
 * A lambda expression has three ingredients:
 * 
 * <ul>
 * <li>1. A block of code</li>
 * <li>2. Parameters</li>
 * <li>3 Values for the free variables - that is, the variables that are not
 * parameters and not defined inside the code.</li>
 * </ul>
 * The rule for capture variables is that a lambda expression can only access
 * local variables from an enclosing scope that are effectively final. An
 * effectively final variable is never modified inside the lambda expression -
 * it either is or could be declared as final.
 */
public class LambdaScopeVariables {

	public static void main(String[] args) {
		new LambdaScopeVariables().repeatMessage("Hi", 3, 2);
	}

	private int instanceVariable = 0;

	public void repeatMessage(/* Effectively final */ String text, /* Effectively final */ int count,
			/* Effectively final */ int threads) {
		Runnable r = () -> {
			int counter = count;
			while (counter > 0) {
				// count--; Error: Can't mutate captured variables
				counter--;
				instanceVariable++; // Instance variables can be accessed in the lambda expressions. NOT ADVICED
				System.out.println(Thread.currentThread().getName() + " " + text + " " + instanceVariable);
			}
		};

		for (int i = 0; i < threads; i++) {
			new Thread(r).start();
		}
	}

}
