package org.core.java.chapter7;

import java.util.ArrayDeque;
import java.util.PriorityQueue;

/**
 * Staks, Queues, Deques and Priority Queues
 * 
 * A stack is a data structure for adding and removing elements at one end (the
 * "top" of the stack). A queue lets you efficiently add elements at one end
 * (the "tail") and remove them from the other end (the "head"). A double-ended
 * queue, or deque, supports insertion and removal at both ends. With all these
 * data structures, adding elements in the middle is not supported.
 */
public class StackQueueDequesAndPriorityQueueExamples {

	public static void main(String[] args) {
		stack();

		queue();
		
		priorityQueue();
	}

	public static void stack() {
		System.out.println("Stack()");

		ArrayDeque<String> stack = new ArrayDeque<>();
		stack.push("Peter");
		stack.push("Paul");
		stack.push("Mary");

		while (!stack.isEmpty()) {
			System.out.println(stack.pop());
		}
	}

	public static void queue() {
		System.out.println("\nqueue()");

		ArrayDeque<String> queue = new ArrayDeque<>();
		queue.add("Peter");
		queue.add("Paul");
		queue.add("Mary");

		while (!queue.isEmpty()) {
			System.out.println(queue.remove());
		}
	}
	
	public static void priorityQueue() {
		System.out.println("\npriorityQueue()");
		
		PriorityQueue<Integer> jobs = new PriorityQueue<>();
		jobs.add(23);
		jobs.add(1);
		jobs.add(5);
		
		while (!jobs.isEmpty()) {
			System.out.println("Execute: " + jobs.remove());
		}
	}

}
