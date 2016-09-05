package com.gmail.mattdiamond98.hexagons;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class Hexagon {
	
	public static final int SIZE = 25;
	public static final double WIDTH = SIZE * 2;
	public static final double HEIGHT = Math.sqrt(3)/2 * WIDTH;
	public static final double R3 = Math.sqrt(3);
	
	public AxialPoint axial;
	public Point center;
	public Color color;
	
	public Hexagon(double x, double y) {
		center = new Point(x, y);
		axial  = new AxialPoint(0,0);
	}
	
//	function hex_to_pixel(hex):
//	    x = size * 3/2 * hex.q
//	    y = size * sqrt(3) * (hex.r + hex.q/2)
//	    return Point(x, y)
//	private Point hexToPixel(AxialPoint p) {
//		return new Point(SIZE * 3/2 * p.q, SIZE * R3 * (p.r + p.q / 2));
//	}
	
	
	public Hexagon(AxialPoint p) {
//		center = hexToPixel(p);
		this(new Hexagon(0,0).hexTo((int)p.q, (int)p.r).center);
		axial = p;
	}
	
	public Hexagon(Point center) {
		this.center = center;
	}

	public Point[] getCorners() {
		Point[] corners = new Point[6];
		for (int i = 0; i < 6; i++) {
			double angle = Math.toRadians(60 * i);
			corners[i] = new Point(Math.round(center.x + SIZE * Math.cos(angle)),
								   Math.round(center.y + SIZE * Math.sin(angle)));
		}
		return corners;
	}
	
	public Line[] getSides() {
		Point[] corners = getCorners();
		Line[] sides = new Line[6];
		for (int i = 0; i < 6; i++) {
			sides[i] = new Line(corners[i], corners[(i + 1) % 6]);
		}
		return sides;
	}
	
	public void drawOn(Graphics g) {
		if (color != null) g.setColor(color);
		else g.setColor(new Color(244,244,241));
		Point[] c = getCorners();
		g.fillPolygon(new Polygon(new int[] {(int)c[0].x,(int)c[1].x,(int)c[2].x,(int)c[3].x,(int)c[4].x,(int)c[5].x},
								  new int[] {(int)c[0].y,(int)c[1].y,(int)c[2].y,(int)c[3].y,(int)c[4].y,(int)c[5].y},
								  6));
		g.setColor(new Color(201, 201, 201));
		for (Line l : getSides()) {
			l.draw(g);
		}
//		g.drawRect((int)center.x, (int)center.y, 1, 1);
		
	}
	
	public Hexagon round() {
		return new Hexagon(AxialPoint.fromPoint(center).round().toPoint());
	}
	
	public Hexagon hexTo(int hx, int hy) {
		Hexagon neighbor = new Hexagon(center.x, center.y);
		neighbor.center.y -= hy * HEIGHT;
		neighbor.center.y -= -hx * HEIGHT / 2;
		neighbor.center.x -= -hx * WIDTH * 3/4;
		
		neighbor.axial = new AxialPoint(hx, hy);
//		neighbor.center.y -= hx * 2 * Math.sqrt(3)+0.01/2 * SIZE;
//		neighbor.center.y -= SIZE * hy;
//		neighbor.center.x += SIZE * 1.5 * hy; 
		return neighbor;
	}
	
	
}
