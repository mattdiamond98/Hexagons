package com.gmail.mattdiamond98.hexagons;

import java.awt.Graphics;

public class Line {
	
	public Point p1;
	public Point p2;
	
	public Line(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public Line(double x1, double y1, double x2, double y2) {
		this.p1 = new Point(x1, y1);
		this.p2 = new Point(x2, y2);
	}
	
	public void draw(Graphics g) {
		g.drawLine((int) p1.x, (int) p1.y, (int) p2.x, (int) p2.y);
	}
	
}
