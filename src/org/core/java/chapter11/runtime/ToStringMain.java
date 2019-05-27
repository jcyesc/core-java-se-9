package org.core.java.chapter11.runtime;

/**
 * Processing Annotations at Runtime
 * 
 * Uses the @ToString annotation to print the properties of the {@link Point}
 * and {@link Rectangle}.
 */
public class ToStringMain {

	public static void main(String[] args) {
		System.out.println("ToStringMain.main()");

		Point point = new Point(10, 89);
		Rectangle rec = new Rectangle(point, 74, 15);

		System.out.println(rec);
	}

	@ToString(includeName = false)
	public static class Point {
		@ToString(includeName = false)
		private int x;
		@ToString(includeName = false)
		private int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return ToStringProcessingRuntime.toString(this);
		}
	}

	@ToString
	public static class Rectangle {
		@ToString(includeName = false)
		private Point topLeft;
		@ToString
		private int width;
		@ToString
		private int height;

		public Rectangle(Point topLeft, int width, int height) {
			this.topLeft = topLeft;
			this.width = width;
			this.height = height;
		}

		@Override
		public String toString() {
			return ToStringProcessingRuntime.toString(this);
		}
	}
}
