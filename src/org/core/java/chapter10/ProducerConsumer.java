package org.core.java.chapter10;

import java.util.ArrayList;
import java.util.List;

/*
 * This example will show how to handle synchronization in
 * a Producer and Consumer threads.
 *
 * The goal is to produce a list with 5 random numbers and print them when
 * they are available. Once that they have been printed, the list
 * will be emptied.
 *
 * Example of the output will be:
 *
 * Producer: generating numbers.
 * Producer: waiting
 * Consumer: printing numbers.
 * 0.- 40.408729 
 * 1.- 9.425326 
 * 2.- 95.006987 
 * 3.- 2.037499 
 * 4.- 49.273043 
 * Consumer: waiting
 * Producer: generating numbers.
 * Producer: waiting
 * Consumer: printing numbers.
 * 0.- 52.561612 
 * 1.- 66.651923 
 * 2.- 81.033187 
 * 3.- 51.890051 
 * 4.- 65.179305 
 * Consumer: waiting
 * Producer: generating numbers.
 * Producer: waiting
 * Consumer: printing numbers.
 * 0.- 47.886271 
 * 1.- 70.894559 
 * 2.- 12.551546 
 * 3.- 15.147796 
 * 4.- 8.012598 
 * Consumer: waiting
 * Producer: generating numbers.
 * Producer: waiting
 * Consumer: printing numbers.
 * 0.- 84.907989 
 * 1.- 15.232504 
 * 2.- 1.611273 
 * 3.- 0.189237 
 * 4.- 46.933600 
 * Consumer: waiting
 */

/*
 * Populates a list of 5 random numbers when it is emptied.
 */
class Producer implements Runnable {
	private List<Double> randomNumbers;

	public Producer(List<Double> list) {
		this.randomNumbers = list;
	}

	public void run() {
		synchronized (this.randomNumbers) {

			while (true) {
				if (this.randomNumbers.isEmpty()) {
					System.out.println("Producer: generating numbers.");
					for (int i = 0; i < 5; i++) {
						this.randomNumbers.add(Math.random() * 100);
					}
					this.randomNumbers.notifyAll();
				} else {
					try {
						System.out.println("Producer: waiting");
						this.randomNumbers.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
}

/*
 * Prints the list of random numbers and empty the list after that.
 */
class Consumer implements Runnable {
	private List<Double> randomNumbers;

	public Consumer(List<Double> list) {
		this.randomNumbers = list;
	}

	public void run() {
		synchronized (this.randomNumbers) {
			while (true) {
				if (randomNumbers.isEmpty()) {
					try {
						System.out.println("Consumer: waiting");
						this.randomNumbers.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {
					System.out.println("Consumer: printing numbers.");
					for (int i = 0; i < randomNumbers.size(); i++) {
						System.out.printf("%d.- %f \n", i, randomNumbers.get(i));
					}
					this.randomNumbers.clear();

					this.randomNumbers.notifyAll();
				}
			}
		}
	}
}

public class ProducerConsumer {

	public static void main(String[] args) {
		System.out.println("Testing");
		List<Double> randomNumbers = new ArrayList<>();
		Thread consumer = new Thread(new Consumer(randomNumbers));
		Thread producer = new Thread(new Producer(randomNumbers));

		consumer.start();
		producer.start();

	}
}
