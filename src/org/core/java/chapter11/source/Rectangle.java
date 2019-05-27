package org.core.java.chapter11.source;

@ToString
public class Rectangle {
	private Point topLeft;
	private int width;
	private int height;

	public Rectangle(Point topLeft, int width, int height) {
		this.topLeft = topLeft;
		this.width = width;
		this.height = height;
	}

	@ToString(includeName = false)
	public Point getTopLeft() {
		return topLeft;
	}

	@ToString
	public int getWidth() {
		return width;
	}

	@ToString
	public int getHeight() {
		return height;
	}
}