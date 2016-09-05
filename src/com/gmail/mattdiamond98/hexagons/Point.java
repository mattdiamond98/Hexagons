package com.gmail.mattdiamond98.hexagons;

import java.awt.Rectangle;
import java.awt.geom.Point2D;

public class Point {
	
	public double x;
	public double y;
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Point(Point2D p) {
		this.x = p.getX();
		this.y = p.getY();
	}
	
	public Point add(Point p) {
		return new Point(p.x + x, p.y + y);
	}
	
	public boolean isWithin(Rectangle bounds) {
		return x <= bounds.getWidth() && x >= 0 && y <= bounds.getHeight() && y >= 0;
	}
	
	public String toString() {
		return "(" + Math.round(x) + "," + Math.round(y) + ")";
	}
	
	public double distanceSquared(Point p) {
		return (p.x - x) * (p.x - x) + (p.y - y) * (p.y - y);
	}
	
}
