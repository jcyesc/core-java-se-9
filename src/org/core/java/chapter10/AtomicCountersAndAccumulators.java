package org.core.java.chapter10;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**
 * Atomic Counters and Accumulators
 * 
 * If multiple threads update a shared counter, you need to make sure that this
 * is done in a threadsafe way. There are a number of classes in the
 * java.util.concurrent.atomic package that use safe and efficient machine-level
 * instructions to guarantee atomicity of operations on integers, long and
 * boolean values, object references, and arrays thereof. Using these classes
 * correctly requires considerable expertise. However, atomic counters and
 * accumulators are convenient for application-level programming.
 * 
 * LongAdder and LongAccumulator
 * 
 * When we have a very large number of threads accessing the same atomic values,
 * performance suffers because updates are carried out optimistically. That is,
 * the operation computes a new value from a given old value, then does the
 * replacement provided the old value is still the current one, or retries if it
 * is not. Under heavy contention, updates require too many retries
 * 
 * <code>
 *   // Example of optimistic locking
 *   do {
 *   	oldValue = map.get(word);
 *      newValue = oldValue + 1;
 *   } while (!map.replace(word, olValue, newValue);
 * 
 * </code>
 * 
 * The classes {@link LongAdder} and {@link LongAccumulator} solve this problem
 * for certain common updates. A {@link LongAdder} is composed of multiple
 * variables whose collective sum is the current value. Multiple threads can
 * update different summands, and new summands are automatically provided when
 * the number of threads increases. This is efficient in the common situation
 * where the value of the sum is not needed until after all work has been done.
 * 
 * The {@link LongAccumulator} generalizes this idea to an arbitrary
 * accumulation operation. In the constructor, you provide the operation as well
 * as its neutral element. To incorporate new values, call accumulate. Call get
 * to obtain the current value.
 * 
 * Internally, the accumulator has variables a1, a2, .., an. Each variable is
 * initialized with the neutral element (0 for example).
 * 
 * When accumulate is called with value v, then one of them is atomically
 * updated as ai = ai op v, where op is the accumulation operation written in
 * infix form. The result of get is a1 op a2 op ... an.
 */
public class AtomicCountersAndAccumulators {
	public static void main(String[] args) {
		usingAtomicLong();

		settingValue();

		longAdder();

		longAccumulator();
	}

	private static void usingAtomicLong() {
		System.out.println("AtomicCountersAndAccumulators.usingAtomicLong()");
		AtomicLong nextNumber = new AtomicLong();
		// How to safely generate a sequence of numbers (assume "nextNumber" is a share
		// variable).
		long id = nextNumber.incrementAndGet(); // Check the code for incrementAndGet to see the Optimistic locking.
		System.out.println("Next value: " + id);
	}

	private static void settingValue() {
		System.out.println("\nAtomicCountersAndAccumulators.settingValue()");
		AtomicLong largest = new AtomicLong(123);
		// Wrong way to update a value
		// largest.set(Math.max(largest.get(), 200));
		long observed = 200;
		largest.updateAndGet(value -> Math.max(value, observed));

		// Or
		largest.accumulateAndGet(observed, Math::max);
		System.out.println(observed);
	}

	private static void longAdder() {
		System.out.println("\nAtomicCountersAndAccumulators.longAdder()");
		LongAdder count = new LongAdder();
		count.increment();
		count.increment();
		count.increment();

		Long result = count.sum();

		System.out.println("Result: " + result);
	}

	private static void longAccumulator() {
		System.out.println("\nAtomicCountersAndAccumulators.longAccumulator()");
		LongAccumulator accumulator = new LongAccumulator(Long::sum, 0 /* identity */);

		accumulator.accumulate(12);
		accumulator.accumulate(2);
		accumulator.accumulate(9);

		long sum = accumulator.get();
		System.out.println("Result: " + sum);
	}
}
